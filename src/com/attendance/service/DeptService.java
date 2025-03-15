package com.attendance.service;

import java.util.List;

import com.attendance.bean.Department;
import com.attendance.bean.UserInfoBean;

public interface DeptService {
	
	public List<UserInfoBean> getManagerInfo();
	
	/**
	 * ��Ӳ�����Ϣ�����ݿ��
	 * @param dept
	 * @return
	 */
	public int insertDept(Department dept);

	public Department getDepartmentById(String departmentId);
	
	/**
	 * �޸Ĳ�����Ϣ�����ݿ��
	 * @param dept
	 * @return
	 */
	public int updateDept(Department dept);
	
	/**
	 * ����ɾ������
	 * @param departmentId
	 * @return
	 */
	public int deleteOneDept(String departmentId);
	
	/**
	 *  ����ɾ������
	 * @param departmentId
	 * @return
	 */
	public int deleteDepts(String departmentIds[]);

	public int deleteByAccount(String account);
}
