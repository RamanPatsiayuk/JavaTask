package com.epam.dao;

import com.epam.model.Employee;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman
 */

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    public DataSource dataSource;

    static final Logger log = Logger.getLogger(EmployeeDaoImpl.class);
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addEmployee(Employee employee) {
        log.debug("Add employee in employee table");
        String sql = "insert into employee (id,firstName,lastName,address,title, department) values (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{employee.getId(),employee.getFirstName(), employee.getLastName(), employee.getAddress(), employee.getTitle(), employee.getDepartment()});
    }

    @Override
    public void updateEmployee(Employee employee) {
        log.debug("Update employee in employee table");
        if (employee.getId() > 0) {
            // update
            String sql = "update employee set firstName=?, lastName=?, address=?,title=?,department=? where id=?";
            jdbcTemplate.update(sql,employee.getFirstName(), employee.getLastName(), employee.getAddress(), employee.getTitle(), employee.getDepartment(), employee.getId());
        } else {
            addEmployee(employee);
        }
    }

    @Override
    public Employee getEmployee(String name) {
        log.debug("Get employee by name");
        List<Employee> empoloyeeList;
        String sql = "select * from employee where firstName=" + name;
        empoloyeeList = jdbcTemplate.query(sql,new EmployeeRowMapper());
        return empoloyeeList.get(0);
        /*Employee empoloyee = jdbcTemplate.queryForObject(sql, new Object[]{name}, new EmployeeRowMapper());
        return empoloyee;*/
    }

    @Override
    public void deleteEmployee(int id) {
        log.debug("Delete employee in employee table");
        String sql = "delete from employee where id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Employee> getEmployees() {
        List<Employee> employeeList;
        String sql = "select * from employee";
        employeeList = jdbcTemplate.query(sql, new EmployeeRowMapper());
        return employeeList;
    }
}
