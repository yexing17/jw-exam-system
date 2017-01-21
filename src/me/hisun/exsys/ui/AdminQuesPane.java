package me.hisun.exsys.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import me.hisun.exsys.dao.AdminDao;

public class AdminQuesPane {
	JButton btn_add;
	JButton btn_delete;
	JButton btn_edit;
	JButton btn_refresh; 
	JPanel jp;
	JScrollPane jsp ;
	//获取默认模版
	String[] headName = {"ID","类型","题目","分值","主标签","次标签","选项A","选项B","选项C","选项D","答案"};
	DefaultTableModel model = FrmTool.getDeModel(headName);
	JTable tbl = new JTable(model);
	
	protected AdminQuesPane(){
		jp = new JPanel();
	    //tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭自动调整列宽
		//jsp的设置
		jsp = new JScrollPane(tbl);
		jsp.setPreferredSize(new Dimension(620,250));
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		//jsp完成
		//打开窗体触发Table和数据库之间的刷新操作
		refresh();
		
		
		btn_add = new JButton("添加试题");
		btn_delete = new JButton("删除试题");
		btn_edit = new JButton("查看/编辑");
		btn_refresh = new JButton("刷新数据");

		jp.add(jsp);
		jp.add(btn_add);
		jp.add(btn_delete);
		jp.add(btn_edit);
		jp.add(btn_refresh);
		
		jp.setBorder(BorderFactory.createTitledBorder("“删除试题”和“查看/编辑”操作须先在表格内点选要操作的项，刷新表格数据显示请点击“刷新数据” "));
		
		btn_add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				new  QuesAddFrm();//调用UserAdd窗体
			}
		});
		
		btn_delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try{
					DataExchange.selectIdQues = Integer.parseInt(String.valueOf(tbl.getValueAt(tbl.getSelectedRow(), 0)));
					FrmTool.toDelete("questions", DataExchange.selectIdQues, "删除改题会导致含有该题的试卷失效\n确认删除当前选中题目？");
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"请确认您点选了一行用户信息！");
				}
			}
		});
		
		btn_edit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try{
					DataExchange.selectIdQues = Integer.parseInt(String.valueOf(tbl.getValueAt(tbl.getSelectedRow(), 0)));
					new QuesEditFrm(DataExchange.selectIdQues);//调用UserEdit窗体并把当前选中行的ID传去
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"请确认您点选了一行试题信息！");
				}
			}
		});
		
		btn_refresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
		});
		//jp_user 结束
	}
	public void refresh(){
		int numR = model.getRowCount();
		while(numR>0){
			model.removeRow(0);
			numR--;
		}
		try {
			ResultSet rs = AdminDao.select("questions");
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
