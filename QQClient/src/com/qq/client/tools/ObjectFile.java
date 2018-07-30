package com.qq.client.tools;

import java.awt.List;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.qq.common.Message;

public class ObjectFile
{
	//将一个对象写到文件末尾
	 public void WriteObjectToFile(Object o,String userid,String friendid)
	 {  
		 	File file = new File("data//"+userid+"_"+friendid+".dat");
	        try
	        {
	        	ObjectOutputStream os;
	        	if (file.length()<1)
				{
	        		os = new ObjectOutputStream(new FileOutputStream(file,true));   
		                     
				}
	        	else {
					os = new MyObjectOutputStream(new FileOutputStream(file,true));
				}
	            
	            os.writeObject(o);// 将Object对象写进文件  
	            os.close();  
	        } catch (FileNotFoundException e)
	        {  
	            e.printStackTrace();  
	        } catch (IOException e)
	        {  
	            e.printStackTrace();  
	        }        
    }  
	 //将文件所有对象读出
	 public ArrayList<Object> ReadAllObjectFromFile(String userid,String friendid)
	 {
		  ArrayList<Object> list = new ArrayList<Object>();
		  Object o;
		  ObjectInputStream is = null;
		  File file = new File("data//"+userid+"_"+friendid+".dat");
		  if (file.exists())
		  {
			  
			  try
			  {
				  is = new ObjectInputStream(new FileInputStream(file));
				  o = is.readObject();// 从流中读取Object的数据
				  while (o!=null)
				  {
					    
			    	  list.add(o);
			    	  o = is.readObject();// 从流中读取Object的数据
				  }	
				   
				           
			      
			  }
			  catch (EOFException e) {
				// TODO: handle exception
			  }
			  catch (IOException e)
			  {  
				  e.printStackTrace();  
			  } catch (ClassNotFoundException e) 
		      {  
		          e.printStackTrace();  
		      }
			  try
			  {
				  is.close();
			  } catch (IOException e)
			  {
					// TODO Auto-generated catch block
				  e.printStackTrace();
			  } 
			     
			  
		}
		return list; 
		 
    }  
	//删除文件
	public boolean deleteObjectFile(String userid,String friendid)
	{
		boolean b = false;
		File file = new File("data\\"+userid+"_"+friendid+".dat");
		if (file.exists())
		{
			  if(file.delete())
			  {
				  System.out.println("删除文件成功");
				  b = true;
			  }else
			  {
				  System.out.println("删除文件失败"+file.getName());
			  }    
		}
		return b;
	}
	public static void main(String[] args) 
	{
//		for (int i = 0; i < 10; i++)
//		{
//			Message message = new Message();
//			message.setContext("123");
//			new ObjectFile().WriteObjectToFile(message,"test");
//		}
		new ObjectFile().deleteObjectFile("2","1");
	}
}


class MyObjectOutputStream extends ObjectOutputStream 
{ 
	  public MyObjectOutputStream() throws IOException
	  {  
		  super(); 
	  }
	  public MyObjectOutputStream(OutputStream out) throws IOException
	  {
		  super(out);
	  } 
	  @Override 
	  protected void writeStreamHeader() throws IOException
	  { 
		  return;
	  }
} 
