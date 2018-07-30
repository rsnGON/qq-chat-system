/**
 * 这是客户端连接服务器的后台
 */
package com.qq.client.model;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.LoginRegisterMessage;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

public class QqClientConnServer
{
	private Socket s;
	
//	public QqClientConnServer()
//	{
//		try
//		{
//			Socket s = new Socket("127.0.0.1",9999);
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//			// TODO: handle exception
//		}finally
//		{
//			
//		}
//		
//	}
	public int sendLoginInfoToServer(Object o)//0登录失败1登录成功2已经登录
	{
		int b = 0;
		try
		{
			
			s = new Socket("127.0.0.1",9999);
			LoginRegisterMessage message = new LoginRegisterMessage();
			message.setMesType(MessageType.message_request_login);
			message.setUser((User)o);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(message);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms = (Message)ois.readObject();
			System.out.println("是1就是而登录成功"+ms.getMesType());
			if (ms.getMesType().equals(MessageType.message_login_succeed))
			{
				//创建一个该qq号与服务器端保持通讯连接的线程
				ClientConServerThread ccst = new ClientConServerThread(s);
				ccst.start();
				ManageClientConServerThread.addClientConServerthread(((User)o).getUserId(), ccst);
				b = 1;
				
			}
			else if(ms.getMesType().equals(MessageType.message_have_logined))
			{
				b = 2;
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
			// TODO: handle exception
		}
		return b;
	}
	public boolean SendRegisetrInfoToServer(Object o)
	{
		boolean b = false;
		try
		{
			
			s = new Socket("127.0.0.1",9999);
			LoginRegisterMessage message = new LoginRegisterMessage();
			message.setMesType(MessageType.message_request_register);
			message.setUser((User)o);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(message);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms = (Message)ois.readObject();
			if (ms.getMesType().equals(MessageType.message_register_succeed));
			{
				
				b = true;	
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
			// TODO: handle exception
		}
		try
		{
			s.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		
	}
	
}
