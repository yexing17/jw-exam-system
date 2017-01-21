package me.hisun.exsys.ui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PaperActionBasePane {
	
	JPanel jp;
	
	JLabel lbl_name;
	JLabel lbl_time;
	JLabel lbl_status;
	JLabel lbl_ques_num;
	JLabel lbl_marks;
	
	JTextField txt_name;
	JTextField txt_time;
	JComboBox cb_status;
	
	AdminQuesPane qp1;
	JPanel quesjp1;
	AdminQuesPane qp2;
	JPanel quesjp2;
	
	JButton btn_edit;
	JButton btn_addques;
	JButton btn_canceladd;
	JButton btn;
	protected PaperActionBasePane(){
		
		//实例化
		jp = new JPanel();
		
		lbl_name = new JLabel("试卷名");
		lbl_time = new JLabel(" 考试时长");
		lbl_status = new JLabel("发布状态");
		lbl_ques_num = new JLabel("当前已选0题");
		lbl_marks = new JLabel("当前已选题分值 0");
		
		txt_name = new JTextField(25);
		txt_time = new JTextField(25);
		
		String[] items = {"暂不发布","发布考试"};
		cb_status = new JComboBox(items);
		
		qp1= new AdminQuesPane();
		quesjp1 = qp1.getPane();
		qp2= new AdminQuesPane();
		quesjp2 = qp2.getPane();
		
		btn_edit = new JButton("查看/编辑题库试题");
		btn_addques = new JButton("从题库添加");
		btn_canceladd = new JButton("撤销上次添加");
		btn = new JButton("确认生成试卷");
		
		quesjp1.setBorder(BorderFactory.createTitledBorder("题库"));
		qp1.btn_add.setVisible(false);
		qp1.btn_delete.setVisible(false);
		qp1.btn_edit.setVisible(false);
		qp1.btn_refresh.setVisible(false);
		qp1.jsp.setPreferredSize(new Dimension(620,200));
		quesjp2.setBorder(BorderFactory.createTitledBorder("已选题"));
		qp2.btn_add.setVisible(false);
		qp2.btn_delete.setVisible(false);
		qp2.btn_edit.setVisible(false);
		qp2.btn_refresh.setVisible(false);
		qp2.jsp.setPreferredSize(new Dimension(620,200));
		
		jp.add(lbl_name);
		jp.add(txt_name);
		jp.add(lbl_time);
		jp.add(txt_time);
		jp.add(lbl_status);
		jp.add(cb_status);
		jp.add(quesjp1);
		jp.add(btn_edit);
		jp.add(btn_addques);
		jp.add(btn_canceladd);
		jp.add(lbl_ques_num);
		jp.add(lbl_marks);
		jp.add(quesjp2);
		jp.add(btn);
		jp.setBorder(BorderFactory.createTitledBorder("该面板只能实现添加试题，组卷出错请重开！要使试卷发布请修改试卷的发布状态为“发布考试”"));
	}
	
	protected void resetBorder(String str){
		jp.setBorder(BorderFactory.createTitledBorder(str));
	}
	
	protected JPanel getPane(){
		return jp;
	}
}
