package com.epam.service.employee;

import com.epam.dao.employee.EmployeeDao;
import com.epam.model.Employee;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Roman
 */
@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    static final Logger log = Logger.getLogger(EmployeeServiceImpl.class);

    @Qualifier("employeeDao")
    @Autowired
    private EmployeeDao employeeDao;

    /*@Autowired
    private EmployeeValidator employeeValidator;*/

    @Override
    public int insertEmployee(final Employee employee) {
        log.debug("Insert employee in employee table");
        if (employee == null) {
            throw new IllegalArgumentException("Employee is null");
        } else if (null == employee.getFirstName() || ("".equals(employee.getFirstName().trim()))) {
            throw new IllegalArgumentException();
        } else if ((null == employee.getLastName()) || ("".equals(employee.getLastName().trim()))) {
            throw new IllegalArgumentException();
        } else if ((null == employee.getAddress()) || (employee.getAddress().trim().length() == 0)) {
            throw new IllegalArgumentException();
        } else if ((null == employee.getPosition()) || ("".equals(employee.getPosition().trim()))) {
            throw new IllegalArgumentException();
        } else if ((null == employee.getDepartmentId())) {
            throw new IllegalArgumentException();
        }else if ((employee.getSalary()<0)) {
            throw new IllegalArgumentException();
        } else {
            List<Employee> exEmployee = employeeDao.getEmployeeByFirstName(employee.getFirstName());
            if ((exEmployee != null) && (exEmployee.size() > 0)) {
                throw new IllegalArgumentException();
            }
        }
        return employeeDao.insertEmployee(employee);
    }

    @Override
    public void updateEmployee(final Employee employee) {
        log.debug("Update employee in employee table");
        Employee exEmployee = employeeDao.getEmployeeById(employee.getEmployeeId());
        if (exEmployee != null) {
            employeeDao.updateEmployee(employee);
        }
    }

    @Override
    public List<Employee> getEmployeeByFirstName(final String name) {
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
        Employee employee = employeeDao.getEmployeeById(id);
        if (employee != null) {
            employeeDao.deleteEmployee(id);
        }
    }

    @Override
    public List<Employee> getEmployees() {
        log.debug("Get all employees");
        List<Employee> employees = employeeDao.getEmployees();
        return employees;
    }

    @Override
    public Employee getEmployeeById(int id) {
        log.debug("Get employee by id in employee table");
        Employee exEmployee = employeeDao.getEmployeeById(id);
        if (exEmployee == null) {
            throw new IllegalArgumentException("Object is not existing in Employee database");
        }
        return exEmployee;
    }

    @Override
    public List<Object> getEmployeesInDepartment() {
        log.info("Get employee in department");
        return employeeDao.getEmployeesInDepartment();
    }
}
