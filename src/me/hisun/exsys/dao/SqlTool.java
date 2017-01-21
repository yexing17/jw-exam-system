package me.hisun.exsys.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlTool {
	private static String driver;
	private static String url;
	private static String user;
	private static String passwd;
	private static Connection conn;
	static{
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/examsystem";
		user = "root";
		passwd = "mysql.0017";
		conn = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection connSet(){
		try {
			conn = DriverManager.getConnection(url,user,passwd);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void connCancel(){
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet executeQuery(String sql){
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url,user,passwd);
			Statement cmd = conn.createStatement();
			rs = cmd.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static int excuteUpdate(String sql){
		int r = 0;
		try {
			conn = DriverManager.getConnection(url,user,passwd);
			Statement cmd = conn.createStatement();
			r = cmd.executeUpdate(sql);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	
}
