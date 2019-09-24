package com.hatiolab.srm.entity.relation;

import java.io.Serializable;
import xyz.elidom.dbist.annotation.Column;
import xyz.elidom.dbist.annotation.PrimaryKey;
import xyz.elidom.dbist.annotation.GenerationRule;
import xyz.elidom.dbist.annotation.Sequence;
import xyz.elidom.dbist.annotation.Table;

@Table(name = "srm_manager", idStrategy = GenerationRule.AUTO_INCREMENT, isRef=true)
public class ManagerRef implements Serializable {
	/**
	 * SerialVersion UID
	 */
	private static final long serialVersionUID = 525921927274205424L;
	
	@PrimaryKey
	@Sequence(name = "srm_manager_id_seq")
	private Integer id;

	@Column (name = "name")
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

