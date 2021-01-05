package com.test.testProject;

import com.test.entities.City;
import com.test.entities.WeatherData;
import com.test.entities.WeatherIndicator;
import com.test.services.WeatherDataService;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureEmbeddedDatabase
@DirtiesContext
@EmbeddedKafka(partitions = 1, topics = "wData", bootstrapServersProperty = "spring.kafka.bootstrap-servers", brokerProperties = "auto.create.topics.enable=false")
class TestProjectApplicationTests {

	@Autowired
	private WeatherDataService service;
	@Autowired
	private KafkaTemplate<String, WeatherData> template;

	@Test
	@FlywayTest(locationsForMigrate = "db/migration")
	public void gettingAndSavingData() {
		WeatherData data = getData();

		template.send("wData", data);
		template.flush();

		WeatherData expectedWeatherData = service.getByObservationDate(data.getObservationDate());
		assertThat(expectedWeatherData).isNotNull();
	}

	private WeatherData getData() {
		WeatherData data = new WeatherData();
		data.setValue(new BigDecimal(12.3));
		data.setObservationDate(LocalDateTime.now());
		data.setCity(new City(1l, "Sevastopol"));
		data.setWeatherIndicator(new WeatherIndicator(1l, "temperature"));
		return data;
	}

}
