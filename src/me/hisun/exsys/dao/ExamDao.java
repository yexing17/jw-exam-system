package me.hisun.exsys.dao;

import java.sql.ResultSet;

public class ExamDao {
	
	public static int insertAnsQues(String uid,int tid,int qid,String ques,String ans,String submit_ans,String mark){
		int r = 0;
		try{
			String sql = "insert into ans_ques(uid,tid,qid,ques,ans,submit_ans,mark) values('"+uid+"',"+tid+","+qid+",'"+ques+"','"+ans+"','"+submit_ans+"','"+mark+"')";
			r = SqlTool.excuteUpdate(sql);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return r;
	}
	
	public static int insertAnsPaper(int tid,String tname,String ques_num,String marks_total,String uid,String uname,String status,String ans_ids){
		int r = 0;
		try{
			String sql = "insert into ans_papers(tid,tname,ques_num,marks_total,uid,uname,status,ans_ids) values("+tid+",'"+tname+"','"+ques_num+"','"+marks_total+"','"+uid+"','"+uname+"','"+status+"','"+ans_ids+"')";
			r = SqlTool.excuteUpdate(sql);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return r;
	}
	
	//select答卷表中的ans_ids专用select语句
	public static ResultSet select_ans_ids(String  uid,int tid){
		ResultSet rs = null;
		try{
			String sql = "select id from ans_ques where uid = '"+uid+"' and tid = "+tid;
			rs = SqlTool.executeQuery(sql);
			return rs;
		}catch (Exception e) {
			e.printStackTrace();
			return rs;
		}
	}
}
