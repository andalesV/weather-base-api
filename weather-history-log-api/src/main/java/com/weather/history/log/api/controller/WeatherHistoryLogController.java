package com.weather.history.log.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.weather.data.storage.dto.WeatherLogDto;
import com.weather.history.log.api.service.WeatherHistoryLogService;

@RestController
@RequestMapping("/weather-history/log")
public class WeatherHistoryLogController {

	@Autowired
	private WeatherHistoryLogService weatherHistorLogService;

	@GetMapping("/save")
	public ResponseEntity<?> saveWeatherForecast(UriComponentsBuilder ucBuilder) {
		weatherHistorLogService.saveWeatherForecast();

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/weather-history/log/report").build().toUri());

		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@GetMapping("/report")
	public List<WeatherLogDto> displayWeatherLog() {
		return weatherHistorLogService.getWeatherLog();
	}
}
