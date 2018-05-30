package com.dajingzhu.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dajingzhu.bean.Environmental_monitoring;
import com.dajingzhu.bean.Equipment;
import com.dajingzhu.dao.EnvironmentalDao;

public class TemperatureThreadIn extends Thread {
	private Socket socket;
	private InputStream inputStream;

	public TemperatureThreadIn(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		System.out.println("进入环境数据接收线程");
		try {
			int enviromental_id = 0;
			boolean flag = true;
			EnvironmentalDao environmentalDao = new EnvironmentalDao();
			List<String> serial_numberlist = environmentalDao.selectSerial_numberList();
			for (String equipment : serial_numberlist) {
				System.out.println(equipment);
			}
			while (true) {
				inputStream = socket.getInputStream();
				byte[] bytes = new byte[1024];
				int n = inputStream.read(bytes);
				// n代表以 整数形式返回实际读取的字节数
				String Inputdata = new String(bytes, 0, n, "utf-8");
				System.out.println("得到环境数据：");
				System.out.println(Inputdata);
				System.out.println("查看数据库是否有该序列号：");
				System.out.println(serial_numberlist.contains(Inputdata));
				// 只查询一次数据库得到序列号id，第二次id是第一次查询的id
				if (serial_numberlist.contains(Inputdata)) {
					System.out.println("发送的序列号正确");
					if (flag) {
						System.out.println("查询一次id");
						Equipment environmental = environmentalDao.selectEnvironmental(Inputdata);
						if (environmental != null) {
							System.out.println(environmental.getEnviromental_id());
							enviromental_id = environmental.getEnviromental_id();
							//查詢到的id
							System.out.println(enviromental_id);
							flag = false;
						}
					}

				} else {
					System.out.println("发送的序列号不正确或者是数据");
					String[] split = Inputdata.split(",");
					if (split.length < 4) {
						System.out.println("错误序列号");
						Date day = new Date();
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						System.out.println(df.format(day));
						socket.close();
					} else {
						// 数据存入数据库
						System.out.println("发送的是数据");
						System.out.println("查看匹配到的序列号id");
						System.out.println(enviromental_id);

						Environmental_monitoring environmental_monitoring = new Environmental_monitoring();

						environmental_monitoring.setEnviromental_id(enviromental_id);

						String str1 = split[0];
						double temperature = Double.parseDouble(str1.substring(1));
						environmental_monitoring.setTemperature(temperature);

						String str2 = split[1];
						double humidity = Double.parseDouble(str2);
						environmental_monitoring.setHumidity(humidity);

						String str3 = split[2];
						double illumination = Double.parseDouble(str3);
						environmental_monitoring.setIllumination(illumination);

						String str4 = split[3];
						double noise = Double.parseDouble(str4);
						environmental_monitoring.setNoise(noise);

						String str5 = split[4];
						double pm2 = Double.parseDouble(str5);
						environmental_monitoring.setPm2(pm2);

						String str6 = split[5];
						double pm10 = Double.parseDouble(str6);
						environmental_monitoring.setPm10(pm10);

						Date day = new Date();
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String sendtime = df.format(day);
						environmental_monitoring.setSendtime(sendtime);

						int i = environmentalDao.insertEnvironmental_monitoring(environmental_monitoring);
						if (i >= 1) {
							System.out.println("环境数据已存入数据库");
							System.out.println();
						} else {
							System.out.println("环境数据插入数据库失败");
						}
					}
				}
				System.out.println("============");
			}
		} catch (Exception e) {
			try {
				inputStream.close();
				socket.close();
				System.out.println("环境socket连接意外关闭");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
