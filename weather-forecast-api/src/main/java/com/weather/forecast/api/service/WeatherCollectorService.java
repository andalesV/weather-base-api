package com.weather.forecast.api.service;

import java.util.List;

import com.weather.forecast.api.dto.WeatherForecastDto;

public interface WeatherCollectorService {

	public List<WeatherForecastDto> getWeatherInformation();

	public WeatherForecastDto getWeatherInfoSpecifyLocation(String location);

}