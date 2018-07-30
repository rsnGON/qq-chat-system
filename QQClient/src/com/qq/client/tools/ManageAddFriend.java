package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.AddFriend;
import com.qq.client.view.QqChat;

public class ManageAddFriend
{
	private static HashMap hm = new HashMap<String, AddFriend>();
	public static void addAddFriend(String userId,AddFriend addFriend)
	{
		hm.put(userId,addFriend);
	}
	public static void delAddFriend(String userId)
	{
		hm.remove(userId);
	}
	public static AddFriend getAddFriend(String userId)
	{
		return (AddFriend)hm.get(userId);
	}
}
