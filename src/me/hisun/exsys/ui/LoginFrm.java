package me.hisun.exsys.ui;

import me.hisun.exsys.dao.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;


public class LoginFrm extends JFrame{
	
	JLabel lbl_id;
	JLabel lbl_pwd;

	JTextField txt_id;
	JPasswordField pwd;
	
	JButton btn_login;
	JButton btn_reg;
	
	public LoginFrm(){
		super("考试系统登录");
		lbl_id = new JLabel("用户ID");
		lbl_pwd = new JLabel("密码");
		
		txt_id = new JTextField();
		pwd = new JPasswordField();
		
		btn_login = new JButton("登录");
		btn_reg = new JButton("注册");

		JPanel jp = (JPanel)this.getContentPane();
		jp.setLayout(new FlowLayout());
		
		jp.add(lbl_id);
		lbl_id.setPreferredSize(new Dimension(50,25));
		jp.add(txt_id);
		txt_id.setPreferredSize(new Dimension(120,25));
				
		jp.add(lbl_pwd);
		lbl_pwd.setPreferredSize(new Dimension(50,25));
		jp.add(pwd);
		pwd.setPreferredSize(new Dimension(120,25));
		
		jp.add(btn_login);
		btn_login.setPreferredSize(new Dimension(80,23));
		jp.add(btn_reg);
		btn_reg.setPreferredSize(new Dimension(80,23));
		
		btn_login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				isLogin();//登录验证
			}
		});
		
		btn_reg.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				new UserRegFrm();//调用UserReg窗体
			}
		});
		
		this.setVisible(true);
		this.setSize(222,128);
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize() ;
		Dimension frameSize = this.getSize();
		this.setLocation((displaySize.width - frameSize.width) / 2, (displaySize.height - frameSize.height ) / 2);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	protected void isLogin() {
		DataExchange.uid = txt_id.getText();
		ResultSet rs = AdminDao.select("name", "users", "login_id",txt_id.getText());
		try{
			while(rs.next()){
				DataExchange.uname = rs.getString(1);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		boolean islogin = UserDao.isLogin(DataExchange.uid, String.valueOf(pwd.getPassword()));
		if(islogin){
			if(UserDao.isAdmin(DataExchange.uid,"管理")){
				//OptionPane.showMessageDialog(this, "管理登录成功");
				String[] options = {"管理界面","考试界面"};
				int opt = JOptionPane.showOptionDialog(null, "管理登录成功", "请选择你要登录的界面" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "管理界面");
				if(opt == JOptionPane.YES_OPTION)
					toAdminFrm();
				else if(opt == JOptionPane.NO_OPTION)
					toPaperChooseFrm();
			}else{
				JOptionPane.showMessageDialog(this, "用户登录成功");
				toPaperChooseFrm();
			}			
		}			
		else
			JOptionPane.showMessageDialog(this, "用户名或密码有误");
}

	private void toAdminFrm() {
		new AdminFrm();//调用Admin窗体
		this.setVisible(false);
	}
	
	private void toPaperChooseFrm() {
		new ExamPaperChooseFrm();
		this.setVisible(false);
	}
	
	public static void main(String[] args) {
		//将界面风格设为当前系统风格
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		LoginFrm loginfrm = new LoginFrm();
	}
}