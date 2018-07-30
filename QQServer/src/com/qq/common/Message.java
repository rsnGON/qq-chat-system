package com.qq.common;

import javax.tools.JavaCompiler;

public class Message implements java.io.Serializable
{
	private String mesType;
	private String sender;
	private String getter;
	private String context;
	private String sendTime;
	

	public String getMesType()
	{
		return mesType;
	}

	public void setMesType(String mesType)
	{
		this.mesType = mesType;
	}

	public String getSender()
	{
		return sender;
	}

	public void setSender(String sender)
	{
		this.sender = sender;
	}

	public String getGetter()
	{
		return getter;
	}

	public void setGetter(String getter)
	{
		this.getter = getter;
	}

	public String getContext()
	{
		return context;
	}

	public void setContext(String context)
	{
		this.context = context;
	}

	public String getSendTime()
	{
		return sendTime;
	}

	public void setSendTime(String sendTime)
	{
		this.sendTime = sendTime;
	}
	
}
