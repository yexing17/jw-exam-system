package me.hisun.exsys.dao;

import java.sql.ResultSet;

import me.hisun.exsys.ui.DataExchange;

public class MarkDao {
	public static ResultSet select(){
		ResultSet rs = null;
		try{
			String sql = "select * from ans_ques where id in("+DataExchange.ansids+") order by field(id,"+DataExchange.ansids+")";
			rs = SqlTool.executeQuery(sql);
			return rs;
		}catch(Exception ex){
			ex.printStackTrace();
			return rs;
		}
	}
	
	public static String selectMark(int id){
		String  str = "";
		try{
			String sql = "select mark from questions where id = "+id;
			ResultSet rs = SqlTool.executeQuery(sql);
			while(rs.next()){
				str = rs.getString(1);
			}
			return str;
		}catch(Exception ex){
			ex.printStackTrace();
			return str;
		}
	}
	
	public static int updateMarks(String status,String marks,int id){
		int r = 0;
		try{
			String sql = "update ans_papers set status = '"+status+"',marks = '"+marks+"' where id = "+id;
			r = SqlTool.excuteUpdate(sql);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return r;
	}
}
