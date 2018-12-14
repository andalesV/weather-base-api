package com.weather.gather.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.gather.api.dto.WeatherForecastDto;
import com.weather.gather.api.service.WeatherCollectorService;

@RestController
@RequestMapping("/weather-api/service/")
public class WeatherController {

	@Autowired
	private WeatherCollectorService weatherCollectorService;

	@GetMapping("forecast")
	public List<WeatherForecastDto> getWeatherInformation() {
		return weatherCollectorService.getWeatherInformation();
	}

	@GetMapping("forecast/london")
	public WeatherForecastDto getWeatherFromLondon() {
		return weatherCollectorService.getWeatherInfoSpecifyLocation("london");
	}

	@GetMapping("forecast/prague")
	public WeatherForecastDto getWeatherFromPrague() {
		return weatherCollectorService.getWeatherInfoSpecifyLocation("prague");
	}

	@GetMapping("forecast/sanFrancisco")
	public WeatherForecastDto getWeatherFromSanFrancisco() {
		return weatherCollectorService.getWeatherInfoSpecifyLocation("san francisco");
	}

	
	
	
}