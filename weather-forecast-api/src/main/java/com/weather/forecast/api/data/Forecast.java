package com.weather.forecast.api.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Forecast {

	@JsonProperty("weather")
	private List<Weather> weatherList;

	@JsonProperty("main")
	private Main main;

	private String dt;
	private String dt_txt;

	public String getDt_txt() {
		return dt_txt;
	}

	public void setDt_txt(String dt_txt) {
		this.dt_txt = dt_txt;
	}

	public List<Weather> getWeatherList() {
		return weatherList;
	}

	public void setWeatherList(List<Weather> weatherList) {
		this.weatherList = weatherList;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

}
