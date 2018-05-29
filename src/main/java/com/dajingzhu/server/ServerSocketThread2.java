package com.dajingzhu.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;

public class ServerSocketThread2 extends Thread {
	private Socket socket;
	private OutputStream outputStream;

	public ServerSocketThread2(Socket socket) {
		this.socket=socket;
	}
	@Override
	public void run() {
		try {
		
			while(true) {
	         outputStream = socket.getOutputStream();
	         final String socketSend = "OK!123";
		try {
			  Thread.sleep(1 * 1000);
              outputStream.write(socketSend.getBytes("UTF-8"));
              outputStream.flush();
              System.out.println("数据已发送");
		} catch (Exception e) {
			System.out.println("客户端 已断开");
			outputStream.close();
			socket.close();
		}
			}
		} catch (Exception e) {
			try {
				System.out.println("通道意外关闭");
				outputStream.close();
				socket.close();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		
	}
	}

}
