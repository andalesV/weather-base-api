package com.weather.history.log.api.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherForecastDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String location;

	@JsonProperty("weatherForecast")
	private List<MainDto> mainDto;

	public List<MainDto> getMainDto() {
		return mainDto;
	}

	public void setMainDto(List<MainDto> mainDto) {
		this.mainDto = mainDto;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
