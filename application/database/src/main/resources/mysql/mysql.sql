# create database
CREATE DATABASE IF NOT EXISTS app;

# create user
DROP USER IF EXISTS apps;
CREATE USER IF NOT EXISTS 'apps'@'%' IDENTIFIED WITH caching_sha2_password BY 'apps';
GRANT ALL PRIVILEGES ON app.* TO 'apps'@'%';

SELECT *
FROM `user`;
SELECT *
FROM device_info;
SELECT *
FROM post;
SELECT *
FROM content;

SELECT *
FROM mysql.time_zone_name
WHERE Name LIKE 'Asia%';

SELECT now();

SELECT *
FROM log.application_log
# WHERE logger LIKE '%CustomQuery'
ORDER BY timestamp DESC;

DELETE
FROM user
WHERE id != 1;
