package me.hisun.exsys.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import me.hisun.exsys.dao.AdminDao;
import me.hisun.exsys.dao.QuesDao;

public class QuesEditFrm extends JFrame implements ItemListener{
	QuesActionBasePane qp1 = new QuesActionBasePane();
	QuesActionBasePane qp2 = new QuesActionBasePane();
	
	//String ans = "";
	protected QuesEditFrm(){
	}
	
	protected QuesEditFrm(int selectId){
		try {
			ResultSet rs = AdminDao.select("type", "questions", selectId);
			String type = null;
			while(rs.next()){
				type = rs.getString(1);
			}
			if(type.equals("客观题")){
				this.setTitle("编辑客观题 ");
				this.setContentPane(qp1.getPane());
				this.setVisible(true);
			}else if(type.equals("主观题")){
				this.setTitle("编辑主观题 ");
				this.setContentPane(qp2.getPane());
				this.setVisible(true);
			}else
				JOptionPane.showMessageDialog(this,"对不起！本系统暂不支持对该题型进行操作");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(466,590);
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize(); 
		Dimension frameSize = this.getSize();
		this.setLocation((displaySize.width - frameSize.width) / 2,(displaySize.height - frameSize.height) / 2);
		this.setResizable(false);
		
		{//客观题部分
			//qp1.lbl_empty.setText("该题正确答案*  "+ans);
			qp1.btn.setText("确认修改此试题");
			qp1.jsp_quessubj.setVisible(false);
			qp1.jsp_empty.setVisible(false);
			qp1.jrb_a.addItemListener(this);
			qp1.jrb_b.addItemListener(this);
			qp1.jrb_c.addItemListener(this);
			qp1.jrb_d.addItemListener(this);
			selectObjById(selectId);
			qp1.btn.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent arg0) {
					toEditObjQues();
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
			qp2.lbl_empty.setText("该题参考答案*");
			qp2.jrb_a.setVisible(false);
			qp2.jrb_b.setVisible(false);
			qp2.jrb_c.setVisible(false);
			qp2.jrb_d.setVisible(false);
			qp2.btn.setText("确认修改此试题");
			qp2.jsp_empty.setVisible(true);
			selectSubjById(selectId);
			//qp2.ta_empty.setText(ans);
			qp2.btn.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent arg0) {
					toEditSubjQues();
				}
			});
			qp2.resetBorder("带*的选项为必填项");
		}

	}
	protected void toEditObjQues(){
		if(FrmTool.isObjQuesEmpty(qp1)){
			int r = QuesDao.updateObj(DataExchange.selectIdQues,qp1.ta_quesobj.getText(),qp1.txt_mark.getText(),qp1.txt_tag.getText(),qp1.txt_subtag.getText(),qp1.ta_a.getText(),qp1.ta_b.getText(),qp1.ta_c.getText(),qp1.ta_d.getText(),QuesAddFrm.itemChoose);
			int isEdit = JOptionPane.showConfirmDialog(null,"确认修改这条试题记录？","请确认 ！",JOptionPane.YES_NO_OPTION);
			if(isEdit == JOptionPane.YES_OPTION){
				if(r!=0){
					JOptionPane.showMessageDialog(this,"修改成功！");
					qp1.jrb_a.setSelected(false);
					qp1.jrb_b.setSelected(false);
					qp1.jrb_c.setSelected(false);
					qp1.jrb_d.setSelected(false);
					/*qp1.ta_quesobj.setText("");
					qp1.txt_mark.setText("");
					qp1.txt_tag.setText("");
					qp1.txt_subtag.setText("");
					qp1.ta_a.setText("");
					qp1.ta_b.setText("");
					qp1.ta_c.setText("");
					qp1.ta_d.setText("");
					QuesAddFrm.itemChoose ="";*/
				}
				else
					JOptionPane.showMessageDialog(this,"修改失败，请检查你的信息是否有误");
			}
		}
	}
	
	
	
	protected void toEditSubjQues(){
		if(FrmTool.isSubjQuesEmpty(qp2)){
			int r = QuesDao.updateSubj(DataExchange.selectIdQues,qp2.ta_quessubj.getText(),qp2.txt_mark.getText(),qp2.txt_tag.getText(),qp2.txt_subtag.getText(),qp2.ta_empty.getText());
			int isEdit = JOptionPane.showConfirmDialog(null,"确认修改这条试题记录？","请确认 ！",JOptionPane.YES_NO_OPTION);
			if(isEdit == JOptionPane.YES_OPTION){
				if(r!=0){
					JOptionPane.showMessageDialog(this,"修改成功！");
					/*qp2.ta_quessubj.setText("");
					qp2.txt_mark.setText("");
					qp2.txt_tag.setText("");
					qp2.txt_subtag.setText("");
					qp2.ta_empty.setText("");*/
					}
				else
					JOptionPane.showMessageDialog(this,"修改失败，请检测你的信息是否有误");
			}
		}
	}
	
	//按照当前选择试题的ID，获取所有试题信息至编辑窗口
			protected void selectObjById(int selectId){
				ResultSet rs = AdminDao.select("questions",selectId);
				try{
					while(rs.next()){
						String type = rs.getString(2);
						String ques = rs.getString(3);
						String mark = rs.getString(4);
						String tag = rs.getString(5);
						String subtag = rs.getString(6);
						String opt_a = rs.getString(7);
						String opt_b = rs.getString(8);
						String opt_c= rs.getString(9);
						String opt_d = rs.getString(10);
						String ans = rs.getString(11);
				
						qp1.ta_quesobj.setText(ques);
						qp1.txt_tag.setText(tag);
						qp1.txt_subtag.setText(subtag);
						qp1.txt_mark.setText(mark);
						qp1.ta_a.setText(opt_a);
						qp1.ta_b.setText(opt_b);
						qp1.ta_c.setText(opt_c);
						qp1.ta_d.setText(opt_d);
						qp1.lbl_empty.setText("该题正确答案*  "+ans);
						
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			protected void selectSubjById(int selectId){
				ResultSet rs = AdminDao.select("questions",selectId);
				try{
					while(rs.next()){
						String type = rs.getString(2);
						String ques = rs.getString(3);
						String mark = rs.getString(4);
						String tag = rs.getString(5);
						String subtag = rs.getString(6);
						String ans = rs.getString(11);
				
						//ta_quesobj.setText(ques);
						qp2.ta_quessubj.setText(ques);
						qp2.txt_tag.setText(tag);
						qp2.txt_subtag.setText(subtag);
						qp2.txt_mark.setText(mark);
						//qp1.ta_a.setText(opt_a);
						//qp1.ta_b.setText(opt_b);
						//qp1.ta_c.setText(opt_c);
						//qp1.ta_d.setText(opt_d);
						qp2.ta_empty.setText(ans);
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		
	
	public void itemStateChanged(ItemEvent e) {
		QuesAddFrm.itemChoose = "";
		if(e.getStateChange() == e.SELECTED){
			if(e.getSource() == qp1.jrb_a)
				QuesAddFrm.itemChoose = "A";
			else if(e.getSource() == qp1.jrb_b)
				QuesAddFrm.itemChoose = "B";
			else if(e.getSource() == qp1.jrb_c)
				QuesAddFrm.itemChoose = "C";
			else if(e.getSource() == qp1.jrb_d)
				QuesAddFrm.itemChoose = "D";
		}
	}
}
