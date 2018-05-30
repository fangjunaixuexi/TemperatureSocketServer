package com.dajingzhu.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Environmental_monitoringRowMapper implements RowMapper<Environmental_monitoring>{
		 
		     public Environmental_monitoring mapRow(ResultSet resultSet, int i) throws SQLException {
		  //        获取结果集中的数据
		         int enviromental_id = resultSet.getInt("enviromental_id");
		          double humidity = resultSet.getDouble("humidity");
		          double illumination = resultSet.getDouble("illumination");
		          double noise = resultSet.getDouble("noise");
		          double pm10 = resultSet.getDouble("pm10");
		          double pm2 = resultSet.getDouble("pm2");
		          double temperature = resultSet.getDouble("temperature");
		          String sendtime=resultSet.getString("sendtime");
		          
		 //        把数据封装成User对象
		          Environmental_monitoring environmental_monitoring = new Environmental_monitoring();
		          environmental_monitoring.setEnviromental_id(enviromental_id);
		          environmental_monitoring.setHumidity(humidity);
		          environmental_monitoring.setIllumination(illumination);
		          environmental_monitoring.setNoise(noise);
		          environmental_monitoring.setPm10(pm10);
		          environmental_monitoring.setPm2(pm2);
		          environmental_monitoring.setTemperature(temperature);
		          environmental_monitoring.setSendtime(sendtime);
		         return environmental_monitoring;
		     }
		}

