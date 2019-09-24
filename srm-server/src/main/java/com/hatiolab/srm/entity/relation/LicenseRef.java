package com.hatiolab.srm.entity.relation;

import java.io.Serializable;
import xyz.elidom.dbist.annotation.Column;
import xyz.elidom.dbist.annotation.PrimaryKey;
import xyz.elidom.dbist.annotation.GenerationRule;
import xyz.elidom.dbist.annotation.Table;

@Table(name = "srm_license", idStrategy = GenerationRule.UUID, isRef=true)
public class LicenseRef implements Serializable {
	/**
	 * SerialVersion UID
	 */
	private static final long serialVersionUID = 584405080900832331L;
	
	@PrimaryKey
	private String id;

	@Column (name = "name", length = 40)
	private String name;
  
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
}

