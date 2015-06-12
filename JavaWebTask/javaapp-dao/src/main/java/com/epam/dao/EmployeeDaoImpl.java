package com.epam.dao;

import com.epam.model.Employee;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 *
 * Created by Roman
 */
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    DataSource dataSource;

    static final Logger log = Logger.getLogger(EmployeeDaoImpl.class);
    private JdbcTemplate jdbcTemplate;




    @Override
    public void addEmployee(Employee employee) {
        log.debug("Add employee in employee");

    }

    @Override
    public void updateEmployee(Employee employee) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        if (employee.getId() > 0) {
            // update
            String sql = "UPDATE employee SET firstName=?, lastName=?, address=?,title=?,department=? WHERE id=?";
            jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(),employee.getAddress(),employee.getTitle(),employee.getDepartment());
        } else {
            // insert
            String sql = "INSERT INTO employee (firstName, lastName, age,title,department)"
                    + " VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(),
                    employee.getAddress(),employee.getTitle(),employee.getDepartment());
        }
    }

    @Override
    public Employee getEmployee(String name) {
        String sql = "SELECT * FROM employee WHERE name=?";
        return null;
    }

    @Override
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id=?";
        int update = jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Employee> getEmployees() {
        String sql = "SELECT * FROM employee";
        return null;
    }
}
