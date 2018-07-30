package com.qq.common;

public class StringAnalysis
{
	/**
	 * 判断指定的字符串是否为纯的数字字符串
	 * @param userID	要判断的字符串
	 * @return	是否为纯的数字字符串
	 */
	public static boolean isDigit(String userID)
	{
		userID=userID.trim();
		for(int i=0;i<userID.length();i++)
		{
			if(!Character.isDigit(userID.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}
	 public static boolean isHave(String[] strs,String s)
	 {
		 //此方法有两个参数，第一个是要查找的字符串数组，第二个是要查找的字符或字符串
		 
		 for(int i=0;i<strs.length;i++)
		 {
			 if(strs[i].equals(s))
			 {//循环查找字符串数组中的每个字符串中是否包含所有查找的内容
				 return true;//查找到了就返回真，不在继续查询
			 }
		 }
		 	return false;//没找到返回false
	 }
}
