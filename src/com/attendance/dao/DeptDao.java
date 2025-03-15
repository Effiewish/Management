package com.attendance.dao;

import java.util.HashMap;
import java.util.List;

import com.attendance.bean.Department;
import com.attendance.bean.UserInfoBean;

public interface DeptDao {
	/**
	 * ������ʼ��¼����������¼���Լ�������������ȡ��ǰҳ�Ļ�����Ϣ�б�
	 * @param fromCount ��ʼ��¼��
	 * @param endCount ��ֹ��¼��
	 * @param queryInforMap ��������
	 * @return List ��ǰҳ�������б�
	 */
	public List getComponentPageList(int fromCount, int endCount,
			HashMap queryInforMap);
	/**
	 * ���ݼ�����������ȡ���������ļ�¼����
	 * @param queryInforMap
	 * @return �ܼ�¼��
	 */
	public int getTotalRecordNumber(HashMap<String,String> queryInforMap);
	
	/**
	 * ��Ӳ����и�������Ϣ
	 * @return
	 */
	public List<UserInfoBean> getManagerInfo();
	
	/**
	 * ��Ӳ�����Ϣ�����ݿ��
	 * @param dept
	 * @return
	 */
	public int insertDept(Department dept);
	
	/**
	 * ���ݲ��ű�Ų�ѯ������Ϣ
	 * @param departmentId
	 * @return
	 */
	public Department getDepartmentById(String departmentId);
	
	/**
	 * �޸Ĳ�����Ϣ
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
