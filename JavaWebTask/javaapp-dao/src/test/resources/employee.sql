DROP TABLE IF EXISTS Employee;
CREATE TABLE Employee(
employeeId int not null primary key auto_increment,
firstName varchar(255) NOT NULL,
lastName varchar(255) NOT NULL,
address varchar(255) NOT NULL,
position varchar(255) NOT NULL,
departmentId INT NOT NULL REFERENCES Department(departmentId),
salary double NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;