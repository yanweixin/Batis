# create database
CREATE DATABASE IF NOT EXISTS app;

# create user
DROP USER IF EXISTS apps;
CREATE USER IF NOT EXISTS 'apps'@'%' IDENTIFIED WITH caching_sha2_password BY 'apps';
GRANT ALL PRIVILEGES ON app.* TO 'apps'@'%';

SELECT *
FROM user;

DELETE
FROM user
WHERE 1 = 1;

SELECT *
FROM mysql.time_zone_name
WHERE Name LIKE 'Asia%';

SELECT now();