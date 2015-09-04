package com.epam.dao.employee;

import com.epam.model.Employee;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Roman
 */

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    static final Logger log = Logger.getLogger(EmployeeDaoImpl.class);
    private static final String insertEmployeeSql = "insert into employee (employeeId,firstName,lastName,address,position, departmentId, salary) values (:employeeId,:firstName,:lastName,:address,:position, :departmentId, :salary)";
    //private static final String addEmployeeSql = "insert into employee (employeeId,firstName,lastName,address,position, departmentId, salary) values (?,?,?,?,?,?,?)";
    private static final String updateEmployeeSql = "update employee set firstName=?, lastName=?, address=?,position=?,departmentId=?, salary=? where employeeId=?";
    private static final String deleteEmployeeSql = "delete from employee where employeeId=?";
    private static final String getEmployeeSql = "select * from employee order by firstName";
    private static final String getEmployeeByFirstNameSql = "select * from employee where firstName=?";
    private static final String getEmployeeById = "select * from employee where employeeId=?";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    //second variant
    /*@Override
    public int insertEmployee(Employee employee) {
        log.debug("Add employee in employee table");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("employeeId", employee.getEmployeeId());
        parameters.put("firstName", employee.getFirstName());
        parameters.put("lastName", employee.getLastName());
        parameters.put("address", employee.getAddress());
        parameters.put("position", employee.getPosition());
        parameters.put("departmentId", employee.getDepartmentId());
        parameters.put("salary", employee.getSalary());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertEmployeeSql, new MapSqlParameterSource(parameters), keyHolder);
        return keyHolder.getKey().intValue();
    }*/

    @Override
    public int insertEmployee(final Employee employee) {
        log.debug("Add employee in employee table");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertEmployeeSql, new BeanPropertySqlParameterSource(employee), keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void updateEmployee(final Employee employee) {
        log.debug("Update employee in employee table");
        jdbcTemplate.update(updateEmployeeSql, employee.getFirstName(), employee.getLastName(), employee.getAddress(), employee.getPosition(), employee.getDepartmentId(),employee.getSalary(), employee.getEmployeeId());
    }

    @Override
    public List<Employee> getEmployeeByFirstName(final String name) {
        log.debug("Get employee by name");
        return jdbcTemplate.query(getEmployeeByFirstNameSql,new Object[]{name}, new BeanPropertyRowMapper(Employee.class));
    }

    @Override
    public void deleteEmployee(int id) {
        log.debug("Delete employee in employee table");
        jdbcTemplate.update(deleteEmployeeSql, id);
    }

    @Override
    public List<Employee> getEmployees() {
        log.debug("Get all employees");
        return jdbcTemplate.query(getEmployeeSql, new BeanPropertyRowMapper(Employee.class));
    }

    @Override
    public Employee getEmployeeById(int id) {
        log.debug("Get employee by id");
        return jdbcTemplate.queryForObject(getEmployeeById, new EmployeeRowMapper(), id);
    }
}
