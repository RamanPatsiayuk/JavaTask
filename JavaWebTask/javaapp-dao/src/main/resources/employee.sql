DROP TABLE IF EXISTS Employee;
CREATE TABLE Employee(
id int primary key,
firstName varchar(255) NOT NULL,
lastName varchar(255) NOT NULL,
address varchar(255) NOT NULL,
title varchar(255) NOT NULL,
department varchar(255) NOT NULL);

INSERT INTO Employee VALUES(1, 'Ivan', 'Sidorov','Brest, Orlovskaya street','SE', 'JAVA');
INSERT INTO Employee VALUES(2, 'Petr', 'Ivanov', 'Brest, Kyibisheva 100','JTAE', '.Net');
INSERT INTO Employee VALUES(3, 'Vitaliy', 'Petrov','Minsk, Voronianskogo 24','SSE', 'Python');
