package com.hatiolab.srm.entity;

import xyz.elidom.dbist.annotation.Column;
import xyz.elidom.dbist.annotation.PrimaryKey;
import xyz.elidom.dbist.annotation.GenerationRule;
import xyz.elidom.dbist.annotation.Sequence;
import xyz.elidom.dbist.annotation.Table;
import xyz.elidom.dbist.annotation.Relation;
import com.hatiolab.srm.entity.relation.SiteRef;
import com.hatiolab.srm.entity.relation.ManagerRef;

@Table(name = "srm_site_manager_link", idStrategy = GenerationRule.AUTO_INCREMENT)
public class SiteManagerLink extends xyz.elidom.orm.entity.basic.ElidomStampHook {
	/**
	 * SerialVersion UID
	 */
	private static final long serialVersionUID = 508761850307455285L;

	@PrimaryKey
	@Sequence(name = "srm_site_manager_link_id_seq")
	private Integer id;

	@Column (name = "site_id", nullable = false)
	private Integer siteId;

	@Relation(field = "siteId")
	private SiteRef site;

	@Column (name = "manager_id", nullable = false)
	private Integer managerId;

	@Relation(field = "managerId")
	private ManagerRef manager;
  
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public SiteRef getSite() {
		return site;
	}

	public void setSite(SiteRef site) {
		this.site = site;

		if(this.site != null) {
			Integer refId = this.site.getId();
			if (refId != null)
				this.siteId = refId;
		}
	
		if(this.siteId == null) {
			this.siteId = 0;
		}
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public ManagerRef getManager() {
		return manager;
	}

	public void setManager(ManagerRef manager) {
		this.manager = manager;

		if(this.manager != null) {
			Integer refId = this.manager.getId();
			if (refId != null)
				this.managerId = refId;
		}
	
		if(this.managerId == null) {
			this.managerId = 0;
		}
	}	
}
