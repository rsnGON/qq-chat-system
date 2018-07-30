package com.qq.client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.qq.client.model.ClientConServerThread;
import com.qq.client.tools.ManageAddFriend;
import com.qq.client.tools.ManageDelFriend;
import com.qq.client.tools.ManageQqChat;
import com.qq.client.tools.Player;
import com.qq.common.Message;
import javax.swing.Icon;


public class QqFriendList extends JFrame implements ActionListener,MouseListener {
	
	public static boolean sound = true;//控制消息提示的
	String owner;
	CardLayout cl;
	int friendnum;
	
	
	private JPanel contentPane1;
	private JPanel contentPane2;
	private JPanel contentPane3;
	
	private boolean isMoved;
	private Point pre_point;
	private Point end_point;
	
	JPanel panelhead1;
	JPanel panelhead2;
	JPanel panelhead3;
	
	JLabel labelh1;
	JLabel labelh2;
	JLabel labelh3;
	
	JPanel panelfoot1;
	JPanel panelfoot2;
	JPanel panelfoot3;
	
	JLabel labelf1;
	JLabel labelf2;
	JLabel labelf3;
	
	JPanel panelcenter1;
	JButton buttongoodfriend1;
	JButton buttonstranger1;
	JButton buttonblacklist1;
	JScrollPane scrollPanelist1;
	JPanel panelc11;
	JPanel panelc12;
	JPanel panelc13;
	
	JPanel panelcenter2;
	JButton buttongoodfriend2;
	JButton buttonstranger2;
	JButton buttonblacklist2;
	JScrollPane scrollPanelist2;
	JPanel panelc21;
	JPanel panelc22;
	JPanel panelc23;
	
	JPanel panelcenter3;
	JButton buttongoodfriend3;
	JButton buttonstranger3;
	JButton buttonblacklist3;
	JScrollPane scrollPanelist3;
	JPanel panelc31;
	JPanel panelc32;
	JPanel panelc33;
	
	JLabel labelfriend1[];//
	JLabel labelfriend2[];
	JLabel labelfriend3[];
	
	
	
	
	JPopupMenu popupMenu1;
	JMenuItem itemdel;
	private JButton buttonmin1;
	private JButton buttonexit1;
	private JButton buttonadd1;
	private JButton buttonmin2;
	private JButton buttonexit2;
	private JButton buttonadd2;
	private JButton buttonmin3;
	private JButton buttonexit3;
	private JButton buttonadd3;
	
	private JLabel labelqqid1;
	private JLabel labelqqface1;
	private JLabel labelqqid2;
	private JLabel labelqqface2;
	private JLabel labelqqid3;
	private JLabel labelqqface3;
	
	
	public static void main(String[] args) {
		//QqFriendList frame = new QqFriendList();
					
	}

	/**
	 * Create the frame.
	 */
	public QqFriendList(String ownerId)
	{
		this.owner = ownerId;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(900, 30, 279, 690);
		setUndecorated(true);
		setDragable(this);
		contentPane1 = new JPanel();
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane1.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane1);
		panelhead1 = new JPanel();
		panelhead1.setPreferredSize(new Dimension(278, 140));
		panelhead1.setLayout(null);
		
//		this.getLayeredPane().add(Pt1,-1);
//		this.getLayeredPane().add(Pt,0);

		
		labelh1=new JLabel(new ImageIcon("image/qqlist/head.jpg"));
		labelh1.setBounds(0, 0, 278, 140);
		panelhead1.add(labelh1,-1);
		contentPane1.add(panelhead1, BorderLayout.NORTH);
		
		//显示用户ID
		labelqqid1 = new JLabel(this.owner);
		labelqqid1.setForeground(Color.white);
		labelqqid1.setBounds(90, 40, 90, 26);
		panelhead1.add(labelqqid1,0);
		
		buttonexit1 = new JButton(new ImageIcon("image/qqlist/exit1.jpg"));
		buttonexit1.addActionListener(this);
		
