package me.hisun.exsys.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import me.hisun.exsys.dao.AdminDao;

/*
 包含该类公用构造函数的JFrame类 
 普通注册用户窗:UserRegFrm
 管理添加用户窗:UseAddFrm
 管理编辑用户窗 :UseEditFrm
上述类一些较为复杂的方法也写在这里：
boolean isEmpty()//已经改名isUserFrmEmpty()，已经转到FrmTool
boolean isNum(String s)//已经转到FrmTool
void toAddUser(String info1,String info2)//已经转到FrmTool
void toEditUser(String info1,String info2)//已经转到FrmTool
void toDeleteUser()//已经改名toDelete()，转到FrmTool
void selectById(int selectId)
 */
public class UserActionBasePane{
	JPanel jp;
	
	JLabel lbl_tip;
	JLabel lbl_usergroup;
	JLabel lbl_name;
	JLabel lbl_num;
	JLabel lbl_id;
	JLabel lbl_pwd;
	JLabel lbl_major;
	JLabel lbl_class;
	JLabel lbl_extra;

	JTextField txt_usergroup;
	JTextField txt_name;
	JTextField txt_num;
	JTextField txt_id;
	JPasswordField pwf_pwd;
	JTextField txt_major;
	JTextField txt_class;
	JTextField txt_extra;
	
	JButton btn_reg;
	
	//主体结构和大部分相同内容
	protected UserActionBasePane(){
		jp = new JPanel();
		lbl_tip = new JLabel();
		lbl_usergroup = new JLabel("用户组*");
		lbl_name = new JLabel("姓名*");
		lbl_num = new JLabel("学/工 号*");
		lbl_id = new JLabel("登录名*");
		lbl_pwd = new JLabel("密码*");
		lbl_major = new JLabel("专业");
		lbl_class = new JLabel("班级");
		lbl_extra = new JLabel();

		txt_usergroup = new JTextField();
		txt_name = new JTextField("");
		txt_num = new JTextField("");
		txt_id = new JTextField("");
		pwf_pwd = new JPasswordField("");
		txt_major = new JTextField("");
		txt_class = new JTextField("");
		txt_extra = new JTextField();
		
		btn_reg = new JButton();
		
		jp.setLayout(new FlowLayout());
		
		jp.add(lbl_tip);
		
		jp.add(lbl_extra);
		jp.add(txt_extra);
		lbl_extra.setVisible(false);
		txt_extra.setEditable(false);
		txt_extra.setVisible(false);
		
		jp.add(lbl_usergroup);
		lbl_usergroup.setPreferredSize(new Dimension(60,25));
		jp.add(txt_usergroup);
		txt_usergroup.setPreferredSize(new Dimension(120,25));
		txt_usergroup.setEditable(false);
		
		jp.add(lbl_name);
		lbl_name.setPreferredSize(new Dimension(60,25));
		jp.add(txt_name);
		txt_name.setPreferredSize(new Dimension(120,25));
		
		jp.add(lbl_num);
		lbl_num.setPreferredSize(new Dimension(60,25));
		jp.add(txt_num);
		txt_num.setPreferredSize(new Dimension(120,25));
		
		jp.add(lbl_id);
		lbl_id.setPreferredSize(new Dimension(60,25));
		jp.add(txt_id);
		txt_id.setPreferredSize(new Dimension(120,25));
				
		jp.add(lbl_pwd);
		lbl_pwd.setPreferredSize(new Dimension(60,25));
		jp.add(pwf_pwd);
		pwf_pwd.setPreferredSize(new Dimension(120,25));
		
		jp.add(lbl_major);
		lbl_major.setPreferredSize(new Dimension(60,25));
		jp.add(txt_major);
		txt_major.setPreferredSize(new Dimension(120,25));
		
		jp.add(lbl_class);
		lbl_class.setPreferredSize(new Dimension(60,25));
		jp.add(txt_class);
		txt_class.setPreferredSize(new Dimension(120,25));
		
		jp.add(btn_reg);
		btn_reg.setPreferredSize(new Dimension(170,26));
		
		//窗体属性设置
		/*this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(218,315);
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize(); 
		Dimension frameSize = this.getSize();
		this.setLocation((displaySize.width - frameSize.width) / 2,(displaySize.height - frameSize.height) / 2);
		this.setResizable(false);
		this.setVisible(true);*/
		
	}

	
	//验证下列框内数据是否为空，并返回boolean值，是空还会弹出警告框
	/*protected boolean isEmpty(){
		boolean flag = true;
		if(((((txt_usergroup.getText().equals("") || txt_name.getText().equals("")) || txt_num.getText().equals("")) || txt_id.getText().equals("")) || String.valueOf(pwf_pwd.getPassword()).equals("")))
			{
			JOptionPane.showMessageDialog(null,"带  * 星号 的项为必填项！请填写完整","错误",JOptionPane.ERROR_MESSAGE);
			flag = false;
			}
		return flag;
	}
	
	//验证一个字符串是否是整型数字，并返回boolean值，不是数字还会弹出警告框
	protected boolean isNum(String s){
		boolean flag = true;
		try{
			int nums = Integer.parseInt(s);
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null," 学/工 号必须为数字！请确认","错误",JOptionPane.ERROR_MESSAGE);
			flag = false;
		}
		return flag;
	}*/
	
