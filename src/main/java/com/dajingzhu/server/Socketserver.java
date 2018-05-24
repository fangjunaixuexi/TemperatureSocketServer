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
			System.out.println("******���������������ȴ��ͻ�������*****");
			Socket socket = null;
			while (true) {
				// ѭ�������ͻ��˵�����
				socket = SERVER_SOCKET.accept();
				InetAddress address = socket.getInetAddress();
				System.out.println(address);

				// ApplicationContext context = new
				// ClassPathXmlApplicationContext("classpath:applicationContext.xml");
				// �½�һ���߳�ServerSocket��������
				new ServerSocketThread(socket).start();
				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
