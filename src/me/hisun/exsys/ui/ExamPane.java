package me.hisun.exsys.ui;


import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * 试题添加界面的设置和考试所需界面比较类似...仿造那段代码写一次= =
 */
public class ExamPane {
	JPanel jp = new JPanel();
	
	JLabel lbl_marks;
	JLabel lbl_time;
	JLabel lbl_num;
	JLabel lbl_progress;
	
	JTextArea ta_quesobj;//客观题用
	JTextArea ta_quessubj;//主观题
	JTextField txt_mark;
	JTextArea ta_a;//客观题用
	JTextArea ta_b;//客观题用
	JTextArea ta_c;//客观题用
	JTextArea ta_d;//客观题用
	JTextArea ta_ans;//主观题
	
	JScrollPane jsp_quesobj;//客观题用
	JScrollPane jsp_quessubj;//主观题
	JScrollPane jsp_a;//客观题用
	JScrollPane jsp_b;//客观题用
	JScrollPane jsp_c;//客观题用
	JScrollPane jsp_d;//客观题用
	JScrollPane jsp_ans;//主观题
	
	JRadioButton jrb_a;//客观题用
	JRadioButton jrb_b;//客观题用
	JRadioButton jrb_c;//客观题用
	JRadioButton jrb_d;//客观题用
	
	ButtonGroup bg;//客观题用
	
	JButton btn_last;
	JButton btn_next;
	JButton btn_submit;
	
	protected ExamPane(){
		//jp = new JPanel();
		
		lbl_marks = new JLabel("本卷总分："+DataExchange.marks);
		lbl_time = new JLabel("	本卷限时："+DataExchange.time);
		lbl_num = new JLabel("试题数量");
		lbl_progress = new JLabel("",JLabel.RIGHT);	
		
		ta_quesobj = new JTextArea(3,50);
		ta_quessubj = new JTextArea(5,50);
		ta_a = new JTextArea(2,50);
		ta_b = new JTextArea(2,50);
		ta_c = new JTextArea(2,50);
		ta_d = new JTextArea(2,50);
		ta_ans = new JTextArea(13,50);
		
		jsp_quesobj = new JScrollPane(ta_quesobj);
		jsp_quessubj = new JScrollPane(ta_quessubj);
		jsp_a =  new JScrollPane(ta_a);
		jsp_b =  new JScrollPane(ta_b);
		jsp_c =  new JScrollPane(ta_c);
		jsp_d =  new JScrollPane(ta_d);
		jsp_ans = new JScrollPane(ta_ans);
		
		jrb_a = new JRadioButton("A");//客观题用
		jrb_b = new JRadioButton("B");//客观题用
		jrb_c = new JRadioButton("C");//客观题用
		jrb_d = new JRadioButton("D");//客观题用
		
		bg = new ButtonGroup();
		bg.add(jrb_a);
		bg.add(jrb_b);
		bg.add(jrb_c);
		bg.add(jrb_d);//使这4个JRadioButton成为一个Group
		
		btn_last = new JButton("上一题");
		btn_next = new JButton("下一题");
		btn_submit = new JButton("提交试卷");
		
		jp.add(lbl_marks);
		jp.add(lbl_time);
		jp.add(lbl_num);
		jp.add(lbl_progress);
		
		jp.add(jsp_quesobj);
		
		jp.add(jsp_quessubj);
		
		jp.add(jsp_a);
		jp.add(jsp_b);
		jp.add(jsp_c);
		jp.add(jsp_d);
		
		jp.add(jsp_ans);
		
		jp.add(jrb_a);
		jp.add(jrb_b);
		jp.add(jrb_c);
		jp.add(jrb_d);
		
		jp.add(btn_last);
		jp.add(btn_next);
		jp.add(btn_submit);
		
		lbl_marks.setPreferredSize(new Dimension(110,25));
		lbl_time.setPreferredSize(new Dimension(110,25));
		lbl_num.setPreferredSize(new Dimension(110,25));
		ta_quesobj.setEditable(false);
		ta_quessubj.setEditable(false);
		jsp_a.setBorder(BorderFactory.createTitledBorder("A"));
		ta_a.setEditable(false);
		jsp_b.setBorder(BorderFactory.createTitledBorder("B"));
		ta_b.setEditable(false);
		jsp_c.setBorder(BorderFactory.createTitledBorder("C"));
		ta_c.setEditable(false);
		jsp_d.setBorder(BorderFactory.createTitledBorder("D"));
		ta_d.setEditable(false);
		jsp_ans.setBorder(BorderFactory.createTitledBorder("请作答"));
		
		ta_quesobj.setLineWrap(true);//使TextArea自动换行
		ta_quessubj.setLineWrap(true);
		ta_a.setLineWrap(true);
		ta_b.setLineWrap(true);
		ta_c.setLineWrap(true);
		ta_d.setLineWrap(true);
		ta_ans.setLineWrap(true);
		jrb_a.setPreferredSize(new Dimension(110,25));
		jrb_b.setPreferredSize(new Dimension(110,25));
		jrb_c.setPreferredSize(new Dimension(110,25));
	}
	
	protected JPanel getPane(){
		return jp;
	}
}
