/**
 * 定义信息的种类
 */
package com.qq.common;

public interface MessageType
{
	String message_request_login = "0";//请求登录消息
	String message_login_succeed = "1";//表明登陆成功
	String message_login_fail = "2";//表明登录失败
	String message_comm_mes = "3";//普通聊天信息
	String message_get_onLineFriend = "4";//要求在线好友的信息
	String message_ret_onLineFriend="5";//返回在线好友的信息
	String message_request_register="6";//请求注册消息
	String message_register_succeed= "7";//表明注册成功
	String message_register_fail = "8";//表明注册失败
	String message_get_friendlist = "9";//要求好友列表消息
	String message_ret_friendlist = "10";//返回好友列表消息
	String message_request_offline = "11";//请求下线
	String message_offline_succeed = "12";//下线成功
	String message_ret_offlineFriend = "13";//返回下线好友信息
	String message_have_logined = "14";//表明已经登录
	String message_request_addFriend = "15";//请求添加好友
	String message_request_delFriend = "16";//请求删除好友
	String message_ret_addFriend_result = "17";//返回添加好友信息的结果
	String message_ret_delFriend_result = "18";//返回删除好友信息的结果
	String message_request_offline_message = "19";//请求好友发送的离线消息
	String message_offline_message = "20";//离线消息
}
