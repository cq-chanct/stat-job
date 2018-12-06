package com.chanct.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.chanct.netsecur.constants.DBConstant;

public class JdbcUtil {
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		Connection conn = null;
        String url = DBConstant.JDBC_URL 
    		    + "?user=" + DBConstant.JDBC_USER
    		    + "&" + "password=" + DBConstant.JDBC_PASSWORD + "&useUnicode=true&characterEncoding=UTF8";
		try {
			Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			conn = DriverManager.getConnection(url);
		} catch (Exception e) {
            e.printStackTrace();
        } 
		return conn;
	}
	
}
