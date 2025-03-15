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
 * 部门管理的添加、修改、删除
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
		//分发到初始化部门添加界面上
		if("initInsert".equals(request.getParameter("method"))) {
			initInsert(request,response);
		//部门添加到数据库
		}else if("insertDept".equals(request.getParameter("method"))) {
			insertDept(request,response);
			//初始化修改界面
		}else if("initUpdate".equals(request.getParameter("method"))) {
			initUpdate(request,response);
		}else if("updateDept".equals(request.getParameter("method"))) {
			updateDept(request,response);
		//单条删除
		}else if("deleteOneDept".equals(request.getParameter("method"))) {
			deleteOneDept(request,response);
		//批量删除
		}else if("deleteDepts".equals(request.getParameter("method"))) {
			deleteDepts(request,response);
		}
	}
	
	/**
	 * 初始化部门添加界面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void initInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//查询所有的可能负责人的信息，为添加部门中负责人列表做准备
		DeptService deptService=new DeptServiceImpl();
		List<UserInfoBean> deptManagerList=deptService.getManagerInfo();
		HttpSession session=request.getSession();
		session.setAttribute("deptManagerList", deptManagerList);
		//转向到部门添加界面上
		response.sendRedirect(request.getContextPath()+"/pages/dept/deptInsert.jsp");
	}
	
	/**
	 * 添加新部门
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void insertDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//从界面上得到要添加的部门信息
		String departmentName=request.getParameter("departmentName");
		String manager=request.getParameter("manager");
		String totalUser=request.getParameter("totalUser");
		
		//flag: 是否成功 默认为true
		boolean flag=true;
		//校验：
		if(FieldCheck.isNullOrBlank(departmentName)) {
			flag=false;
			
			request.setAttribute("departmentNameError", "部门名称必填");
		}
		
		//校验有错误
		if(!flag) {
			//值的回显
			request.setAttribute("departmentName", departmentName);
			request.setAttribute("manager", manager);
			request.setAttribute("totalUser", totalUser);
			request.getRequestDispatcher("/pages/dept/deptInsert.jsp").forward(request, response);
		}
		
		//校验成功
		if(flag) {

			//封装到Department
			Department dept=new Department();
			dept.setDepartmentName(departmentName);
			dept.setManager(manager);
			dept.setTotalUser(totalUser);
			
			//添加新部门
			DeptService deptService=new DeptServiceImpl();
			int count=deptService.insertDept(dept);
			//添加成功
			if(count>0) {
				//转向到一览部门一览界面上
				response.sendRedirect(request.getContextPath()+"/deptServlet?method=firstPage");
			}else {
				request.setAttribute("error", "添加部门错误");
				//值的回显
				request.setAttribute("departmentName", departmentName);
				request.setAttribute("totalUser", totalUser);
				//界面迁移到添加界面
				request.getRequestDispatcher("/pages/dept/deptInsert.jsp");
			}
		}
	}
		
		/**
		 * 初始化部门修改界面
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		public void initUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			//查询所有的可能负责人的信息，为添加部门中负责人列表做准备
			DeptService deptService=new DeptServiceImpl();
			List<UserInfoBean> deptManagerList=deptService.getManagerInfo();
			HttpSession session=request.getSession();
			session.setAttribute("deptManagerList", deptManagerList);
			
			//根据部门编号检索要修改的部门信息
			String departmentId=request.getParameter("departmentId");
			Department dept=deptService.getDepartmentById(departmentId);
			session.setAttribute("dept", dept);
			
			
			//转向到修改部门界面上
			response.sendRedirect(request.getContextPath()+"/pages/dept/deptUpdate.jsp");
		}

		/**
		 * 修改部门
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		public void updateDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			//从界面上得到要修改的部门信息
			String departmentId=request.getParameter("departmentId"); //部门编号
			String departmentName=request.getParameter("departmentName"); //部门名称
			String manager=request.getParameter("manager"); //负责人
			

			//flag: 是否成功 默认为true
			boolean flag=true;
			//校验：
			if(FieldCheck.isNullOrBlank(departmentName)) {
				flag=false;
				request.setAttribute("departmentNameError", "部门名称必填");
			}
			
			//校验有错误
			if(!flag) {
				//值的回显
				Department department=new Department();
				department.setDepartmentId(departmentId);
				department.setDepartmentName(departmentName);
				department.setManager(manager);
				request.setAttribute("dept", department);
				request.getRequestDispatcher("/pages/dept/deptUpdate.jsp").forward(request, response);
			}
			
			//校验成功
			if(flag) {
				Department department=new Department();
				department.setDepartmentId(departmentId);
				department.setDepartmentName(departmentName);
				department.setManager(manager);

				//修改部门
				DeptService deptService=new DeptServiceImpl();
				int count=deptService.updateDept(department);
				//修改成功
				if(count>0) {
					//转向到一览部门一览界面上
					response.sendRedirect(request.getContextPath()+"/deptServlet?method=firstPage");
				}else {
					request.setAttribute("error", "修改部门错误");
					request.setAttribute("dept", department);//完成值的回显
					//界面迁移到修改界面
					request.getRequestDispatcher("/pages/dept/deptUpdate.jsp");
				}
			}
		}
		
		/**
		 *  单条删除部门
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		public void deleteOneDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//得到要删除的部门编号
			String departmentId=request.getParameter("departmentId");
			//调用Model进行删除
			DeptService deptService=new DeptServiceImpl();
			deptService.deleteOneDept(departmentId);
			//转向到一览部门一览界面上
			response.sendRedirect(request.getContextPath()+"/deptServlet?method=firstPage");
		}
		
		/**
		 *  批量删除部门
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		public void deleteDepts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//得到要删除的所有部门编号
			String departmentIds[]=request.getParameterValues("departmentId");
			//调用Model进行删除
			DeptService deptService=new DeptServiceImpl();
			deptService.deleteDepts(departmentIds);
			//转向到一览部门一览界面上
			response.sendRedirect(request.getContextPath()+"/deptServlet?method=firstPage");
		}
}
