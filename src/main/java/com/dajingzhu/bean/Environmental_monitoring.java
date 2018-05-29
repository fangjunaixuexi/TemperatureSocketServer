package com.dajingzhu.bean;

public class Environmental_monitoring {
	private int id;
	private int enviromental_id;
	private double temperature;
	private double humidity;
	private double illumination;
	private double noise;
	private double pm2;
	private double pm10;
	private String sendtime;
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
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
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getIllumination() {
		return illumination;
	}
	public void setIllumination(double illumination) {
		this.illumination = illumination;
	}
	public double getNoise() {
		return noise;
	}
	public void setNoise(double noise) {
		this.noise = noise;
	}
	public double getPm2() {
		return pm2;
	}
	public void setPm2(double pm2) {
		this.pm2 = pm2;
	}
	public double getPm10() {
		return pm10;
	}
	public void setPm10(double pm10) {
		this.pm10 = pm10;
	}
	@Override
	public String toString() {
		return "Environmental_monitoring [id=" + id + ", enviromental_id=" + enviromental_id + ", temperature="
				+ temperature + ", humidity=" + humidity + ", illumination=" + illumination + ", noise=" + noise
				+ ", pm2=" + pm2 + ", pm10=" + pm10 + ", sendtime=" + sendtime + "]";
	}
	
}
