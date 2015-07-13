DROP TABLE IF EXISTS Employee;
CREATE TABLE Employee(
id int primary key,
firstName varchar(255) NOT NULL,
lastName varchar(255) NOT NULL,
address varchar(255) NOT NULL,
position varchar(255) NOT NULL,
departmentId int foreign key references Department(departmentId),
salary double NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;