		//头像
		ImageIcon image1=new ImageIcon("image/qqlist/qq.jpg");
		image1.setImage(image1.getImage().getScaledInstance(59,59,Image.SCALE_DEFAULT));
		labelqqface1 = new JLabel(image1);
		labelqqface1.setBounds(10, 45, 59, 59);
		panelhead1.add(labelqqface1,0);
		buttonexit1.setBounds(250, 0, 20, 20);
		panelhead1.add(buttonexit1,0);
		
		buttonmin1 = new JButton(new ImageIcon("image/qqlist/min1.jpg"));
		buttonmin1.addActionListener(this);
		buttonmin1.setBounds(230, 0, 20, 20);
		panelhead1.add(buttonmin1,0);
		
		
		panelcenter1 = new JPanel(new BorderLayout());
		panelcenter1.setPreferredSize(new Dimension(278, 500));
		//panelcenter1.add(new PopupMenuPanel(owner));
		buttongoodfriend1=new JButton("我的好友");
		buttonstranger1=new JButton("陌生人");
		buttonstranger1.addActionListener(this);
		buttonblacklist1=new JButton("黑名单");
		buttonblacklist1.addActionListener(this);
		panelc12=new JPanel(new GridLayout(20,1,4,4));
		
//		labelfriend1=new JLabel[20];
//		for(int i=0;i<labelfriend1.length;i++){
//			ImageIcon image=new ImageIcon("image/qqlist/qq.jpg");
//			image.setImage(image.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
//			labelfriend1[i]=new JLabel(i+1+"",image,JLabel.LEFT);
//			final String id=labelfriend1[i].getText();
//			popupMenu1=new JPopupMenu(id);
//			itemdel=new JMenuItem("删除");
//			itemdel.addMouseListener(new MouseAdapter() {
//
//				@Override
//				public void mouseReleased(MouseEvent e) {
//					if (JOptionPane.showConfirmDialog(null, "确定删除"+id+"？", "提示", JOptionPane.YES_NO_OPTION)==0) {
//						JOptionPane.showMessageDialog(null, "删除成功！","提示" , JOptionPane.PLAIN_MESSAGE);
//					}
//					super.mouseReleased(e);
//				}
//				
//			});
//			popupMenu1.add(itemdel);
//			labelfriend1[i].setComponentPopupMenu(popupMenu1);
//			
//			labelfriend1[i].addMouseListener(this);			
//			labelfriend1[i].setEnabled(false);
//			panelc12.add(labelfriend1[i]);
//		}
		panelc13=new JPanel(new GridLayout(2,1));
		panelc13.add(buttonstranger1);
		panelc13.add(buttonblacklist1);
		scrollPanelist1=new JScrollPane(panelc12);
		panelcenter1.add(buttongoodfriend1, "North");
		panelcenter1.add(scrollPanelist1, "Center");
		panelcenter1.add(panelc13, "South");
				
		contentPane1.add(panelcenter1, BorderLayout.CENTER);
			
		
		panelfoot1 = new JPanel();
		panelfoot1.setPreferredSize(new Dimension(278, 60));
		panelfoot1.setLayout(null);
		labelf1=new JLabel(new ImageIcon("image/qqlist/foot.jpg"));
		labelf1.setBounds(0, 0, 278, 60);		 
		panelfoot1.add(labelf1,-1);
		
		buttonadd1 = new JButton(new ImageIcon("image/qqlist/add.jpg"));
		buttonadd1.addActionListener(this);
		buttonadd1.setBounds(150, 35, 60, 25);
		panelfoot1.add(buttonadd1,0);
		contentPane1.add(panelfoot1, BorderLayout.SOUTH);
		
///////////////////////////////////////////////////////////////////			
		contentPane2 = new JPanel();
		contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane2.setLayout(new BorderLayout(0, 0));
		
		panelhead2 = new JPanel(new BorderLayout());
		panelhead2.setPreferredSize(new Dimension(278, 140));
		panelhead2.setLayout(null);
		labelh2=new JLabel(new ImageIcon("image/qqlist/head.jpg"));
		labelh2.setBounds(0, 0, 278, 140);
		panelhead2.add(labelh2,-1);
		contentPane2.add(panelhead2, BorderLayout.NORTH);
		
