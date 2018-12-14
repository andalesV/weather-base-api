package com.weather.forecast.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MainDto {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@JsonProperty("weatherDetails")
	private WeatherDetailsDto weatherDetailsDto;

	public WeatherDetailsDto getWeatherDetailsDto() {
		return weatherDetailsDto;
	}

	public void setWeatherDetailsDto(WeatherDetailsDto weatherDetailsDto) {
		this.weatherDetailsDto = weatherDetailsDto;
	}
}