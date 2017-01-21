package me.hisun.exsys.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import me.hisun.exsys.dao.AdminDao;
import me.hisun.exsys.dao.SqlTool;

/*此窗体要调用：
RegFrm窗体
*/

public class AdminFrm extends JFrame{
	JTabbedPane tp = new JTabbedPane(JTabbedPane.TOP);

	//为每个不同的管理项初始化面板
	JPanel jp_user = new JPanel();//JPanel 默认布局FlowLayout
	JPanel jp_ques = new JPanel();
	JPanel jp_paper = new JPanel();
	JPanel jp_mark = new JPanel();//阅卷评分
	//JPanel jp_report = new JPanel();
	
	public AdminFrm(){
		super("管理面板");
		
		AdminUserPane aup = new AdminUserPane();
		AdminQuesPane aqp = new AdminQuesPane();
		AdminPaperPane  app = new AdminPaperPane();
		AdminMarkPane amp = new AdminMarkPane();
		
		tp.addTab("用户管理",aup.getPane());
		tp.addTab("试题管理",aqp.getPane());
		tp.addTab("试卷管理",app.getPane());
		tp.addTab("试卷批阅",amp.getPane());
		//-tp.addTab("成绩管理",jp_report);jp_report.add(new JLabel("苦逼撸码中，敬请期待！"));
				
		this.setContentPane(tp);//和下面的效果类似，都是把一个已存在pane设为窗体主内容
		//add(tp,BorderLayout.CENTER);	
		setVisible(true);
		setSize(669, 380);
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize() ;
		Dimension frameSize = this.getSize();
		this.setLocation((displaySize.width - frameSize.width) / 2, (displaySize.height - frameSize.height ) / 2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}
