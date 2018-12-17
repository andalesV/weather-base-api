package com.weather.data.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weather.data.storage.entity.WeatherLog;

@Repository
public interface WeatherLogRepository extends JpaRepository<WeatherLog, Integer> {

}
