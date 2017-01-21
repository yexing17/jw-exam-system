package me.hisun.exsys.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import me.hisun.exsys.dao.AdminDao;
import me.hisun.exsys.dao.PaperDao;
import me.hisun.exsys.dao.UserDao;

public class ExamPaperChooseFrm  extends JFrame{
	JPanel jp = new JPanel();
	
	String[] headName = {"编号","试卷名","题数","总分","时长"};
	DefaultTableModel model = FrmTool.getDeModel(headName);

	JTable tbl = new JTable(model);
	
	JScrollPane jsp;

	JButton btn;
	JButton btn_query;
	protected ExamPaperChooseFrm(){
		//tbl设置
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn col0 = tbl.getColumnModel().getColumn(0);
		col0.setPreferredWidth(40);
		TableColumn col1 = tbl.getColumnModel().getColumn(1);
		col1.setPreferredWidth(133);
		TableColumn col2 = tbl.getColumnModel().getColumn(2);
		col2.setPreferredWidth(45);
		TableColumn col3 = tbl.getColumnModel().getColumn(3);
		col3.setPreferredWidth(45);
		TableColumn col4 = tbl.getColumnModel().getColumn(4);
		col4.setPreferredWidth(45);
		
		//<!--jsp设置
		jsp = new JScrollPane(tbl);//新加入一个jsp，并把之前创建的tbl放入
		jsp.setPreferredSize(new Dimension(310,125));
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//按需开启滚动条
		//-->
		
		getPapers();//获得已发布的试卷
		
		btn = new JButton("选择此卷进入考试");
		btn_query = new JButton("查询成绩");
		
		jp.add(jsp);
		jp.add(btn);
		jp.add(btn_query);
		
		this.setTitle("考卷选择");
		this.setContentPane(jp);
		this.setVisible(true);
		this.setSize(335, 215);
		this.setResizable(false);
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize() ;
		Dimension frameSize = this.getSize();
		this.setLocation((displaySize.width - frameSize.width) / 2, (displaySize.height - frameSize.height ) / 2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jp.setBorder(BorderFactory.createTitledBorder("请选择一份试卷进入考试"));
		
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				toExam();
			}
		});
		btn_query.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				toQuery();
			}
		});
	}
	protected void getPapers(){
		try{
			ResultSet rs = AdminDao.select("papers", "status", "发布考试");
			while(rs.next()){
				String str1 = String.valueOf(rs.getInt(1));
				String str2 = rs.getString(2);
				String str3 =  rs.getString(3);
				String str4 = rs.getString(4);
				String str5 = String.valueOf(rs.getInt(5));

				String[] strs =  {str1,str2,str3,str4,str5};
				model.addRow(strs);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void toExam() {//通过点选的试卷ID获得该试卷所有试题的ID
		try{
			DataExchange.selectIdPaper = Integer.parseInt(String.valueOf(tbl.getValueAt(tbl.getSelectedRow(), 0)));
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"请确认您点选了一份试卷！");
		}
		if(DataExchange.selectIdPaper != 0){
			try{
				ResultSet rs = AdminDao.select( "papers",DataExchange.selectIdPaper );
				while(rs.next()){
					DataExchange.tname = rs.getString(2);
					DataExchange.quesnum = rs.getString(3);
					DataExchange.marks = rs.getString(4);
					DataExchange.time = rs.getString(5);
					DataExchange.quesids = rs.getString(7);
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			//new ExamQuesBufferTable();
			new ExamFrm();
			this.setVisible(false);
		}
	}
	protected void toQuery(){
		try{
			DataExchange.selectIdPaper = Integer.parseInt(String.valueOf(tbl.getValueAt(tbl.getSelectedRow(), 0)));
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"请确认您点选了一份试卷！");
		}
		try{
			String marks = UserDao.selectMarks(DataExchange.selectIdPaper, DataExchange.uid);
			JOptionPane.showMessageDialog(null, "您本张试卷得分："+marks);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"请确认您点选了一份试卷！");
		}
		
	}
	
}
