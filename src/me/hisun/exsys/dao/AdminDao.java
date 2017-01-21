package me.hisun.exsys.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDao {
	//返回某表全部数据记录
	public static ResultSet select(String table){
		ResultSet rs = null;
		try {
			String sql = "select * from "+table;
			rs = SqlTool.executeQuery(sql);
		} //为什么无法？？？关闭连接
		catch (Exception e) {
			e.printStackTrace();
			return rs;
		}
		return rs;
	}
	
	//按ID返回某表一行数据记录
	public static ResultSet select(String table,int id){
		ResultSet rs = null;
		try {
			String sql = "select * from "+table+" where id = "+id;
			rs = SqlTool.executeQuery(sql);
			return rs;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return rs;
		}
	}
	
	//按ID返回指定表一行中指定字段数据记录
	public static ResultSet select(String column,String table,int id){
		ResultSet rs = null;
		try {
			String sql = "select "+column+" from "+table+" where id = "+id;
			rs = SqlTool.executeQuery(sql);
			return rs;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return rs;
		}
	}
	
	//按某字段的条件，返回指定表所有符合条件的数据记录
	public static ResultSet select(String table,String column,String condition){
		ResultSet rs = null;
		try {
			String sql = "select * from "+table+"  where  "+column+" = '"+condition+"'";
			rs = SqlTool.executeQuery(sql);
			return rs;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return rs;
		}
	}
	
	//按column2字段的condition条件，返回指定表该条记录中column1字段中的内容
	public static ResultSet select(String column1,String table,String column2,String condition){
		ResultSet rs = null;
		try {
			String sql = "select * from "+table+"  where  "+column2+" = '"+condition+"'";//其实并没有写...
			rs = SqlTool.executeQuery(sql);
			return rs;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return rs;
		}
	}
	
	
	public static int delete(String table,int id){
		int r = 0;
			try {
				String sql = "delete from "+table+" where id= "+id;
				r = SqlTool.excuteUpdate(sql);
				return r;
			} 
			catch (Exception e) {
				e.printStackTrace();
				return r;
			}
	}
}

