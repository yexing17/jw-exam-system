package me.hisun.exsys.dao;

import java.sql.ResultSet;

public class QuesDao {
	
	public static int insertSubj(String type,String ques,String mark,String tag,String subtag,String ans){//class only support number
		int r = 0;
		try{
			String sql = "insert into questions(type,ques,mark,tag,subtag,ans) values('"+type+"','"+ques+"','"+mark+"','"+tag+"','"+subtag+"','"+ans+"')";
			r = SqlTool.excuteUpdate(sql);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return r;
	}
	
	public static int insertObj(String type,String ques,String mark,String tag,String subtag,String opt_a,String opt_b,String opt_c,String opt_d,String ans){//class only support number
		int r = 0;
		try{
			String sql = "insert into questions(type,ques,mark,tag,subtag,opt_a,opt_b,opt_c,opt_d,ans) values('"+type+"','"+ques+"','"+mark+"','"+tag+"','"+subtag+"','"+opt_a+"','"+opt_b+"','"+opt_c+"','"+opt_d+"','"+ans+"')";
			r = SqlTool.excuteUpdate(sql);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return r;
	}
	
	public static int delete(int id){
		int r = 0;
			try {
				String sql = "delete from users where id= "+id;
				r = SqlTool.excuteUpdate(sql);
				return r;
			} 
			catch (Exception e) {
				e.printStackTrace();
				return r;
			}
	}
	
	public static int updateObj(int id,String ques,String mark,String tag,String subtag,String opt_a,String opt_b,String opt_c,String opt_d,String ans){
		int r= 0;
		try {
			String sql = "update questions set ques = '"+ques+"',mark = '"+mark+"',tag = '"+tag+"',subtag ='"+subtag+"',opt_a = '"+opt_a+"',opt_b = '"+opt_b+"',opt_c = '"+opt_c+"',opt_d = '"+opt_d+"',ans = '"+ans+"'  where id = "+id;
			r = SqlTool.excuteUpdate(sql);
			return r;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return r;
		}
	}
	
	public static int updateObj(int id,String ques,String mark,String tag,String subtag,String opt_a,String opt_b,String opt_c,String opt_d){
		int r= 0;
		try {
			String sql = "update questions set ques = '"+ques+"',mark = '"+mark+"',tag = '"+tag+"',subtag ='"+subtag+"',opt_a = '"+opt_a+"',opt_b = '"+opt_b+"',opt_c = '"+opt_c+"',opt_d = '"+opt_d+"'  where id = "+id;
			r = SqlTool.excuteUpdate(sql);
			return r;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return r;
		}
	}
	
	public static int updateSubj(int id,String ques,String mark,String tag,String subtag,String ans){
		int r= 0;
		try {
			String sql = "update questions set ques = '"+ques+"',mark = '"+mark+"',tag = '"+tag+"',subtag = '"+subtag+"',ans = '"+ans+"'  where id = "+id;
			r = SqlTool.excuteUpdate(sql);
			return r;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return r;
		}
	}
	
}
