package me.hisun.exsys.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import me.hisun.exsys.dao.QuesDao;

//基于QuesActionBaseFrm构建，生成一个用于管理添加试题的窗体
//此类调用了toAddUser()方法
public class QuesAddFrm extends JFrame implements ItemListener{
	JTabbedPane tp = new JTabbedPane(JTabbedPane.TOP);
	QuesActionBasePane qp1 = new QuesActionBasePane();
	QuesActionBasePane qp2 = new QuesActionBasePane();
	static String itemChoose = "";
	protected QuesAddFrm(){
		//设置窗体属性
		this.setTitle("添加试题 ");
		tp.addTab("添加客观题", qp1.getPane());
		tp.addTab("添加主观题", qp2.getPane());//中间可以添加ico图标
		this.setContentPane(tp);//和上面的效果类似
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(466,620);
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize(); 
		Dimension frameSize = this.getSize();
		this.setLocation((displaySize.width - frameSize.width) / 2,(displaySize.height - frameSize.height) / 2);
		this.setResizable(false);
		this.setVisible(true);
		{//客观题部分
			qp1.lbl_empty.setText("选择题正确答案*");
			qp1.btn.setText("确认添加此试题");
			qp1.jsp_quessubj.setVisible(false);
			qp1.jsp_empty.setVisible(false);
			qp1.jrb_a.addItemListener(this);
			qp1.jrb_b.addItemListener(this);
			qp1.jrb_c.addItemListener(this);
			qp1.jrb_d.addItemListener(this);
			qp1.btn.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent arg0) {
					toAddObjQues();
				}
			});
			
			qp1.resetBorder("带*的选项为必填项，选择题的答案也请务必勾选");
		}
		{//主观题部分
			qp2.jsp_quesobj.setVisible(false);
			qp2.lbl_a.setVisible(false);
			qp2.lbl_b.setVisible(false);
			qp2.lbl_c.setVisible(false);
			qp2.lbl_d.setVisible(false);
			qp2.jsp_a.setVisible(false);
			qp2.jsp_b.setVisible(false);
			qp2.jsp_c.setVisible(false);
			qp2.jsp_d.setVisible(false);
			qp2.lbl_empty.setText("主观题参考答案*");
			qp2.jrb_a.setVisible(false);
			qp2.jrb_b.setVisible(false);
			qp2.jrb_c.setVisible(false);
			qp2.jrb_d.setVisible(false);
			qp2.btn.setText("确认添加此试题");
			qp2.jsp_empty.setVisible(true);
			qp2.btn.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent arg0) {
					toAddSubjQues();
				}
			});
			qp2.resetBorder("带*的选项为必填项");
		}

	}
	
	protected void toAddObjQues(){
		if(FrmTool.isObjQuesEmpty(qp1)){
			int r = QuesDao.insertObj("客观题",qp1.ta_quesobj.getText(),qp1.txt_mark.getText(),qp1.txt_tag.getText(),qp1.txt_subtag.getText(),qp1.ta_a.getText(),qp1.ta_b.getText(),qp1.ta_c.getText(),qp1.ta_d.getText(),itemChoose);
			if(r!=0){
				JOptionPane.showMessageDialog(this,"添加成功！");
				qp1.ta_quesobj.setText("");
				qp1.txt_mark.setText("");
				qp1.txt_tag.setText("");
				qp1.txt_subtag.setText("");
				qp1.ta_a.setText("");
				qp1.ta_b.setText("");
				qp1.ta_c.setText("");
				qp1.ta_d.setText("");
				qp1.jrb_a.setSelected(false);
				qp1.jrb_b.setSelected(false);
				qp1.jrb_c.setSelected(false);
				qp1.jrb_d.setSelected(false);
				}
			else
				JOptionPane.showMessageDialog(this,"添加失败，请检测你的信息是否有误");
		}
	}
	
	protected void toAddSubjQues(){
		if(FrmTool.isSubjQuesEmpty(qp2)){
			int r = QuesDao.insertSubj("主观题",qp2.ta_quessubj.getText(),qp2.txt_mark.getText(),qp2.txt_tag.getText(),qp2.txt_subtag.getText(),qp2.ta_empty.getText());
			if(r!=0){
				JOptionPane.showMessageDialog(this,"添加成功！");
				qp2.ta_quessubj.setText("");
				qp2.txt_tag.setText("");
				qp2.txt_subtag.setText("");
				qp2.ta_empty.setText("");
				}
			else
				JOptionPane.showMessageDialog(this,"添加失败，请检测你的信息是否有误");
		}
	}
	
	public void itemStateChanged(ItemEvent e) {
		itemChoose = "";
		if(e.getStateChange() == e.SELECTED){
			if(e.getSource() == qp1.jrb_a)
				itemChoose = "A";
			else if(e.getSource() == qp1.jrb_b)
				itemChoose = "B";
			else if(e.getSource() == qp1.jrb_c)
				itemChoose = "C";
			else if(e.getSource() == qp1.jrb_d)
				itemChoose = "D";
		}
	}
}