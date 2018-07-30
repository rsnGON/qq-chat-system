package com.qq.server.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Types;
  
import com.qq.server.db.*;//必须引入的  

  
public class TestSqlHelper {  
    // 测试SqlHelper  
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		TestSqlHelper testSqlHelper = new TestSqlHelper();
		testSqlHelper.testSqlHelper3();
	}
    public void testSqlHelper1() {// 一条SQL语句insert/update/delete  
        //testInsert();  //三行代码分别注释其它俩行测试
        //testUpdate();  
        //testDelete();  
    }  
   
    public void testSqlHelper3() {// 测试SQl的Select语句  
        testQuery();  
    }  
  

  
    private void testInsert() { 
    	SqlHelper.getConnection();
        String sql = "INSERT INTO user (userid,passwd) VALUES (?,?)";  
        String[] parameters = { "3","123456" };  
        SqlHelper.executeUpdate(sql, parameters); 
        SqlHelper.close(SqlHelper.getPs(), SqlHelper.getConn());
    }  
  
    private void testUpdate() {  
    	SqlHelper.getConnection();
        String sql = "UPDATE user SET passwd=? WHERE userid = '1'";  
        String[] parameters = { "1234567" };  
        SqlHelper.executeUpdate(sql, parameters); 
        SqlHelper.close(SqlHelper.getPs(), SqlHelper.getConn());
    }  
  
    private void testDelete() {  
    	SqlHelper.getConnection();
        String sql = "DELETE FROM user WHERE userid = ?";  
        String[] parameters = { "1" };  
        SqlHelper.executeUpdate(sql, parameters);
        SqlHelper.close(SqlHelper.getPs(), SqlHelper.getConn());
        
    }  
  
    private void testQuery() 
    {  
    	SqlHelper.getConnection();
    	String sql = "select * from user";  
        try 
        {  
            ResultSet rs = SqlHelper.executeQuery(sql, null);  
            while (rs.next()) 
            {  
                System.out.println("userid:" + rs.getString("userid")  
                        + "\tpasswd:" + rs.getString("passwd"));  
            }  
        } catch (SQLException e)
        {  
            e.printStackTrace();  
        } 
        finally
        {
        	SqlHelper.close(SqlHelper.getPs(), SqlHelper.getConn());
        }
    }  
}  