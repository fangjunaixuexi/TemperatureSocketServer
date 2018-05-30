package com.dajingzhu.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HatThread extends Thread {
	private static ServerSocket SERVER_SOCKET2 = null;

	static {
		try {
			SERVER_SOCKET2 = new ServerSocket(45678);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		//System.out.println("进入线程2");
		Socket socket2 = null;
		while(true) {
		try {
			socket2 = SERVER_SOCKET2.accept();
			//System.out.println("启动线程2 ");
			new HatThreadInOut(socket2).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}

}
