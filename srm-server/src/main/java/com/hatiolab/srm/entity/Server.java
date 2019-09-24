package com.hatiolab.srm.entity;

import xyz.elidom.dbist.annotation.Column;
import xyz.elidom.dbist.annotation.PrimaryKey;
import xyz.elidom.dbist.annotation.GenerationRule;
import xyz.elidom.dbist.annotation.Table;
import xyz.elidom.dbist.annotation.Relation;
import com.hatiolab.srm.entity.relation.SiteRef;

@Table(name = "srm_server", idStrategy = GenerationRule.UUID)
public class Server extends xyz.elidom.orm.entity.basic.ElidomStampHook {
	/**
	 * SerialVersion UID
	 */
	private static final long serialVersionUID = 165737236276010369L;

	@PrimaryKey
	@Column (name = "id", nullable = false, length = 40)
	private String id;

	@Column (name = "site_id")
	private Integer siteId;

	@Relation(field = "siteId")
	private SiteRef site;

	@Column (name = "server_type", length = 10)
	private String serverType;

	@Column (name = "release_info", length = 50)
	private String releaseInfo;

	@Column (name = "app_properties", length = 100)
	private String appProperties;

	@Column (name = "before_src", length = 100)
	private String beforeSrc;

	@Column (name = "after_src", length = 100)
	private String afterSrc;

	@Column (name = "ip", length = 15)
	private String ip;

	@Column (name = "port", length = 10)
	private String port;

	@Column (name = "os_info", length = 30)
	private String osInfo;

	@Column (name = "os_account", length = 30)
	private String osAccount;

	@Column (name = "os_pw", length = 30)
	private String osPw;

	@Column (name = "db_info", length = 30)
	private String dbInfo;

	@Column (name = "db_account", length = 30)
	private String dbAccount;

	@Column (name = "db_pw", length = 30)
	private String dbPw;

	@Column (name = "app_account", length = 30)
	private String appAccount;

	@Column (name = "app_pw", length = 30)
	private String appPw;
  
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

	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

	public String getReleaseInfo() {
		return releaseInfo;
	}

	public void setReleaseInfo(String releaseInfo) {
		this.releaseInfo = releaseInfo;
	}

	public String getAppProperties() {
		return appProperties;
	}

	public void setAppProperties(String appProperties) {
		this.appProperties = appProperties;
	}

	public String getBeforeSrc() {
		return beforeSrc;
	}

	public void setBeforeSrc(String beforeSrc) {
		this.beforeSrc = beforeSrc;
	}

	public String getAfterSrc() {
		return afterSrc;
	}

	public void setAfterSrc(String afterSrc) {
		this.afterSrc = afterSrc;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getOsInfo() {
		return osInfo;
	}

	public void setOsInfo(String osInfo) {
		this.osInfo = osInfo;
	}

	public String getOsAccount() {
		return osAccount;
	}

	public void setOsAccount(String osAccount) {
		this.osAccount = osAccount;
	}

	public String getOsPw() {
		return osPw;
	}

	public void setOsPw(String osPw) {
		this.osPw = osPw;
	}

	public String getDbInfo() {
		return dbInfo;
	}

	public void setDbInfo(String dbInfo) {
		this.dbInfo = dbInfo;
	}

	public String getDbAccount() {
		return dbAccount;
	}

	public void setDbAccount(String dbAccount) {
		this.dbAccount = dbAccount;
	}

	public String getDbPw() {
		return dbPw;
	}

	public void setDbPw(String dbPw) {
		this.dbPw = dbPw;
	}

	public String getAppAccount() {
		return appAccount;
	}

	public void setAppAccount(String appAccount) {
		this.appAccount = appAccount;
	}

	public String getAppPw() {
		return appPw;
	}

	public void setAppPw(String appPw) {
		this.appPw = appPw;
	}	
}
