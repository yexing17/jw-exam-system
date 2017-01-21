package me.hisun.exsys.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import me.hisun.exsys.dao.AdminDao;
import me.hisun.exsys.dao.UserDao;

public class FrmTool extends JFrame{
	//验证下列用户信息框内数据是否为空，并返回boolean值，是空还会弹出警告框
	protected static boolean isUserFrmEmpty(UserActionBasePane up){
		boolean flag = true;
		if(((((up.txt_usergroup.getText().equals("") || up.txt_name.getText().equals("")) || up.txt_num.getText().equals("")) || up.txt_id.getText().equals("")) || String.valueOf(up.pwf_pwd.getPassword()).equals("")))
			{
			JOptionPane.showMessageDialog(null,"带  * 星号 的项为必填项！请填写完整","错误",JOptionPane.ERROR_MESSAGE);
			flag = false;
			}
		return flag;
	}
	
	protected static boolean isObjQuesEmpty(QuesActionBasePane qp){
		boolean flag = true;
		if((((((qp.ta_quesobj.getText().equals("")||qp.ta_a.getText().equals(""))||qp.ta_b.getText().equals(""))||qp.ta_c.getText().equals(""))||qp.ta_d.getText().equals(""))||QuesAddFrm.itemChoose.equals(""))||qp.txt_mark.getText().equals("")){
			JOptionPane.showMessageDialog(null,"带  * 星号 的项为必填项！请填写完整","错误",JOptionPane.ERROR_MESSAGE);
			flag = false;
		}
		return flag;
	}
	
	protected static boolean isSubjQuesEmpty(QuesActionBasePane qp){
		boolean flag = true;
		if((qp.ta_quessubj.getText().equals("")||qp.ta_empty.equals(""))||qp.txt_mark.getText().equals("")){
			JOptionPane.showMessageDialog(null,"带  * 星号 的项为必填项！请填写完整","错误",JOptionPane.ERROR_MESSAGE);
			flag = false;
		}
		return flag;
	}
	
	protected static boolean isPaperFrmEmpty(PaperActionBasePane pp){
		boolean flag = true;
		if(((pp.txt_name.getText().equals("")||pp.txt_time.getText().equals(""))||pp.cb_status.getSelectedItem().toString().equals(""))||DataExchange.quesids.equals("")){
			JOptionPane.showMessageDialog(null,"请把必须内容填写完整","错误",JOptionPane.ERROR_MESSAGE);
			flag = false;
		}
		return flag;
	}
	
	//验证一个字符串是否是整型数字，并返回boolean值，不是数字还会弹出警告框
	protected static boolean isNum(String s,String msn){
		boolean flag = true;
		try{
			int nums = Integer.parseInt(s);
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null,msn,"错误",JOptionPane.ERROR_MESSAGE);
			flag = false;
		}
		return flag;
	}
	
	//按表名和id删除单条数据
	protected static void toDelete(String table,int id,String msn){
		int isDelete = JOptionPane.showConfirmDialog(null,msn,"请确认 ！",JOptionPane.YES_NO_OPTION);
		if(isDelete == JOptionPane.YES_OPTION){
			int r = AdminDao.delete(table,id);
			if(r != 0){
				JOptionPane.showMessageDialog(null, "删除"+r+"条数据成功！\n点击刷新数据可更新数据显示");
			}
		}
	}
	//取得一个默认的model
	public static DefaultTableModel getDeModel(String[] headName){
		String[][] cellData = null;
		DefaultTableModel model = new DefaultTableModel(cellData,headName){
			public boolean isCellEditable(int row,int column){
				return false;
			}
		};
		return model;
	}
	
	//ExamQuesBufferTable 中按列获取内容，按照当前考试题目序号返回
	public static String getFromTblQues(int column){
		return String.valueOf(ExamQuesBufferTable.tbl.getValueAt(DataExchange.examQuesId, column));
	}
	
	//ExamQuesBufferTable 中按列获取内容，定位取得Table中内容
	public static String getFromTblQues(int row,int column){
		return String.valueOf(ExamQuesBufferTable.tbl.getValueAt(row, column));
	}
	
	//ExamQuesBufferTable 中按列获取内容，按照当前考试题目序号返回
	public static String getFromTblAns(int column){
		return String.valueOf(AnsPapersBufferTable.tbl.getValueAt(DataExchange.markQuesId, column));
	}
	
	//ExamQuesBufferTable 中按列获取内容，定位取得Table中内容
	public static String getFromTblAns(int row,int column){
		return String.valueOf(AnsPapersBufferTable.tbl.getValueAt(row, column));
	}
	
	
	//字符串切割
	public static String minusString(String str1,String str2){
		return str1.replaceAll(str2, "");
	}
}
