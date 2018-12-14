package com.weather.history.log.api.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weather.data.storage.dto.WeatherLogDto;
import com.weather.data.storage.entity.WeatherLog;
import com.weather.data.storage.repository.WeatherLogRepository;
import com.weather.history.log.api.dto.MainDto;
import com.weather.history.log.api.dto.WeatherForecastDto;

@Service
public class WeatherHistoryLogServiceImpl implements WeatherHistoryLogService {

	@Autowired
	private WeatherLogRepository weatherLogRepository;

	@Autowired
	private ConsumeWeatherForecast weatherForecast;

	private List<Integer> list = new ArrayList<>();

	@Override
	public void saveWeatherForecast() {
		WeatherLog weatherLog = null;

		Collections.sort(list);
		List<WeatherLogDto> listOfWeather = getWeatherForecast();
		Set<Integer> top5 = new HashSet<Integer>(list.subList(list.size() - 5, list.size()));
		List<WeatherLog> weatherLogListDb = weatherLogRepository.findAll();

		List<WeatherLog> weatherLogList = new ArrayList<>();
		for (WeatherLogDto logDto : listOfWeather) {
			for (Integer id : top5) {
				if (id == Integer.parseInt(logDto.getResponseId()) && logDto.getLocation().equals("San Francisco")) {
					weatherLog = new WeatherLog();
					weatherLog.setActualWeather(logDto.getActualWeather());
					weatherLog.setDtimeInserted(logDto.getDtimeInserted());
					weatherLog.setLocation(logDto.getLocation());
					weatherLog.setResponseId(logDto.getResponseId());
					weatherLog.setTemperature(logDto.getTemperature());
					weatherLogList.add(weatherLog);
				}
			}
		}

		if (!weatherLogList.isEmpty()) {
			List<WeatherLog> logs = new ArrayList<>();
			for (WeatherLog log : weatherLogList) {
				if (weatherLogListDb.isEmpty()) {
					weatherLogRepository.saveAndFlush(log);
				} else {
					for (WeatherLog log2 : weatherLogListDb) {
						if (log.getResponseId().equals(log2.getResponseId())) {
							logs.add(log);
						}
					}
				}
			}

			if (!weatherLogListDb.isEmpty())
				weatherLogList.removeAll(logs);

			weatherLogList.forEach(t -> {
				weatherLogRepository.saveAndFlush(t);
			});

		}

	}

	private List<WeatherLogDto> getWeatherForecast() {
		List<WeatherLogDto> weatherLogDtoList = new ArrayList<>();
		List<WeatherForecastDto> weatherForecastDtoList = weatherForecast.comsumeWeatherInformation();
		WeatherLogDto weatherLogDto = null;

		for (WeatherForecastDto weatherForecastDto : weatherForecastDtoList) {
			for (MainDto mainDto : weatherForecastDto.getMainDto()) {
				weatherLogDto = new WeatherLogDto();
				weatherLogDto.setLocation(weatherForecastDto.getLocation());
				weatherLogDto.setActualWeather(mainDto.getWeatherDetailsDto().getActualWeather());
				weatherLogDto.setDtimeInserted(getTimestamp());
				weatherLogDto.setResponseId(mainDto.getWeatherDetailsDto().getTimestamp());
				weatherLogDto.setTemperature(mainDto.getWeatherDetailsDto().getTemperature());
				weatherLogDtoList.add(weatherLogDto);
				list.add(Integer.parseInt(mainDto.getWeatherDetailsDto().getTimestamp()));
			}
		}

		return weatherLogDtoList;
	}

	private Timestamp getTimestamp() {
		Date date = new Date();
		long time = date.getTime();
		return new Timestamp(time);
	}

}