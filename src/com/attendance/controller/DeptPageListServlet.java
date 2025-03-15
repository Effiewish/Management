package com.attendance.controller;

import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.attendance.service.impl.DeptServiceImpl;
import common.controller.PageListBaseServlet;

public class DeptPageListServlet extends PageListBaseServlet {
    
	
	/**
	 * 1、初始化PageInforBean，封装客户端传递的检索条件信息；
	 * 2、初始化forward和pageInforService；
	 * listBean 和 forward
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws java.io.IOException
	 */
	public void initPageInforBean(HttpServletRequest request, HttpServletResponse response) {
		//获取界面中部门名
		String departmentName=request.getParameter("departmentName"); 
		//记忆检索条件
	    HttpSession session=request.getSession();
    	session.setAttribute("departmentName", departmentName);
		
		//将两个参数放到hm中
		HashMap<String ,Object> hm=new HashMap<String,Object>();
		hm.put("departmentName", departmentName);
		
		super.getPageInforBean().setHm(hm);
		super.setPageInforService(new DeptServiceImpl());
		super.setForward("/pages/dept/deptSearch.jsp");
	}
  
}
