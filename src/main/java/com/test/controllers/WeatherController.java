package com.test.controllers;

import com.test.entities.WeatherData;
import com.test.services.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@EnableKafka
@Component
public class WeatherController {
    @Autowired
    private WeatherDataService service;

    @KafkaListener(topics = "wData")
    public void dataListener(WeatherData data) {
        service.save(data);
    }
}
