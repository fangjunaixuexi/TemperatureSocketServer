package com.dajingzhu.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dajingzhu.bean.Environmental_monitoring;
import com.dajingzhu.bean.Equipment;
import com.dajingzhu.bean.EquipmentRowMapper;
import com.dajingzhu.bean.JDBCUtils;
import com.dajingzhu.bean.Location;

@Repository
public class EnvironmentalDao {
	private static JDBCUtils jdbc = new JDBCUtils();
	private static JdbcTemplate jdbcTemplate = jdbc.setJDBCtemplate();

	public Equipment selectEnvironmental(String serial_number) {

		System.out.println("连接数据库成功，发送sql语句");
		String sql = "select * from equipment where serial_number=?";
		Equipment equipment = null;
		try {
			equipment = jdbcTemplate.queryForObject(sql, new Object[] { serial_number }, new EquipmentRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return equipment;
	}

	public List<String> selectSerial_numberList() {
		
		String sql = "select serial_number from equipment ";
		List<String> serial_numberlist = jdbcTemplate.queryForList(sql, java.lang.String.class);

		return serial_numberlist;

	}

	public int insertEnvironmental_monitoring(Environmental_monitoring environmental_monitoring) {
		
		 String sql ="insert into environmental_monitoring(enviromental_id,temperature,humidity,illumination,noise,pm2,pm10,sendtime) values(?,?,?,?,?,?,?,?)" ; 
		 // 执行插入
		 int result = jdbcTemplate.update(sql, new Object[] {
		  environmental_monitoring.getEnviromental_id(),
		  environmental_monitoring.getTemperature(),
		  environmental_monitoring.getHumidity(),
		  environmental_monitoring.getIllumination(),
		  environmental_monitoring.getNoise(), environmental_monitoring.getPm2(),
		  environmental_monitoring.getPm10(), environmental_monitoring.getSendtime()
		  });

	return result;

}
	public int insertLocation(Location location) {
		  String sql="insert into location(data_length,data_number,north_latitude,east_longitude,version,transmission_speed,safety_helmet_angle,satellite_number,level_factor,transmission_time,signal_intensity,alarm_value,state,battery_voltage,battery_level) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  
	        //执行插入  
	        int result=jdbcTemplate.update(sql, new Object[]{location.getData_length(),location.getData_number(),
	        													location.getNorth_latitude(),location.getEast_longitude(),location.getVersion(),
	        													location.getTransmission_speed(),location.getSafety_helmet_angle(),
	        													location.getSatellite_number(),location.getLevel_factor(),location.getTransmission_time(),
	        													location.getSignal_intensity(),location.getAlarm_value(),location.getState(),
	        													location.getBattery_voltage(),location.getBattery_level()});  
		return result;
	}


}
