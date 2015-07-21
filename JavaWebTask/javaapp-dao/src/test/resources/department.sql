DROP TABLE IF EXISTS Department;
CREATE TABLE Department (
  departmentId int primary key auto_increment,
  department varchar(255) NOT NULL,
  location varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;