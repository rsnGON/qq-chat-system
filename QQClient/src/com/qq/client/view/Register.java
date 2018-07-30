package com.qq.client.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.qq.client.model.QqClientUser;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.User;

public class Register extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textFieldqqid;
	private JPasswordField passwordFieldpw;
	private JPasswordField passwordFieldconfirmpw;
	private JLabel labelconfirmpw;
	JButton buttonregister;
	
	public Register() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelqqid = new JLabel("QQ号码：");
		labelqqid.setFont(new Font("宋体", Font.PLAIN, 14));
		labelqqid.setBounds(57, 51, 72, 25);
		contentPane.add(labelqqid);
		
		textFieldqqid = new JTextField();
		textFieldqqid.setFont(new Font("宋体", Font.PLAIN, 14));
		textFieldqqid.setBounds(139, 53, 180, 25);
		contentPane.add(textFieldqqid);
		textFieldqqid.setColumns(10);
		
		JLabel labelqqpw = new JLabel("QQ密码：");
		labelqqpw.setFont(new Font("宋体", Font.PLAIN, 14));
		labelqqpw.setBounds(57, 110, 72, 25);
		contentPane.add(labelqqpw);

		passwordFieldpw = new JPasswordField();
		passwordFieldpw.setBounds(139, 112, 180, 25);
		contentPane.add(passwordFieldpw);
		
		labelconfirmpw = new JLabel("确认密码：");
		labelconfirmpw.setFont(new Font("宋体", Font.PLAIN, 14));
		labelconfirmpw.setBounds(57, 157, 72, 25);
		contentPane.add(labelconfirmpw);
		
		passwordFieldconfirmpw = new JPasswordField();
		passwordFieldconfirmpw.setBounds(139, 159, 180, 25);
		contentPane.add(passwordFieldconfirmpw);
		
		buttonregister = new JButton("立即注册");
		buttonregister.addActionListener(this);
		buttonregister.setFont(new Font("宋体", Font.PLAIN, 14));
		buttonregister.setBounds(126, 207, 140, 25);
		contentPane.add(buttonregister);
		
		setVisible(true);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==buttonregister) 
		{
			if (textFieldqqid.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "QQ号码不能为空");
				return;
			}
			else if (!new String(passwordFieldconfirmpw.getPassword()).equals(new String(passwordFieldpw.getPassword())))
			{
				JOptionPane.showMessageDialog(this, "两次输入密码不相同");
				return;
			}
			
			QqClientUser qqClientUser=new QqClientUser();
			User user = new User();
			user.setUserId(textFieldqqid.getText().trim());
			String passwdString = new String(passwordFieldconfirmpw.getPassword());
			user.setPassWd(passwdString);
			if (qqClientUser.registerUser(user))
			{
				JOptionPane.showMessageDialog(this, "注册成功！你的QQID是："+user.getUserId());
			}
			else
			{
				JOptionPane.showMessageDialog(this, "注册失败，已经有该用户");
			}
			
		}
		
	}

}
