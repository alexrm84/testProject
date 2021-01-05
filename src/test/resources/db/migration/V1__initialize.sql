DROP TABLE IF EXISTS cities;
CREATE TABLE cities (
  id                    bigserial,
  title                 VARCHAR(30) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO cities (title)
VALUES
('Sevastopol');

DROP TABLE IF EXISTS weather_indicators;
CREATE TABLE weather_indicators (
  id                    bigserial,
  title                 VARCHAR(30) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO weather_indicators (title)
VALUES
('temperature');


DROP TABLE IF EXISTS weather_data;
CREATE TABLE weather_data (
    id bigserial,
    cities_id bigint NOT NULL,
    weather_indicators_id bigint NOT NULL,
    value numeric(3,1) NOT NULL,
    date_of_observation timestamp NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (cities_id) REFERENCES cities (id),
    FOREIGN KEY (weather_indicators_id) REFERENCES weather_indicators (id)
);

INSERT INTO weather_data (cities_id, weather_indicators_id, value, date_of_observation)
VALUES
(1, 1, 12.2, '2021.01.01');