package com.hatiolab.srm.entity.relation;

import java.io.Serializable;
import xyz.elidom.dbist.annotation.Column;
import xyz.elidom.dbist.annotation.PrimaryKey;
import xyz.elidom.dbist.annotation.GenerationRule;
import xyz.elidom.dbist.annotation.Table;

@Table(name = "srm_service_request", idStrategy = GenerationRule.UUID, isRef=true)
public class ServiceRequestRef implements Serializable {
	/**
	 * SerialVersion UID
	 */
	private static final long serialVersionUID = 174145355259602951L;
	
	@PrimaryKey
	private String id;
  
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
}

