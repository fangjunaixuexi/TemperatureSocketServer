package com.dajingzhu.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerSocketThread extends Thread {
	private Socket socket;
	private OutputStream outputStream;
	private InputStream inputStream;

	public ServerSocketThread(Socket socket) {
		this.socket=socket;
	}
	@Override
	public void run() {
		try {
			 if(!socket.getKeepAlive()) socket.setKeepAlive(true);//true������ʱ��û��������Ͽ�
	         if(!socket.getOOBInline())socket.setOOBInline(true);//true,�����ͽ������ݣ���������
	         inputStream=socket.getInputStream();//���socket������
	         byte[] bytes = new byte[1024];
				//read��byte []������,��������ʽ����ʵ�ʶ�ȡ���ֽ�����
			 int n = inputStream.read(bytes);
			 String Inputdata=new String(bytes, 0, n);
	         System.out.println(Inputdata);
	         outputStream = socket.getOutputStream();//���socket�������
	         final String socketContent = "TCPOK";
		try {
			  Thread.sleep(10 * 1000);//10s����һ������
              outputStream.write(socketContent.getBytes("UTF-8"));
              outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��������ʧ��");
			outputStream.close();
		}
		} catch (Exception e) {
			try {
				inputStream.close();
				socket.close();
			} catch (IOException e1) {
				System.out.println("socket�����쳣�ر�");
				
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("��������ʧ��");
	}
	}

}
