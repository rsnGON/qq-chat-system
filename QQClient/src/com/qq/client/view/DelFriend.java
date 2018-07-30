package com.qq.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

import com.qq.client.model.ClientConServerThread;
import com.qq.client.model.QqClientUser;
import com.qq.client.tools.ManageQqChat;

public class DelFriend extends JFrame implements ActionListener {
	
	private String ownerId;
	private String friendId;
	private JPanel contentPane;
	JButton buttondel_sure,buttondel_cancel;

	public DelFriend(String ownerId,String friendId) {
		this.ownerId = ownerId;
		this.friendId = friendId;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 300, 348, 130);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		buttondel_sure = new JButton("确定");
		buttondel_sure.addActionListener(this);
		buttondel_sure.setFont(new Font("宋体", Font.PLAIN, 14));
		buttondel_sure.setBounds(100, 23, 65, 25);
		contentPane.add(buttondel_sure);
		buttondel_cancel = new JButton("取消");
		buttondel_cancel.addActionListener(this);
		buttondel_cancel.setFont(new Font("宋体", Font.PLAIN, 14));
		buttondel_cancel.setBounds(180, 23, 65, 25);
		contentPane.add(buttondel_cancel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==buttondel_sure)
		{
			ClientConServerThread.SendDelFriendRequest(this.ownerId, this.friendId);
			System.out.println(this.friendId+"好友被删除");
			QqChat qqChat = ManageQqChat.getQqChat(this.ownerId+" "+this.friendId);
			if (qqChat!=null)
			{
				//从hashmap删除QqChat
				ManageQqChat.delQqChat(this.ownerId+" "+this.friendId);
				qqChat.dispose();
			}
		}
		if (e.getSource()==buttondel_cancel)
		{
			this.dispose();
		}
		
	}
	public void analysisDelFriendResult(int result)
	{
		//1:删除成功2:不是好友
		if (result == 1)
		{
			//更新好友
			ClientConServerThread.RequestFriend(this.ownerId);
			JOptionPane.showMessageDialog(this, "删除好友成功");
			this.dispose();
		}
		else if (result == 2)
		{
			JOptionPane.showMessageDialog(this, "已经不是好友");
			this.dispose();
		}
	}

}
