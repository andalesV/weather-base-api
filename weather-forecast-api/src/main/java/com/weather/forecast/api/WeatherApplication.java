package com.weather.forecast.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan({ "com.weather.forecast.api", "com.weather.data.storage" })
@SpringBootApplication
@EnableWebMvc
public class WeatherApplication extends SpringBootServletInitializer {

	@Value("${connectionTimeOut}")
	private int connectionTimeOut;

	@Value("${readTimeout}")
	private int readTimeout;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(WeatherApplication.class, args);

		DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
	}

	@Bean
	public AsyncRestTemplate asynRestTemplate() {
		AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
		final SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setTaskExecutor(new SimpleAsyncTaskExecutor());
		requestFactory.setConnectTimeout(connectionTimeOut);
		requestFactory.setReadTimeout(readTimeout);
		asyncRestTemplate.setAsyncRequestFactory(requestFactory);

		return asyncRestTemplate;
	}

}