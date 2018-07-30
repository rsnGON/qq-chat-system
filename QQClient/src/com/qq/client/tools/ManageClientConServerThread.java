/**
 * 管理客户端和服务器保持通讯的线程类
 */

package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.model.ClientConServerThread;

public class ManageClientConServerThread
{
	private static HashMap hm=new HashMap<String, ClientConServerThread>();
	public static void addClientConServerthread(String qqId,ClientConServerThread ccst)
	{
		hm.put(qqId, ccst);
	}
	public static void  delClientConServerthread(String qqId)
	{
		hm.remove(qqId);
	}
	public static ClientConServerThread getClientConServerThread(String qqId)
	{
		return (ClientConServerThread)hm.get(qqId);
	}
}
