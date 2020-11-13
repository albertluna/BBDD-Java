
DELIMITER $$

drop event if exists oltp.actualitzaOLAP$$
CREATE EVENT IF NOT EXISTS OLTP.actualitzaOLAP
ON SCHEDULE EVERY 20 SECOND

DO BEGIN 

	INSERT INTO OLAP.city (city, country, country_code, country_dst, timezone_id, timezone_olson, timezone_utc, daylight_saving_time)
    SELECT ci.city, co.country, co.code, co.dst, tz.timezone_id, tz.timezone_olson, tz.timezone_utc, tz.daylight_saving_time 
    FROM OLTP.city AS ci, OLTP.country AS co, OLTP.timezone AS tz , OLTP.timezone_city AS tc
    WHERE ci.country = co.country AND ci.city = tc.city AND co.country = tc.country AND tc.timezone_id = tz.timezone_id;
    
    INSERT INTO OLAP.route (
    route_id, airline_id, airline_name, airline_alias, airline_country, airline_country_code, 
    airline_country_dst, src_airport_id, src_airport_name, src_airport_city, src_airport_country, 
    src_airport_timezone, 
    src_airport_latitude, src_airport_longitude, src_airport_altitude, 
    dst_airport_id, dst_airport_name, dst_airport_city, dst_airport_country, 
    dst_airport_timezone,
    dst_airport_latitude, dst_airport_longitude, dst_airport_altitude, codeshare,
    stops, plane_id, plane_name, iata_code, icao_code)
    
    SELECT r.route_id, al.airline_id, al.name, al.alias, al.country, co.code,
    co.dst, srcA.airport_id, srcA.name, srcA.city, srcA.country, 
    (SELECT timezone_id FROM OLTP.timezone_city AS tc2 WHERE srcA.city = tc2.city AND srcA.country = tc2.country LIMIT 1),
    srcA.latitude, srcA.longitude, srcA.altitude,
    dstA.airport_id, dstA.name, dstA.city, dstA.country,
    (SELECT timezone_id FROM OLTP.timezone_city AS tc2 WHERE dstA.city = tc2.city AND dstA.country = tc2.country LIMIT 1),
    dstA.latitude, dstA.longitude, dstA.altitude, r.codeshare,
    r.stops, p.plane_id, p.name, p.iata_code, p.icao_code
    FROM OLTP.route AS r, OLTP.airline AS al, OLTP.country AS co, OLTP.airport AS srcA, OLTP.airport AS dstA, OLTP.plane AS p
    WHERE r.airline_id = al.airline_id AND al.country = co.country AND r.src_airport_id = srcA.airport_id 
    AND r.dst_airport_id = dstA.airport_id AND r.plane = p.plane_id;
    
    INSERT INTO OLAP.temps (data_actualitzacio, route, airline, src_airport, dst_airport, plane, city, country, timezone)
    SELECT NOW(), r.route_id, r.airline_id, r.src_airport_id, r.dst_airport_id, r.plane_id, c.city, c.country, c.timezone_id
    FROM OLAP.route AS r, OLAP.city AS c
    WHERE 
		(r.dst_airport_country = c.country AND r.dst_airport_city = c.city AND r.dst_airport_timezone = c.timezone_id)
        OR
		(r.src_airport_country = c.country AND r.src_airport_city = c.city AND r.src_airport_timezone = c.timezone_id);
END$$

DELIMITER ;
    
    
