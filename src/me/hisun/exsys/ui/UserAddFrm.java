package me.hisun.exsys.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import me.hisun.exsys.dao.UserDao;

//基于UserActionBasePane构建，生成一个用于管理添加用户的窗体
/*
 * 调用了FrmHelp这个Frm工具类中的两个方法：
 * FrmHelp.isUserFrmEmpty(UserActionBasePane up)
 * FrmHelp.isNum(String s,String msn)
 */
public class UserAddFrm extends JFrame{
	UserActionBasePane up = new UserActionBasePane();
	
	//JPanel jp = new UserActionBasePane().getPane();
	protected UserAddFrm(){
		//设置窗体属性
		this.setTitle("添加用户 ");
		//this.add(up.getPane(),BorderLayout.CENTER);
		this.setContentPane(up.getPane());//和上面的效果类似
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(218,315);
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize(); 
		Dimension frameSize = this.getSize();
		this.setLocation((displaySize.width - frameSize.width) / 2,(displaySize.height - frameSize.height) / 2);
		this.setResizable(false);
		this.setVisible(true);
		
		up.lbl_tip.setText("仅用户组为“管理”时有管理权限");
		up.txt_usergroup.setEditable(true);
		up.txt_usergroup.setText("管理");
		up.pwf_pwd.setEchoChar((char)0);
		up.btn_reg.setText("添加用户");
		up.btn_reg.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				toAddUser("添加成功！","添加失败，请检测你的信息是否有误");
			}
		});

	}
	
	//可设置 注册/加入 用户信息成功或失败的提示语
	protected void toAddUser(String info1,String info2) {
		if(FrmTool.isUserFrmEmpty(up)){//isEmpty()验证某些参数是否为空，具体详情见函数
			if(FrmTool.isNum(up.txt_num.getText(),"学/工 号必须为数字！请确认")){//verifyNum()验证框内的字符串是否为数字
				int r = UserDao.insert(up.txt_usergroup.getText(),up.txt_name.getText(),Integer.parseInt(up.txt_num.getText()),up.txt_id.getText(),String.valueOf(up.pwf_pwd.getPassword()), up.txt_major.getText(),up.txt_class.getText());
				if(r!=0){
					JOptionPane.showMessageDialog(this, info1);
					up.txt_name.setText("");
					up.txt_num.setText("");
					up.txt_id.setText("");
					up.pwf_pwd.setText("");
					up.txt_major.setText("");
					up.txt_class.setText("");
					}
				else
					JOptionPane.showMessageDialog(this, info2);
			}
		}
	}
}
