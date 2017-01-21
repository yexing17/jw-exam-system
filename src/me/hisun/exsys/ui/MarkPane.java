package me.hisun.exsys.ui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * 阅卷界面的设置和考试所需界面比较类似...仿造考试代码写一次= =
 */
public class MarkPane {
	JPanel jp = new JPanel();
	
	JLabel lbl_marks;
	JLabel lbl_num;
	JLabel lbl_mark;
	
	JTextField txt_mark;
	JTextArea ta_ques;
	//JTextArea ta_quesobj;//客观题用
	//JTextArea ta_quessubj;//主观题
	//JTextArea ta_a;//客观题用
	//JTextArea ta_b;//客观题用
	//JTextArea ta_c;//客观题用
	//JTextArea ta_d;//客观题用
	JTextArea ta_ans;
	JTextArea ta_rans;//参考答案
	
	JScrollPane jsp_ques;
	//JScrollPane jsp_quesobj;//客观题用
	//JScrollPane jsp_quessubj;//主观题
	//JScrollPane jsp_a;//客观题用
	//JScrollPane jsp_b;//客观题用
	//JScrollPane jsp_c;//客观题用
	//JScrollPane jsp_d;//客观题用
	JScrollPane jsp_ans;//主观题
	JScrollPane jsp_rans;//参考答案
	
	JButton btn_mark;
	JButton btn_last;
	JButton btn_next;
	JButton btn_submit;
	
	protected MarkPane(){
		//jp = new JPanel();
		
		lbl_marks = new JLabel();
		lbl_num = new JLabel();
		lbl_mark = new JLabel("         给此题打分：");
		
		ta_ques = new JTextArea(3,50);
		//ta_quesobj = new JTextArea(3,50);
		//ta_quessubj = new JTextArea(5,50);
		//ta_a = new JTextArea(2,50);
		//ta_b = new JTextArea(2,50);
		//ta_c = new JTextArea(2,50);
		//ta_d = new JTextArea(2,50);
		ta_ans = new JTextArea(8,50);
		ta_rans = new JTextArea(5,50);
		txt_mark = new JTextField(20);
		
		jsp_ques = new JScrollPane(ta_ques);
		//jsp_quesobj = new JScrollPane(ta_quesobj);
		//jsp_quessubj = new JScrollPane(ta_quessubj);
		//jsp_a =  new JScrollPane(ta_a);
	//	jsp_b =  new JScrollPane(ta_b);
	//	jsp_c =  new JScrollPane(ta_c);
		//jsp_d =  new JScrollPane(ta_d);
		jsp_ans = new JScrollPane(ta_ans);
		jsp_rans = new JScrollPane(ta_rans);
		
		btn_mark = new JButton("刷新此题评分");
		btn_last = new JButton("上一题");
		btn_next = new JButton("下一题");
		btn_submit = new JButton("提交批阅");
		
		jp.add(lbl_marks);
		jp.add(lbl_num);
		
		jp.add(jsp_ques);
		//jp.add(jsp_quesobj);
		
		//jp.add(jsp_quessubj);
		
		//jp.add(jsp_a);
		//jp.add(jsp_b);
		//jp.add(jsp_c);
		//jp.add(jsp_d);
		
		jp.add(jsp_ans);
		jp.add(jsp_rans);
		
		jp.add(lbl_mark);
		jp.add(txt_mark);
		jp.add(btn_mark);
		
		jp.add(btn_last);
		jp.add(btn_next);
		jp.add(btn_submit);
		
		lbl_marks.setPreferredSize(new Dimension(280,25));
		lbl_num.setPreferredSize(new Dimension(140,25));
		//lbl_mark.setPreferredSize(new Dimension(140,25));
		ta_ques.setEditable(false);
		ta_ans.setEditable(false);
		ta_rans.setEditable(false);
		//ta_quesobj.setEditable(false);
		//ta_quessubj.setEditable(false);
		//jsp_a.setBorder(BorderFactory.createTitledBorder("A"));
		//ta_a.setEditable(false);
		//jsp_b.setBorder(BorderFactory.createTitledBorder("B"));
		//ta_b.setEditable(false);
		//jsp_c.setBorder(BorderFactory.createTitledBorder("C"));
		//ta_c.setEditable(false);
		//jsp_d.setBorder(BorderFactory.createTitledBorder("D"));
		//ta_d.setEditable(false);
		jsp_ans.setBorder(BorderFactory.createTitledBorder("考生答案"));
		jsp_rans.setBorder(BorderFactory.createTitledBorder("参考答案"));
		
		ta_ques.setLineWrap(true);
		//ta_quesobj.setLineWrap(true);//使TextArea自动换行
		//ta_quessubj.setLineWrap(true);
		//ta_a.setLineWrap(true);
		//ta_b.setLineWrap(true);
		//ta_c.setLineWrap(true);
		//ta_d.setLineWrap(true);
		ta_ans.setLineWrap(true);
		ta_rans.setLineWrap(true);
		jp.setBorder(BorderFactory.createTitledBorder("客观题已自动评分，如有需要也可重新评分"));
		
	}
	
	protected JPanel getPane(){
		return jp;
	}
}
