package com.dajingzhu.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.dajingzhu.dao.EnvironmentalDao;


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
			EnvironmentalDao environmentalDao = new EnvironmentalDao();
			List<String> serial_numberlist=environmentalDao.selectSerial_numberList();
			for (String equipment : serial_numberlist) {
				System.out.println(equipment);
			}
			Socket socket = null;
			System.out.println("服务器已启动");
			while (true) {
				socket = SERVER_SOCKET.accept();
				System.out.println("SOCKET连接已建立");
				InetAddress address = socket.getInetAddress();
				System.out.println(address);
				new ServerSocketThread(socket,serial_numberlist).start();
				new ServerSocketThread2(socket).start();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
