package me.hisun.exsys.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import me.hisun.exsys.dao.AdminDao;
import me.hisun.exsys.dao.QuesDao;

public class QuesActionBasePane{
	//声明所有
	JPanel jp;//记得这货一定要实例化...
	JLabel lbl_top;
	JLabel lbl_id;
	JLabel lbl_type;
	JLabel lbl_ques;
	JLabel lbl_tag;
	JLabel lbl_subtag;
	JLabel lbl_mark;
	JLabel lbl_a;
	JLabel lbl_b;
	JLabel lbl_c;
	JLabel lbl_d;
	JLabel lbl_empty;
	//JLabel lbl_ans = new JLabel("主观题参考答案");
	
	JTextField txt_top;
	JTextField txt_id;
	JTextField txt_type;
	JTextArea ta_quesobj;
	JTextArea ta_quessubj;
	JTextField txt_tag;
	JTextField txt_subtag;
	JTextField txt_mark;
	JTextArea ta_a;
	JTextArea ta_b;
	JTextArea ta_c;
	JTextArea ta_d;
	JTextArea ta_empty;//主观题用
	//JTextArea ta_ans = new JTextArea(5,50);//主观题用
	
	JScrollPane jsp_quesobj;
	JScrollPane jsp_quessubj;
	JScrollPane jsp_a;
	JScrollPane jsp_b;
	JScrollPane jsp_c;
	JScrollPane jsp_d;
	JScrollPane jsp_empty;
	
	
	JRadioButton jrb_a;//客观题用
	JRadioButton jrb_b;//客观题用
	JRadioButton jrb_c;//客观题用
	JRadioButton jrb_d;//客观题用
	
	ButtonGroup bg;
	
	JButton btn;
	JButton btn_extra;
	
	//主体结构和大部分相同内容
	protected QuesActionBasePane(){
		//组件初始化
		jp = new JPanel();
		lbl_top = new JLabel();
		lbl_id = new JLabel();
		lbl_type = new JLabel();
		lbl_ques = new JLabel("题目内容*");
		lbl_tag = new JLabel("题目主标签");
		lbl_subtag = new JLabel("题目副标签");
		lbl_mark = new JLabel("该题分值*");
		lbl_a = new JLabel("A选项内容*");
		lbl_b = new JLabel("B选项内容*");
		lbl_c = new JLabel("C选项内容*");
		lbl_d = new JLabel("D选项内容*");
		lbl_empty = new JLabel("主观题参考答案*");
		//JLabel lbl_ans = new JLabel("主观题参考答案");
		
		txt_top = new JTextField();
		txt_id = new JTextField();
		txt_type = new JTextField();
		txt_tag = new JTextField();
		txt_subtag = new JTextField();
		txt_mark = new  JTextField("");
		ta_quesobj = new JTextArea(3,50);
		ta_quessubj = new JTextArea(8,50);
		ta_a = new JTextArea(2,50);
		ta_b = new JTextArea(2,50);
		ta_c = new JTextArea(2,50);
		ta_d = new JTextArea(2,50);
		ta_empty = new JTextArea(12,50);//主观题用
		//JTextArea ta_ans = new JTextArea(5,50);//主观题用
		
		jsp_quesobj = new JScrollPane(ta_quesobj);
		jsp_quessubj = new JScrollPane(ta_quessubj);
		jsp_a = new JScrollPane(ta_a);
		jsp_b= new JScrollPane(ta_b);
		jsp_c = new JScrollPane(ta_c);
		jsp_d = new JScrollPane(ta_d);
		jsp_empty = new JScrollPane(ta_empty);
		
		jrb_a = new JRadioButton("A");//客观题用
		jrb_b = new JRadioButton("B");//客观题用
		jrb_c = new JRadioButton("C");//客观题用
		jrb_d = new JRadioButton("D");//客观题用
		
		bg = new ButtonGroup();
		bg.add(jrb_a);
		bg.add(jrb_b);
		bg.add(jrb_c);
		bg.add(jrb_d);//使这4个JRadioButton成为一个Group
		
		btn = new JButton();
		btn_extra = new JButton();
		//组件初始化结束

		//向jp加控件
		//jp.add(lbl_top);
		jp.add(lbl_tag);
		jp.add(lbl_subtag);
		
		jp.add(txt_tag);
		jp.add(txt_subtag);
		
		jp.add(lbl_ques);
		
		jp.add(lbl_mark);
		jp.add(txt_mark);
		
		jp.add(jsp_quesobj);
		jp.add(jsp_quessubj);
		
		jp.add(lbl_a);
		jp.add(jsp_a);
		
		jp.add(lbl_b);
		jp.add(jsp_b);
		
		jp.add(lbl_c);
		jp.add(jsp_c);
		
		jp.add(lbl_d);
		jp.add(jsp_d);
	
		jp.add(lbl_empty);
		jp.add(jsp_empty);
		
		jp.add(jrb_a);
		jp.add(jrb_b);
		jp.add(jrb_c);
		jp.add(jrb_d);
		
		jp.add(btn);
		//组件的公用设置
		lbl_tag.setPreferredSize(new Dimension(210,25));
		lbl_subtag.setPreferredSize(new Dimension(210,25));
		
		txt_tag.setPreferredSize(new Dimension(210,25));
		txt_subtag.setPreferredSize(new Dimension(210,25));
		
		lbl_ques.setPreferredSize(new Dimension(320,25));
		
		lbl_mark.setPreferredSize(new Dimension(60,25));
		txt_mark.setPreferredSize(new Dimension(40,25));
		
		lbl_a.setPreferredSize(new Dimension(430,25));
		lbl_b.setPreferredSize(new Dimension(430,25));
		lbl_c.setPreferredSize(new Dimension(430,25));
		lbl_d.setPreferredSize(new Dimension(430,25));
		lbl_empty.setPreferredSize(new Dimension(430,25));
		
		ta_quesobj.setLineWrap(true);//使TextArea自动换行
		ta_quessubj.setLineWrap(true);
		ta_a.setLineWrap(true);
		ta_b.setLineWrap(true);
		ta_c.setLineWrap(true);
		ta_d.setLineWrap(true);
		ta_empty.setLineWrap(true);
		
	}
	
	//按照当前选择试题的ID，获取所有试题信息至编辑窗口
	/*protected void selectById(int selectId){
		ResultSet rs = AdminDao.select("users",selectId);
		try{
			while(rs.next()){
				//String type = rs.getString(1);
				String ques = rs.getString(2);
				String tag = rs.getString(3);
				String subtag = rs.getString(4);
				String opt_a = rs.getString(5);
				String opt_b = rs.getString(6);
				String opt_c = rs.getString(7);
				String opt_d = rs.getString(8);
				ans = rs.getString(9);
				
				ta_quesobj.setText(ques);
				ta_quessubj.setText(ques);
				txt_tag.setText(tag);
				txt_subtag.setText(tag);
				ta_a.setText(opt_a);
				ta_b.setText(opt_b);
				ta_c.setText(opt_c);
				ta_d.setText(opt_d);
				//ans的操作见QuesEditFrm
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}*/
	public void resetBorder(String s){
		jp.setBorder(BorderFactory.createTitledBorder(s));
	}
	
	
	public JPanel getPane(){
		return jp;
	}
	
}
