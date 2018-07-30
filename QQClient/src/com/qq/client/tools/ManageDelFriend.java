package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.AddFriend;
import com.qq.client.view.DelFriend;

public class ManageDelFriend
{
	private static HashMap hm = new HashMap<String, DelFriend>();
	public static void addDelFriend(String userId,DelFriend delFriend)
	{
		hm.put(userId,delFriend);
	}
	public static void delDelFriend(String userId)
	{
		hm.remove(userId);
	}
	public static DelFriend getDelFriend(String userId)
	{
		return (DelFriend)hm.get(userId);
	}
}
