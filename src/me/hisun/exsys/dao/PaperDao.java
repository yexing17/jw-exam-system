package me.hisun.exsys.dao;

import java.sql.ResultSet;

import me.hisun.exsys.ui.DataExchange;

public class PaperDao {
	public static ResultSet select(){
		ResultSet rs = null;
		try{
			String sql = "select * from questions where id in("+DataExchange.quesids+") order by field(id,"+DataExchange.quesids+")";
			rs = SqlTool.executeQuery(sql);
		}catch(Exception ex){
			ex.printStackTrace();
			return rs;
		}
		return rs;
	}
	
	public static int insert(String name,String ques_num,String marks,int time,String status,String ques_ids){
		int r = 0;
		try{
			String sql = "insert into papers(name,ques_num,marks,time,status,ques_ids) value('"+name+"','"+ques_num+"','"+marks+"','"+time+"','"+status+"','"+ques_ids+"')";
			r = SqlTool.excuteUpdate(sql);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return r;
	}
	
	public static int update(int id,String name,String ques_num,String marks,int time,String status,String ques_ids){
		int r= 0;
		try{
			String sql = "update papers set name = '"+name+"',ques_num = '"+ques_num+"',marks = '"+marks+"',time = "+time+",status = '"+status+"',ques_ids = '"+ques_ids+"' where id = "+id;
			r = SqlTool.excuteUpdate(sql);
			return r;
		}
		catch (Exception e) {
			e.printStackTrace();
			return r;
		}
	}
	
}
