package me.hisun.exsys.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import me.hisun.exsys.dao.MarkDao;

/*
 * 用这个正式使用时不显示的虚拟表格，按照选取答题卷表中的ans_ids拉取
 * 已答题至一个JTable存储所有已答题信息作为缓冲区，避免过多的访问数据库
 * 
 */
public class AnsPapersBufferTable{
	JPanel jp = new JPanel();
	protected static String[] headName = {"ID","用户名","试卷ID","试题ID","试题","试题参考答案","学生作答","得分"};
	protected static DefaultTableModel model = FrmTool.getDeModel(headName);
	protected static JTable tbl = new JTable(model);
		
	protected AnsPapersBufferTable(){
		/*
		JScrollPane jsp;//调试时使用...
	    //tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭自动调整列宽
		//jsp的设置
		jsp = new JScrollPane(tbl);
		jsp.setPreferredSize(new Dimension(620,250));
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		//jsp完成
		//打开窗体触发Table和数据库之间的刷新操作
		jp.add(jsp);
		this.setContentPane(jp);
		this.setSize(466,620);
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize(); 
		Dimension frameSize = this.getSize();
		this.setLocation((displaySize.width - frameSize.width) / 2,(displaySize.height - frameSize.height) / 2);
		//this.setResizable(false);
		this.setVisible(true);
		
		
		*/
		try{
			ResultSet rs = MarkDao.select();
			while(rs.next()){
				String str1 = String.valueOf(rs.getInt(1));
				String str2 = rs.getString(2);
				String str3 = rs.getString(3);
				String str4 = rs.getString(4);
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
}
