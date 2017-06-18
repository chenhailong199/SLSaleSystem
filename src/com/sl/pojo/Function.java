package com.sl.pojo;

import java.sql.Timestamp;

public class Function {
	private Integer id;
	private String functionCode;
	private String functionName;
	private String functionUrl;
	private Integer parentId;
	private Timestamp createdTime;
	private Timestamp modifiedTime;
	private Integer roleId;
	public Function() {
		super();
	}
	public Function(String functionCode, String functionName, String functionUrl, Integer parentId,
			Timestamp createdTime, Timestamp modifiedTime) {
		super();
		this.functionCode = functionCode;
		this.functionName = functionName;
		this.functionUrl = functionUrl;
		this.parentId = parentId;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
	}
	
	@Override
	public String toString() {
		return "Function [id=" + id + ", functionCode=" + functionCode + ", functionName=" + functionName
				+ ", functionUrl=" + functionUrl + ", parentId=" + parentId + ", createdTime=" + createdTime
				+ ", modifiedTime=" + modifiedTime + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getFunctionUrl() {
		return functionUrl;
	}
	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public Timestamp getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
}