		buttonexit2 = new JButton(new ImageIcon("image/qqlist/exit1.jpg"));
		buttonexit2.addActionListener(this);
		panelhead2.add(buttonexit2, 0);
		
		//头像
		ImageIcon image2=new ImageIcon("image/qqlist/qq.jpg");
		image2.setImage(image2.getImage().getScaledInstance(59,59,Image.SCALE_DEFAULT));
		labelqqface2 = new JLabel(image2);
		labelqqface2.setSize(59, 59);
		labelqqface2.setLocation(10, 45);
		panelhead2.add(labelqqface2, 0);
//		panelhead2.add(labelqqface2, BorderLayout.NORTH);
		
		//显示用户ID
		labelqqid2 = new JLabel(this.owner);
		labelqqid2.setForeground(Color.white);
		labelqqid2.setSize(90, 26);
		labelqqid2.setLocation(90, 40);
		panelhead2.add(labelqqid2, 0);
//		panelhead2.add(labelqqid2, BorderLayout.NORTH);
		buttonexit2.setSize(20, 20);
		buttonexit2.setLocation(250, 0);
		panelhead2.add(buttonexit2, BorderLayout.NORTH);
		
		buttonmin2 = new JButton(new ImageIcon("image/qqlist/min1.jpg"));
		buttonmin2.addActionListener(this);
		buttonmin2.setSize(20, 20);
		buttonmin2.setLocation(230, 0);
		panelhead2.add(buttonmin2,0);
//		panelhead2.add(buttonmin2, BorderLayout.NORTH);
		
		panelcenter2 = new JPanel(new BorderLayout());
		panelcenter2.setPreferredSize(new Dimension(278, 500));
		buttongoodfriend2=new JButton("我的好友");
		buttongoodfriend2.addActionListener(this);
		buttonstranger2=new JButton("陌生人");
		buttonblacklist2=new JButton("黑名单");
		buttonblacklist2.addActionListener(this);
		panelc22=new JPanel(new GridLayout(10,1,4,4));
		labelfriend2=new JLabel[10];
//		for(int i=0;i<labelfriend2.length;i++){
//			ImageIcon image=new ImageIcon("image/qqlist/qq.jpg");
//			image.setImage(image.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
//			labelfriend2[i]=new JLabel(i+1+"",image,JLabel.LEFT);
//			
//			final String id=labelfriend2[i].getText();
//			popupMenu1=new JPopupMenu(id);
//			itemdel=new JMenuItem("删除");
//			itemdel.addMouseListener(new MouseAdapter() {
//
//				@Override
//				public void mouseReleased(MouseEvent e) {
//					if (JOptionPane.showConfirmDialog(null, "确定删除"+id+"？", "提示", JOptionPane.YES_NO_OPTION)==0) {
//						JOptionPane.showMessageDialog(null, "删除成功！","提示" , JOptionPane.PLAIN_MESSAGE);
//					}
//					super.mouseReleased(e);
//				}
//				
//			});
//			popupMenu1.add(itemdel);
//			labelfriend2[i].setComponentPopupMenu(popupMenu1);
//			
//			
//			labelfriend2[i].addMouseListener(this);
//			labelfriend2[i].setEnabled(false);
//			panelc22.add(labelfriend2[i]);
//		}
		panelc21=new JPanel(new GridLayout(2,1));
		panelc21.add(buttongoodfriend2);
		panelc21.add(buttonstranger2);
		scrollPanelist2=new JScrollPane(panelc22);
		panelcenter2.add(panelc21, "North");
		panelcenter2.add(scrollPanelist2, "Center");
		panelcenter2.add(buttonblacklist2, "South");
		contentPane2.add(panelcenter2,BorderLayout.CENTER);
		
		panelfoot2 = new JPanel();
		panelfoot2.setPreferredSize(new Dimension(278, 60));
		panelfoot2.setLayout(null);
		labelf2=new JLabel(new ImageIcon("image/qqlist/foot.jpg"));
		labelf2.setBounds(0, 0, 278, 60);
		panelfoot2.add(labelf2,-1);
		
