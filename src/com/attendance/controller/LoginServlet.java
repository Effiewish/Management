package com.attendance.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.attendance.service.LoginService;
import com.attendance.service.impl.LoignServiceImpl;

/**
 * 用户登录
 * 
 */
public class LoginServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("login".equals(method)) {
			doLogin(request, response);
		}

	}

	/**
	 * 登录校验
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		LoginService loginService = new LoignServiceImpl();
		String level = loginService.login(account, password);
		// 登录成功
		if (level != null) {
			// 账户、级别等放到session中
			session.setAttribute("account", account);
			session.setAttribute("password", password);
			session.setAttribute("level", level);
			response.sendRedirect(request.getContextPath() + "/main.jsp");
			// 登录失败
		} else {
			request.setAttribute("error", "账户名或密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		}
	}

}
