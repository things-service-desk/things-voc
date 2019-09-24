package com.hatiolab.srm.entity;

import xyz.elidom.dbist.annotation.Column;
import xyz.elidom.dbist.annotation.PrimaryKey;
import xyz.elidom.dbist.annotation.GenerationRule;
import xyz.elidom.dbist.annotation.Sequence;
import xyz.elidom.dbist.annotation.Table;

@Table(name = "srm_manager", idStrategy = GenerationRule.AUTO_INCREMENT)
public class Manager extends xyz.elidom.orm.entity.basic.ElidomStampHook {
	/**
	 * SerialVersion UID
	 */
	private static final long serialVersionUID = 515254676030315090L;

	@PrimaryKey
	@Sequence(name = "srm_manager_id_seq")
	private Integer id;

	@Column (name = "name")
	private String name;

	@Column (name = "phone_number")
	private String phoneNumber;

	@Column (name = "email")
	private String email;

	@Column (name = "manager_class")
	private String managerClass;
  
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getManagerClass() {
		return managerClass;
	}

	public void setManagerClass(String managerClass) {
		this.managerClass = managerClass;
	}	
}
