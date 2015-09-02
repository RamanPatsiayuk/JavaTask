package com.epam.service.employee;

import com.epam.dao.employee.EmployeeDao;
import com.epam.model.Employee;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

/**
 * Created by Roman
 */
@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    static final Logger log = Logger.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public int insertEmployee(Employee employee) {
        log.debug("Insert employee in employee table");
        assertThat(employee.getEmployeeId(), is(notNullValue()));
        assertThat(employee.getFirstName(), is(notNullValue()));
        assertThat(employee.getLastName(), is(notNullValue()));
        assertThat(employee.getAddress(), is(notNullValue()));
        assertThat(employee.getPosition(), is(notNullValue()));
        assertThat(employee.getDepartmentId(), is(notNullValue()));
        assertThat(employee.getSalary(), is(notNullValue()));
        Employee exEmployee = getEmployeeById(employee.getEmployeeId());
        if (exEmployee != null) {
            throw new IllegalArgumentException("Object is existing in Employee database");
        }
        return employeeDao.insertEmployee(employee);
    }

    @Override
    public void addEmployee(Employee employee) {
        log.debug("Update employee in employee table");
        Employee exEmployee = getEmployeeById(employee.getEmployeeId());
        if (exEmployee != null) {
            throw new IllegalArgumentException("Object is existing in Employee database");
        }
        employeeDao.addEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        if(employee.getFirstName() != null & employee.getLastName() != null){
            employeeDao.updateEmployee(employee);
        }
    }

    @Override
    public List<Employee> getEmployeeByFirstName(String name) {
        log.debug("getEmployeeByFirstName" + name);
        List<Employee> listEmployee = null;
        try {
            listEmployee = employeeDao.getEmployeeByFirstName(name);
        } catch (EmptyResultDataAccessException e) {
            log.debug("Empty result. Employee is not present in employee database " + name);
        }
        return listEmployee;
    }

    @Override
    public void deleteEmployee(int id) {
        log.debug("Delete employee");
        employeeDao.deleteEmployee(id);
    }

    @Override
    public List<Employee> getEmployees() {
        log.debug("Get all employees");
         return employeeDao.getEmployees();
    }

    @Override
    public Employee getEmployeeById(int id) {
        log.debug("Update employee in employee table");
        Employee exEmployee = getEmployeeById(id);
        if (exEmployee != null) {
            throw new IllegalArgumentException("Object is existing in Employee database");
        }
        return exEmployee;
    }
}
