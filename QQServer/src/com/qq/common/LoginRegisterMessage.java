package com.qq.common;

public class LoginRegisterMessage extends Message
{
	private User user;
	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}
