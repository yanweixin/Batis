# create user
CREATE USER 'apps'@'%' IDENTIFIED WITH mysql_native_password BY 'apps';
GRANT ALL PRIVILEGES ON *.* TO 'apps'@'%';

# create database
CREATE DATABASE app;