		buttonadd2 = new JButton(new ImageIcon("image/qqlist/add.jpg"));
		buttonadd2.addActionListener(this);
		buttonadd2.setBounds(150, 35, 60, 25);
		panelfoot2.add(buttonadd2,0);
		contentPane2.add(panelfoot2, BorderLayout.SOUTH);
			
/////////////////////////////////////////////////////////////////////
		contentPane3 = new JPanel();
		contentPane3.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane3.setLayout(new BorderLayout(0, 0));
		
		panelhead3 = new JPanel(new BorderLayout());
		panelhead3.setPreferredSize(new Dimension(278, 140));
		panelhead3.setLayout(null);
		labelh3=new JLabel(new ImageIcon("image/qqlist/head.jpg"));
		labelh3.setBounds(0, 0, 278, 140);
		panelhead3.add(labelh3,-1);
		contentPane3.add(panelhead3, BorderLayout.NORTH);
		
		buttonexit3 = new JButton(new ImageIcon("image/qqlist/exit1.jpg"));
		buttonexit3.addActionListener(this);
		panelhead3.add(buttonexit3, 0);
		
		
		//头像
		ImageIcon image3=new ImageIcon("image/qqlist/qq.jpg");
		image3.setImage(image3.getImage().getScaledInstance(59,59,Image.SCALE_DEFAULT));
		labelqqface3 = new JLabel(image3);
		labelqqface3.setSize(59, 59);
		labelqqface3.setLocation(10, 45);
		panelhead3.add(labelqqface3,0);
//		panelhead3.add(labelqqface3, BorderLayout.NORTH);
		
		
		//显示用户ID
		labelqqid3 = new JLabel(this.owner);
		labelqqid3.setForeground(Color.white);
		labelqqid3.setBounds(90, 40, 90, 26);
		panelhead3.add(labelqqid3,0);
		
		
//		labelqqid3 = new JLabel(this.owner);
//		panelhead3.add(labelqqid3, BorderLayout.NORTH);
		buttonexit3.setSize(20, 20);
		buttonexit3.setLocation(250, 0);
		panelhead3.add(buttonexit3, BorderLayout.NORTH);
		
		buttonmin3 = new JButton(new ImageIcon("image/qqlist/min1.jpg"));
		buttonmin3.addActionListener(this);
		buttonmin3.setSize(20, 20);
		buttonmin3.setLocation(230, 0);
		panelhead3.add(buttonmin3, 0);
//		panelhead3.add(buttonmin3, BorderLayout.NORTH);
		
		panelcenter3 = new JPanel(new BorderLayout());
		panelcenter3.setPreferredSize(new Dimension(278, 500));
		buttongoodfriend3=new JButton("我的好友");
		buttongoodfriend3.addActionListener(this);
		buttonstranger3=new JButton("陌生人");
		buttonstranger3.addActionListener(this);
		buttonblacklist3=new JButton("黑名单");
		panelc32=new JPanel(new GridLayout(5,1,4,4));
		labelfriend3=new JLabel[5];
//		for(int i=0;i<labelfriend3.length;i++){
//			ImageIcon image=new ImageIcon("image/qqlist/qq.jpg");
//			image.setImage(image.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
//			labelfriend3[i]=new JLabel(i+1+"",image,JLabel.LEFT);
//			
//			final String id=labelfriend3[i].getText();
//			popupMenu1=new JPopupMenu(id);
//			itemdel=new JMenuItem("删除");
//			itemdel.addMouseListener(new MouseAdapter() {
//
//				@Override
//				public void mouseReleased(MouseEvent e) {
//					if (JOptionPane.showConfirmDialog(null, "确定删除"+id+"？", "提示", JOptionPane.YES_NO_OPTION)==0) {
//						JOptionPane.showMessageDialog(null, "删除成功！","提示" , JOptionPane.PLAIN_MESSAGE);
//					}
//					super.mouseReleased(e);
//				}
//				
//			});
//			popupMenu1.add(itemdel);
//			labelfriend3[i].setComponentPopupMenu(popupMenu1);
//			
//			labelfriend3[i].addMouseListener(this);
//			labelfriend3[i].setEnabled(false);
//			panelc32.add(labelfriend3[i]);
//		}
		panelc31=new JPanel(new GridLayout(3,1));
		panelc31.add(buttongoodfriend3);
		panelc31.add(buttonstranger3);
		panelc31.add(buttonblacklist3);
		scrollPanelist3=new JScrollPane(panelc32);
		panelcenter3.add(panelc31, "North");
		panelcenter3.add(scrollPanelist3, "Center");
		contentPane3.add(panelcenter3,BorderLayout.CENTER);
		
