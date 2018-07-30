package com.qq.client.model;

import com.qq.common.User;

public class QqClientUser
{
	public int checkUser(User u)//0µÇÂ¼Ê§°Ü1µÇÂ¼³É¹¦2ÒÑ¾­µÇÂ¼
	{
		return new QqClientConnServer().sendLoginInfoToServer(u);
	}
	public boolean registerUser(User u)
	{
		return new QqClientConnServer().SendRegisetrInfoToServer(u);
	}

}
