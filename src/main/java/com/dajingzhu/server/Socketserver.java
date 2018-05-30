package com.dajingzhu.server;

public class Socketserver {

	public static void main(String[] args) {

		try {
			System.out.println("服务器已启动");

			new TemperatureThread().start();

			new HatThread().start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
