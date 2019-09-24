package com.hatiolab.srm.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hatiolab.srm.entity.Site;
import com.hatiolab.srm.entity.SiteManagerLink;

import xyz.elidom.dbist.dml.Filter;
import xyz.elidom.dbist.dml.Page;
import xyz.elidom.dbist.dml.Query;
import xyz.elidom.dbist.util.StringJoiner;
import xyz.elidom.orm.OrmConstants;
import xyz.elidom.orm.system.annotation.service.ApiDesc;
import xyz.elidom.orm.system.annotation.service.ServiceDesc;
import xyz.elidom.sys.SysConstants;
import xyz.elidom.sys.system.service.AbstractRestService;
import xyz.elidom.sys.system.service.params.BasicInOut;
import xyz.elidom.sys.util.ValueUtil;

@RestController
@Transactional
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/rest/srm_site")
@ServiceDesc(description="Site Service API")
public class SiteController extends AbstractRestService {

	@Override
	protected Class<?> entityClass() {
		return Site.class;
	}
  
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description="Search (Pagination) By Search Conditions")  
	public Page<?> index(
		@RequestParam(name="page", required=false) Integer page, 
		@RequestParam(name="limit", required=false) Integer limit, 
		@RequestParam(name="select", required=false) String select, 
		@RequestParam(name="sort", required=false) String sort,
		@RequestParam(name="query", required=false) String query) {   
		return this.search(this.entityClass(), page, limit, select, sort, query);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description="Find one by ID")
	public Site findOne(@PathVariable("id") Long id) {
		return this.getOne(this.entityClass(), id);
	}

	@RequestMapping(value="/{id}/exist", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description="Check exists By ID")
	public Boolean isExist(@PathVariable("id") Long id) {
		return this.isExistOne(this.entityClass(), id);
	}

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiDesc(description="Create")
	public Site create(@RequestBody Site input) {
		return this.createOne(input);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description="Update")
	public Site update(@PathVariable("id") Long id, @RequestBody Site input) {
		return this.updateOne(input);
	}
  
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description="Delete")
	public void delete(@PathVariable("id") Long id) {
		this.deleteOne(this.entityClass(), id);
	}  
  
	@RequestMapping(value="/update_multiple", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description="Create, Update or Delete multiple at one time")
	public Boolean multipleUpdate(@RequestBody List<Site> list) {
		return this.cudMultipleData(this.entityClass(), list);
	}
	
	@RequestMapping(value="/{id}/detail", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description="Search (Pagination) By Search Conditions")  
	public List<Map> indexDetail(@PathVariable("id") Long id) {
		
		StringJoiner query = new StringJoiner(SysConstants.LINE_SEPARATOR);
		
		query.add(" SELECT srmSite.* ");
		query.add(" 	 , COALESCE((SELECT name FROM srm_manager where id = srmSite.main_customer_id), '') as main_customer_name ");
		query.add("      , COALESCE((SELECT name FROM srm_manager where id = srmSite.main_hatiolab_user_id), '') as main_hatiolab_user_name ");
		query.add("   FROM srm_site_manager_link srmLink ");
		query.add("      , srm_site srmSite ");
		query.add("  WHERE srmLink.site_id = srmSite.id ");
		query.add("    and srmLink.manager_id = :id" );
		
		Map<String, Object> paramMap = ValueUtil.newMap("id", id);
		
		return  this.queryManager.selectListBySql(query.toString(), paramMap, Map.class, 0, 0);
	}
	
	@RequestMapping(value="{id}/detail/update_multiple", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiDesc(description="Site Detail - Update or Delete multiple at one time")
	public BasicInOut detailMultipleUpdate(@PathVariable("id") Long id, @RequestBody List<Map<String, String>> list) {
		
		if ( ! list.isEmpty() ) {
			List<SiteManagerLink> createLinkList = new ArrayList<SiteManagerLink>();
			List<Integer> deleteLinkList = new ArrayList<Integer>();
			
			for( Map<String, String> site : list ) {
				
				if( OrmConstants.CUD_FLAG_CREATE.equalsIgnoreCase(site.get("cud_flag_")) ) {
					
					SiteManagerLink link = new SiteManagerLink();
					link.setManagerId(ValueUtil.toInteger(id));
					link.setSiteId( ValueUtil.toInteger(site.get("site_id")));
					
					if ( this.queryManager.selectList(SiteManagerLink.class, link).size() == 0 ) {
						createLinkList.add(link);
					}
				} else if ( OrmConstants.CUD_FLAG_DELETE.equalsIgnoreCase(site.get("cud_flag_")) ) {
					if ( ! deleteLinkList.contains(ValueUtil.toInteger(site.get("site_id"))) ) {
						deleteLinkList.add(ValueUtil.toInteger(site.get("site_id")));
					}
				}
				
			}
			
			if ( ! deleteLinkList.isEmpty() ) {
				
				Query query = new Query();
				query.addFilter(new Filter("manager_id", id));
				query.addFilter(new Filter("site_id", OrmConstants.IN, deleteLinkList));
				
				List<SiteManagerLink> deleteList = this.queryManager.selectList(SiteManagerLink.class, query);
				this.queryManager.deleteBatch(deleteList);
				
			}
			
			if( ! createLinkList.isEmpty() ) {
				this.queryManager.insertBatch(createLinkList);
			}
			
		}
		
		return new BasicInOut();
	}

}