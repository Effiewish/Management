package common.controller;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.bean.PageInforBean;
import common.service.PageInforService;


/**
 * 实现分页显示共通功能的servlet类
 * @author guojl
 *
 */


public abstract class PageListBaseServlet extends HttpServlet {
		/**
		 * 分页抽象类Service
		 */
		private PageInforService pageInforService = null;
		/**
		 * 分页信息bean
		 */
		private PageInforBean pageInforBean = null;
		/**
		 * 跳转路径
		 */
		private String forward = null;

		@Override
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doPost(request, response);
		}

		@Override
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			pageInforBean = new PageInforBean();
			String showCount = request.getParameter("showCount");
			if(showCount!=null&&!"".equals(showCount)){
				pageInforBean.setShowCount(Integer.valueOf(showCount));
			}
			String pageNo = request.getParameter("pageNo");
			if(pageNo!=null&&!"".equals(pageNo)){
				pageInforBean.setCurrentPage(Integer.valueOf(pageNo));
			}
			setPageInforBean(pageInforBean);
			
			//initPageInforBean(request,response);
			HttpSession session = request.getSession();
			initPageInforBean(request,response);
			if (request.getParameter("method") != null) {
				if (request.getParameter("method").equals("firstPage")) {// 显示第一页
					session.setAttribute("pageInforBean", pageInforService.getPageInitialList(pageInforBean));
				} else if (request.getParameter("method").equals("showPage")) {//根据页码数显示当前页
					session.setAttribute("pageInforBean", pageInforService.getPageListByPageNo(pageInforBean));
				}
			}
			response.sendRedirect(request.getContextPath() + this.forward);
		}
		
		/**
		 * 初始化PageInforBean，封装客户端传递的查询条件信息；
		 * 初始化forward；
		 * listBean 和 forward
		 * @param request
		 * @param response
		 */
		public abstract void initPageInforBean(HttpServletRequest request,
				HttpServletResponse response);

		public PageInforService getPageInforService() {
			return pageInforService;
		}

		public void setPageInforService(PageInforService pageInforService) {
			this.pageInforService = pageInforService;
		}

		public PageInforBean getPageInforBean() {
			return pageInforBean;
		}

		public void setPageInforBean(PageInforBean pageInforBean) {
			this.pageInforBean = pageInforBean;
		}

		public String getForward() {
			return forward;
		}

		public void setForward(String forward) {
			this.forward = forward;
		}
		
		

	
	}

	


