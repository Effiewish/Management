package com.attendance.bean;

import java.io.Serializable;

/**
 * ����
 * 
 * 
 */
public class Department implements Serializable{

	private String departmentId;// ���ű��
	private String departmentName;// ��������
	private String name;// ������
	private String manager;//�����˱��
	private String totalUser;// ������
	private String createTime;// ����ʱ��

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
