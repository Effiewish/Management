package com.attendance.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;

import com.attendance.bean.UserInfoBean;
import com.attendance.dao.UserDao;
import com.attendance.dao.impl.UserDaoImpl;
import com.attendance.service.UserService;

import cn.hutool.core.util.StrUtil;
import common.bean.PageInforBean;

public class UserServiceImpl implements UserService{
	private UserDao userDao = new UserDaoImpl();

	@Override
	public int updateUser(UserInfoBean userinfo) {
		return userDao.updateUser(userinfo);
	}

	@Override
	public int insertUser(UserInfoBean userinfo) {
		return userDao.insertUser(userinfo);
	}

	@Override
	public int delUser(UserInfoBean user) {
		if(Objects.nonNull(user.getId())) {
			return userDao.delUser(user);
		}
		if(StrUtil.isNotBlank(user.getAccount())) {
			return userDao.delUser(user);
		}
		return 0;
	}

	@Override
	public List<UserInfoBean> getUserList(PageInforBean pageInfo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(Objects.nonNull(pageInfo.getHm())) {
			if(!pageInfo.getHm().isEmpty()) {
				for (Entry<String, Object> entry : pageInfo.getHm().entrySet()) {
					if(Objects.nonNull(entry.getValue())) {
						map.put(entry.getKey(), entry.getValue());
					}
				}
			}
			
		}
		
		return userDao.getUserInfoList(pageInfo.getCurrentPage(), pageInfo.getShowCount(),map);
	}

	@Override
	public int getTotalRecordNumber(HashMap<String, Object> queryInforMap) {
		// TODO Auto-generated method stub
		return userDao.getTotalRecordNumber(queryInforMap);
	}

	@Override
	public UserInfoBean getUserById(String id) {
		
		return userDao.getUserById(id);
	}

	@Override
	public int deleteUser(String[] ids) {
		// TODO Auto-generated method stub
		return userDao.delUser(ids);
	}

}
