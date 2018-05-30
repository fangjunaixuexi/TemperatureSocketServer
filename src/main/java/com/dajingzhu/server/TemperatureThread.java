package com.dajingzhu.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TemperatureThread extends Thread {

	private static ServerSocket SERVER_SOCKET = null;

	static {
		try {
			SERVER_SOCKET = new ServerSocket(8647);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// System.out.println("进入线程");
		Socket socket = null;
		while (true) {
			try {
				socket = SERVER_SOCKET.accept();
				// System.out.println("线程启动");
				new TemperatureThreadIn(socket).start();
				new TemperatureThreadOut(socket).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
