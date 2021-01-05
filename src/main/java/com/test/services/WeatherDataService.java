package com.test.services;

import com.test.entities.WeatherData;
import com.test.repositories.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class WeatherDataService {
    @Autowired
    private WeatherDataRepository repository;

    public WeatherData getByObservationDate(LocalDateTime dateTime) {
        return repository.findByObservationDate(dateTime);
    }
    public WeatherData getById(Long id) {
        return repository.findById(id).get();
    }
    public WeatherData save(WeatherData weatherData) {
        return repository.save(weatherData);
    }

    @EnableKafka
    @Component
    class Listener {
        @Autowired
        private WeatherDataService service;

        @KafkaListener(topics = "wData")
        public void dataListener(WeatherData data) {
            System.out.println(data);
            service.save(data);
        }
    }
}
