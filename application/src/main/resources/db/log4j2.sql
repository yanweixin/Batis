# create database and user
DROP DATABASE IF EXISTS log;
CREATE DATABASE IF NOT EXISTS log;
CREATE USER IF NOT EXISTS 'logs'@'%' IDENTIFIED WITH caching_sha2_password BY 'logs';
GRANT ALL PRIVILEGES ON log.* TO 'logs'@'%';
GRANT ALL PRIVILEGES ON log.* TO 'apps'@'%';

# create log table
USE log;

BEGIN;
DROP TABLE IF EXISTS application_log;
COMMIT;

BEGIN;
CREATE TABLE application_log
(
    log_id          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    timestamp       DATETIME     NOT NULL,
    level           VARCHAR(254) NOT NULL,
    logger          VARCHAR(254) NOT NULL,
    marker          VARCHAR(254),
    message         TEXT,
    location        TEXT,
    caller_filename VARCHAR(254),
    caller_class    VARCHAR(254),
    caller_method   VARCHAR(254),
    caller_line     CHAR(4),
    process_id      INT          NOT NULL,
    thread_id       INT          NOT NULL,
    thread_name     VARCHAR(254) NOT NULL,
    thread_priority INT          NOT NULL,
    exception       TEXT
);
COMMIT;

SELECT count(log_id)
FROM application_log;
TRUNCATE TABLE application_log;
SELECT *
FROM application_log
WHERE level = 'ERROR';
SELECT *
FROM application_log
# WHERE logger LIKE 'org.hibernate.%'
ORDER BY log_id DESC;