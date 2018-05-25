package com.dajingzhu.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;


public class Socketserver {
	private static ServerSocket SERVER_SOCKET = null;;
	public static Map map;
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
			
			while (true) {
				socket = SERVER_SOCKET.accept();
				System.out.println("服务器已启动");
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
