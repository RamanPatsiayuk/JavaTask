DROP TABLE IF EXISTS Department;
CREATE TABLE Department (
  departmentId int primary key auto_increment,
  departmentName varchar(255) NOT NULL,
  location varchar(255) NOT NULL,
  constraint departmentId_un unique(departmentId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;