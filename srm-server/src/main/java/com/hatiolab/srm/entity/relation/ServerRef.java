package com.hatiolab.srm.entity.relation;

import java.io.Serializable;
import xyz.elidom.dbist.annotation.Column;
import xyz.elidom.dbist.annotation.PrimaryKey;
import xyz.elidom.dbist.annotation.GenerationRule;
import xyz.elidom.dbist.annotation.Table;

@Table(name = "srm_server", idStrategy = GenerationRule.UUID, isRef=true)
public class ServerRef implements Serializable {
	/**
	 * SerialVersion UID
	 */
	private static final long serialVersionUID = 937922147364596560L;
	
	@PrimaryKey
	private String id;
  
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
}

