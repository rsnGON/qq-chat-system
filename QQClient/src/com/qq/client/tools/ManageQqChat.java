package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.QqChat;

public class ManageQqChat
{
	private static HashMap hm = new HashMap<String, QqChat>();
	public static void addQqChat(String LoginIdAndFriendId,QqChat qqChat)
	{
		hm.put(LoginIdAndFriendId, qqChat);
	}
	public static void delQqChat(String LoginIdAndFriendId)
	{
		hm.remove(LoginIdAndFriendId);
	}
	public static QqChat getQqChat(String loginIdAndFriendId)
	{
		return (QqChat)hm.get(loginIdAndFriendId);
	}
	
}
