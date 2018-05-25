package com.dajingzhu.bean;

public class Equipment {
	private int id;
	private int enviromental_id;
	private double serial_number;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEnviromental_id() {
		return enviromental_id;
	}
	public void setEnviromental_id(int enviromental_id) {
		this.enviromental_id = enviromental_id;
	}
	public double getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(double serial_number) {
		this.serial_number = serial_number;
	}
	@Override
	public String toString() {
		return "Equipment [id=" + id + ", enviromental_id=" + enviromental_id + ", serial_number=" + serial_number
				+ "]";
	}
	
}
