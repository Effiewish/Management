package com.attendance.dao;

import java.util.HashMap;
import java.util.List;

import com.attendance.bean.Department;
import com.attendance.bean.UserInfoBean;

public interface DeptDao {
	/**
	 * 根据起始记录数、结束记录数以及检索条件，获取当前页的货币信息列表
	 * @param fromCount 起始记录数
	 * @param endCount 截止记录数
	 * @param queryInforMap 检索条件
	 * @return List 当前页的数据列表
	 */
	public List getComponentPageList(int fromCount, int endCount,
			HashMap queryInforMap);
	/**
	 * 根据检索条件，获取满足条件的记录总数
	 * @param queryInforMap
	 * @return 总记录数
	 */
	public int getTotalRecordNumber(HashMap<String,String> queryInforMap);
	
	/**
	 * 添加部门中负责人信息
	 * @return
	 */
	public List<UserInfoBean> getManagerInfo();
	
	/**
	 * 添加部门信息到数据库表
	 * @param dept
	 * @return
	 */
	public int insertDept(Department dept);
	
	/**
	 * 根据部门编号查询部门信息
	 * @param departmentId
	 * @return
	 */
	public Department getDepartmentById(String departmentId);
	
	/**
	 * 修改部门信息
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
