package com.weather.forecast.api.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.weather.forecast.api.data.Forecast;
import com.weather.forecast.api.data.Weather;
import com.weather.forecast.api.data.WeatherInformation;
import com.weather.forecast.api.dto.MainDto;
import com.weather.forecast.api.dto.WeatherDetailsDto;
import com.weather.forecast.api.dto.WeatherForecastDto;

@Service
public class WeatherCollectorServiceImpl implements WeatherCollectorService {

	@Autowired
	private AsyncRestTemplate asyncRestTemplate;

	private HttpHeaders headers = new HttpHeaders();

	@Value("${weather.service.url}")
	private String url;

	@Value("${open.weather.api.key}")
	private String apiKey;

	@Override
	public List<WeatherForecastDto> getWeatherInformation() {
		List<String> locations = new ArrayList<>();
		locations.add("London");
		locations.add("Prague");
		locations.add("San Francisco");
		List<WeatherForecastDto> forecastDtoList = new ArrayList<>();

		for (String location : locations) {
			forecastDtoList.add(comsumeWeatherData(location));
		}

		return forecastDtoList;
	}

	@Override
	public WeatherForecastDto getWeatherInfoSpecifyLocation(String location) {
		return comsumeWeatherData(location);
	}

	private WeatherForecastDto comsumeWeatherData(String location) {
		WeatherDetailsDto weatherDetailsDto = null;
		MainDto mainDto = null;
		List<MainDto> mainDtoList = new ArrayList<>();
		WeatherForecastDto weatherForecastDto = new WeatherForecastDto();

		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<String> request = new HttpEntity<String>(headers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("q", location)
				.queryParam("appid", apiKey);

		ListenableFuture<ResponseEntity<WeatherInformation>> result = asyncRestTemplate.exchange(
				builder.build().toUri(), HttpMethod.GET, request, new ParameterizedTypeReference<WeatherInformation>() {
				});
		try {
			for (Forecast forecast : result.get().getBody().getForecasts()) {
				weatherDetailsDto = new WeatherDetailsDto();
				mainDto = new MainDto();
				weatherDetailsDto.setTemperature(convertTemperature(forecast.getMain().getTemp()));
				weatherDetailsDto.setDateTime(forecast.getDt_txt());
				weatherDetailsDto.setTimestamp(forecast.getDt());

				for (Weather weather : forecast.getWeatherList()) {
					weatherDetailsDto.setActualWeather(weather.getMain());
					weatherDetailsDto.setResponseId(weather.getId());
				}
				mainDto.setWeatherDetailsDto(weatherDetailsDto);
				mainDtoList.add(mainDto);
				weatherForecastDto.setMainDto(mainDtoList);

			}
			weatherForecastDto.setLocation(location);

			return weatherForecastDto;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String convertTemperature(double kelvin) {
		DecimalFormat df = new DecimalFormat("#.###");
		return df.format(kelvin - 273.15) + "°C";
	}

}