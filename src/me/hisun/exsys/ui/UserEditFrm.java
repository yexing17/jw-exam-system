package me.hisun.exsys.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import me.hisun.exsys.dao.AdminDao;
import me.hisun.exsys.dao.UserDao;

//基于UserActionBaseFrm构建，生成一个管理编辑用户信息的窗体
/*
 * 调用了FrmHelp这个Frm工具类中的两个方法：
 * FrmHelp.isUserFrmEmpty(UserActionBasePane up)
 * FrmHelp.isNum(String s,String msn)
 */
public class UserEditFrm extends JFrame{
	UserActionBasePane up = new UserActionBasePane();
	
	protected UserEditFrm(){
	}
	
	protected UserEditFrm(int selectId){
		//设置窗体属性
		this.setTitle("编辑 ");
		this.setContentPane(up.getPane());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(218,315);
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize(); 
		Dimension frameSize = this.getSize();
		this.setLocation((displaySize.width - frameSize.width) / 2,(displaySize.height - frameSize.height) / 2);
		this.setResizable(false);
		this.setVisible(true);
		// 编辑组件属性
		up.lbl_tip.setText(" 仅用户组为“管理”时有管理权限  ");
		up.txt_extra.setEditable(false);
		up.txt_usergroup.setEditable(true);
		up.lbl_extra.setVisible(true);
		up.pwf_pwd.setEchoChar((char)0);
		up.lbl_extra.setPreferredSize(new Dimension(60,25));
		up.lbl_extra.setText("ID");
		up.txt_extra.setVisible(true);
		up.txt_extra.setPreferredSize(new Dimension(120,25));
		up.btn_reg.setText("完成用户信息编辑");
		up.selectById(selectId);
		up.btn_reg.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				toEditUser("编辑成功！","编辑失败，请确认你的信息是否有误");
			}
		});

		this.setSize(218,340);
	}
	
	protected void toEditUser(String info1,String info2) {
		if(FrmTool.isUserFrmEmpty(up)){//verifyEmpty()验证某些参数是否为空，具体详情见函数
			if(FrmTool.isNum(up.txt_num.getText(),"学/工 号必须为数字！请确认")){//verifyNum()验证框内的字符串是否为数字
				int r = UserDao.update(Integer.parseInt(up.txt_extra.getText()),up.txt_usergroup.getText(),up.txt_name.getText(),Integer.parseInt(up.txt_num.getText()),up.txt_id.getText(),String.valueOf(up.pwf_pwd.getPassword()),up.txt_major.getText(),up.txt_class.getText());
				if(r!=0)
					JOptionPane.showMessageDialog(this, info1);
				else
					JOptionPane.showMessageDialog(this, info2);
			}
		}
	}
	
}
