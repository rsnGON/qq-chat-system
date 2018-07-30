/**
 * 操作数据库user
 */
package com.qq.server.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.qq.common.Message;
import com.qq.common.User;
import com.qq.server.db.SqlHelper;

public class QqServerUser
{
	public boolean checkUser(User u)
	{
		boolean b = false;
		//进入数据库验证用户账号。
		String sql = "select passwd from user where userid = ?";
		String[] parameters = { u.getUserId()};  
		SqlHelper.getConnection();
		ResultSet rs = SqlHelper.executeQuery(sql, parameters);
		String passwd = null;
		try
		{
			if(rs.next()) 
			{  
			        passwd = rs.getString("passwd");
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		SqlHelper.close(SqlHelper.getPs(), SqlHelper.getConn());
		if(u.getPassWd().equals(passwd))	
		{
			
			b = true;
		}
		return b;
	}
	
	//注册用户
	public boolean registerUser(User u)
	{
		boolean b = false;
		if(!existUser(u))
		{
			String sql = "insert into user(userid,passwd) values(?,?)";
			String[] parameters = { u.getUserId(),u.getPassWd() }; 
			SqlHelper.getConnection();
			SqlHelper.executeUpdate(sql, parameters); 
			SqlHelper.close(SqlHelper.getPs(), SqlHelper.getConn());
			b = true;
		}
		return b;
	}
	//查询用户是否存在
	public boolean existUser(User u)
	{
		boolean b = false;
		String sql = "select * from user where userid = ?";
		String[] parameters = { u.getUserId()};  
		SqlHelper.getConnection();
		ResultSet rs = SqlHelper.executeQuery(sql, parameters);
		try
		{
			if(rs.next()) 
			{  
			        b = true;
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlHelper.close(SqlHelper.getPs(), SqlHelper.getConn());
		
		return b;
	}
	public boolean existUser(String userId)
	{
		boolean b = false;
		String sql = "select * from user where userid = ?";
		String[] parameters = {userId};  
		SqlHelper.getConnection();
		ResultSet rs = SqlHelper.executeQuery(sql, parameters);
		try
		{
			if(rs.next()) 
			{  
			        b = true;
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlHelper.close(SqlHelper.getPs(), SqlHelper.getConn());
		
		return b;
	}
	//获得朋友列表
	public static String getFriendList(String userid)
	{
		String res = "";
		String sql = "select friendid from friend where userid = ?";
		String[] parameters = {userid};  
		SqlHelper.getConnection();
		ResultSet rs = SqlHelper.executeQuery(sql, parameters);
		try
		{
			while(rs.next()) 
			{  
				res+=rs.getString("friendid")+" ";    
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlHelper.close(SqlHelper.getPs(), SqlHelper.getConn());
		return res;
	}
	//获得用户的离线消息
	public static ArrayList<Message> getUserOfflineMessage(String userId)
	{
		ArrayList<Message> list = new ArrayList<Message>() ;
		String sql = "select * from message where getter = ?";
		String[] parameters = {userId};
		SqlHelper.getConnection();
		ResultSet rs = SqlHelper.executeQuery(sql, parameters);
		try
		{
			while(rs.next())
			{  
					Message message = new Message();
					message.setMesType(rs.getString("type"));
					message.setSender(rs.getString("sender"));
					message.setGetter(rs.getString("getter"));
					message.setContext(rs.getString("context"));
					message.setSendTime(rs.getString("sendtime"));
			        list.add(message);
			        //测试
			        System.out.println(message.getMesType()+":"+message.getSender()+":"
			        		+message.getGetter()+message.getContext()+message.getSendTime());
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlHelper.close(SqlHelper.getPs(), SqlHelper.getConn());
		return list;
	}
	//删除离线消息
	public static void delUserOfflineMessage(String userId)
	{
		String sql = "delete from message where getter = ?";
		String[] parameters = {userId}; 
		SqlHelper.getConnection();
		SqlHelper.executeUpdate(sql, parameters);
		SqlHelper.close(SqlHelper.getPs(), SqlHelper.getConn());
	}
	//检查Id是否为好友
	public boolean isFriend(String userId,String friendId)
	{
		boolean b = false;
		String sql = "select * from friend where userid = ? and friendid = ?";
		String[] parameters = {userId,friendId};  
		SqlHelper.getConnection();
		ResultSet rs = SqlHelper.executeQuery(sql, parameters);
		try
		{
			if(rs.next()) 
			{  
			        b = true;
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlHelper.close(SqlHelper.getPs(), SqlHelper.getConn());
		
		return b;
	}
	//添加好友
	public int addFriend(String userId,String friendId)
	{
		int result=1;//1:添加成功2:已经是好友3:没有该用户
		if (!existUser(friendId))
		{
			return 3;
		}
		if (isFriend(userId, friendId))
		{
			return 2;
		}
		
		String sql = "insert into friend(userid,friendid) values(?,?)";
		String[] parameters1 = { userId,friendId}; 
		String[] parameters2 = { friendId,userId};//给对方添加userid
		SqlHelper.getConnection();
		SqlHelper.executeUpdate(sql, parameters1);
		SqlHelper.executeUpdate(sql, parameters2);
		SqlHelper.close(SqlHelper.getPs(), SqlHelper.getConn());
		return result;
	}
	public int delFriend(String userId,String friendId)
	{
		int result=1;//1:删除成功2:不是好友
		if (!isFriend(userId, friendId))
		{
			return 2;
		}
		String sql = "delete from friend where userid=? and friendid=?";
		String[] parameters1 = { userId,friendId}; 
		String[] parameters2 = { friendId,userId};//给对方删除userid
		SqlHelper.getConnection();
		SqlHelper.executeUpdate(sql, parameters1);
		SqlHelper.executeUpdate(sql, parameters2);
		SqlHelper.close(SqlHelper.getPs(), SqlHelper.getConn());
		return result;
	}
	//存聊天消息到数据库
	public static void StoreMessageToDatabase(Message m)
	{
		String sql = "insert into message(type,sender,getter,context,sendtime) values(?,?,?,?,?)";
		String[] parameters = {m.getMesType(),m.getSender(),m.getGetter(),m.getContext(),m.getSendTime()};
		SqlHelper.getConnection();
		SqlHelper.executeUpdate(sql, parameters);
		SqlHelper.close(SqlHelper.getPs(), SqlHelper.getConn());
	}
	
}
