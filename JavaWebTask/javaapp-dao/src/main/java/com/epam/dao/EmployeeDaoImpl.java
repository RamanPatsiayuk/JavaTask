package com.epam.dao;

import com.epam.model.Employee;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Roman
 */

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    public DataSource dataSource;

    static final Logger log = Logger.getLogger(EmployeeDaoImpl.class);
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addEmployee(Employee employee) {
        log.debug("Add employee in employee table");
        String sql = "insert into employee (firstName,lastName,address,title, department) values (?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{employee.getFirstName(), employee.getLastName(), employee.getAddress(), employee.getTitle(), employee.getDepartment()});
    }

    @Override
    public void updateEmployee(Employee employee) {
        log.debug("Update employee in employee table");
        if (employee.getId() > 0) {
            // update
            String sql = "update employee set firstName=?, lastName=?, address=?,title=?,department=? where id=?";
            jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getAddress(), employee.getTitle(), employee.getDepartment());
        } else {
            addEmployee(employee);
        }
    }

    @Override
    public Employee getEmployee(String name) {
        log.debug("Get employee by name");
        List<Employee> empolyeeList;
        String sql = "select * from employee where name=?" + name;
        empolyeeList = jdbcTemplate.query(sql, new EmployeeRowMapper());
        return empolyeeList.get(0);
    }

    @Override
    public void deleteEmployee(int id) {
        log.debug("Delete employee in employee table");
        String sql = "delete from employee where id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Employee> getEmployees() {
        List employeeList;
        String sql = "select * from employee";
        employeeList = jdbcTemplate.query(sql, new EmployeeRowMapper());
        return employeeList;
    }
}
