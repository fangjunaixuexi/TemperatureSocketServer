package com.dajingzhu.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Socketserver {
	private static ServerSocket SERVER_SOCKET = null;;
	
	static {
		try {
			SERVER_SOCKET = new ServerSocket(8647);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		try {
		
			Socket socket = null;
			System.out.println("服务器已启动");
			while (true) {
				socket = SERVER_SOCKET.accept();
				System.out.println("SOCKET连接已建立");
				InetAddress address = socket.getInetAddress();
				System.out.println(address);
				new ServerSocketThread(socket).start();
				new ServerSocketThread2(socket).start();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
