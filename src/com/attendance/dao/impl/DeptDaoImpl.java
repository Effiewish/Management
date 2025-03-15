package com.attendance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.attendance.bean.Department;
import com.attendance.bean.UserInfoBean;
import com.attendance.dao.DeptDao;

import cn.hutool.core.util.StrUtil;
import common.util.ConnectionPool;
import common.util.FieldCheck;

public class DeptDaoImpl implements DeptDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/**
	 * ���ݼ��������õ������б�
	 */
	public List<Department> getComponentPageList(int fromCount, int endCount,
			HashMap queryInforMap) {
		List<Department> deptList = new ArrayList<Department>();
		// 1.��ȡ����
		conn = ConnectionPool.getConn();
		// 2.����sql
		String sql = "select * from (select rownum rn,d.department_id,d.department_name,u.name,d.total_user,d.create_time  from t_department d, t_user_info u where d.manager = u.account and d.department_name like '%' || ? || '%') where rn>=? and rn<=?";

		try {
			// 3.��ռλ����ֵ
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, FieldCheck
					.convertNullToEmpty((String) queryInforMap
							.get("departmentName")));
			pstmt.setInt(2, fromCount);
			pstmt.setInt(3, endCount);
			// 4.����ִ��sql
			rs = pstmt.executeQuery();
			// 5.�ӽ������ȡ����
			while (rs.next()) {
				Department dept = new Department();
				dept.setDepartmentId(rs.getString("department_id"));
				dept.setDepartmentName(rs.getString("department_name"));
				dept.setName(rs.getString("name"));
				dept.setTotalUser(rs.getString("total_user"));
				dept.setCreateTime(rs.getString("create_time"));
				deptList.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(pstmt, rs, conn);
		}

		return deptList;
	}

	/**
	 * �õ��ܵļ�¼����
	 */
	public int getTotalRecordNumber(HashMap<String, String> queryInforMap) {
		int totalNum = 0;
		// 1.��ȡ����
		conn = ConnectionPool.getConn();
		// 2.����sql
		String sql = "SELECT count(*) num from t_department d, t_user_info u where d.manager = u.account and d.department_name like '%' || ? || '%'";
		try {
			// 3.��ռλ����ֵ
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, FieldCheck.convertNullToEmpty(queryInforMap
					.get("departmentName")));

			// 4.����ִ��sql
			rs = pstmt.executeQuery();
			// 5.�ӽ������ȡ����
			while (rs.next()) {
				totalNum = rs.getInt("num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(pstmt, rs, conn);
		}
		return totalNum;
	}

	/**
	 * ��ʼ�����Ÿ������б�
	 */
	@Override
	public List<UserInfoBean> getManagerInfo() {
		
		List<UserInfoBean> deptManagerList = new ArrayList<UserInfoBean>();
		// 1.��ȡ����
		conn = ConnectionPool.getConn();
		String sql="SELECT account,name FROM t_user_info";
		// 3.��ռλ����ֵ
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				UserInfoBean user=new UserInfoBean();
				user.setAccount(rs.getString("account"));
				user.setName(rs.getString("name"));
				deptManagerList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionPool.close(pstmt, rs, conn);
		}
		return deptManagerList;
	}

	/**
	 * ��Ӳ��ŵ����ݿ��
	 */
	@Override
	public int insertDept(Department dept) {
		int count=0;//�Ƿ���ӳɹ��ı��
		conn = ConnectionPool.getConn();
		
		String sql="INSERT INTO t_department(department_id, department_name, manager, total_user, create_time)VALUES(Att_seq.nextval,?,?,?, sysdate)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dept.getDepartmentName());
			pstmt.setString(2, dept.getManager());
			pstmt.setString(3, dept.getTotalUser());
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionPool.close(pstmt, conn);
		}
		return count;
	}

	/**
	 * ���ݲ��ű�Ų�ѯ������Ϣ
	 */
	@Override
	public Department getDepartmentById(String departmentId) {
		Department department=new Department();
		conn = ConnectionPool.getConn();
		String sql="SELECT d.department_id,d.department_name,d.manager FROM t_department d WHERE d.department_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,departmentId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				department.setDepartmentId(rs.getString("department_id"));
				department.setDepartmentName(rs.getString("department_name"));
				department.setManager(rs.getString("manager"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionPool.close(pstmt, rs, conn);
		}
		return department;
	}

	/**
	 * �޸Ĳ�����Ϣ
	 */
	@Override
	public int updateDept(Department dept) {
		int count=0;
		conn = ConnectionPool.getConn();
		String sql="UPDATE t_department t SET t.department_name=?,t.manager=?  WHERE t.department_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dept.getDepartmentName());
			pstmt.setString(2, dept.getManager());
			pstmt.setString(3, dept.getDepartmentId());
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionPool.close(pstmt, conn);
		}
		return count;
	}

	/**
	 * ����ɾ������
	 */
	@Override
	public int deleteOneDept(String departmentId) {
		int count=0;
		conn = ConnectionPool.getConn();
		String sql="DELETE FROM t_department t WHERE t.department_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, departmentId);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionPool.close(pstmt, conn);
		}
		return count;
	}
	
	/**
	 * ����ɾ������
	 */
	@Override
	public int deleteDepts(String[] departmentIds) {
		int count=0;
		conn = ConnectionPool.getConn();
		
		String sql="DELETE FROM t_department t WHERE t.department_id=?";
		try {
			conn.setAutoCommit(false);//����Ϊ�ֶ��ύ
			if(departmentIds!=null&departmentIds.length>0) {
				for(String departmentId:departmentIds) {
					//����ɾ��
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, departmentId);
					count=pstmt.executeUpdate();
				}
			}
			conn.commit();//�ֶ��ύ����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionPool.close(pstmt, conn);
		}
		return count;
	}

	@Override
	public int deleteByAccount(String account) {
		int count=0;
		conn = ConnectionPool.getConn();
		
		String sql="DELETE FROM t_department t WHERE t.MANAGER=?";
		try {
			conn.setAutoCommit(false);//����Ϊ�ֶ��ύ
			if(StrUtil.isNotBlank(account)) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, account);
				count=pstmt.executeUpdate();
			}
			conn.commit();//�ֶ��ύ����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionPool.close(pstmt, conn);
		}
		return count;
	}

}
