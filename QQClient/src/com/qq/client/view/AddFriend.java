package com.qq.client.view;

import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JApplet;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.JTextField;
import javax.swing.JButton;

import com.qq.client.model.ClientConServerThread;
import com.qq.client.model.QqClientUser;

public class AddFriend extends JFrame implements ActionListener {
	
	private String owner;
	private JPanel contentPane;
	private JTextField textFieldqqid;
	JLabel labelqqid;
	JButton buttonadd;
	
	public AddFriend(String ownerId) {
		this.owner = ownerId;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 300, 348, 130);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelqqid = new JLabel("QQ号码:");
		labelqqid.setFont(new Font("宋体", Font.PLAIN, 14));
		labelqqid.setBounds(26, 34, 60, 20);
		contentPane.add(labelqqid);
		
		textFieldqqid = new JTextField();
		textFieldqqid.setFont(new Font("宋体", Font.PLAIN, 14));
		textFieldqqid.setBounds(90, 33, 150, 21);
		contentPane.add(textFieldqqid);
		textFieldqqid.setColumns(10);
		
		buttonadd = new JButton("添加");
		buttonadd.addActionListener(this);
		buttonadd.setFont(new Font("宋体", Font.PLAIN, 14));
		buttonadd.setBounds(252, 30, 65, 25);
		contentPane.add(buttonadd);
		setVisible(true);
	}
	public static void main(String[] args) {
		//new AddFriend();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==buttonadd)
		{
			
			String friendId = textFieldqqid.getText().trim();
			if (friendId.equals(""))
			{
				JOptionPane.showMessageDialog(this, "QQ号码不能为空");
				return;
			}
			if (friendId.equals(this.owner))
			{
				JOptionPane.showMessageDialog(this, "不能添加自己为好友");
				return;
			}
			//发送添加好友请求
			ClientConServerThread.SendAddFriendRequest(this.owner, friendId);
					
			
		}	
	}
	public void analysisAddFriendResult(int result)
	{
		//1:添加成功2:已经是好友3:没有该用户
		if (result == 1)
		{
			//更新好友
			ClientConServerThread.RequestFriend(this.owner);
			JOptionPane.showMessageDialog(this, "添加好友成功");
			this.dispose();
		}
		else if (result == 2)
		{
			JOptionPane.showMessageDialog(this, "该用户已经是你好友");
			this.dispose();
		}
		else if(result == 3)
		{
			JOptionPane.showMessageDialog(this, "没有该用户");
			this.dispose();
		}
	}
	
	
}

