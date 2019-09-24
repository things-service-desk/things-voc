package com.hatiolab.srm.entity.relation;

import java.io.Serializable;
import xyz.elidom.dbist.annotation.Column;
import xyz.elidom.dbist.annotation.PrimaryKey;
import xyz.elidom.dbist.annotation.GenerationRule;
import xyz.elidom.dbist.annotation.Sequence;
import xyz.elidom.dbist.annotation.Table;

@Table(name = "srm_site", idStrategy = GenerationRule.AUTO_INCREMENT, isRef=true)
public class SiteRef implements Serializable {
	/**
	 * SerialVersion UID
	 */
	private static final long serialVersionUID = 580608300057812525L;
	
	@PrimaryKey
	@Sequence(name = "srm_site_id_seq")
	private Integer id;

	@Column (name = "name", length = 100)
	private String name;
  
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
}

