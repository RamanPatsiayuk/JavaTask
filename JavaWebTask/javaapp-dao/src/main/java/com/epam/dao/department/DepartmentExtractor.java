package com.epam.dao.department;

import com.epam.model.Department;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Roman
 */
public class DepartmentExtractor implements ResultSetExtractor<Department> {
    @Override
    public Department extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Department department = new Department();
        department.setDepartmentId(resultSet.getInt(1));
        department.setDepartmentName(resultSet.getString(2));
        department.setLocation(resultSet.getString(3));
        return department;
    }
}
