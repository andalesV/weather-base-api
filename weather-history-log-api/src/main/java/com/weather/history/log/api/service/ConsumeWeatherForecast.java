package com.weather.history.log.api.service;

import java.util.List;

import com.weather.history.log.api.dto.WeatherForecastDto;


public interface ConsumeWeatherForecast {
	public List<WeatherForecastDto> comsumeWeatherInformation();

}
