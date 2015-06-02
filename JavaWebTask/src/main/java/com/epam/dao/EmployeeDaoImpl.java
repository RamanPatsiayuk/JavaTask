package com.epam.dao;

import com.epam.model.Employee;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 *
 * Created by Roman
 */
public class EmployeeDaoImpl implements EmployeeDao {

    static final Logger log = Logger.getLogger(EmployeeDaoImpl.class);
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addEmployee(Employee employee) {
        log.debug("Add employee in employee");

    }

    @Override
    public void updateEmployee(Employee employee) {
        if (employee.getId() > 0) {
            // update
            String sql = "UPDATE employee SET firstName=?, lastName=?, age=?,title=?,department=? WHERE id=?";
            jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(),
                    employee.getAge(),employee.getTitle(),employee.getDepartment());
        } else {
            // insert
            String sql = "INSERT INTO employee (firstName, lastName, age,title,department)"
                    + " VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(),
                    employee.getAge(),employee.getTitle(),employee.getDepartment());
        }
    }

    @Override
    public Employee getEmployee(int id) {
        String sql = "SELECT * FROM employee WHERE id=?";
        return null;
    }

    @Override
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Employee> getEmployees() {

        return null;
    }
}
