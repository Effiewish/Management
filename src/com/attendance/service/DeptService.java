package com.attendance.service;

import java.util.List;

import com.attendance.bean.Department;
import com.attendance.bean.UserInfoBean;

public interface DeptService {
	
	public List<UserInfoBean> getManagerInfo();
	
	/**
	 * 添加部门信息到数据库表
	 * @param dept
	 * @return
	 */
	public int insertDept(Department dept);

	public Department getDepartmentById(String departmentId);
	
	/**
	 * 修改部门信息到数据库表
	 * @param dept
	 * @return
	 */
	public int updateDept(Department dept);
	
	/**
	 * 单条删除部门
	 * @param departmentId
	 * @return
	 */
	public int deleteOneDept(String departmentId);
	
	/**
	 *  批量删除部门
	 * @param departmentId
	 * @return
	 */
	public int deleteDepts(String departmentIds[]);

	public int deleteByAccount(String account);
}
