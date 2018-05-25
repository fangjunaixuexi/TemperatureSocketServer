package com.dajingzhu.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

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
	         System.out.println("传输数据连接通道已建立");
	         final String socketSend = "TCPOK";
		try {
			  Thread.sleep(10 * 1000);
              outputStream.write(socketSend.getBytes("UTF-8"));
              outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
			outputStream.close();
		}
			}
		} catch (Exception e) {
			try {
				System.out.println("通道意外关闭");
				System.out.println("客户端 已断开");
				socket.close();
				outputStream.close();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
			try {
				socket.close();
				outputStream.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	}
	}

}
