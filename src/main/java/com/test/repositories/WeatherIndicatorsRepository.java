package com.test.repositories;

import com.test.entities.WeatherIndicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherIndicatorsRepository extends JpaRepository<WeatherIndicator, Long> {
}
