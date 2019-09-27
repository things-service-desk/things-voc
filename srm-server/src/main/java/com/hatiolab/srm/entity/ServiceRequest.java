package com.hatiolab.srm.entity;

import java.util.Date;

import com.hatiolab.srm.entity.relation.ManagerRef;
import com.hatiolab.srm.entity.relation.SiteRef;
import com.hatiolab.srm.rest.SiteManagerLinkController;

import xyz.elidom.dbist.annotation.Column;
import xyz.elidom.dbist.annotation.GenerationRule;
import xyz.elidom.dbist.annotation.PrimaryKey;
import xyz.elidom.dbist.annotation.Relation;
import xyz.elidom.dbist.annotation.Table;
import xyz.elidom.orm.IQueryManager;
import xyz.elidom.sys.util.ValueUtil;
import xyz.elidom.util.BeanUtil;

@Table(name = "srm_service_request", idStrategy = GenerationRule.UUID)
public class ServiceRequest extends xyz.elidom.orm.entity.basic.ElidomStampHook {
	/**
	 * SerialVersion UID
	 */
	private static final long serialVersionUID = 184323778179842356L;

	@PrimaryKey
	@Column (name = "id", nullable = false, length = 40)
	private String id;

	@Column (name = "site_id")
	private Integer siteId;

	@Relation(field = "siteId")
	private SiteRef site;

	@Column (name = "site_class", length = 30)
	private String siteClass;

	@Column (name = "requested_at", type = xyz.elidom.dbist.annotation.ColumnType.DATETIME)
	private Date requestedAt;

	@Column (name = "request_detail")
	private String requestDetail;

	@Column (name = "check_detail")
	private String checkDetail;

	@Column (name = "cuase_detail")
	private String cuaseDetail;

	@Column (name = "solution_detail")
	private String solutionDetail;

	@Column (name = "status", length = 30)
	private String status;

	@Column (name = "receive_class", length = 30)
	private String receiveClass;

	@Column (name = "module_class", length = 30)
	private String moduleClass;

	@Column (name = "process_class", length = 30)
	private String processClass;

	@Column (name = "custom_user_id")
	private Integer customUserId;

	@Relation(field = "customUserId")
	private ManagerRef customUser;

	@Column (name = "hatiolab_user_id")
	private Integer hatiolabUserId;

	@Relation(field = "hatiolabUserId")
	private ManagerRef hatiolabUser;

	@Column (name = "received_at", type = xyz.elidom.dbist.annotation.ColumnType.DATETIME)
	private Date receivedAt;

	@Column (name = "completed_at", type = xyz.elidom.dbist.annotation.ColumnType.DATETIME)
	private Date completedAt;

	@Column (name = "description")
	private String description;

	@Column (name = "plan_to_release", type = xyz.elidom.dbist.annotation.ColumnType.DATETIME)
	private Date planToRelease;

	@Column (name = "released_at", type = xyz.elidom.dbist.annotation.ColumnType.DATETIME)
	private Date releasedAt;

	@Column (name = "man_hours")
	private Integer manHours;
  
	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getSiteClass() {
		return siteClass;
	}

	public void setSiteClass(String siteClass) {
		this.siteClass = siteClass;
	}

	public Date getRequestedAt() {
		return requestedAt;
	}

	public void setRequestedAt(Date requestedAt) {
		this.requestedAt = requestedAt;
	}

	public String getRequestDetail() {
		return requestDetail;
	}

	public void setRequestDetail(String requestDetail) {
		this.requestDetail = requestDetail;
	}

	public String getCheckDetail() {
		return checkDetail;
	}

	public void setCheckDetail(String checkDetail) {
		this.checkDetail = checkDetail;
	}

	public String getCuaseDetail() {
		return cuaseDetail;
	}

	public void setCuaseDetail(String cuaseDetail) {
		this.cuaseDetail = cuaseDetail;
	}

	public String getSolutionDetail() {
		return solutionDetail;
	}

	public void setSolutionDetail(String solutionDetail) {
		this.solutionDetail = solutionDetail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReceiveClass() {
		return receiveClass;
	}

	public void setReceiveClass(String receiveClass) {
		this.receiveClass = receiveClass;
	}

	public String getModuleClass() {
		return moduleClass;
	}

	public void setModuleClass(String moduleClass) {
		this.moduleClass = moduleClass;
	}

	public String getProcessClass() {
		return processClass;
	}

	public void setProcessClass(String processClass) {
		this.processClass = processClass;
	}

	public Integer getCustomUserId() {
		return customUserId;
	}

	public void setCustomUserId(Integer customUserId) {
		this.customUserId = customUserId;
	}

	public ManagerRef getCustomUser() {
		return customUser;
	}

	public void setCustomUser(ManagerRef customUser) {
		this.customUser = customUser;

		if(this.customUser != null) {
			Integer refId = this.customUser.getId();
			if (refId != null)
				this.customUserId = refId;
		}
	
		if(this.customUserId == null) {
			this.customUserId = 0;
		}
	}

	public Integer getHatiolabUserId() {
		return hatiolabUserId;
	}

	public void setHatiolabUserId(Integer hatiolabUserId) {
		this.hatiolabUserId = hatiolabUserId;
	}

	public ManagerRef getHatiolabUser() {
		return hatiolabUser;
	}

	public void setHatiolabUser(ManagerRef hatiolabUser) {
		this.hatiolabUser = hatiolabUser;

		if(this.hatiolabUser != null) {
			Integer refId = this.hatiolabUser.getId();
			if (refId != null)
				this.hatiolabUserId = refId;
		}
	
		if(this.hatiolabUserId == null) {
			this.hatiolabUserId = 0;
		}
	}

	public Date getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}

	public Date getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(Date completedAt) {
		this.completedAt = completedAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPlanToRelease() {
		return planToRelease;
	}

	public void setPlanToRelease(Date planToRelease) {
		this.planToRelease = planToRelease;
	}

	public Date getReleasedAt() {
		return releasedAt;
	}

	public void setReleasedAt(Date releasedAt) {
		this.releasedAt = releasedAt;
	}

	public Integer getManHours() {
		return manHours;
	}

	public void setManHours(Integer manHours) {
		this.manHours = manHours;
	}

	@Override
	public void afterCreate() {
		super.afterCreate();
		
		if ( this.getHatiolabUserId() != 0 ) {
			this.saveSiteManagerLink(this.getHatiolabUserId());
		}
		
		if ( this.getCustomUserId() != 0 ) {
			this.saveSiteManagerLink(this.getCustomUserId());
		}
		
	}

	@Override
	public void afterUpdate() {
		super.afterUpdate();
		
		if ( this.getHatiolabUserId() != 0 ) {
			this.saveSiteManagerLink(this.getHatiolabUserId());
		}
		
		if ( this.getCustomUserId() != 0 ) {
			this.saveSiteManagerLink(this.getCustomUserId());
		}
	}
	
	public void saveSiteManagerLink(int managerId) {
		// 입력 값에 사이트가 입력된 경우
		if ( this.getSiteId() != 0 ) {
			// 하티오랩 담당자가 입된 경우 사이트에 하티오랩 담당자 연결
			if( managerId != 0 ) {
				SiteManagerLink condition = new SiteManagerLink();
				condition.setSiteId(this.getSiteId());
				condition.setManagerId(managerId);
				
				SiteManagerLink link = BeanUtil.get(IQueryManager.class).selectByCondition(SiteManagerLink.class, condition);
				if ( ValueUtil.isEmpty(link) ) {
					BeanUtil.get(SiteManagerLinkController.class).create(condition);
				}
			}
		}
	}
	
}
