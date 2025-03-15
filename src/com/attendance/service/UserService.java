package com.attendance.service;


import java.util.HashMap;
import java.util.List;
import common.bean.PageInforBean;
import com.attendance.bean.UserInfoBean;

public interface UserService {
	
	int updateUser(UserInfoBean userinfo);
	int insertUser(UserInfoBean userinfo);
	int delUser(UserInfoBean user);
	List<UserInfoBean> getUserList(PageInforBean pageInfo);
	UserInfoBean getUserById(String id);
	int deleteUser(String[] ids);
	int getTotalRecordNumber(HashMap<String, Object> hm);
	
	
	
	
	
//	public void login(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String account = request.getParameter("account");
//		String password = request.getParameter("password");
//		HttpSession session = request.getSession();
//		LoginService loginService = new LoignServiceImpl();
//		String level = loginService.login(account, password);
//		// 登录成功
//		if (level != null) {
//			// 账户、级别等放到session中
//			session.setAttribute("account", account);
//			session.setAttribute("password", password);
//			session.setAttribute("level", level);
//			response.sendRedirect(request.getContextPath() + "/main.jsp");
//			// 登录失败
//		} else {
//			request.setAttribute("error", "账户名或密码错误");
//			request.getRequestDispatcher("/login.jsp").forward(request,
//					response);
//		}
//	}
//	public static void insertUser(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException{
//		
//		
//	}
}