		panelfoot3 = new JPanel();
		panelfoot3.setPreferredSize(new Dimension(300, 60));
		panelfoot3.setLayout(null);
		labelf3=new JLabel(new ImageIcon("image/qqlist/foot.jpg"));
		labelf3.setBounds(0, 0, 278, 60);
		panelfoot3.add(labelf3,-1);
		
		buttonadd3 = new JButton(new ImageIcon("image/qqlist/add.jpg"));
		buttonadd3.addActionListener(this);
		buttonadd3.setBounds(150, 35, 60, 25);
		panelfoot3.add(buttonadd3,0);
		contentPane3.add(panelfoot3, BorderLayout.SOUTH);
		
		
////////////////////////////////////////////////////////////////////
		getContentPane().add(contentPane1);
		cl=new CardLayout();
		getContentPane().setLayout(cl);
		getContentPane().add(contentPane1, "1");
		getContentPane().add(contentPane2, "2");
		getContentPane().add(contentPane3, "3");
		
		String path2="image/qqlist/qq.jpg";
		Image image=getToolkit().getImage(path2);
		setIconImage(image);
		setVisible(true);
		
		//请求更新朋友列表
		ClientConServerThread.RequestFriend(ownerId);
		//请求服务器存储的离线消息
		ClientConServerThread.RequestOfflineMessage(ownerId);
	}
	//更新在线的好友情况
		public void updateOnlineFriend(Message m)
		{
			if (m.getContext().equals(""))
			{
				System.out.println("没有在线好友");
				return;
			}
			String onLineFriend[]=m.getContext().split(" ");
			
			System.out.println("进入循环次数"+onLineFriend.length);//调试信息
			for(int i=0;i<friendnum;i++)
			{
				
				for (int k = 0; k < onLineFriend.length; k++)
				{
					System.out.println("更新好友"+onLineFriend[k]);//调试信息
					if (labelfriend1[i].getText().equals(onLineFriend[k]))
					{
						labelfriend1[i].setEnabled(true);
					}
					
				}
				//jbls[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);
			}
		}
		//更新下线好友
		public void updateOfflineFriend(Message m)
		{
			if (m.getContext().equals(""))
			{
				System.out.println("没有下线好友");
				return;
			}
			String offlineId[]=m.getContext().split(" ");
			for(int i=0;i<friendnum;i++)
			{		
				for (int j = 0; j < offlineId.length; j++)
				{
					if (labelfriend1[i].getText().equals(offlineId[j]))
					{
						labelfriend1[i].setEnabled(false);
					}
				}	
					
			}
		}
		//显示好友列表
		public void updateFriendList(Message m)
		{
			panelc12.removeAll();//把之前显示的好友删除，重新绘制
			panelc12.repaint();
			
			if(m.getContext().equals(""))
			{
				System.out.println("他没有朋友");
				return;
			}
			String Friend[]=m.getContext().split(" ");
			System.out.println("好友列表，进入循环次数"+Friend.length);//调试信息
			
			friendnum = Friend.length;
			if(Friend.length>0)
			{
				labelfriend1 = new JLabel[Friend.length];
			}
			for(int i=0;i<Friend.length;i++)
			{
				System.out.println("显示好友"+Friend[i]);//调试信息
				ImageIcon image=new ImageIcon("image/qqlist/qq.jpg");
				image.setImage(image.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
				labelfriend1[i]=new JLabel(Friend[i],image,JLabel.LEFT);
				final String id=labelfriend1[i].getText();
				popupMenu1=new JPopupMenu(id);
				
				itemdel=new JMenuItem("删除");
				itemdel.addMouseListener(new MouseAdapter() {
	
					@Override
					public void mouseReleased(MouseEvent e)
					{
					
						//if (JOptionPane.showConfirmDialog(null, "确定删除"+id+"？", "提示", JOptionPane.YES_NO_OPTION)==0) 
						//{
						//	JOptionPane.showMessageDialog(null, "删除成功！","提示" , JOptionPane.PLAIN_MESSAGE);
						//}
						
						
							DelFriend delFriend = new DelFriend(owner,id);
							ManageDelFriend.addDelFriend(owner, delFriend);
				
						//DelFriend delFriend = new DelFriend(owner);
						//ManageDelFriend.addDelFriend(owner, delFriend);
						super.mouseReleased(e);
					}
					
				});
				popupMenu1.add(itemdel);
				labelfriend1[i].setComponentPopupMenu(popupMenu1);
				
				labelfriend1[i].addMouseListener(this);			
				labelfriend1[i].setEnabled(false);			
				
				
				panelc12.add(labelfriend1[i]);
				
			}
			this.setVisible(true);
			
		}
		//提示有好友上线
		public void haveFriendOnline()//声音提醒
		{
			new Player().haveFriendOnline();
		}
		//提示好友有信息
		public void HaveMessage(String sender)//声音消息提醒
		{
			//判断哪个发消息
		
//			int i = 0;
//			for(i=0;i<friendnum;i++)
//			{			
//					if (jbls[i].getText().equals(sender))
//					{
//						break;
//					}		
//			}
				//jbls[i].setEnabled(false);
				//jbls[i].setEnabled(true);
			new Player().haveMessage();
			
		}
	
	
	
	
	private void setDragable(final QqFriendList qqFriendList) {
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent e) {
				isMoved = false;// 鼠标释放了以后，是不能再拖拽的了
				qqFriendList.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
				isMoved = true;
				pre_point = new Point(e.getX(), e.getY());// 得到按下去的位置
				qqFriendList.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		//拖动时当前的坐标减去鼠标按下去时的坐标，就是界面所要移动的向量。
		this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent e) {
				if (isMoved) {// 判断是否可以拖拽
					end_point = new Point(qqFriendList.getLocation().x + e.getX() - pre_point.x,
							qqFriendList.getLocation().y + e.getY() - pre_point.y);
					qqFriendList.setLocation(end_point);
				}
			}
		});
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==buttongoodfriend2||e.getSource()==buttongoodfriend3){
			cl.show(this.getContentPane(), "1");
		}else if (e.getSource()==buttonstranger1||e.getSource()==buttonstranger3) {
			cl.show(this.getContentPane(), "2");
		}else if (e.getSource()==buttonblacklist1||e.getSource()==buttonblacklist2) {
			cl.show(this.getContentPane(), "3");
		}else if (e.getSource()==buttonmin1||e.getSource()==buttonmin2||e.getSource()==buttonmin3) {
			setExtendedState(JFrame.ICONIFIED);
		}else if (e.getSource()==buttonexit1||e.getSource()==buttonexit2||e.getSource()==buttonexit3) {
			ClientConServerThread.RequestOffline(owner);
			dispose();
			System.out.println("close");
			
		}else if (e.getSource()==buttonadd1||e.getSource()==buttonadd2||e.getSource()==buttonadd3) {
//			JOptionPane.showMessageDialog(this, "Find");
			AddFriend addFriend = new AddFriend(this.owner);
			ManageAddFriend.addAddFriend(this.owner, addFriend);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1&&e.getClickCount() == 2)
		{

			String friendNo = ((JLabel)e.getSource()).getText().trim();
			
			if (ManageQqChat.getQqChat(this.owner+" "+friendNo)!=null)
			{
				return;
			}
			QqChat qqChat = new QqChat(this.owner,friendNo);
			ManageQqChat.addQqChat(this.owner+" "+friendNo,qqChat);
			
			//System.out.println("你希望和"+friendNo+"聊天");
//			System.out.println("click two times");
			JLabel label=(JLabel)e.getSource();
			label.setForeground(Color.red);
		}else if (e.getButton()==MouseEvent.BUTTON3) {
			
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel label=(JLabel)e.getSource();
		label.setForeground(Color.red);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel label=(JLabel)e.getSource();
		label.setForeground(Color.black);
		
	}
}
