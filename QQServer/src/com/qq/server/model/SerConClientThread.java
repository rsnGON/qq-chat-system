/**
 * 功能：是服务器和某个客户端的通信线程
 */
package com.qq.server.model;

import java.awt.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.StringAnalysis;
import com.qq.common.User;
import com.qq.server.tools.ManageClientThread;

public class SerConClientThread extends Thread
{
	Socket socket;
	public SerConClientThread(Socket s)
	{
		//把服务器和该客户端的连接赋给this.socket
		this.socket = s;
	
	}
	
	//通知其它用户其他用户自己在线或者下线
	public void notifyother(String iAm,int type)//type=1,上线。type=0,下线。
	{
		HashMap hm = ManageClientThread.hm;
		Iterator it = hm.keySet().iterator();
		Message message = new Message();
		message.setContext(iAm);
		message.setSender(iAm);//指明是某个用户发的，如果是“0”，则是服务器发给客户端的，不是客户之间的通信
		if (type==1)
		{
			message.setMesType(MessageType.message_ret_onLineFriend);
		}
		else
		{
			message.setMesType(MessageType.message_ret_offlineFriend);
		}
		//获取好友列表
		String friendList[] = QqServerUser.getFriendList(iAm).split(" ");
		
		while (it.hasNext())
		{	
			String onLineUserId = it.next().toString();
			if (StringAnalysis.isHave(friendList,onLineUserId))
			{
				try
				{
					//调试信息
					if (type==1)
					{
						System.out.println("通知"+onLineUserId+":"+iAm+"在线了");
					}
					else {
						System.out.println("通知"+onLineUserId+":"+iAm+"下线了");
					}
					
					ObjectOutputStream oss = new ObjectOutputStream
					(ManageClientThread.getClientThread(onLineUserId).socket.getOutputStream());
					message.setGetter(onLineUserId);
					oss.writeObject(message);				
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}	
		}
	
	}
	//把离线消息发给用户
	public void  sendOfflineMessageToUser(String userId)
	{
		ArrayList<Message> list = QqServerUser.getUserOfflineMessage(userId);//获得用户的离线消息
		QqServerUser.delUserOfflineMessage(userId);//删除数据库离线消息
		try
		{
			ObjectOutputStream oss;
			System.out.println("离线消息个数为："+list.size());
			for (int i = 0; i < list.size(); i++)
			{
				oss = new ObjectOutputStream
						(ManageClientThread.getClientThread(userId).socket.getOutputStream()); 
				oss.writeObject((Message)list.get(i));
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//给客户端发送在线好友消息
	public void  sendOnlineFriend(Message message)
	{
		System.out.println(message.getSender()+"要他的在线好友");
		//把在线好友返回给该客户端
		String res = ManageClientThread.getAllOnLineUserId(message.getSender());
		Message m = new Message();
		m.setMesType(MessageType.message_ret_onLineFriend);
		m.setContext(res);
		m.setGetter(message.getSender());
		m.setSender("0");//表示服务器发送给用户的。
		try
		{
			SerConClientThread scct = ManageClientThread.getClientThread(message.getSender());
			if (scct == null)
			{
				//不在线就不发送在线好友
				return;
			}
			ObjectOutputStream oos = new ObjectOutputStream(scct.socket.getOutputStream());
			oos.writeObject(m);
		} catch (Exception e)
		{
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	//给客户端发送好友列表
	public void sendFriendList(Message message)
	{
		System.out.println(message.getSender()+"要他的好友列表");
		//把好友列表返回给该客户端Friendlist
		String res = QqServerUser.getFriendList(message.getSender());
		System.out.println(res);//打印好友列表
		Message m = new Message();
		m.setMesType(MessageType.message_ret_friendlist);
		m.setContext(res);
		m.setGetter(message.getSender());
		try
		{
			SerConClientThread scct = ManageClientThread.getClientThread(message.getSender());
			if (scct == null)
			{
				//不在线就不发送好友列表
				return;
			}
			ObjectOutputStream oos = new ObjectOutputStream(scct.socket.getOutputStream());
			oos.writeObject(m);
		} catch (Exception e)
		{
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	public void run()
	{
		while (true)
		{
			//这里该线程就可以接受客户端的信息
			try
			{
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Message message = (Message)ois.readObject();
				if (message.getMesType().equals(MessageType.message_comm_mes))
				{
					System.out.println(message.getSender()+"给"+message.getGetter()+"发:"+message.getContext());
					
					
					SerConClientThread scct = ManageClientThread.getClientThread(message.getGetter());
					if (scct==null)
					{
						//不在线。先把数据存储数据库。
						message.setMesType(MessageType.message_offline_message);//把消息变成离线消息
						QqServerUser.StoreMessageToDatabase(message);
					}
					else 
					{
						//转发信息
						ObjectOutputStream oos = new ObjectOutputStream(scct.socket.getOutputStream());
						oos.writeObject(message);
						//System.out.println("转发成功");
					}
					
				}else if (message.getMesType().equals(MessageType.message_get_onLineFriend))
				{
					sendOnlineFriend(message);
					
				}else if(message.getMesType().equals(MessageType.message_get_friendlist))
				{
					
					sendFriendList(message);
					
				}
				else if (message.getMesType().equals(MessageType.message_request_offline))
				{
					//通知其他在线好友他下线了
					notifyother(message.getSender(), 0);
					System.out.println(message.getSender()+"下线成功");
					Message m = new Message();
					m.setMesType(MessageType.message_offline_succeed);
					m.setGetter(message.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(m);
					ManageClientThread.delClientThread(message.getSender());
					//终止线程
					break;
				}else if (message.getMesType().equals(MessageType.message_request_addFriend))
				{
					//为该用户添加好友(强制添加，以后可以改进)
					int result;//1:添加成功2:已经是好友3:没有该用户
					result = new QqServerUser().addFriend(message.getSender(),message.getContext());
					if (result == 1)
					{
						//通知被加用户
						Message tofriendMess = new Message();
						tofriendMess.setSender(message.getContext());//假造被加用户主动请求更新好友列表
						sendFriendList(tofriendMess);
						sendOnlineFriend(tofriendMess);
					}
					Message m = new Message();
					m.setMesType(MessageType.message_ret_addFriend_result);
					m.setGetter(message.getSender());
					m.setContext(Integer.toString(result));
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(m);	
					
				}else if(message.getMesType().equals(MessageType.message_request_delFriend))
				{
					//为该用户删除好友(强制删除，以后可以改进)
					int result;//1:删除成功2:不是好友
					result = new QqServerUser().delFriend(message.getSender(),message.getContext());
					if (result == 1)
					{
						//通知被删用户
						System.out.println(message.getContext()+"被删了");
						Message tofriendMess = new Message();
						tofriendMess.setSender(message.getContext());//假造被加用户主动请求更新好友列表
						sendFriendList(tofriendMess);
						sendOnlineFriend(tofriendMess);
					}
					Message m = new Message();
					m.setMesType(MessageType.message_ret_delFriend_result);
					m.setGetter(message.getSender());
					m.setContext(Integer.toString(result));
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(m);	
				}else if(message.getMesType().equals(MessageType.message_request_offline_message))
				{
					//发送离线消息给用户
					sendOfflineMessageToUser(message.getSender());
				}
				
			
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
}



