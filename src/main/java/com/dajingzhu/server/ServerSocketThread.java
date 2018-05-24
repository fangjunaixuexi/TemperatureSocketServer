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
			 if(!socket.getKeepAlive()) socket.setKeepAlive(true);//true，若长时间没有连接则断开
	         if(!socket.getOOBInline())socket.setOOBInline(true);//true,允许发送紧急数据，不做处理
	         inputStream=socket.getInputStream();//获得socket输入流
	         byte[] bytes = new byte[1024];
				//read（byte []）方法,以整数形式返回实际读取的字节数。
			 int n = inputStream.read(bytes);
			 String Inputdata=new String(bytes, 0, n);
	         System.out.println(Inputdata);
	         outputStream = socket.getOutputStream();//获得socket的输出流
	         final String socketContent = "TCPOK";
		try {
			  Thread.sleep(10 * 1000);//10s发送一次心跳
              outputStream.write(socketContent.getBytes("UTF-8"));
              outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("发送心跳失败");
			outputStream.close();
		}
		} catch (Exception e) {
			try {
				inputStream.close();
				socket.close();
			} catch (IOException e1) {
				System.out.println("socket连接异常关闭");
				
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("建立连接失败");
	}
	}

}
