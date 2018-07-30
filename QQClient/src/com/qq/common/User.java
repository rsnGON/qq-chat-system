/**
 * 这是用户信息类
 */
package com.qq.common;

import javax.tools.JavaCompiler;

public class User implements java.io.Serializable
{
	private String userId;
	private String passWd;
	
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public String getPassWd()
	{
		return passWd;
	}
	public void setPassWd(String passWd)
	{
		this.passWd = passWd;
	}
}
