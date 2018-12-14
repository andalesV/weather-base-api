package com.weather.history.log.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.history.log.api.service.WeatherHistoryLogService;

@RestController
@RequestMapping("/weather-history/log/")
public class WeatherHistoryLogController {

	@Autowired
	private WeatherHistoryLogService weatherHistorLogService;

	@GetMapping("save")
	public void saveWeatherForecast() {
		weatherHistorLogService.saveWeatherForecast();
	}
}
