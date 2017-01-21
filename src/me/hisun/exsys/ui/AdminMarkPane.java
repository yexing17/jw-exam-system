package me.hisun.exsys.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import me.hisun.exsys.dao.AdminDao;

public class AdminMarkPane {
	JPanel jp = new JPanel();
	JScrollPane jsp ;
	JButton btn_mark ;
	JButton btn_refresh;
	
	String[] headName = {"ID","试卷ID","试卷名","试卷题数","卷面总分","考生用户名","考生姓名","评卷状态","得分","答案ID组"};
	DefaultTableModel model = FrmTool.getDeModel(headName);
	JTable tbl = new JTable(model);
	
	AdminMarkPane(){
	    //tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭自动调整列宽
		//jsp的设置
		jsp = new JScrollPane(tbl);
		jsp.setPreferredSize(new Dimension(620,250));
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		//jsp完成
		//打开窗体触发Table和数据库之间的刷新操作
		refresh();
		
		btn_mark = new JButton("批阅试卷");
		btn_refresh = new JButton("刷新数据");
		
		jp.add(jsp);
		jp.add(btn_mark);
		jp.add(btn_refresh);
		jp.setBorder(BorderFactory.createTitledBorder("“批阅试卷”操作须先在表格内点选要操作的项，刷新表格数据显示请点击“刷新数据” "));
		
		btn_mark.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try{
					DataExchange.selectIdMark = Integer.parseInt(String.valueOf(tbl.getValueAt(tbl.getSelectedRow(), 0)));
					//new AnsPapersBufferTable();//缓冲板测试显示
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null,"请确认您点选了一行答卷信息！");
				}
				
				if(DataExchange.selectIdMark != 0){
					try{
						ResultSet rs = AdminDao.select( "ans_papers",DataExchange.selectIdMark);
						while(rs.next()){
							DataExchange.quesnum =  rs.getString(4);
							DataExchange.papermarks = rs.getString(5);
							DataExchange.ansids = rs.getString(10);
						}
					}
					catch(SQLException e){
						e.printStackTrace();
					}
				}
				new MarkFrm();//调用Mark窗体
			}
		});
		
		btn_refresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
		});
	}
	
	public void refresh(){
		int numR = model.getRowCount();
		while(numR>0){
			model.removeRow(0);
			numR--;
		}
		try {
			ResultSet rs = AdminDao.select("ans_papers");
			while(rs.next()){
				String str1 = String.valueOf(rs.getInt(1));
				String str2 = String.valueOf(rs.getInt(2));
				String str3 = rs.getString(3);
				String str4 = rs.getString(4);
				String str5 = rs.getString(5);
				String str6 = rs.getString(6);
				String str7 = rs.getString(7);
				String str8 = rs.getString(8);
				String str9 = rs.getString(9);
				String str10 = rs.getString(10);
				String[] strs = {str1,str2,str3,str4,str5,str6,str7,str8,str9,str10}; 
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
