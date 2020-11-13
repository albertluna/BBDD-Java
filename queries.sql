#1

SELECT * FROM OLTP.route;

SELECT * FROM OLAP.route;

#2

(SELECT r.route_id, al.airline_id, al.name, al.alias, al.country, co.code, co.dst, srcA.airport_id, srcA.name, srcA.city,
srcA.country, srcA.latitude, srcA.longitude, srcA.altitude,
dstA.airport_id, dstA.name, dstA.city, dstA.country, dstA.latitude, dstA.latitude, dstA.longitude, dstA.altitude,
r.codeshare, r.stops, p.plane_id, p.name, p.iata_code, p.icao_code
FROM OLTP.route AS r, OLTP.airline AS al, OLTP.country AS co, OLTP.airport AS srcA, OLTP.airport AS dstA, OLTP.plane AS p
WHERE r.airline_id = al.airline_id AND al.country = co.country AND r.src_airport_id = srcA.airport_id 
AND r.dst_airport_id = dstA.airport_id AND r.plane = p.plane_id);
    
SELECT * FROM OLAP.route;

#3

INSERT INTO OLTP.plane (plane_id, name, iata_code, icao_code)
VALUES (3021, 'avioneta', 'IATA1', 'ICAO1');

INSERT INTO OLAP.city (city, country, country_code, country_dst, timezone_id, timezone_olson, timezone_utc, daylight_saving_time)
VALUES ('ciutat', 'pais', 'CO', 'Z', 78, 'timezone', 45, 'F');

#4

UPDATE OLTP.country SET code = CONCAT (code, ' 01'), dst = 'G'
WHERE country LIKE 'c%';

UPDATE OLAP.city SET timezone_olson = CONCAT(timezone_olson , 'njakllcbdkom') 
WHERE country LIKE 'c%';

#5

DELETE FROM OLTP.timezone_city WHERE country = 'Germany';

DELETE FROM OLAP.temps WHERE country = 'Germany';
