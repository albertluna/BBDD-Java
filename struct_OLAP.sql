drop database if exists OLAP;
create database OLAP;
use OLAP;

DROP TABLE IF EXISTS city;
CREATE TABLE city (
	city VARCHAR(256),
    country VARCHAR(256),
    country_code CHAR(255),
    country_dst VARCHAR(255),
    timezone_id INTEGER,
    timezone_olson VARCHAR(256),
    timezone_utc INTEGER,
    daylight_saving_time VARCHAR(255),
    PRIMARY KEY (country, city, timezone_id)
);

DROP TABLE IF EXISTS route;
CREATE TABLE route (
	route_id INTEGER,
	airline_id INTEGER,
    airline_name VARCHAR(255),  
    airline_alias VARCHAR(255),
    airline_country VARCHAR(256),
    airline_country_code VARCHAR(255), 
    airline_country_dst CHAR,
	src_airport_id INTEGER,
    src_airport_name VARCHAR(256),
    src_airport_city VARCHAR(256),
    src_airport_country VARCHAR(256),
    src_airport_timezone INT,
    src_airport_latitude REAL,
    src_airport_longitude REAL, 
    src_airport_altitude INTEGER,
	dst_airport_id INTEGER,
    dst_airport_name VARCHAR(256),
    dst_airport_city VARCHAR(256),
    dst_airport_country VARCHAR(256),
    dst_airport_timezone INT,
	dst_airport_latitude REAL,
    dst_airport_longitude REAL,
    dst_airport_altitude INTEGER,
	codeshare CHAR,
    stops INTEGER,
	plane_id INTEGER,
    plane_name VARCHAR(256),
    iata_code VARCHAR(255),
    icao_code VARCHAR(255),
    PRIMARY KEY (route_id, airline_id, src_airport_id, dst_airport_id, plane_id),
    FOREIGN KEY (dst_airport_country, dst_airport_city, dst_airport_timezone) REFERENCES city (country, city, timezone_id),
	FOREIGN KEY (src_airport_country, src_airport_city, src_airport_timezone) REFERENCES city (country, city, timezone_id)
);

DROP TABLE IF EXISTS temps;
CREATE TABLE temps (
	data_actualitzacio timestamp,
    route INTEGER,
    airline INTEGER,
    src_airport INTEGER,
    dst_airport INTEGER,
    plane INTEGER,
    country VARCHAR(256),
    city VARCHAR(256),
    timezone INT,
    PRIMARY KEY (data_actualitzacio, route, airline, src_airport, dst_airport, plane, country, city, timezone),
    FOREIGN KEY (route, airline, src_airport, dst_airport, plane) 
	REFERENCES route (route_id, airline_id, src_airport_id, dst_airport_id, plane_id),
	FOREIGN KEY (country, city) REFERENCES city (country, city)
);
    