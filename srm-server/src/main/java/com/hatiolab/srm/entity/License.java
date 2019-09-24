package com.hatiolab.srm.entity;

import java.util.Date; 
import xyz.elidom.dbist.annotation.Column;
import xyz.elidom.dbist.annotation.PrimaryKey;
import xyz.elidom.dbist.annotation.GenerationRule;
import xyz.elidom.dbist.annotation.Table;
import xyz.elidom.dbist.annotation.Relation;
import com.hatiolab.srm.entity.relation.SiteRef;

@Table(name = "srm_license", idStrategy = GenerationRule.UUID)
public class License extends xyz.elidom.orm.entity.basic.ElidomStampHook {
	/**
	 * SerialVersion UID
	 */
	private static final long serialVersionUID = 232331155843953707L;

	@PrimaryKey
	@Column (name = "id", nullable = false, length = 40)
	private String id;

	@Column (name = "name", length = 40)
	private String name;

	@Column (name = "product", length = 20)
	private String product;

	@Column (name = "license_type", length = 20)
	private String licenseType;

	@Column (name = "purchase_at", type = xyz.elidom.dbist.annotation.ColumnType.DATETIME)
	private Date purchaseAt;

	@Column (name = "expiration_at", type = xyz.elidom.dbist.annotation.ColumnType.DATETIME)
	private Date expirationAt;

	@Column (name = "hostname", length = 15)
	private String hostname;

	@Column (name = "site_id")
	private Integer siteId;

	@Relation(field = "siteId")
	private SiteRef site;

	@Column (name = "license_value", length = 350)
	private String licenseValue;
  
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public Date getPurchaseAt() {
		return purchaseAt;
	}

	public void setPurchaseAt(Date purchaseAt) {
		this.purchaseAt = purchaseAt;
	}

	public Date getExpirationAt() {
		return expirationAt;
	}

	public void setExpirationAt(Date expirationAt) {
		this.expirationAt = expirationAt;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
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

	public String getLicenseValue() {
		return licenseValue;
	}

	public void setLicenseValue(String licenseValue) {
		this.licenseValue = licenseValue;
	}	
}
