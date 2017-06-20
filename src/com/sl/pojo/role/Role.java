package com.sl.pojo.role;

import java.io.Serializable;
import java.sql.Timestamp;

public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String roleName;
	private Timestamp createdTime;
	public Role() {
		super();
	}
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	
	
}
