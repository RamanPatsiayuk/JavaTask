package com.epam.dao;

import com.epam.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Roman
 */
public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        EmployeeExtractor employeeExtractor = new EmployeeExtractor();
        return employeeExtractor.extractData(resultSet);
    }
}
