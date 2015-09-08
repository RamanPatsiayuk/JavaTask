package com.epam.service.employee;

import com.epam.dao.employee.EmployeeDao;
import com.epam.model.Employee;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public int insertEmployee(final Employee employee) {
        log.debug("Insert employee in employee table");
        Employee exEmployee = getEmployeeById(employee.getEmployeeId());
        if (exEmployee != null) {
            throw new IllegalArgumentException("Object is existing in Employee database");
        }
        return employeeDao.insertEmployee(employee);
    }

    @Override
    public void updateEmployee(final Employee employee) {
        if(employee.getFirstName() != null & employee.getLastName() != null){
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
        employeeDao.deleteEmployee(id);
    }

    @Override
    public List<Employee> getEmployees() {
        log.debug("Get all employees");
        List<Employee> employees = employeeDao.getEmployees();
        return employees;
    }

    @Override
    public Employee getEmployeeById(int id) {
        log.debug("Update employee in employee table");
        Employee exEmployee = employeeDao.getEmployeeById(id);
        if (exEmployee != null) {
            throw new IllegalArgumentException("Object is existing in Employee database");
        }
        return exEmployee;
    }

    public void setEmployeeDao(com.epam.dao.employee.EmployeeDaoImpl employeeDao) {
    }
}
