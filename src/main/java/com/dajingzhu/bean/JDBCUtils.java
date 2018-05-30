package com.dajingzhu.bean;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JDBCUtils{
	public JdbcTemplate setJDBCtemplate(){
	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/Smart_site");
	dataSource.setUsername("root");
	dataSource.setPassword("123");
	// 2、创建jdbcTemplate对象，设置数据源
	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	return jdbcTemplate;
	}

}