	//可设置 注册/加入 用户信息成功或失败的提示语
	/*protected void toAddUser(String info1,String info2) {
		if(isEmpty()){//verifyEmpty()验证某些参数是否为空，具体详情见函数
			if(isNum(txt_num.getText())){//verifyNum()验证框内的字符串是否为数字
				int r = UserDao.insert(txt_usergroup.getText(),txt_name.getText(),Integer.parseInt(txt_num.getText()),txt_id.getText(),String.valueOf(pwf_pwd.getPassword()), txt_major.getText(),txt_class.getText());
				if(r!=0){
					JOptionPane.showMessageDialog(this, info1);
					txt_name.setText("");
					txt_num.setText("");
					txt_id.setText("");
					pwf_pwd.setText("");
					txt_major.setText("");
					txt_class.setText("");
					}
				else
					JOptionPane.showMessageDialog(this, info2);
			}
		}
	}*/
	//编辑用户，有提示成功或失败的信息
	/*protected void toEditUser(String info1,String info2) {
		if(isEmpty()){//verifyEmpty()验证某些参数是否为空，具体详情见函数
			if(isNum(txt_num.getText())){//verifyNum()验证框内的字符串是否为数字
				int r = UserDao.update(Integer.parseInt(txt_extra.getText()),txt_usergroup.getText(),txt_name.getText(),Integer.parseInt(txt_num.getText()),txt_id.getText(),String.valueOf(pwf_pwd.getPassword()), txt_major.getText(),txt_class.getText());
				if(r!=0)
					JOptionPane.showMessageDialog(this, info1);
				else
					JOptionPane.showMessageDialog(this, info2);
			}
		}
	}
	
	protected void toDeleteUser(){
		int isDelete = JOptionPane.showConfirmDialog(null, "确认删除当前选中用户？","注意！",JOptionPane.YES_NO_CANCEL_OPTION);
		if(isDelete == JOptionPane.YES_OPTION){
			int r = UserDao.delete(Integer.parseInt(txt_extra.getText()));
			if(r != 0){
				JOptionPane.showMessageDialog(null, "删除成功！影响"+r+"行数据记录\n请于管理面板处点击刷新数据\n以更新数据显示");
			}
		}
	}*/
	
	//按照当前选择用户的ID，获取所有用户信息至编辑窗口
	protected void selectById(int selectId){
		ResultSet rs = AdminDao.select("users",selectId);
		try{
			while(rs.next()){
				String id = String.valueOf(rs.getInt(1));
				String usergroup = rs.getString(2);
				String name = rs.getString(3);
				String num = String.valueOf(rs.getLong(4));
				String login_id = rs.getString(5);
				String login_pwd = rs.getString(6);
				String major = rs.getString(7);
				String _class = rs.getString(8);
				txt_extra.setText(id);
				txt_usergroup.setText(usergroup);
				txt_name.setText(name);
				txt_num.setText(num);
				txt_id.setText(login_id);
				pwf_pwd.setText(login_pwd);
				txt_major.setText(major);
				txt_class.setText(_class);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public JPanel getPane(){
		return jp;
	}
}
