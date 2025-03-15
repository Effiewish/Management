package com.attendance.service.impl;

import com.attendance.dao.LoginDao;
import com.attendance.dao.impl.LoginDaoImpl;
import com.attendance.service.LoginService;

public class LoignServiceImpl implements LoginService{

	@Override
	public String login(String account, String password) {
		LoginDao loginDao= new LoginDaoImpl();
		return loginDao.login(account,password);
	}

}
