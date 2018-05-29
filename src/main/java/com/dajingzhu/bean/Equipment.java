package com.dajingzhu.bean;

public class Equipment {
	private int id;
	private int enviromental_id;
	private String serial_number;
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

	public String getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}
	@Override
	public String toString() {
		return "Equipment [id=" + id + ", enviromental_id=" + enviromental_id + ", serial_number=" + serial_number
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + enviromental_id;
		result = prime * result + id;
		result = prime * result + ((serial_number == null) ? 0 : serial_number.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipment other = (Equipment) obj;
		if (enviromental_id != other.enviromental_id)
			return false;
		if (id != other.id)
			return false;
		if (serial_number == null) {
			if (other.serial_number != null)
				return false;
		} else if (!serial_number.equals(other.serial_number))
			return false;
		return true;
	}
	
}
