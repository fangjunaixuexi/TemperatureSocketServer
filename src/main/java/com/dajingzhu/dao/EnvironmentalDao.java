package com.dajingzhu.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dajingzhu.bean.Environmental_monitoring;
import com.dajingzhu.bean.Equipment;
import com.dajingzhu.bean.EquipmentRowMapper;
import com.dajingzhu.bean.JDBCUtils;

@Repository
public class EnvironmentalDao {
	private static JDBCUtils jdbc = new JDBCUtils();
	private static JdbcTemplate jdbcTemplate = jdbc.setJDBCtemplate();

	public Equipment selectEnvironmental(String serial_number) {
		/*
		 * Connection conn = null; PreparedStatement pstmt = null; ResultSet rs = null;
		 * Equipment equipment = new Equipment(); JDBCUtils jdbcUtils =
		 * JDBCUtils.getjdbcUtils(); try { // 1.获取连接 conn = jdbcUtils.getConnection();
		 * // 2.编写sql语句 String sql = "select * from equipment where serial_number=?"; //
		 * 3.获取执行sql语句对象 pstmt = conn.prepareStatement(sql); // 4.设置参数
		 * pstmt.setString(1, serial_number); // 5.执行查询操作 rs = pstmt.executeQuery();
		 * while(rs.next()) { System.out.println("打印查询的结果：");
		 * System.out.println(rs.getInt(2) + "----" + rs.getString("enviromental_id"));
		 * int enviromental_id = rs.getInt(2);
		 * equipment.setEnviromental_id(enviromental_id); } } catch (Exception e) {
		 * throw new RuntimeException(e); } finally { // 6.释放资源 jdbcUtils.release(conn,
		 * pstmt, rs); } return equipment; }
		 */

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
		/*
		 * Connection conn = null; PreparedStatement pstmt = null; ResultSet rs = null;
		 * List<String> list = new ArrayList<String>(); JDBCUtils jdbcUtils =
		 * JDBCUtils.getjdbcUtils(); try { // 1.获取连接 conn = jdbcUtils.getConnection();
		 * // 2.编写sql语句 String sql = "select serial_number from equipment"; //
		 * 3.获取执行sql语句对象 pstmt = conn.prepareStatement(sql); // 5.执行查询操作 rs =
		 * pstmt.executeQuery(); ResultSetMetaData md = rs.getMetaData(); //
		 * 获得结果集结构信息,元数据 int columnCount = md.getColumnCount(); while (rs.next()) {
		 * String rowData = null; for (int i = 1; i <= columnCount; i++) { rowData =
		 * rs.getString(md.getColumnName(i)); } list.add(rowData); } } catch (Exception
		 * e) { e.printStackTrace(); throw new RuntimeException(e); } finally { //
		 * 6.释放资源 jdbcUtils.release(conn, pstmt, rs); } return list;
		 */

		/*
		 * DriverManagerDataSource dataSource = new DriverManagerDataSource();
		 * dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		 * dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/environmental_science");
		 * dataSource.setUsername("root"); dataSource.setPassword("123"); // //
		 * 2、创建jdbcTemplate对象，设置数据源 JdbcTemplate jdbcTemplate = new
		 * JdbcTemplate(dataSource);
		 */
		String sql = "select serial_number from equipment ";
		List<String> serial_numberlist = jdbcTemplate.queryForList(sql, java.lang.String.class);

		return serial_numberlist;

	}

	public int insertEnvironmental_monitoring(Environmental_monitoring environmental_monitoring) {
		/*Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		JDBCUtils jdbcUtils = JDBCUtils.getjdbcUtils();
		try {
			// 1.获取连接
			conn = jdbcUtils.getConnection();
			// 2.编写sql语句
			String sql = "insert into environmental_monitoring(enviromental_id,temperature,humidity,illumination,noise,pm2,pm10,sendtime) values(?,?,?,?,?,?,?,?)";
			// 3.获取执行sql语句对象
			pstmt = conn.prepareStatement(sql);
			// 4.设置参数
			pstmt.setInt(1, environmental_monitoring.getEnviromental_id());
			pstmt.setDouble(2, environmental_monitoring.getTemperature());
			pstmt.setDouble(3, environmental_monitoring.getHumidity());
			pstmt.setDouble(4, environmental_monitoring.getIllumination());
			pstmt.setDouble(5, environmental_monitoring.getNoise());
			pstmt.setDouble(6, environmental_monitoring.getPm2());
			pstmt.setDouble(7, environmental_monitoring.getPm10());
			pstmt.setString(8, environmental_monitoring.getSendtime());
			// 5.执行插入操作
			row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("添加成功!");
			} else {
				System.out.println("添加失败!");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 6.释放资源
			jdbcUtils.release(conn, pstmt, null);
		}*/
/*		
		  DriverManagerDataSource dataSource = new DriverManagerDataSource();
		  dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		  dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/environmental_science");
		 dataSource.setUsername("root"); dataSource.setPassword("123"); //
		// 2、创建jdbcTemplate对象，设置数据源
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); */
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

}
