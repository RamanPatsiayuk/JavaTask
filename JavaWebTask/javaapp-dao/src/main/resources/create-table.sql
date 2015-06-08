DROP TABLE IF EXISTS Employee;
CREATE TABLE Employee(
id int primary key,
firstName varchar(255) NOT NULL,
lastName varchar(255) NOT NULL,
address varchar(255) NOT NULL,
title varchar(255) NOT NULL,
department varchar(255) NOT NULL);