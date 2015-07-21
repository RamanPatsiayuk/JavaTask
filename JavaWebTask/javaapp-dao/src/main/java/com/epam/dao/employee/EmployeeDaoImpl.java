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
    private static final String addEmployeeSql = "insert into employee (employee_id,firstName,lastName,address,position, departmentId, salary) values (?,?,?,?,?,?,?)";
    private static final String updateEmployeeSql = "update employee set firstName=?, lastName=?, address=?,position=?,departmentId=?, salary=? where employee_id=?";
    private static final String deleteEmployeeSql = "delete from employee where employee_id=?";
    private static final String getEmployeeSql = "select * from employee";
    private static final String getEmployeeByFirstNameSql = "select * from employee where firstName=?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addEmployee(Employee employee) {
        log.debug("Add employee in employee table");
        jdbcTemplate.update(addEmployeeSql, new Object[]{employee.getEmployee_id(), employee.getFirstName(), employee.getLastName(), employee.getAddress(), employee.getPosition(), employee.getDepartmentId(),employee.getSalary()});
    }

    @Override
    public void updateEmployee(Employee employee) {
        log.debug("Update employee in employee table");
        if (employee.getEmployee_id() > 0) {
            // update
            jdbcTemplate.update(updateEmployeeSql, employee.getFirstName(), employee.getLastName(), employee.getAddress(), employee.getPosition(), employee.getDepartmentId(),employee.getSalary(), employee.getEmployee_id());
        } else {
            addEmployee(employee);
        }
    }

    @Override
    public Employee getEmployee(String name) {
        log.debug("Get employee by name");
        /*List<Employee> empoloyeeList = employeeDa;
        for (Employee p : empoloyeeList) {
            if (oldest == null || p.getAge() > oldest.getAge()) oldest = p;
        }*/
        employeeList = jdbcTemplate.query(getEmployeeByFirstNameSql, new EmployeeRowMapper(), name);
        return employeeList.get(0);
        /*Employee empoloyee = jdbcTemplate.queryForObject(getEmployeeByFirstNameSql, new Object[]{name}, new EmployeeRowMapper());
        return empoloyee;*/
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
