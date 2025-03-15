package com.attendance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Map.Entry;

import com.attendance.bean.UserInfoBean;
import com.attendance.dao.UserDao;

import cn.hutool.core.util.StrUtil;
import common.util.ConnectionPool;

public class UserDaoImpl implements UserDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/**
	 * 根据起始记录数、结束记录数以及检索条件，获取当前页的货币信息列表
	 * 
	 * @param fromCount     起始记录数
	 * @param endCount      截止记录数
	 * @param queryInforMap 检索条件
	 * @return List 当前页的数据列表
	 */
	public List getComponentPageList(int fromCount, int endCount, HashMap queryInforMap) {
		return null;
	}

	/**
	 * 根据检索条件，获取满足条件的记录总数
	 * 
	 * @param queryInforMap
	 * @return 总记录数
	 */
	public int getTotalRecordNumber(HashMap<String, Object> queryInforMap) {
		conn = ConnectionPool.getConn();
		String sql = "select count(*) from T_USER_INFO a join t_department b on a.DEPARTMENT_ID =b.DEPARTMENT_ID where 1=1 ";
		if(Objects.nonNull(queryInforMap)) {
			if(!queryInforMap.isEmpty()) {
				for (Entry<String, Object> entry : queryInforMap.entrySet()) {
					if(Objects.nonNull(entry.getValue())) {
						sql+=" and "+entry.getKey() +" = '"+entry.getValue()+"'";
					}
				}
			}
		}
		Statement pstmt = null;
		ResultSet rs = null;
		Integer total =0;
		try {
			pstmt = conn.createStatement();
			rs = pstmt.executeQuery(sql);
			if (rs.next()) {
				total = rs.getInt(1);// 结果集第一列的值
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(conn, pstmt, rs);
		}
		return total;
	}
	 private void closeConn(Connection conn, Statement stmt, ResultSet rs)
	    {
	        if (null!=conn)
	        {
	            try
	            {
	                conn.close();
	            }
	            catch (SQLException e)
	            {}
	        }
	        if (null!=stmt)
	        {
	            try
	            {
	                stmt.close();
	            }
	            catch (SQLException e)
	            {}
	        }
	        if (null!=rs)
	        {
	            try
	            {
	                rs.close();
	            }
	            catch (SQLException e)
	            {}
	        }
	    }

	/**
	 * 添加用户信息
	 * 
	 * @return
	 */
	public List<UserInfoBean> getUserInfo() {
		return null;
	}

	@Override
	public int insertUser(UserInfoBean user) {
		int i =0;
		conn = ConnectionPool.getConn();
		String sql = "insert into T_USER_INFO (ID, ACCOUNT, PASSWORD, NAME, DEPARTMENT_ID, SEX, BIRTHDAY, MOBILE, EMAIL, MYLEVEL, STATE, USER_TYPE)"
				+ "values (seq_user_id.nextval, ?, ?, ?, ?, '"+user.getSex()+"', ?, ?, ?, '"+user.getLevel()+"', '1', '2')";
		try {
			// 3.为预编译的SQL中占位符设置数值
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getAccount());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getDepartmentId());
			pstmt.setString(5, user.getBirthday());
			pstmt.setString(6, user.getMobile());
			pstmt.setString(7, user.getEmail());
			// 4.执行SQL
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
		return i;

	}

	@Override
	public int updateUser(UserInfoBean user) {
		int i=0;
		if (Objects.isNull(user)) {
			return i;
		}
		conn = ConnectionPool.getConn();
		String sql = " update T_USER_INFO set ";

		if (Objects.nonNull(user.getPassword())) {
			sql += " PASSWORD='" + user.getPassword() + "',";
		}
		if (Objects.nonNull(user.getName())) {
			sql += "NAME = '" + user.getName() + "',";
		}
		if (Objects.nonNull(user.getDepartmentId())) {
			sql += "DEPARTMENT_ID = '" + user.getDepartmentId() + "',";
		}
		if (Objects.nonNull(user.getSex())) {
			sql += "sex = '" + user.getSex() + "',";
		}
		if (Objects.nonNull(user.getBirthday())) {
			sql += "BIRTHDAY = '" + user.getBirthday() + "',";
		}
		if (Objects.nonNull(user.getMobile())) {
			sql += "MOBILE = '" + user.getMobile() + "',";
		}
		if (Objects.nonNull(user.getEmail())) {
			sql += "EMAIL = '" + user.getEmail() + "',";
		}
		if (Objects.nonNull(user.getLevel())) {
			sql += "MYLEVEL = '" + user.getLevel() + "',";
		}
		if (Objects.nonNull(user.getLevel())) {
			sql += "MYLEVEL = '" + user.getLevel() + "',";
		}
		
		sql = sql.subSequence(0, sql.length() - 1).toString();
		if (StrUtil.isEmptyIfStr(user.getAccount())) {
			return i;
		}
		sql += " where ACCOUNT = '" + user.getAccount()+"'" ;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			i =ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(ps, conn);
			return i;
		}
	}

	private void close(PreparedStatement ps, Connection conn) {
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Override
	public int delUser(UserInfoBean user) {
		int i = 0;
		String sql = "delete from T_USER_INFO where 1=1";
		if (Objects.nonNull(user.getId())) {
			sql += " and id ='" + user.getId()+"'";
		}
		if (Objects.nonNull(user.getAccount())) {
			sql += " and account = '" + user.getAccount()+"'";
		}
		conn = ConnectionPool.getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    close(pstmt, conn);
		}
		return i;
	}

	
	@Override
	public List<UserInfoBean> getUserInfoList(int pageNo, int pageSize,HashMap<String, Object> map) {
		conn = ConnectionPool.getConn();
		String sql = "select * from (select rownum rn, u.account,u.name,d.department_name,u.mylevel,u.create_time,u.sex,u.mobile,u.birthday,u.email,d.department_id,u.id from t_user_info u left join t_department d "
				+ " on u.department_id=d.department_id where 1=1 ";
		if(!map.isEmpty()) {
			for (Entry<String, Object> entry : map.entrySet()) {
				if(Objects.nonNull(entry.getValue())) {
					sql+=" and "+entry.getKey() +" = '"+entry.getValue()+"'";
				}
			}
		}
		sql += " ) where rn >= ? and rn <= ?";
		List<UserInfoBean> userList  = new ArrayList<UserInfoBean>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNo-1)*pageSize+1);
			pstmt.setInt(2, pageNo*pageSize);
			rs=pstmt.executeQuery();
			while(rs.next()){
				//ID, ACCOUNT, PASSWORD, NAME, DEPARTMENT_ID, SEX, BIRTHDAY, MOBILE, EMAIL, LEVEL, CREATE_TIME, STATE, permission
				UserInfoBean user=new UserInfoBean();
				user.setId(rs.getString("id"));
				user.setAccount(rs.getString("account"));
				user.setName(rs.getString("name"));
				user.setDepartmentName(rs.getString("department_name"));
				user.setLevel(rs.getString("mylevel"));
				user.setSex(rs.getString("sex"));
				user.setMobile(rs.getString("mobile"));
				user.setEmail(rs.getString("email"));
				user.setBirthday(rs.getString("birthday"));
				Date date = rs.getDate("create_time");
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time =simpleDateFormat.format(date);
				user.setCreateTime(time);
				user.setDepartmentId(rs.getString("department_id"));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;
	}

	@Override
	public UserInfoBean getUserById(String id) {
		int i = 0;
		String sql = "select * from T_USER_INFO where id='"+id+"'";
		conn = ConnectionPool.getConn();
		UserInfoBean user=new UserInfoBean();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				//ID, ACCOUNT, PASSWORD, NAME, DEPARTMENT_ID, SEX, BIRTHDAY, MOBILE, EMAIL, LEVEL, CREATE_TIME, STATE, permission
				user.setId(rs.getString("id"));
				user.setAccount(rs.getString("ACCOUNT"));
				user.setName(rs.getString("name"));
				user.setLevel(rs.getString("mylevel"));
				user.setSex(rs.getString("sex"));
				user.setMobile(rs.getString("mobile"));
				user.setEmail(rs.getString("email"));
				user.setBirthday(rs.getString("birthday"));
				Date date = rs.getDate("create_time");
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time =simpleDateFormat.format(date);
				user.setCreateTime(time);
				user.setDepartmentId(rs.getString("department_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    close(pstmt, conn);
		}
		return user;
	}

	@Override
	public int delUser(String[] ids) {
	    int count =0;
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			UserInfoBean bean = new UserInfoBean();
			bean.setId(id);
			count+=this.delUser(bean);
		}
		return count;
	}

}
