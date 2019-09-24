package com.hatiolab.srm.entity;

import xyz.elidom.dbist.annotation.Column;
import xyz.elidom.dbist.annotation.PrimaryKey;
import xyz.elidom.dbist.annotation.GenerationRule;
import xyz.elidom.dbist.annotation.Sequence;
import xyz.elidom.dbist.annotation.Table;
import xyz.elidom.dbist.annotation.Relation;
import com.hatiolab.srm.entity.relation.ManagerRef;

@Table(name = "srm_site", idStrategy = GenerationRule.AUTO_INCREMENT)
public class Site extends xyz.elidom.orm.entity.basic.ElidomStampHook {
	/**
	 * SerialVersion UID
	 */
	private static final long serialVersionUID = 164390113170597135L;

	@PrimaryKey
	@Sequence(name = "srm_site_id_seq")
	private Integer id;

	@Column (name = "name", length = 100)
	private String name;

	@Column (name = "center_name", length = 100)
	private String centerName;

	@Column (name = "site_domain")
	private Integer siteDomain;

	@Column (name = "site_url", length = 100)
	private String siteUrl;

	@Column (name = "site_ip", length = 15)
	private String siteIp;

	@Column (name = "site_port", length = 10)
	private String sitePort;

	@Column (name = "business_type", length = 30)
	private String businessType;

	@Column (name = "main_customer_id")
	private Integer mainCustomerId;

	@Relation(field = "mainCustomerId")
	private ManagerRef mainCustomer;

	@Column (name = "main_hatiolab_user_id")
	private Integer mainHatiolabUserId;

	@Relation(field = "mainHatiolabUserId")
	private ManagerRef mainHatiolabUser;

	@Column (name = "active")
	private Boolean active;
  
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public Integer getSiteDomain() {
		return siteDomain;
	}

	public void setSiteDomain(Integer siteDomain) {
		this.siteDomain = siteDomain;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getSiteIp() {
		return siteIp;
	}

	public void setSiteIp(String siteIp) {
		this.siteIp = siteIp;
	}

	public String getSitePort() {
		return sitePort;
	}

	public void setSitePort(String sitePort) {
		this.sitePort = sitePort;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Integer getMainCustomerId() {
		return mainCustomerId;
	}

	public void setMainCustomerId(Integer mainCustomerId) {
		this.mainCustomerId = mainCustomerId;
	}

	public ManagerRef getMainCustomer() {
		return mainCustomer;
	}

	public void setMainCustomer(ManagerRef mainCustomer) {
		this.mainCustomer = mainCustomer;

		if(this.mainCustomer != null) {
			Integer refId = this.mainCustomer.getId();
			if (refId != null)
				this.mainCustomerId = refId;
		}
	
		if(this.mainCustomerId == null) {
			this.mainCustomerId = 0;
		}
	}

	public Integer getMainHatiolabUserId() {
		return mainHatiolabUserId;
	}

	public void setMainHatiolabUserId(Integer mainHatiolabUserId) {
		this.mainHatiolabUserId = mainHatiolabUserId;
	}

	public ManagerRef getMainHatiolabUser() {
		return mainHatiolabUser;
	}

	public void setMainHatiolabUser(ManagerRef mainHatiolabUser) {
		this.mainHatiolabUser = mainHatiolabUser;

		if(this.mainHatiolabUser != null) {
			Integer refId = this.mainHatiolabUser.getId();
			if (refId != null)
				this.mainHatiolabUserId = refId;
		}
	
		if(this.mainHatiolabUserId == null) {
			this.mainHatiolabUserId = 0;
		}
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}	
}
