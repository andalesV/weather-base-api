package com.weather.gather.api;

import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.weather.gather.api.service.WeatherCollectorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherCollectorServiceTest {

	@Autowired
	private WeatherCollectorService weatherCollectorService;

	@Test
	public void weatherCollectorServiceTest() {
		weatherCollectorService.getWeatherInformation();

	}

}
