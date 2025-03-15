package com.attendance.service.impl;

import java.util.HashMap;
import java.util.List;

import com.attendance.bean.Department;
import com.attendance.bean.UserInfoBean;
import com.attendance.dao.DeptDao;
import com.attendance.dao.impl.DeptDaoImpl;
import com.attendance.service.DeptService;
import common.service.PageInforService;

/**
 * service
 *
 */
public class DeptServiceImpl extends PageInforService implements DeptService {

	public List<Department> getComponentPageList(int fromCount, int endCount,
			HashMap queryInforMap) {
		DeptDao deptDao = new DeptDaoImpl();
		return deptDao.getComponentPageList(fromCount, endCount, queryInforMap);
	}

	public int getTotalRecordNumber(HashMap queryInforMap) {
		DeptDao deptDao = new DeptDaoImpl();
		return deptDao.getTotalRecordNumber(queryInforMap);
	}

	@Override
	public List<UserInfoBean> getManagerInfo() {
		DeptDao deptDao = new DeptDaoImpl();
		return deptDao.getManagerInfo();
	}

	@Override
	public int insertDept(Department dept) {
		DeptDao deptDao = new DeptDaoImpl();
		return deptDao.insertDept(dept);
	}

	@Override
	public Department getDepartmentById(String departmentId) {
		DeptDao deptDao = new DeptDaoImpl();
		return deptDao.getDepartmentById(departmentId);
	}

	@Override
	public int updateDept(Department dept) {
		DeptDao deptDao = new DeptDaoImpl();
		return deptDao.updateDept(dept);
	}

	@Override
	public int deleteOneDept(String departmentId) {
		DeptDao deptDao = new DeptDaoImpl();
		return deptDao.deleteOneDept(departmentId);
	}

	@Override
	public int deleteDepts(String[] departmentIds) {
		DeptDao deptDao = new DeptDaoImpl();
		return deptDao.deleteDepts(departmentIds);
	}

	@Override
	public int deleteByAccount(String account) {
		DeptDao deptDao = new DeptDaoImpl();
		return deptDao.deleteByAccount(account);
	}

}
