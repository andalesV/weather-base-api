package com.weather.history.log.api.dto;

import java.io.Serializable;

public class WeatherDetailsDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String responseId;
	private String actualWeather;
	private String temperature;
	private String dateTime;
	private String timestamp;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getResponseId() {
		return responseId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	public String getActualWeather() {
		return actualWeather;
	}

	public void setActualWeather(String actualWeather) {
		this.actualWeather = actualWeather;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

}
