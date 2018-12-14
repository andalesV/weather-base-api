package com.weather.gather.api.service;

import java.util.List;

import com.weather.gather.api.dto.WeatherForecastDto;

public interface WeatherCollectorService {

	public List<WeatherForecastDto> getWeatherInformation();

	public WeatherForecastDto getWeatherInfoSpecifyLocation(String location);

}