package me.hisun.exsys.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import me.hisun.exsys.dao.AdminDao;
import me.hisun.exsys.dao.PaperDao;

public class PaperEditFrm  extends JFrame{
	PaperActionBasePane pp = new PaperActionBasePane();
	int ques_num = 0;
	int marks = 0;
	
	protected PaperEditFrm(){
	}
	
	protected PaperEditFrm(int selectId){
		this.setTitle("修改信息");
		this.setContentPane(pp.getPane());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(660, 615);
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		this.setLocation((displaySize.width - frameSize.width)/2,(displaySize.height - frameSize.height));
		this.setResizable(false);
		this.setVisible(true);
		
		selectById(selectId);
		pp.qp1.refresh();
		refreshChooseQues();
		
		pp.btn.setText("确认修改此试卷");
		
		pp.btn_edit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				editQues();
			}
		});
		
		pp.btn_addques.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				addQues();
			}
		});
		
		pp.btn_canceladd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"对不起！该功能尚未完善，敬请期待！");
			}
		});
		
		pp.btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				toEditPaper();
			}
		});
		
		pp.resetBorder("该面板只能实现添加试题、修改试卷名和考试时间，修复试卷出错请重开！要使试卷发布请设发布状态为“发布考试”");
	}
	
	protected void editQues() {
		try{
			DataExchange.selectIdQuesInPaper1 = Integer.parseInt(String.valueOf(pp.qp1.tbl.getValueAt(pp.qp1.tbl.getSelectedRow(), 0)));
			QuesEditFrm qef = new QuesEditFrm(DataExchange.selectIdQuesInPaper1);
			qef.qp1.btn.setVisible(false);
			qef.qp1.jrb_a.setVisible(false);
			qef.qp1.jrb_b.setVisible(false);
			qef.qp1.jrb_c.setVisible(false);
			qef.qp1.jrb_d.setVisible(false);
			qef.qp1.resetBorder("");
			qef.qp2.btn.setVisible(false);
			qef.qp2.resetBorder("");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"请确认您点选了一道试题！");
		}
	}
	
	protected void addQues() {
		try{
			DataExchange.selectIdQuesInPaper1 = Integer.parseInt(String.valueOf(pp.qp1.tbl.getValueAt(pp.qp1.tbl.getSelectedRow(), 0)));
			DataExchange.selectMarkQuesInPaper1 = Integer.parseInt(String.valueOf(pp.qp1.tbl.getValueAt(pp.qp1.tbl.getSelectedRow(), 3)));
			if(DataExchange.quesids == "")
				DataExchange.quesids = DataExchange.quesids + String.valueOf(DataExchange.selectIdQuesInPaper1);
			else
				DataExchange.quesids = DataExchange.quesids + "," +String.valueOf(DataExchange.selectIdQuesInPaper1);
			ques_num++;
			pp.lbl_ques_num.setText("  当前已选"+ques_num+"题");
			marks = marks + DataExchange.selectMarkQuesInPaper1;
			pp.lbl_marks.setText("  当前已选题分值 "+marks);
			refreshChooseQues();//访问数据库，刷新jsp2
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"请确认您点选了一道试题！");
		}
	}
	
	private void selectById(int selectId) {
		ResultSet rs = AdminDao.select("papers", selectId);
		try{
			while(rs.next()){
				String name = rs.getString(2);
				ques_num = Integer.parseInt(rs.getString(3));
				pp.lbl_ques_num.setText("  当前已选"+ques_num+"题");
				marks = Integer.parseInt(rs.getString(4));
				pp.lbl_marks.setText("  当前已选题分值 "+marks);
				String time = String.valueOf(rs.getInt(5));
				String status = rs.getString(6);
				DataExchange.quesids = rs.getString(7);
				
				pp.txt_name.setText(name);
				pp.txt_time.setText(time);
				pp.cb_status.setSelectedItem(status);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected void toEditPaper() {
		if(FrmTool.isPaperFrmEmpty(pp)){
			int r= PaperDao.update(DataExchange.selectIdPaper, pp.txt_name.getText(),String.valueOf(ques_num),String.valueOf(marks), Integer.parseInt(pp.txt_time.getText()), pp.cb_status.getSelectedItem().toString(), DataExchange.quesids);
			int isEdit = JOptionPane.showConfirmDialog(null,"确认修改这条试题记录？","请确认 ！",JOptionPane.YES_NO_OPTION);
			if(isEdit == JOptionPane.YES_OPTION){
				if(r != 0){
					JOptionPane.showMessageDialog(this,"修改成功！");
					pp.txt_name.setText("");
					pp.txt_time.setText("");
					pp.cb_status.setSelectedItem("暂未发布");
					ques_num = 0;
					pp.lbl_ques_num.setText("  当前已选"+ques_num+"题");
					marks = 0;
					pp.lbl_marks.setText("  当前已选题分值 "+marks);
					DataExchange.quesids="";
					DataExchange.selectIdQuesInPaper1 = 0;
					DataExchange.selectMarkQuesInPaper1 = 0;
					int numR = pp.qp2.model.getRowCount();
					while(numR>0){
						pp.qp2.model.removeRow(0);
						numR--;
					}//刷新空的字符串会有报错，直接remove all...暂时这样写
					//refreshChooseQues();//BUG?
				}
				else
					JOptionPane.showMessageDialog(this,"修改失败，请检查你的信息是否有误");
			}
		}
	}
	
	protected void refreshChooseQues(){
		int numR = pp.qp2.model.getRowCount();
		while(numR>0){
			pp.qp2.model.removeRow(0);
			numR--;
		}
		try{
			ResultSet rs = PaperDao.select();
			while(rs.next()){
				String str1 = String.valueOf(rs.getInt(1));
				String str2 = rs.getString(2);
				String str3 = rs.getString(3);
				String str4 = rs.getString(4);
				String str5 = rs.getString(5);
				String str6 = rs.getString(6);
				String str7 = rs.getString(7);
				String str8 = rs.getString(8);
				String str9 = rs.getString(9);
				String str10 = rs.getString(10);
				String str11 = rs.getString(11);
				String[] strs = {str1,str2,str3,str4,str5,str6,str7,str8,str9,str10,str11}; 
				pp.qp2.model.addRow(strs);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
