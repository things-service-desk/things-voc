package com.hatiolab.srm.entity.relation;

import java.io.Serializable;
import xyz.elidom.dbist.annotation.Column;
import xyz.elidom.dbist.annotation.PrimaryKey;
import xyz.elidom.dbist.annotation.GenerationRule;
import xyz.elidom.dbist.annotation.Sequence;
import xyz.elidom.dbist.annotation.Table;

@Table(name = "srm_site_manager_link", idStrategy = GenerationRule.AUTO_INCREMENT, isRef=true)
public class SiteManagerLinkRef implements Serializable {
	/**
	 * SerialVersion UID
	 */
	private static final long serialVersionUID = 117057942183605077L;
	
	@PrimaryKey
	@Sequence(name = "srm_site_manager_link_id_seq")
	private Integer id;
  
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	
}

