package com.epam.dao.department;

import com.epam.model.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Roman
 */
public class DepartmentRowMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet resultSet, int i) throws SQLException {
        DepartmentExtractor departmentExtractor = new DepartmentExtractor();
        return departmentExtractor.extractData(resultSet);
    }
}
