package com.sl.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户权限类
 * @author chen
 *
 */
public class Authority extends Base implements Serializable{
	private static final long serialVersionUID = 1L;
	private int roleId;
	private int functionId;
	private int userTypeId;
	private Date creationTime;
	private String createdBy;
	public Authority() {
		super();
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getFunctionId() {
		return functionId;
	}
	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}
	public int getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}
