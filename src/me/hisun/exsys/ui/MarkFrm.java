package me.hisun.exsys.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import me.hisun.exsys.dao.MarkDao;

public class MarkFrm extends JFrame {
	
	MarkPane mp = new MarkPane();
	
	protected MarkFrm(){
		this.setTitle("评卷");
		new AnsPapersBufferTable();//生成不可见的缓冲数据表,测试阶段设为可见
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle(DataExchange.tname);//从ExamPaperChooseFrm中获得
		this.setSize(450,530);
		this.setContentPane(mp.getPane());
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize(); 
		Dimension frameSize = this.getSize();
		this.setLocation((displaySize.width - frameSize.width) / 2,(displaySize.height - frameSize.height) / 2);
		this.setResizable(false);
		this.setVisible(true);
		
		//将储存试题得分的String[] mark和试题卷面分String[] papermark的赋值为已有值
		for(int i = 0 ; i < Integer.parseInt(DataExchange.quesnum) ; i++){
			DataExchange.mark[i] = String.valueOf(FrmTool.getFromTblAns(i, 7));
			DataExchange.papermark[i] = MarkDao.selectMark(Integer.parseInt(FrmTool.getFromTblAns(i, 3)));
		}
		
		resetPane();
		
		
		mp.btn_mark.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				DataExchange.mark[DataExchange.markQuesId] = mp.txt_mark.getText();
				refreshMarks();
			}
		});
		
		mp.btn_last.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				DataExchange.mark[DataExchange.markQuesId] = mp.txt_mark.getText();
				refreshMarks();
				toLast();
			}
		});
		mp.btn_next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				DataExchange.mark[DataExchange.markQuesId] = mp.txt_mark.getText();
				refreshMarks();
				toNext();
			}
		});
		
		mp.btn_submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				DataExchange.mark[DataExchange.markQuesId] = mp.txt_mark.getText();
				refreshMarks();
				int isDelete = JOptionPane.showConfirmDialog(null, "不用再检测一下了吗，确认提交？","请确认 ！",JOptionPane.YES_NO_OPTION);
				if(isDelete == JOptionPane.YES_OPTION){
					toSubmit();
				}
			}
		});
		
	}
	


	protected void toLast() {
		if(DataExchange.markQuesId>0){
			DataExchange.markQuesId--;
			resetPane();
		}
		else{
			JOptionPane.showMessageDialog(null,"没有上一题了!");
		}
	}



	protected void toNext() {
		if(DataExchange.markQuesId<(Integer.parseInt(DataExchange.quesnum)-1)){
			DataExchange.markQuesId++;
			resetPane();
		}
		else{
			JOptionPane.showMessageDialog(null,"没有下一题了!");
		}
	}



	protected void toSubmit() {
		int r = MarkDao.updateMarks("已阅", DataExchange.marks,DataExchange.selectIdMark);
		if(r != 0){
			JOptionPane.showMessageDialog(null,"评分成功！");
		}
	}



	protected void refreshMarks(){
		DataExchange.marks = "0";
		for(int i = 0 ; i < Integer.parseInt(DataExchange.quesnum) ; i++ ){
			DataExchange.marks = String.valueOf(Integer.parseInt(DataExchange.marks) + Integer.parseInt(DataExchange.mark[i]));
		}
		mp.lbl_marks.setText("当前分数："+DataExchange.marks+"/卷面总分："+DataExchange.papermarks);
	}
	
	protected void resetPane(){
		refreshMarks();
		//mp.lbl_marks.setText("当前分数："+DataExchange.marks+"/卷面总分："+DataExchange.papermarks);
		mp.lbl_num.setText("当前第"+(DataExchange.markQuesId+1)+"题/试题总数："+DataExchange.quesnum);
		mp.ta_ques.setText(FrmTool.getFromTblAns(4));
		mp.ta_ans.setText(FrmTool.getFromTblAns(6));
		mp.ta_rans.setText(FrmTool.getFromTblAns(5));
		TitledBorder tb =BorderFactory.createTitledBorder("第"+(DataExchange.markQuesId+1)+"题（"+DataExchange.papermark[DataExchange.markQuesId]+"分）");
		tb.setTitleJustification(TitledBorder.RIGHT);
		mp.jsp_ques.setBorder(tb);
		mp.txt_mark.setText(DataExchange.mark[DataExchange.markQuesId]);
	}
}
