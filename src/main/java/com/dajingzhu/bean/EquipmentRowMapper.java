package com.dajingzhu.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EquipmentRowMapper implements RowMapper<Equipment> {

	@Override
	public Equipment mapRow(ResultSet rs, int rowNum) throws SQLException {
		  //        获取结果集中的数据
        int id = rs.getInt("id");
         int enviromental_id = rs.getInt("enviromental_id");
         String serial_number = rs.getString("serial_number");
         
//        把数据封装成对象
         Equipment equipment = new Equipment();
         equipment.setEnviromental_id(enviromental_id);
         equipment.setId(id);
         equipment.setSerial_number(serial_number);
        
		return equipment;
	}

}
