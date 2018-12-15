package com.weather.history.log.api.service;

import java.util.List;

import com.weather.data.storage.dto.WeatherLogDto;

public interface WeatherHistoryLogService {

	public void saveWeatherForecast();

	public List<WeatherLogDto> getWeatherLog();
}
