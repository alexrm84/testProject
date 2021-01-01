package com.test.configs;

import com.test.entities.WeatherData;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    private final String KAFKA_SERVER = "localhost:9092";

    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new  HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "app.1");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<Long, WeatherData> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new LongDeserializer(), new JsonDeserializer<>(WeatherData.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, WeatherData> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, WeatherData> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
