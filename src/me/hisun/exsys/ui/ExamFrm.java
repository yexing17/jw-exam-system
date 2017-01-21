package me.hisun.exsys.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import me.hisun.exsys.dao.AdminDao;
import me.hisun.exsys.dao.ExamDao;

public class ExamFrm extends JFrame implements ItemListener{
	ExamPane ep = new ExamPane();
	

	
	protected ExamFrm(){
		new ExamQuesBufferTable();//生成不可见的缓冲数据表
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(DataExchange.tname);//从ExamPaperChooseFrm中获得
		this.setSize(450,499);
		this.setContentPane(ep.getPane());
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize(); 
		Dimension frameSize = this.getSize();
		this.setLocation((displaySize.width - frameSize.width) / 2,(displaySize.height - frameSize.height) / 2);
		//this.setResizable(false);
		this.setVisible(true);
		
		//将储存答案的数组String[] ans赋值为""方便操作
		for(int i = 0 ; i < Integer.parseInt(DataExchange.quesnum) ; i++){
			DataExchange.ans[i] = "";
			DataExchange.mark[i] = "0";
		}
		
		resetPane();
		
		ep.jrb_a.addItemListener(this);
		ep.jrb_b.addItemListener(this);
		ep.jrb_c.addItemListener(this);
		ep.jrb_d.addItemListener(this);
		ep.btn_last.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(FrmTool.getFromTblQues(1).equals("主观题"))//文本域存答案到数组
					DataExchange.ans[DataExchange.examQuesId] = ep.ta_ans.getText();
				//System.out.println(DataExchange.ans[DataExchange.examQuesId]);
				toLast();
			}
		});
		
		ep.btn_next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(FrmTool.getFromTblQues(1).equals("主观题"))//文本域存答案到数组
					DataExchange.ans[DataExchange.examQuesId] = ep.ta_ans.getText();
				//System.out.println(DataExchange.ans[DataExchange.examQuesId]);
				toNext();
			}
		});
		
		ep.btn_submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(FrmTool.getFromTblQues(1).equals("主观题"))//文本域存答案到数组
					DataExchange.ans[DataExchange.examQuesId] = ep.ta_ans.getText();
				//System.out.println(DataExchange.ans[DataExchange.examQuesId]);
				toSubmit();
			}
		});
	}

	protected void toLast() {
		if(DataExchange.examQuesId>0){
			DataExchange.examQuesId--;
			resetPane();
		}
		else{
			JOptionPane.showMessageDialog(null,"没有上一题了!");
		}
	}
	
	protected void toNext() {
		if(DataExchange.examQuesId<(Integer.parseInt(DataExchange.quesnum)-1)){
			DataExchange.examQuesId++;
			resetPane();
		}
		else{
			JOptionPane.showMessageDialog(null,"没有下一题了!");
		}
	}


	protected void toSubmit() {
		//检测是否有试题未写
		boolean flag = true;//true 为非空
		for(int i = 0 ; i < Integer.parseInt(DataExchange.quesnum) ; i++){
			if(DataExchange.ans[i].equals("")){
				flag = false;//false为空
				break;
			}
		}
				
		if(flag){
			int isDelete = JOptionPane.showConfirmDialog(null, "不用再检测一下了吗，确认提交？","请确认 ！",JOptionPane.YES_NO_OPTION);
			if(isDelete == JOptionPane.YES_OPTION){
				submit();
			}
		}
		else{
			int isDelete = JOptionPane.showConfirmDialog(null, "你还有试题未完成，确认提交？","请确认 ！",JOptionPane.YES_NO_OPTION);
			if(isDelete == JOptionPane.YES_OPTION){
				submit();
			}
		}	
	}

	private void submit() {
		//自动给客观题打分
		for(int i = 0 ; i < Integer.parseInt(DataExchange.quesnum) ; i++){
			if(FrmTool.getFromTblQues(i,1).equals("客观题")){
				if(FrmTool.getFromTblQues(i, 10).equals(DataExchange.ans[i]))
					DataExchange.mark[i] = FrmTool.getFromTblQues(i, 3);
			}
		}
		
		//往数据库里加入每道题答题数据的提交过程
		int r1 = 0;
		for(int i = 0 ; i < Integer.parseInt(DataExchange.quesnum) ; i++){
			r1 = ExamDao.insertAnsQues(DataExchange.uid, DataExchange.selectIdPaper, Integer.parseInt(FrmTool.getFromTblQues(i,0)),FrmTool.getFromTblQues(i,2), FrmTool.getFromTblQues(i,10),DataExchange.ans[i],DataExchange.mark[i]);
			if(r1 == 0){
				JOptionPane.showMessageDialog(null, "提交失败！");
				break;
			}
		}
		
		//往数据库里添加含有已答题ID组的答题卷信息,按uid和tid将指定的已答题的已答题id全部取出来组成一个字符串
		DataExchange.ansids = "";
		ResultSet rs = ExamDao.select_ans_ids(DataExchange.uid, DataExchange.selectIdPaper);//这个uid竟然是登陆用的ID...不是序号，反正唯一...也行
		try {
			while(rs.next()){
				if(DataExchange.ansids.equals("")){
					DataExchange.ansids = DataExchange.ansids + rs.getInt(1);
				}
				else{
					DataExchange.ansids = DataExchange.ansids+","+rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//把答题卷的信息导入数据库的ans_papers表
		//DataExchange.selectIdPaper 从ExamPaperChooseFrm取得
		//DataExchange.tname 从ExamPaperChooseFrm取得
		//DataExchange.uname 从LoginFrm取得
		//DataExchange.uid 从LoginFrm取得
		//ststus一律设为教师未评卷
		
		int r2 = 0;
		r2 = ExamDao.insertAnsPaper(DataExchange.selectIdPaper, DataExchange.tname,DataExchange.quesnum, DataExchange.marks,DataExchange.uid, DataExchange.uname, "未评卷", DataExchange.ansids);
		if(r2 == 0){
			JOptionPane.showMessageDialog(null, " 提交失败！");
		}
		//如果导入数据过程执行没中断，则弹出导入成功对话框
		if(r1 != 0 && r2 != 0){
			JOptionPane.showMessageDialog(null, "提交成功！");
		}
	}

	protected void resetPane(){
		ep.lbl_num.setText("试题总数："+DataExchange.quesnum);
		ep.lbl_progress.setText("进度："+(DataExchange.examQuesId+1)+"/"+DataExchange.quesnum);
		if(FrmTool.getFromTblQues(1).equals("客观题")){
			ep.ta_quesobj.setText(FrmTool.getFromTblQues( 2));
			TitledBorder tb =BorderFactory.createTitledBorder("第"+(DataExchange.examQuesId+1)+"题（"+FrmTool.getFromTblQues(3)+"分）");
			tb.setTitleJustification(TitledBorder.RIGHT);
			ep.jsp_quesobj.setBorder(tb);
			//<!--只在当前题目未选是刷新ButtonGroup选项区，已有答案则选择答案为选项
			if(DataExchange.ans[DataExchange.examQuesId].equals("")){
				ep.bg.clearSelection();
			}else if(DataExchange.ans[DataExchange.examQuesId].equals("A")){
				//ep.jrb_a = new JRadioButton("A",true);
				ep.jrb_a.setSelected(true);
			}else if(DataExchange.ans[DataExchange.examQuesId].equals("B")){
				//ep.jrb_b = new JRadioButton("B",true);
				ep.jrb_b.setSelected(true);
			}else if(DataExchange.ans[DataExchange.examQuesId].equals("C")){
				//ep.jrb_c = new JRadioButton("C",true);
				ep.jrb_c.setSelected(true);
			}else if(DataExchange.ans[DataExchange.examQuesId].equals("D")){
				//ep.jrb_d = new JRadioButton("D",true);
				ep.jrb_d.setSelected(true);
			}//-->
			ep.jsp_quessubj.setVisible(false);
			ep.ta_a.setText(FrmTool.getFromTblQues(6));
			ep.ta_b.setText(FrmTool.getFromTblQues(7));
			ep.ta_c.setText(FrmTool.getFromTblQues(8));
			ep.ta_d.setText(FrmTool.getFromTblQues(9));
			ep.jsp_ans.setVisible(false);
			ep.jsp_quesobj.setVisible(true);
			ep.jsp_a.setVisible(true);
			ep.jsp_b.setVisible(true);
			ep.jsp_c.setVisible(true);
			ep.jsp_d.setVisible(true);
			ep.jrb_a.setVisible(true);
			ep.jrb_b.setVisible(true);
			ep.jrb_c.setVisible(true);
			ep.jrb_d.setVisible(true);
		}else if(FrmTool.getFromTblQues(1).equals("主观题")){
			ep.ta_quessubj.setText(FrmTool.getFromTblQues(2));
			TitledBorder tb =BorderFactory.createTitledBorder("第"+(DataExchange.examQuesId+1)+"题（"+FrmTool.getFromTblQues(3)+"分）");
			tb.setTitleJustification(TitledBorder.RIGHT);
			ep.jsp_quessubj.setBorder(tb);	//显示在右边的边界标签
			ep.jsp_quessubj.setVisible(true);
			ep.jsp_ans.setVisible(true);
			ep.jsp_quesobj.setVisible(false);
			ep.jsp_a.setVisible(false);
			ep.jsp_b.setVisible(false);
			ep.jsp_c.setVisible(false);
			ep.jsp_d.setVisible(false);
			ep.jrb_a.setVisible(false);
			ep.jrb_b.setVisible(false);
			ep.jrb_c.setVisible(false);
			ep.jrb_d.setVisible(false);
		}
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == e.SELECTED){
			if(e.getSource() == ep.jrb_a)
				DataExchange.ans[DataExchange.examQuesId] = "A";
			else if(e.getSource() == ep.jrb_b)
				DataExchange.ans[DataExchange.examQuesId] = "B";
			else if(e.getSource() == ep.jrb_c)
				DataExchange.ans[DataExchange.examQuesId] = "C";
			else if(e.getSource() == ep.jrb_d)
				DataExchange.ans[DataExchange.examQuesId] = "D";
		}
	
	}
}
