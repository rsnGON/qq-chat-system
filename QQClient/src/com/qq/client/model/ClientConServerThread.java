/**
 * 这是客户端和服务器端保持通讯的线程
 */

package com.qq.client.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.qq.client.tools.ManageAddFriend;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageDelFriend;
import com.qq.client.tools.ManageQqChat;
import com.qq.client.tools.ManageQqFriendList;
import com.qq.client.tools.ObjectFile;
import com.qq.client.view.AddFriend;
import com.qq.client.view.DelFriend;
import com.qq.client.view.QqChat;
import com.qq.client.view.QqFriendList;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

public class ClientConServerThread extends Thread
{
	private Socket s;
	public Socket getS()
	{
		return s;
	}
	public void setS(Socket s)
	{
		this.s = s;
	}
	public ClientConServerThread(Socket s)
	{
		this.s = s;
	}
	public void run()
	{
		//不停的读取从服务器端发来的信息
		while(true)
		{
			try
			{
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				if (m.getMesType().equals(MessageType.message_comm_mes)
						|| m.getMesType().equals(MessageType.message_offline_message))
				{	
					
					System.out.println(m.getSender()+"对"+m.getGetter()+"说"+m.getContext()+"\r\n");
					//把从服务器获得的消息，显示到该显示的聊天界面
					QqChat qqChat = ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					
					QqFriendList qqFriendList = ManageQqFriendList.getQqFriendList(m.getGetter());
					
					if (m.getMesType().equals(MessageType.message_comm_mes))
					{
						qqFriendList.sound = true;		
					}
					if (qqFriendList.sound)
					{
						qqFriendList.HaveMessage(m.getSender());//friendlist通知用户有消息了
					}
					if (m.getMesType().equals(MessageType.message_offline_message))
					{
						qqFriendList.sound = false;
					}
						
				
					
					
					if (qqChat==null)
					{
						//聊天窗口没打开
						
						//消息存到本地文件
						new ObjectFile().WriteObjectToFile(m, m.getGetter(),m.getSender());
						
						////测试
						QqFriendList qqFriendList1 = ManageQqFriendList.getQqFriendList("1");
						if (qqFriendList1==null)
						{
							//bug?
							System.out.println("0000000000000000000000");
						}
						////测试
						
						
					}
					else
					{
						//显示
						qqChat.showMessage(m);
					}
					
				}
				else if (m.getMesType().equals(MessageType.message_ret_onLineFriend))
				{
					System.out.println("收到好友的在线信息");
//					String con = m.getContext();
//					String friengs[] = con.split(" ");
					String getter = m.getGetter();
					System.out.println(getter+":"+m.getContext());
					//修改相应的好友列表
					QqFriendList  qqFriendList =  ManageQqFriendList.getQqFriendList(getter);
					//更新在线好友
					System.out.println("更新好友在线");
					if (qqFriendList!=null)
					{
						qqFriendList.updateOnlineFriend(m);
						if (!m.getSender().equals("0"))//如果是“0”，则是服务器发给客户端的，不是客户之间的通信
						{								//如果不是“0”，则是某个客户端好友提示，说我上线了
							qqFriendList.haveFriendOnline();
						}
						
					}
				}else if(m.getMesType().equals(MessageType.message_ret_offlineFriend))
				{
					String getter = m.getGetter();
					QqFriendList  qqFriendList =  ManageQqFriendList.getQqFriendList(getter);
					//更新在线好友
					System.out.println("更新下线好友"+m.getContext());
					if (qqFriendList!=null)
					{
						qqFriendList.updateOfflineFriend(m);
					}
				}else if (m.getMesType().equals(MessageType.message_ret_friendlist))
				{
					System.out.println("收到好友列表");
					System.out.println(m.getContext());//好友列表
					String getter = m.getGetter();
					//修改相应的好友列表
					QqFriendList  qqFriendList =  ManageQqFriendList.getQqFriendList(getter);
					//更新在线好友
					System.out.println("更新好友列表");
					if (qqFriendList!=null)
					{
						System.out.println("正在更新");
						qqFriendList.updateFriendList(m);
					}
//					//发送一个要求返回在线好友的请求包
//					ObjectOutputStream oos = new ObjectOutputStream
//					(ManageClientConServerThread.getClientConServerThread(m.getGetter()).getS().getOutputStream());
//				
//					Message message = new Message();
//					message.setMesType(MessageType.message_get_onLineFriend);
//					message.setSender(m.getGetter());
//					oos.writeObject(message);
				}
				else if (m.getMesType().equals(MessageType.message_offline_succeed))
				{
					ManageClientConServerThread.delClientConServerthread(m.getGetter());
					ManageQqFriendList.delQqFriendList(m.getGetter());
					s.close();
					break;
				}else if (m.getMesType().equals(MessageType.message_ret_addFriend_result))
				{
					//请求添加好友请求返回结果
					int result = Integer.parseInt(m.getContext());//1:添加成功2:已经是好友3:没有该用户
					AddFriend addFriend = ManageAddFriend.getAddFriend(m.getGetter());
					addFriend.analysisAddFriendResult(result);	
				}else if(m.getMesType().equals(MessageType.message_ret_delFriend_result))
				{
					//请求删除好友请求返回结果
					int result = Integer.parseInt(m.getContext());//1:删除成功2:不是好友
				
					DelFriend delFriend = ManageDelFriend.getDelFriend(m.getGetter());
					delFriend.analysisDelFriendResult(result);	
				}
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	//发送聊天内容
	public static void SendChatMessage(Message message)
	{
		//发送给服务器
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream
			(ManageClientConServerThread.getClientConServerThread(message.getSender()).getS().getOutputStream());
			oos.writeObject(message);
		
		} catch (Exception e2)
		{
			e2.printStackTrace();
		}
	}
	//请求下线
	public static void RequestOffline(String uid)
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream
			(ManageClientConServerThread.getClientConServerThread(uid).getS().getOutputStream());
			Message message = new Message();
			message.setMesType(MessageType.message_request_offline);
			message.setSender(uid);
			oos.writeObject(message);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void RequestFriend(String uid)
	{
	////发送一个要求返回好友列表的请求包
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream
			(ManageClientConServerThread.getClientConServerThread(uid).getS().getOutputStream());
			Message message = new Message();
			message.setMesType(MessageType.message_get_friendlist);
			message.setSender(uid);
			oos.writeObject(message);
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//发送一个要求返回在线好友的请求包
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream
			(ManageClientConServerThread.getClientConServerThread(uid).getS().getOutputStream());
			Message message = new Message();
			message.setMesType(MessageType.message_get_onLineFriend);
			message.setSender(uid);
		
			oos.writeObject(message);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//请求离线好友离线消息
	public static void RequestOfflineMessage(String uid)
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream
					(ManageClientConServerThread.getClientConServerThread(uid).getS().getOutputStream());
			Message message = new Message();
			message.setSender(uid);
			message.setMesType(MessageType.message_request_offline_message);
			oos.writeObject(message);
		} catch (Exception e)
		{
			e.printStackTrace();
			// TODO: handle exception
		}	
	}
	//发送添加好友请求
	public static void SendAddFriendRequest(String uid,String friendId)
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream
					(ManageClientConServerThread.getClientConServerThread(uid).getS().getOutputStream());
			Message message = new Message();
			message.setContext(friendId);
			message.setSender(uid);
			message.setMesType(MessageType.message_request_addFriend);
			oos.writeObject(message);
		} catch (Exception e)
		{
			e.printStackTrace();
			// TODO: handle exception
		}	
	}
	//发送删除好友求情
	public static void SendDelFriendRequest(String uid,String friendId)
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream
					(ManageClientConServerThread.getClientConServerThread(uid).getS().getOutputStream());
			Message message = new Message();
			message.setContext(friendId);
			message.setSender(uid);
			message.setMesType(MessageType.message_request_delFriend);
			oos.writeObject(message);
		} catch (Exception e)
		{
			e.printStackTrace();
			// TODO: handle exception
		}	
	}
}
