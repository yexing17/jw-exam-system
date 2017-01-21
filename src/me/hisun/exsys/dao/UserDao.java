package me.hisun.exsys.dao;

import me.hisun.exsys.ui.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDao{
	public static boolean isLogin(String id,String pwd){
		boolean flag = false;
		try{
			String sql = "select login_pwd from users where login_id = '"+id+"'";
			ResultSet rs = SqlTool.executeQuery(sql);
			if(rs.next()){
				if(pwd.equals(rs.getString("login_pwd")));{
						flag = true;//用户信息验证成功，flag置为true
					}
			}
			SqlTool.connCancel();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return flag;
	}
	
	public static boolean isAdmin(String id,String usergroup){
		boolean flag = false;
		try{
			String sql = "select usergroup from users where login_id = '"+id+"'";
			ResultSet rs = SqlTool.executeQuery(sql);
			if(rs.next()){
				if(usergroup.equals(rs.getString("usergroup")))
					flag = true;//如果检测用户为管理，flag置为true
			}
			SqlTool.connCancel();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return flag;
	}
	
	public static int insert(String type,String name,int no,String id,String pwd,String major,String _class){//class only support number
		int r = 0;
		try{
			String sql = "insert into users(usergroup,name,num,login_id,login_pwd,major,class) values('"+type+"','"+name+"',"+no+",'"+id+"','"+pwd+"','"+major+"','"+_class+"')";
			r = SqlTool.excuteUpdate(sql);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return r;
	}
	
	public static int update(int id,String usergroup,
			String name,int num,String login_id,
			String login_pwd,String major,String _class){
		int r= 0;
		try {
			String sql = "update users set usergroup = '"+usergroup+"',name = '"+name+"',num = "+num+",login_id = '"+login_id+"',login_pwd = '"+login_pwd+"',major = '"+major+"',class = '"+_class+"' where id = "+id;
			r = SqlTool.excuteUpdate(sql);
			return r;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return r;
		}
	}
	
	public static String selectMarks(int tid,String uid){
		String marks = "";
		try{
			String sql = "select marks from ans_papers where tid =  "+tid+" and uid = '"+uid+"'";
			ResultSet rs = SqlTool.executeQuery(sql);
			while(rs.next()){
				marks = rs.getString(1);
			}
			return marks;
		}
		catch (Exception e) {
			e.printStackTrace();
			return marks;
		}
	}
}
