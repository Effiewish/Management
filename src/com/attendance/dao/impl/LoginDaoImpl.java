package com.attendance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.attendance.dao.LoginDao;
import common.util.ConnectionPool;

/**
 * 用戶登录
 * 
 */
public class LoginDaoImpl implements LoginDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/**
	 * 用户登录
	 */
	@Override
	public String login(String account, String password) {
		String level = null;// 如果帐户名和密码错误，则返回null
		// 1、加载驱动
		try {
			// SQL文
			String sql = "select mylevel from t_user_info where account=? and password=?";
			conn = ConnectionPool.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			// 帐户名和密码正确
			if (rs.next()) {
				level = rs.getString("mylevel");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			ConnectionPool.close(pstmt, rs, conn);
		}
		return level;
	}

}
