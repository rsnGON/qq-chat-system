package com.qq.client.tools;

import java.applet.AudioClip;
import java.io.File;
import java.net.URL;

import javax.swing.JApplet;
import javax.swing.JFileChooser;



public class Player
{
	public void haveMessage()//消息提醒
	{
		AudioClip adc;
		File file;

		file = new File("sound\\havemess.au");
			try
			{
				URL url = new URL("file:"+file.getPath());
				adc = JApplet.newAudioClip(url);
				adc.play();
			} catch (Exception e1)
			{
					e1.printStackTrace();
					// TODO: handle exception
			}
	}
	public void haveFriendOnline()//好友上线提醒
	{
		AudioClip adc;
		File file;

		file = new File("sound\\friendOnline.au");
			try
			{
				URL url = new URL("file:"+file.getPath());
				adc = JApplet.newAudioClip(url);
				adc.play();
			} catch (Exception e1)
			{
					e1.printStackTrace();
					// TODO: handle exception
			}
	}
	public static void main(String[] args) 
	{
		new Player().haveMessage();
	}
}
