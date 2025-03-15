package com.attendance.dao;

import java.util.HashMap;
import java.util.List;

import com.attendance.bean.UserInfoBean;

public interface UserDao {
	/**
	 * 根据起始记录数、结束记录数以及检索条件，获取当前页的货币信息列表
	 * @param fromCount 起始记录数
	 * @param endCount 截止记录数
	 * @param queryInforMap 检索条件
	 * @return List 当前页的数据列表
	 */
	public List getComponentPageList(int fromCount, int endCount,HashMap queryInforMap);
	/**
	 * 根据检索条件，获取满足条件的记录总数
	 * @param queryInforMap
	 * @return 总记录数
	 */
	public int getTotalRecordNumber(HashMap<String, Object> queryInforMap);
	
	/**
	 * 查询用户
	 * @param map 
	 * @return
	 */
	public List<UserInfoBean> getUserInfoList(int pageNo,int pageSize, HashMap<String, Object> map);
	/**
	 * 添加用户
	 * @param user
	 */
	int insertUser(UserInfoBean user);
	/**
	 * 更新用户
	 * @param user
	 */
    int updateUser(UserInfoBean user);
    /**
     * 删除用户
     * @param user
     * @return
     */
    int delUser(UserInfoBean user);
	public UserInfoBean getUserById(String id);
	public int delUser(String[] ids);
}
