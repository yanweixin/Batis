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

INSERT INTO app.user (id, created_at, created_by, object_version_number, updated_at, updated_by, address, birthday,
                      description, display_name, email, enabled, gender, password, phone_number, user_name)
VALUES (1, '2020-08-27 13:38:22.000000', 1, 1, '2020-08-27 13:38:32.000000', 1, NULL, '2020-08-27', NULL, 'Super User',
        'admin@mail.com', TRUE, NULL, '{bcrypt}$2a$10$R7Vy1mt8Ar6ECkR8h/mJje0QXG4EoS0Q7Jl5yX04pStUmKSz.ZfBO', NULL, 'super');


