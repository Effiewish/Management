package com.attendance.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.attendance.bean.UserInfoBean;
import com.attendance.service.DeptService;
import com.attendance.service.UserService;
import com.attendance.service.impl.DeptServiceImpl;
import com.attendance.service.impl.UserServiceImpl;

import common.bean.PageInforBean;
import common.controller.PageListBaseServlet;

public class UserPageListServlet extends PageListBaseServlet{
	private UserService userService = new UserServiceImpl();
	private DeptService deptService = new DeptServiceImpl();

	@Override
	public void initPageInforBean(HttpServletRequest request, HttpServletResponse response) {
		PageInforBean pageInfo = new PageInforBean();
		int total = userService.getTotalRecordNumber(null);
        pageInfo.setTotalNumber(total);
        pageInfo.setTotalPage(total%pageInfo.getShowCount()==0?total/pageInfo.getShowCount():total/pageInfo.getShowCount()+1);
		List<UserInfoBean> userList =userService.getUserList(pageInfo);
		//获取界面中部门名
//		String departmentName=request.getParameter("departmentName"); 
		//记忆检索条件
	    HttpSession session=request.getSession();
    	session.setAttribute("userList", userList);
    	session.setAttribute("pageInforBean", pageInfo);
		
		//将两个参数放到hm中
		HashMap<String ,Object> hm=new HashMap<String,Object>();
		hm.put("userList", userList);
		pageInfo.setHm(hm);
		super.setPageInforBean(pageInfo);
		super.setPageInforService(new DeptServiceImpl());
		super.setForward("/pages/user/userSearch.jsp");
		
	}

}
