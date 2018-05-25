package com.dajingzhu.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;

public class ServerSocketThread extends Thread {
	private Socket socket;
	private InputStream inputStream;

	public ServerSocketThread(Socket socket) {
		this.socket=socket;
	}
	@Override
	public void run() {
		try {
			while(true) {
				inputStream=socket.getInputStream();
				System.out.println("接收数据连接通道建立");
	         byte[] bytes = new byte[1024];
			 int n = inputStream.read(bytes);
			 String Inputdata=new String(bytes, 0, n);
	         System.out.println(Inputdata);
	         Socketserver.map=new HashMap<>();
	         Socketserver.map.put(1, "12345456");
			}
		} catch (Exception e) {
			try {
				System.out.println("连接意外关闭");
				inputStream.close();
				socket.close();
			} catch (IOException e1) {
				System.out.println("socket连接断开");
				e1.printStackTrace();
			}
			e.printStackTrace();
			try {
				inputStream.close();
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	
			
	}
	}

}
