package com.epam.dao;

import com.epam.model.Employee;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Roman
 */
public class EmployeeExtractor implements ResultSetExtractor<Employee> {
    @Override
    public Employee extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt(1));
        employee.setFirstName(resultSet.getString(2));
        employee.setLastName(resultSet.getString(3));
        employee.setAddress(resultSet.getString(4));
        employee.setDepartmentId(resultSet.getInt(5));
        employee.setSalary(resultSet.getDouble(7));
        return employee;
    }
}
