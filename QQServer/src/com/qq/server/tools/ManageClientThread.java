package com.qq.server.tools;

import java.util.HashMap;
import java.util.Iterator;

import com.qq.server.model.SerConClientThread;

public class ManageClientThread
{
	public static HashMap hm = new HashMap<String,SerConClientThread>();
	public static void addClientThread(String uid,SerConClientThread ct)
	{
		hm.put(uid, ct);
		
	}
	public static SerConClientThread getClientThread(String uid)
	{
		return (SerConClientThread)hm.get(uid);
	}
	public static void delClientThread(String uid)
	{
		hm.remove(uid);
	}
	//返回当前在线的人情况
	public static String getAllOnLineUserId(String uid)
	{
		//使用迭代器完后
		Iterator it = hm.keySet().iterator();
		String res = "";
		String id;
		while (it.hasNext())
		{
			id = it.next().toString();
			if(!id.equals(uid))
			{
				res+=id+" ";
			}
			
			
		}
		return res;
	}
}
