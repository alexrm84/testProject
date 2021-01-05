package com.test.testProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.listener.LoggingErrorHandler;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.test")
@EntityScan("com.test.entities")
@EnableJpaRepositories("com.test.repositories")
public class TestProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestProjectApplication.class, args);
	}

	@Bean
	public LoggingErrorHandler errorHandler() {
		return new LoggingErrorHandler();
	}

}
