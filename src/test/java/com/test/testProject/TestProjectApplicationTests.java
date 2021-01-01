package com.test.testProject;

import com.test.entities.WeatherData;
import com.test.services.WeatherDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TestProjectApplicationTests {
	@Autowired
	private KafkaTemplate<Long, WeatherData> kafkaTemplate;
	@Autowired
	private WeatherDataService service;

	@Test
	public void gettingAndSavingData() {
		WeatherData data = getData();

		ListenableFuture<SendResult<Long, WeatherData>> future = kafkaTemplate.send("wData", data);
		kafkaTemplate.flush();

		WeatherData expectedWeatherData = service.getByObservationDate(data.getObservationDate());
		assertThat(expectedWeatherData).isNotNull();
	}

	private WeatherData getData() {
		WeatherData data = service.getById(1l);
		data.setId(null);
		data.setObservationDate(LocalDateTime.now());
		return data;
	}

}
