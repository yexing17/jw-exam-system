package me.hisun.exsys.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import me.hisun.exsys.dao.AdminDao;

public class AdminPaperPane {
	JPanel jp = new JPanel();
	//获取默认模版
	String[] headName = {"ID","试卷名","题目数量","试卷总分","考试时长","试卷状态","包含题目的ID"};
	DefaultTableModel model = FrmTool.getDeModel(headName);
	JTable tbl = new JTable(model);
	
	protected AdminPaperPane(){
		//tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭自动调整列宽
		//<!--jsp设置
		JScrollPane jsp = new JScrollPane(tbl);//新加入一个jsp，并把之前创建的tbl放入
		jsp.setPreferredSize(new Dimension(620,250));
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//按需开启滚动条
		//-->
		refresh();//界面出现时自动刷新一次呈现tbl内容
		
		//加Button
		
		JButton btn_add = new JButton("新组试卷");
		JButton btn_delete = new JButton("删除试卷");
		JButton btn_edit = new JButton("查看/编辑");
		JButton btn_refresh = new JButton("刷新数据");
		JButton btn_exam = new JButton("进入考试系统");
		
		jp.add(jsp);
		jp.add(btn_add);
		jp.add(btn_delete);
		jp.add(btn_edit);
		jp.add(btn_refresh);
		jp.add(btn_exam);
		
		jp.setBorder(BorderFactory.createTitledBorder("“删除试卷”和“查看/编辑”操作须先在表格内点选要操作的项，刷新表格数据显示请点击“刷新数据”"));
		
		btn_add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				PaperAddFrm paf = new  PaperAddFrm();//调用PaperAdd窗体
				JOptionPane.showMessageDialog(paf, "因为该功能尚未完善，存在一些BUG,使用者应注意以下几点：\n①只支持从题库添加试题，不支持移除已选题\n②请勿重复添加ID相同的试题，这会造成统分和考试系统出问题\n③已选题自动统计和已选题表格中实际数量不符\n如果出现了上述相关问题或者其他错误，请关闭该组卷窗口重开","使用须知",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		btn_delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try{
					DataExchange.selectIdPaper = Integer.parseInt(String.valueOf(tbl.getValueAt(tbl.getSelectedRow(), 0)));
					FrmTool.toDelete("papers", DataExchange.selectIdPaper, "确认删除当前选中试卷？");
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"请确认您点选了一行试卷信息！");
				}
			}
		});
		
		btn_edit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try{
					DataExchange.selectIdPaper = Integer.parseInt(String.valueOf(tbl.getValueAt(tbl.getSelectedRow(), 0)));
					PaperEditFrm pef = new  PaperEditFrm(DataExchange.selectIdPaper );//调用PaperEdit窗体
					JOptionPane.showMessageDialog(pef, "因为该功能尚未完善，存在一些BUG,使用者应注意以下几点：\n①只支持从题库添加试题，不支持移除已选题\n②请勿重复添加ID相同的试题，这会造成统分和考试系统出问题\n③已选题自动统计和已选题表格中实际数量不符\n如果出现了上述相关问题或者其他错误，请关闭该修改信息窗口重开","使用须知",JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null,"请确认您点选了一行试卷信息！");
				}
			}
		});
		
		btn_refresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
		});
		
		btn_exam.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				ExamPaperChooseFrm epc = new ExamPaperChooseFrm();
				epc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
	}
	
	private void refresh() {
		int numR = model.getRowCount();
		while(numR>0){
			model.removeRow(0);
			numR--;
		}
		try{
			ResultSet rs = AdminDao.select("papers");
			while(rs.next()){
				String str1 = String.valueOf(rs.getInt(1));
				String str2 = rs.getString(2);
				String str3 =  rs.getString(3);
				String str4 = rs.getString(4);
				String str5 = String.valueOf(rs.getInt(5));
				String str6 = rs.getString(6);
				String str7 = rs.getString(7);
				String[] strs =  {str1,str2,str3,str4,str5,str6,str7};
				model.addRow(strs);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected JPanel getPane(){
		return jp;
	}
}
