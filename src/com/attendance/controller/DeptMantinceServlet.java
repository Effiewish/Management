package com.attendance.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.attendance.bean.Department;
import com.attendance.bean.UserInfoBean;
import com.attendance.service.DeptService;
import com.attendance.service.impl.DeptServiceImpl;

import common.util.FieldCheck;

/**
 * ���Ź������ӡ��޸ġ�ɾ��
 * @author ji_zhh
 *
 */
public class DeptMantinceServlet extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doPost(request,response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�ַ�����ʼ��������ӽ�����
		if("initInsert".equals(request.getParameter("method"))) {
			initInsert(request,response);
		//������ӵ����ݿ�
		}else if("insertDept".equals(request.getParameter("method"))) {
			insertDept(request,response);
			//��ʼ���޸Ľ���
		}else if("initUpdate".equals(request.getParameter("method"))) {
			initUpdate(request,response);
		}else if("updateDept".equals(request.getParameter("method"))) {
			updateDept(request,response);
		//����ɾ��
		}else if("deleteOneDept".equals(request.getParameter("method"))) {
			deleteOneDept(request,response);
		//����ɾ��
		}else if("deleteDepts".equals(request.getParameter("method"))) {
			deleteDepts(request,response);
		}
	}
	
	/**
	 * ��ʼ��������ӽ���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void initInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//��ѯ���еĿ��ܸ����˵���Ϣ��Ϊ��Ӳ����и������б���׼��
		DeptService deptService=new DeptServiceImpl();
		List<UserInfoBean> deptManagerList=deptService.getManagerInfo();
		HttpSession session=request.getSession();
		session.setAttribute("deptManagerList", deptManagerList);
		//ת�򵽲�����ӽ�����
		response.sendRedirect(request.getContextPath()+"/pages/dept/deptInsert.jsp");
	}
	
	/**
	 * ����²���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void insertDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�ӽ����ϵõ�Ҫ��ӵĲ�����Ϣ
		String departmentName=request.getParameter("departmentName");
		String manager=request.getParameter("manager");
		String totalUser=request.getParameter("totalUser");
		
		//flag: �Ƿ�ɹ� Ĭ��Ϊtrue
		boolean flag=true;
		//У�飺
		if(FieldCheck.isNullOrBlank(departmentName)) {
			flag=false;
			
			request.setAttribute("departmentNameError", "�������Ʊ���");
		}
		
		//У���д���
		if(!flag) {
			//ֵ�Ļ���
			request.setAttribute("departmentName", departmentName);
			request.setAttribute("manager", manager);
			request.setAttribute("totalUser", totalUser);
			request.getRequestDispatcher("/pages/dept/deptInsert.jsp").forward(request, response);
		}
		
		//У��ɹ�
		if(flag) {

			//��װ��Department
			Department dept=new Department();
			dept.setDepartmentName(departmentName);
			dept.setManager(manager);
			dept.setTotalUser(totalUser);
			
			//����²���
			DeptService deptService=new DeptServiceImpl();
			int count=deptService.insertDept(dept);
			//��ӳɹ�
			if(count>0) {
				//ת��һ������һ��������
				response.sendRedirect(request.getContextPath()+"/deptServlet?method=firstPage");
			}else {
				request.setAttribute("error", "��Ӳ��Ŵ���");
				//ֵ�Ļ���
				request.setAttribute("departmentName", departmentName);
				request.setAttribute("totalUser", totalUser);
				//����Ǩ�Ƶ���ӽ���
				request.getRequestDispatcher("/pages/dept/deptInsert.jsp");
			}
		}
	}
		
		/**
		 * ��ʼ�������޸Ľ���
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		public void initUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			//��ѯ���еĿ��ܸ����˵���Ϣ��Ϊ��Ӳ����и������б���׼��
			DeptService deptService=new DeptServiceImpl();
			List<UserInfoBean> deptManagerList=deptService.getManagerInfo();
			HttpSession session=request.getSession();
			session.setAttribute("deptManagerList", deptManagerList);
			
			//���ݲ��ű�ż���Ҫ�޸ĵĲ�����Ϣ
			String departmentId=request.getParameter("departmentId");
			Department dept=deptService.getDepartmentById(departmentId);
			session.setAttribute("dept", dept);
			
			
			//ת���޸Ĳ��Ž�����
			response.sendRedirect(request.getContextPath()+"/pages/dept/deptUpdate.jsp");
		}

		/**
		 * �޸Ĳ���
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		public void updateDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			//�ӽ����ϵõ�Ҫ�޸ĵĲ�����Ϣ
			String departmentId=request.getParameter("departmentId"); //���ű��
			String departmentName=request.getParameter("departmentName"); //��������
			String manager=request.getParameter("manager"); //������
			

			//flag: �Ƿ�ɹ� Ĭ��Ϊtrue
			boolean flag=true;
			//У�飺
			if(FieldCheck.isNullOrBlank(departmentName)) {
				flag=false;
				request.setAttribute("departmentNameError", "�������Ʊ���");
			}
			
			//У���д���
			if(!flag) {
				//ֵ�Ļ���
				Department department=new Department();
				department.setDepartmentId(departmentId);
				department.setDepartmentName(departmentName);
				department.setManager(manager);
				request.setAttribute("dept", department);
				request.getRequestDispatcher("/pages/dept/deptUpdate.jsp").forward(request, response);
			}
			
			//У��ɹ�
			if(flag) {
				Department department=new Department();
				department.setDepartmentId(departmentId);
				department.setDepartmentName(departmentName);
				department.setManager(manager);

				//�޸Ĳ���
				DeptService deptService=new DeptServiceImpl();
				int count=deptService.updateDept(department);
				//�޸ĳɹ�
				if(count>0) {
					//ת��һ������һ��������
					response.sendRedirect(request.getContextPath()+"/deptServlet?method=firstPage");
				}else {
					request.setAttribute("error", "�޸Ĳ��Ŵ���");
					request.setAttribute("dept", department);//���ֵ�Ļ���
					//����Ǩ�Ƶ��޸Ľ���
					request.getRequestDispatcher("/pages/dept/deptUpdate.jsp");
				}
			}
		}
		
		/**
		 *  ����ɾ������
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		public void deleteOneDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//�õ�Ҫɾ���Ĳ��ű��
			String departmentId=request.getParameter("departmentId");
			//����Model����ɾ��
			DeptService deptService=new DeptServiceImpl();
			deptService.deleteOneDept(departmentId);
			//ת��һ������һ��������
			response.sendRedirect(request.getContextPath()+"/deptServlet?method=firstPage");
		}
		
		/**
		 *  ����ɾ������
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		public void deleteDepts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//�õ�Ҫɾ�������в��ű��
			String departmentIds[]=request.getParameterValues("departmentId");
			//����Model����ɾ��
			DeptService deptService=new DeptServiceImpl();
			deptService.deleteDepts(departmentIds);
			//ת��һ������һ��������
			response.sendRedirect(request.getContextPath()+"/deptServlet?method=firstPage");
		}
}
