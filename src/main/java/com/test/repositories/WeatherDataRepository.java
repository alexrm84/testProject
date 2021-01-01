package com.test.repositories;

import com.test.entities.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    WeatherData findByObservationDate(LocalDateTime dateTime);
}
