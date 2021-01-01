package com.test.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather_data")
@Data
@NoArgsConstructor
public class WeatherData implements Serializable {
    private static final long serialVersionUID = -3665025603461591833L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne()
    @JoinColumn(name = "cities_id")
    private City city;

    @OneToOne()
    @JoinColumn(name = "weather_indicators_id")
    private WeatherIndicator weatherIndicator;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "date_of_observation")
    private LocalDateTime observationDate;
}
