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
	 * 1����ʼ��PageInforBean����װ�ͻ��˴��ݵļ���������Ϣ��
	 * 2����ʼ��forward��pageInforService��
	 * listBean �� forward
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws java.io.IOException
	 */
	public void initPageInforBean(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�����в�����
		String departmentName=request.getParameter("departmentName"); 
		//�����������
	    HttpSession session=request.getSession();
    	session.setAttribute("departmentName", departmentName);
		
		//�����������ŵ�hm��
		HashMap<String ,Object> hm=new HashMap<String,Object>();
		hm.put("departmentName", departmentName);
		
		super.getPageInforBean().setHm(hm);
		super.setPageInforService(new DeptServiceImpl());
		super.setForward("/pages/dept/deptSearch.jsp");
	}
  
}
