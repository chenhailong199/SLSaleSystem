package com.sl.pojo;

import java.io.Serializable;
import java.util.Date;

public class Function implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String functionCode;
	private String functionName;
	private String funcUrl;
	private int parentId;
	private Date creationTime;
	private Integer roleId;
	public Function() {
		super();
	}
	@Override
	public String toString() {
		return "Function [id=" + id + ", functionCode=" + functionCode + ", functionName=" + functionName + ", funcUrl="
				+ funcUrl + ", parentId=" + parentId + ", creationTime=" + creationTime + ", roleId=" + roleId
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
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
	public String getFuncUrl() {
		return funcUrl;
	}
	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
}
