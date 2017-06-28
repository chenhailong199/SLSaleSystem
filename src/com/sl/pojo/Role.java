package com.sl.pojo;

import java.io.Serializable;
import java.util.Date;

public class Role  extends Base implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String roleCode;
	private String roleName;
	private Integer isStart;
	private Date createDate;
	private String createdBy;
	public Role() {
		super();
	}
	
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", roleCode=" + roleCode + ", roleName=" + roleName + ", isStart=" + isStart
				+ ", createDate=" + createDate + ", createdBy=" + createdBy + "]";
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getIsStart() {
		return isStart;
	}
	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
	
}
