package com.attendance.bean;

import java.io.Serializable;

/**
 * 部门
 * 
 * 
 */
public class Department implements Serializable{

	private String departmentId;// 部门编号
	private String departmentName;// 部门名称
	private String name;// 负责人
	private String manager;//负责人编号
	private String totalUser;// 总人数
	private String createTime;// 创建时间

	public Department() {

	}

	public Department(String departmentId, String departmentName, String name,
			String totalUser, String createTime) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.name = name;
		this.totalUser = totalUser;
		this.createTime = createTime;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTotalUser() {
		return totalUser;
	}

	public void setTotalUser(String totalUser) {
		this.totalUser = totalUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

}
