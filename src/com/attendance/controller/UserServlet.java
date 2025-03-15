package com.attendance.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.bean.PageInforBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.attendance.bean.Department;
import com.attendance.bean.UserInfoBean;
import com.attendance.service.DeptService;
import com.attendance.service.UserService;
import com.attendance.service.impl.DeptServiceImpl;
import com.attendance.service.impl.UserServiceImpl;

import cn.hutool.core.util.StrUtil;

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 5980624287662190230L;
	private UserService service = new UserServiceImpl();
	private DeptService deptService = new DeptServiceImpl();

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		UserInfoBean user = getData(request, response);
		PageInforBean pageInfo = getPageInfo(request);
        if(Objects.nonNull(user)) {
        	HashMap<String, Object> map =new HashMap<String, Object>();
        	map.put("name", user.getName());
        	map.put("department_name", user.getDepartmentName());
        	pageInfo.setHm(map);
		}
		switch (method) {
		case "insertUser":
			insertUser(user,request,response);
			break;
		case "updateUser":
			updateUser(user,request,response);
			break;
		case "initUpdateUser":
			initUpdateUser(user,request,response);
			break;
		case "delUser":
			delUser(user,request,response);
			break;
		case "delteUser":
			deleteUser(request, response);
			break;
		case "jumpaddUser":
			insertUser(user,request,response);
			break;
		case "jumpedituser":
			updateUser(user,request,response);
			break;
		case "adduser":
//			updateUser(user);
			break;
		case "getUserList":
			getUserList(pageInfo,response,request);
			break;

		default:
			break;
		}

		System.out.println("dopost");

	}

	private void initUpdateUser(UserInfoBean user, HttpServletRequest request, HttpServletResponse response) {
		if(Objects.nonNull(user)) {
			if(Objects.nonNull(user.getId())) {
				UserInfoBean userInfo=service.getUserById(user.getId());
				HttpSession session=request.getSession();
				session.setAttribute("user", userInfo);
				//转向到修改部门界面上
				try {
					response.sendRedirect(request.getContextPath()+"/pages/user/userUpdate.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}

	private PageInforBean getPageInfo(HttpServletRequest request) {
		PageInforBean pageInfo = new PageInforBean();
		if (StrUtil.isNotBlank(request.getParameter("currentPage"))) {
			Integer temp =Integer.parseInt(request.getParameter("currentPage"));
			pageInfo.setCurrentPage(temp);
		} 
		if (StrUtil.isNotBlank(request.getParameter("showCount"))) {
			pageInfo.setShowCount(Integer.parseInt(request.getParameter("showCount")));
		} 
		if(StrUtil.isEmptyIfStr(request.getParameter("currentPage")) &&StrUtil.isEmptyIfStr(request.getParameter("showCount"))){
			pageInfo.setShowCount(1);
			pageInfo.setCurrentPage(1);
		}
		return pageInfo;

	}

	private UserInfoBean getData(HttpServletRequest request, HttpServletResponse response) {
		UserInfoBean user = new UserInfoBean();
		if (StrUtil.isNotBlank(request.getParameter("id"))) {
			user.setId(request.getParameter("id"));
		}
		if (StrUtil.isNotBlank(request.getParameter("account"))) {
			user.setAccount(request.getParameter("account"));
		}
		if (StrUtil.isNotBlank(request.getParameter("password"))) {
			user.setPassword(request.getParameter("password"));
		}
		if (StrUtil.isNotBlank(request.getParameter("name"))) {
			user.setName(request.getParameter("name"));
		}
		if (StrUtil.isNotBlank(request.getParameter("department_id"))) {
			user.setDepartmentId(request.getParameter("department_id"));
		}
		if (StrUtil.isNotBlank(request.getParameter("department_name"))) {
			user.setDepartmentName(request.getParameter("department_name"));
		}
		if (StrUtil.isNotBlank(request.getParameter("sex"))) {
			user.setSex(request.getParameter("sex"));
		}
		if (StrUtil.isNotBlank(request.getParameter("mobile"))) {
			user.setMobile(request.getParameter("mobile"));
		}
		if (StrUtil.isNotBlank(request.getParameter("birthday"))) {
			user.setBirthday(request.getParameter("birthday"));
		}
		if (StrUtil.isNotBlank(request.getParameter("email"))) {
			user.setEmail(request.getParameter("email"));
		}
		if (StrUtil.isNotBlank(request.getParameter("level"))) {
			user.setLevel(request.getParameter("level"));
		}
		if (StrUtil.isNotBlank(request.getParameter("permission"))) {
			user.setPermission(request.getParameter("permission"));
		}
		if (StrUtil.isNotBlank(request.getParameter("state"))) {
			user.setState(request.getParameter("state"));
		}
		if (StrUtil.isNotBlank(request.getParameter("user_name"))) {
			user.setName(request.getParameter("user_name"));
		}
		return user;

	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		this.doPost(request, response);

	}

	// 删除

	private void delUser(UserInfoBean user, HttpServletRequest request, HttpServletResponse response) {
		int i =deptService.deleteByAccount(user.getAccount());
			String resp = "删除用户失败";
			i = service.delUser(user);
			if (i > 0 && !Objects.equals(user.getPermission(), "99")) {
				resp = "删除用户成功";
				HashMap<String, Object> queryInforMap =null;
				PageInforBean pageInfo = new PageInforBean();
				int total = service.getTotalRecordNumber(queryInforMap);
		        pageInfo.setTotalNumber(total);
		        pageInfo.setTotalPage(total%pageInfo.getShowCount()==0?total/pageInfo.getShowCount():total/pageInfo.getShowCount()+1);
				List<UserInfoBean> list = service.getUserList(pageInfo);
				pageInfo.setList(list);
				   HttpSession session=request.getSession();
			    	session.setAttribute("userList", list);
			    	session.setAttribute("pageInfo", pageInfo);
			    	session.setAttribute("resp", resp);
					try {
						request.getRequestDispatcher("/pages/user/userSearch.jsp").forward(request, response);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			

	}
	
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到要删除的所有部门编号
		String ids[]=request.getParameterValues("id");
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			UserInfoBean bean = service.getUserById(id);
			bean.setPermission("99");
			delUser(bean, request, response);
		}
		//转向到一览部门一览界面上
		response.sendRedirect(request.getContextPath()+"/userMantinceServlet");
	}

	public void getUserList(PageInforBean pageInfo, HttpServletResponse response, HttpServletRequest request) {
		HashMap<String, String> queryInforMap =null;
		int total = service.getTotalRecordNumber(pageInfo.getHm());
        pageInfo.setTotalNumber(total);
        pageInfo.setTotalPage(total%pageInfo.getShowCount()==0?total/pageInfo.getShowCount():total/pageInfo.getShowCount()+1);
		List<UserInfoBean> list = service.getUserList(pageInfo);
		pageInfo.setList(list);
		   HttpSession session=request.getSession();
	    	session.setAttribute("userList", list);
	    	session.setAttribute("pageInforBean", pageInfo);
	    	session.setAttribute("user", pageInfo.getHm());
	    	session.setAttribute("resp", StrUtil.EMPTY);
			try {
				request.getRequestDispatcher("/pages/user/userSearch.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	
	

	// 添加用户
	public void insertUser(UserInfoBean user, HttpServletRequest request, HttpServletResponse response) {
		String resp = "添加用户失败";
		int i = service.insertUser(user);
		if (i > 0) {
			resp = "添加用户成功";
		}
		HttpSession session=request.getSession();
    	session.setAttribute("resp", resp);
		try {
			request.getRequestDispatcher("/pages/user/userInsert.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 修改用户
	private void updateUser(UserInfoBean user, HttpServletRequest request, HttpServletResponse response) {
		String resp = "更新用户失败";
		int i = service.updateUser(user);
		if (i > 0) {
			resp = "更新用户成功";
		}
		HttpSession session=request.getSession();
    	session.setAttribute("resp", resp);
		try {
			request.getRequestDispatcher("/pages/user/userUpdate.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public void getUserList(){
//		if (departmentId == 0) {
//            departmentId = (long)session.getAttribute("department_id");
//        }
//        QueryResult<UserInfo> result = userService.getUserInfoList(departmentId, user_name, pageNo, pageCount);
//        userlist = result.getDataList();
//        totalRecord = result.getTotal();
//        totalPage = (result.getTotal() + 9) / 10;
//        if (pageNo > 1) {
//            if (userlist == null || userlist.size() == 0) {
//                pageNo--;
//                getUserList();
//            }
//        }
//        // 如果是超级管理员，则取得所有部门信息
//        if ((int)session.getAttribute("level") == 2) {
//            devmanagelist = devService.getAllDevInfo();
//            if (devmanagelist != null && devmanagelist.size() > 0) {
//                for (DevInfo devInfo:devmanagelist) {
//                    if (devInfo.getDepartment_id() == departmentId) {
//                        devInfo.setIs_selected(1);
//                    }
//                }
//            }
//        } else {
//            // 如果只是部门主管，则只取得本部门情报
//            devmanagelist = new ArrayList<DevInfo>();
//            // 取得部门名称等
//            DevInfo devinfo = devService.getDevInfoById((long)session.getAttribute("department_id"));
//            // 加入对应的list中
//            devmanagelist.add(devinfo);
//        }
//        return SUCCESS;
//    }
//	}
//	public void jumpaddUser() {
//		userInfo = new UserInfo();
//        String accountod = accountService.getAccountInfo();
//        userInfo.setAccount(accountod);
//        // 如果是超级管理员则取得所有部门信息
//        if ((int)session.getAttribute("level") == 2) {
//            devlist = devService.getAllDevInfo();
//        } else {
//            // 如果只是部门主管，则只取得本部门情报
//            devlist = new ArrayList<DevInfo>();
//            // 取得部门名称等
//            DevInfo devinfo = devService.getDevInfoById((long)session.getAttribute("department_id"));
//            // 加入对应的list中
//            devlist.add(devinfo);
//        }
//        edit_flag = 0;
//        return SUCCESS;
//    }
//		
//	}
//	public void jumpedituser() {
//		 userInfo = new UserInfo();
//	        if (id == 0) {
//	            if (session != null && session.getAttribute("userid") != null ) {
//	                id = (long) session.getAttribute("userid");
//	            }
//	        }
//	        userInfo = userService.getUserInfoById(id);
//	        devlist = devService.getAllDevInfo();
//	        if (userInfo != null) {
//	            for (DevInfo devinfo:devlist) {
//	                if (devinfo.getDepartment_id() == userInfo.getDepartment_id()) {
//	                    devinfo.setIs_selected(1);
//	                    break;
//	                }
//	            }
//	        }
//	        edit_flag = 1;
//	        return SUCCESS;
//	    }
//	}
//	public void adduser() {
//		if (edit_flag == 1) {
//            UserInfo adduserInfo = userService.getUserInfoById(user_id);
//            long olddevid = adduserInfo.getDepartment_id();
//            if (adduserInfo.getLevel()!=2) {
//                adduserInfo.setPassword(password);
//                adduserInfo.setName(name);
//                adduserInfo.setDepartment_id(department_id);
//                adduserInfo.setSex(sex);
//                adduserInfo.setMobile(mobile);
//                adduserInfo.setBirthday(birthday);
//                adduserInfo.setEmail(email);
//   }else {
//     adduserInfo.setPassword(password);
//   }
//            userService.updateUser(adduserInfo, olddevid);
//            if ((int)request.getSession().getAttribute("level") == 0) {
//                return "SUCCESS1";
//            } else {
//                return SUCCESS;
//            }
//        } else {
//            UserInfo adduserInfo = new UserInfo();
//            adduserInfo.setAccount(account);
//            adduserInfo.setPassword(password);
//            adduserInfo.setName(name);
//            adduserInfo.setDepartment_id(department_id);
//            adduserInfo.setSex(sex);
//            adduserInfo.setMobile(mobile);
//            adduserInfo.setBirthday(birthday);
//            adduserInfo.setEmail(email);
//            adduserInfo.setCreate_time(new Date());
//            adduserInfo.setPermission("1,2,3,4,7");
//            userService.insertUser(adduserInfo);
//            if (edit_flag == 0) {
//                return SUCCESS;
//            } else {
//                mesaccount = account;
//                return "SUCCESS2";
//            }
//        }
//
//    }
//	}
//	public void lockuser() {
//		 if (userInfo == null) {
//	            setResultStr("error");
//	        } else {
//	            userInfo.setLock(state);
//	            userService.lockUser(userInfo);
//	            setResultStr("success");
//	        }
//	    }
//	}
//	public void deluser() {
//		UserInfo userInfo = userService.getUserInfoById(id);
//        if (userInfo == null) {
//            setResultStr("error");
//        } else {
//            userService.delUser(userInfo);
//            setResultStr("success");
//        }
//    }
//	}
//	public void checkUser() {
//		boolean result = userService.checkUser(user_id, name);
//        // 如果不存在重名的
//        if (result) {
//            setResultStr("success");
//        } else {
//            setResultStr("error");
//        }
//    }
//	}
//	public void register() {
//		userInfo = new UserInfo();
//        // 取得最大工号
//        String accountod = accountService.getAccountInfo();
//        // 设定画面的工号
//        userInfo.setAccount(accountod);
//        // 取得所有部门信息
//        devlist = devService.getAllDevInfo();
//        // 设置为注册时
//        edit_flag = 2;
//        return SUCCESS;
//    }
//	}
}
