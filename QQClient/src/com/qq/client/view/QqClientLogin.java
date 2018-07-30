package com.qq.client.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.qq.client.model.ClientConServerThread;
import com.qq.client.model.QqClientUser;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageQqFriendList;
import com.qq.client.view.QqFriendList;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JCheckBox;

public class QqClientLogin extends JFrame implements ActionListener {

	private JPanel contentPane;

	private boolean isMoved;
	private Point pre_point;
	private Point end_point;
	
	JButton buttonexit;
	private JButton buttonmin;
	private JButton buttonset;
	private JLabel lblqqid;
	private JLabel lblqqpw;
	private JTextField textFieldqqid;
	private JPasswordField passwordFieldpw;
	private JButton buttonlogin;
	private JCheckBox checkBoxmember;
	private JCheckBox checkBoxautulog;
	private JLabel labelregister;
	private JLabel labelfindpw;
	
	public static void main(String[] args) {
		QqClientLogin test = new QqClientLogin();
	}

	/**
	 * Create the frame.
	 */
	public QqClientLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 430, 330);
		setUndecorated(true);
		setDragable(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String path1 = "image/bg.jpg"; 
		ImageIcon background = new ImageIcon(path1);
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 430, 180);
		contentPane.add(label);
		
		buttonexit = new JButton(new ImageIcon("image/exit.jpg"));
		buttonexit.addActionListener(this);
		buttonexit.setBounds(405, 0, 25, 25);
		contentPane.add(buttonexit);
		
		buttonmin = new JButton(new ImageIcon("image/min.jpg"));
		buttonmin.addActionListener(this);
		buttonmin.setBounds(376, 0, 25, 25);
		contentPane.add(buttonmin);
		
		buttonset = new JButton(new ImageIcon("image/set.jpg"));
		buttonset.addActionListener(this);
		buttonset.setBounds(341, 0, 25, 25);
		contentPane.add(buttonset);
		
		lblqqid = new JLabel("QQ号码：");
		lblqqid.setFont(new Font("宋体", Font.PLAIN, 14));
		lblqqid.setBounds(38, 194, 72, 25);
		contentPane.add(lblqqid);
		
		lblqqpw = new JLabel("QQ密码：");
		lblqqpw.setFont(new Font("宋体", Font.PLAIN, 14));
		lblqqpw.setBounds(38, 228, 72, 25);
		contentPane.add(lblqqpw);
		
		textFieldqqid = new JTextField();
		textFieldqqid.setFont(new Font("宋体", Font.PLAIN, 14));
		textFieldqqid.setBounds(120, 190, 180, 25);
		contentPane.add(textFieldqqid);
		textFieldqqid.setColumns(10);
		
		passwordFieldpw = new JPasswordField();
		passwordFieldpw.setFont(new Font("宋体", Font.PLAIN, 14));
		passwordFieldpw.setColumns(10);
		passwordFieldpw.setBounds(120, 225, 180, 25);
		contentPane.add(passwordFieldpw);
		
		buttonlogin = new JButton(new ImageIcon("image/login.jpg"));
		buttonlogin.addActionListener(this);
		buttonlogin.setBounds(109, 292, 192, 28);
		contentPane.add(buttonlogin);
		
		labelregister = new JLabel("注册账号");
		labelregister.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				new Register();	
			}
		});
		labelregister.setForeground(Color.blue);
		labelregister.setFont(new Font("宋体", Font.PLAIN, 14));
		labelregister.setBounds(333, 194, 72, 25);
		contentPane.add(labelregister);
		
		labelfindpw = new JLabel("找回密码");
		labelfindpw.setForeground(Color.blue);
		labelfindpw.setFont(new Font("宋体", Font.PLAIN, 14));
		labelfindpw.setBounds(333, 228, 72, 25);
		contentPane.add(labelfindpw);
		
		checkBoxmember = new JCheckBox("记住密码");
		checkBoxmember.setBounds(108, 259, 81, 20);
		contentPane.add(checkBoxmember);
		
		checkBoxautulog = new JCheckBox("自动登录");
		checkBoxautulog.setBounds(219, 259, 81, 20);
		contentPane.add(checkBoxautulog);
		
		String path2="image/qq.jpg";
		Image image=getToolkit().getImage(path2);
		setIconImage(image);
		
		setVisible(true);
	}
	
	private void setDragable(final QqClientLogin qqClientLogin) {
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent e) {
				isMoved = false;// 鼠标释放了以后，是不能再拖拽的了
				qqClientLogin.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
				isMoved = true;
				pre_point = new Point(e.getX(), e.getY());// 得到按下去的位置
				qqClientLogin.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		//拖动时当前的坐标减去鼠标按下去时的坐标，就是界面所要移动的向量。
		this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent e) {
				if (isMoved) {// 判断是否可以拖拽
					end_point = new Point(qqClientLogin.getLocation().x + e.getX() - pre_point.x,
							qqClientLogin.getLocation().y + e.getY() - pre_point.y);
					qqClientLogin.setLocation(end_point);
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==buttonexit)
		{
			dispose();
		}else if (e.getSource()==buttonmin)
		{
			setExtendedState(JFrame.ICONIFIED);
		}else if (e.getSource()==buttonlogin) 
		{
			QqClientUser qqClientUser=new QqClientUser();
			User u=new User();
			u.setUserId(textFieldqqid.getText().trim());
			u.setPassWd(new String(passwordFieldpw.getPassword()));
			int loginstatus = qqClientUser.checkUser(u);
			if (loginstatus==1) 
			{
				try {
					//创建好友列表
					QqFriendList qqFriendList = new QqFriendList(u.getUserId());
					ManageQqFriendList.addQqFriendList(u.getUserId(), qqFriendList);
					//ClientConServerThread.RequestFriend(u);
					
				} catch (Exception e2) 
				{
					e2.printStackTrace();
				}
				
				
				this.dispose();
			}
			else if (loginstatus==2)
			{
				JOptionPane.showMessageDialog(this, "该用户已经登录！");
			}
			else if(loginstatus==0)
			{
				JOptionPane.showMessageDialog(this, "用户名密码错误！");
			}
		}
		
	}
}
