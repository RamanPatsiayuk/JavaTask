CREATE TABLE Employee(
id int primary key,
firstName varchar(255),
lastName varchar(255),
age int,
title varchar(255),
department varchar(255))

INSERT INTO Employee(id, firstName, lastName, age, title, department) VALUES(1, 'Ivan', 'Sidorov',30,'SE', 'JAVA')
INSERT INTO Employee(id, firstName, lastName, age, title, department) VALUES(2, 'Petr', 'Ivanov', 23,'JTAE', '.Net')
INSERT INTO Employee(id, firstName, lastName, age, title, department) VALUES(3, 'Vitaliy', 'Petrov',25,'SSE', 'Python')