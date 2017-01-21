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

public class AdminUserPane {
	
	JPanel jp = new JPanel();
	//获得一个table的DefaultModel并用于Table初始化
	String[] headName = {"ID","用户组","姓名","学/工 号","登录名","登录密码","所学/所授 专业","班级（学生填）"};
	DefaultTableModel model = FrmTool.getDeModel(headName);
	JTable tbl = new JTable(model);
	protected AdminUserPane(){
		//tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//jsp_user的设置
		JScrollPane jsp = new JScrollPane(tbl);
		jsp.setPreferredSize(new Dimension(620,250));
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		//jsp_user完成
		
		//打开窗体触发Table和数据库之间的刷新操作
		refreshUser();
		//button
		
		JButton btn_add = new JButton("添加用户");
		JButton btn_delete = new JButton("删除用户");
		JButton btn_edit = new JButton("查看/编辑");
		JButton btn_refresh = new JButton("刷新数据");

		
		jp.add(jsp);
		jp.add(btn_add);
		jp.add(btn_delete);
		jp.add(btn_edit);
		jp.add(btn_refresh);
		
		
		jp.setBorder(BorderFactory.createTitledBorder("“删除用户”和“查看/编辑”操作须先在表格内点选要操作的项，刷新表格数据显示请点击“刷新数据”"));
		//添加监听
		btn_add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				new UserAddFrm();//调用UserAdd窗体
			}
		});
		
		btn_delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try{
					DataExchange.selectIdUser = Integer.parseInt(String.valueOf(tbl.getValueAt(tbl.getSelectedRow(), 0)));
					FrmTool.toDelete("users", DataExchange.selectIdUser,"确认删除当前选中用户？");
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"请确认您点选了一行用户信息！");
				}
			}
		});
		
		btn_edit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try{
					DataExchange.selectIdUser = Integer.parseInt(String.valueOf(tbl.getValueAt(tbl.getSelectedRow(), 0)));
					new UserEditFrm(DataExchange.selectIdUser);//调用UserEdit窗体并把当前选中行的ID传去
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"请确认您点选了一行用户信息！");
				}
			}
		});
		
		btn_refresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				refreshUser();
			}
		});
	}
	
	//方法
	public void refreshUser(){
		int numR = model.getRowCount();
		while(numR>0){
			model.removeRow(0);
			numR--;
		}
		try {
			ResultSet rs = AdminDao.select("users");
			while(rs.next()){
				String str1 = String.valueOf(rs.getInt(1));
				String str2 = rs.getString(2);
				String str3 = rs.getString(3);
				String str4 = String.valueOf(rs.getLong(4));
				String str5 = rs.getString(5);
				String str6 = rs.getString(6);
				String str7 = rs.getString(7);
				String str8 = rs.getString(8);
				String[] strs = {str1,str2,str3,str4,str5,str6,str7,str8}; 
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