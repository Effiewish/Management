package com.attendance.dao;

import java.util.HashMap;
import java.util.List;

import com.attendance.bean.UserInfoBean;

public interface UserDao {
	/**
	 * ������ʼ��¼����������¼���Լ�������������ȡ��ǰҳ�Ļ�����Ϣ�б�
	 * @param fromCount ��ʼ��¼��
	 * @param endCount ��ֹ��¼��
	 * @param queryInforMap ��������
	 * @return List ��ǰҳ�������б�
	 */
	public List getComponentPageList(int fromCount, int endCount,HashMap queryInforMap);
	/**
	 * ���ݼ�����������ȡ���������ļ�¼����
	 * @param queryInforMap
	 * @return �ܼ�¼��
	 */
	public int getTotalRecordNumber(HashMap<String, Object> queryInforMap);
	
	/**
	 * ��ѯ�û�
	 * @param map 
	 * @return
	 */
	public List<UserInfoBean> getUserInfoList(int pageNo,int pageSize, HashMap<String, Object> map);
	/**
	 * ����û�
	 * @param user
	 */
	int insertUser(UserInfoBean user);
	/**
	 * �����û�
	 * @param user
	 */
    int updateUser(UserInfoBean user);
    /**
     * ɾ���û�
     * @param user
     * @return
     */
    int delUser(UserInfoBean user);
	public UserInfoBean getUserById(String id);
	public int delUser(String[] ids);
}
