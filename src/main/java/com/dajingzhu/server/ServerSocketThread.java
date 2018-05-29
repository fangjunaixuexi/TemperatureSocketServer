package com.dajingzhu.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dajingzhu.bean.Environmental_monitoring;
import com.dajingzhu.bean.Equipment;
import com.dajingzhu.dao.EnvironmentalDao;

public class ServerSocketThread extends Thread {
	private Socket socket;
	private InputStream inputStream;
	private List<String> serial_numberlist;

	public ServerSocketThread(Socket socket, List<String> serial_numberlist) {
		this.socket = socket;
		this.serial_numberlist = serial_numberlist;
	}

	@Override
	public void run() {
		try {
			int enviromental_id = 0;
			boolean flag = true;
			while (true) {
				inputStream = socket.getInputStream();
				System.out.println("接收数据连接通道建立");
				
				byte[] bytes = new byte[1024];
				int n = inputStream.read(bytes);
				//n代表以    整数形式返回实际读取的字节数
				System.out.println(n);
				String Inputdata = new String(bytes, 0, n,"utf-8");
				
				System.out.println(Inputdata);
				
				System.out.println(serial_numberlist.contains(Inputdata));
				// 查找数据库得到序列号id
				EnvironmentalDao environmentalDao = new EnvironmentalDao();

				// 只查询一次数据库得到序列号id，第二次id是第一次查询的id
				if (flag) {
					Equipment environmental = environmentalDao.selectEnvironmental(Inputdata);
					if (environmental != null) {
						System.out.println(environmental.getEnviromental_id());
						enviromental_id = environmental.getEnviromental_id();
						System.out.println(enviromental_id);
						flag = false;
					}
				}

				if (serial_numberlist.contains(Inputdata)) {
					System.out.println("发送的序列号正确");
				} else {
					System.out.println("发送的序列号不正确或者是数据");
					String[] split = Inputdata.split(",");
					if (split.length < 4) {
						System.out.println("错误序列号");
					
					} else {
						// 数据存入数据库
						System.out.println("发送的是数据");
						System.out.println(enviromental_id);

						Environmental_monitoring environmental_monitoring = new Environmental_monitoring();

						environmental_monitoring.setEnviromental_id(enviromental_id);

						String str1 = split[0];
						double temperature = Double.parseDouble(str1.substring(1));
						System.out.println(temperature);
						environmental_monitoring.setTemperature(temperature);

						String str2 = split[1];
						double humidity = Double.parseDouble(str2);
						System.out.println(humidity);
						environmental_monitoring.setHumidity(humidity);

						String str3 = split[2];
						double illumination = Double.parseDouble(str3);
						System.out.println(illumination);
						environmental_monitoring.setIllumination(illumination);

						String str4 = split[3];
						double noise = Double.parseDouble(str4);
						System.out.println(noise);
						environmental_monitoring.setNoise(noise);

						String str5 = split[4];
						double pm2 = Double.parseDouble(str5);
						System.out.println(pm2);
						environmental_monitoring.setPm2(pm2);

						String str6 = split[5];
						double pm10 = Double.parseDouble(str6);
						System.out.println(pm10);
						environmental_monitoring.setPm10(pm10);
						
						Date day=new Date();    
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
						System.out.println(df.format(day));  
						String sendtime=df.format(day);
						environmental_monitoring.setSendtime(sendtime);

						int i = environmentalDao.insertEnvironmental_monitoring(environmental_monitoring);
						System.out.println(i);
					}

					/*
					 * EnvironmentalDao environmentalDao = new EnvironmentalDao(); Equipment
					 * environmental = environmentalDao.selectEnvironmental(Inputdata);
					 * System.out.println(environmental); if (environmental==null) {
					 * Socketserver.map = new HashMap<>(); InetAddress address =
					 * socket.getInetAddress(); Socketserver.map.put(address, null); }else {
					 * Socketserver.map = new HashMap<>(); InetAddress address =
					 * socket.getInetAddress(); Socketserver.map.put(address,
					 * environmental.getSerial_number()); }
					 */

				}

				System.out.println("============");

				/*
				 * System.out.println("查找成功"); Socketserver.map = new HashMap<>(); InetAddress
				 * address = socket.getInetAddress(); Socketserver.map.put(address,
				 * environmental.getSerial_number());
				 */
			}
		} catch (Exception e) {
			try {

				inputStream.close();
				socket.close();
				System.out.println("连接意外关闭");
			} catch (IOException e1) {
				System.out.println("socket连接断开");
				e1.printStackTrace();
			}
			try {
				inputStream.close();
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
