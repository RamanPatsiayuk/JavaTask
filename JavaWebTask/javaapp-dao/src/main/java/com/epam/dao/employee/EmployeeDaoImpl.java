package com.epam.dao.employee;

import com.epam.model.Employee;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Roman
 */

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    List<Employee> employeeList;
    static final Logger log = Logger.getLogger(EmployeeDaoImpl.class);
    private static final String addEmployeeSql = "insert into employee (id,firstName,lastName,address,position, department) values (?,?,?,?,?,?)";
    private static final String updateEmployeeSql = "update employee set firstName=?, lastName=?, address=?,position=?,department=? where id=?";
    private static final String deleteEmployeeSql = "delete from employee where id=?";
    private static final String getEmployeeSql = "select * from employee";
    private static final String getEmployeeByIdSql = "select * from employee where id=?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addEmployee(Employee employee) {
        log.debug("Add employee in employee table");
        jdbcTemplate.update(addEmployeeSql, new Object[]{employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getAddress(), employee.getPosition(), employee.getDepartmentId(),employee.getSalary()});
    }

    @Override
    public void updateEmployee(Employee employee) {
        log.debug("Update employee in employee table");
        if (employee.getId() > 0) {
            // update
            jdbcTemplate.update(updateEmployeeSql, employee.getFirstName(), employee.getLastName(), employee.getAddress(), employee.getPosition(), employee.getDepartmentId(),employee.getSalary(), employee.getId());
        } else {
            addEmployee(employee);
        }
    }

    @Override
    public Employee getEmployee(String name) {
        log.debug("Get employee by name");
        List<Employee> empoloyeeList;
        //empoloyeeList = jdbcTemplate.query(getEmployeeByIdSql, new EmployeeRowMapper(), name);
        //return empoloyeeList.get(0);
        Employee empoloyee = jdbcTemplate.queryForObject(getEmployeeByIdSql, new Object[]{name}, new EmployeeRowMapper());
        return empoloyee;
    }

    @Override
    public void deleteEmployee(int id) {
        log.debug("Delete employee in employee table");
        jdbcTemplate.update(deleteEmployeeSql, id);
    }

    @Override
    public List<Employee> getEmployees() {
        employeeList = jdbcTemplate.query(getEmployeeSql, new EmployeeRowMapper());
        return employeeList;
    }
}
