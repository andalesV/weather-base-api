package com.weather.history.log.api.service;

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

import com.weather.history.log.api.dto.WeatherForecastDto;

@Service
public class ConsumeWeatherForecastImpl implements ConsumeWeatherForecast {

	@Autowired
	private AsyncRestTemplate asyncRestTemplate;

	private HttpHeaders headers = new HttpHeaders();

	@Value("${forecastUrl}")
	private String forecastUrl;

	@Override
	public List<WeatherForecastDto> comsumeWeatherInformation() {

		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<String> request = new HttpEntity<String>(headers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(forecastUrl);

		ListenableFuture<ResponseEntity<List<WeatherForecastDto>>> result = asyncRestTemplate.exchange(
				builder.build().toUri(), HttpMethod.GET, request,
				new ParameterizedTypeReference<List<WeatherForecastDto>>() {
				});

		try {
			return result.get().getBody();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

}