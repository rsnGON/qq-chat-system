/**
 * 这是qq服务器，它在监听，等待某个qq客户端来连接
 */
package com.qq.server.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;

import javax.crypto.spec.PSource;

import com.qq.common.LoginRegisterMessage;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;
import com.qq.server.db.SqlHelper;
import com.qq.server.tools.ManageClientThread;

public class QqServer
{
	public QqServer()
	{
		try
		{
			//在9999端口监听
			System.out.println("我是服务器，在9999监听");//用于调试
			ServerSocket ss=new ServerSocket(9999);
			while(true)
			{
				//阻塞，等待连接
				Socket s = ss.accept(); 
				//接受客户端发来的信息
	//			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	//			String info = br.readLine();
				ObjectInputStream oss = new ObjectInputStream(s.getInputStream());
				LoginRegisterMessage message = (LoginRegisterMessage)oss.readObject();
				if (message.getMesType().equals(MessageType.message_request_login))
				{
					User u = message.getUser();
					
					Message m = new Message();//返回用户的消息
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					//判断用户是否已经登录
					if ((ManageClientThread.getClientThread(u.getUserId()))!=null)
					{
						//返回一个已经登录的信息报
						m.setMesType(MessageType.message_have_logined);
						oos.writeObject(m);
					}
					else
					{
						//用户不存在或者未登录
						if (new QqServerUser().checkUser(u))//进入数据库验证用户账号。
						{
							
							System.out.println("服务器接收到用户ID："+u.getUserId()+"用户密码："+u.getPassWd());//用于调试
							//返回一个成功登陆的信息报
							m.setMesType(MessageType.message_login_succeed);
							oos.writeObject(m);
							
							//开一个线程，保持与该客户端通信
							SerConClientThread scct = new SerConClientThread(s);
							ManageClientThread.addClientThread(u.getUserId(), scct);
							
							scct.start();
							
							//通知其他在线用户
							scct.notifyother(u.getUserId(),1);
							
							
							
							
						}
						else
						{
							System.out.println("验证错误了1111111111111");
							//返回一个登录失败的信息报
							m.setMesType(MessageType.message_login_fail);
							oos.writeObject(m);
							s.close();
						}
					}
					
				}
				else if (message.getMesType().equals(MessageType.message_request_register))
				{
					User u = message.getUser();
					Message m = new Message();//返回用户的消息
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					//注册用户，成功返回true
					if (new QqServerUser().registerUser(u))
					{
						//返回一个成功注册的信息报
						m.setMesType(MessageType.message_register_succeed);
						oos.writeObject(m);
					}
				}
				
				
			
			}
			
			
		} catch (Exception e)
		{
			e.printStackTrace();
			// TODO: handle exception
		}finally
		{
			
		}
	}
}
