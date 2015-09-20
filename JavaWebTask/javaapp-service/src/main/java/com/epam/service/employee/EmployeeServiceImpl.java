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
        if (employee == null) {
            throw new IllegalArgumentException("Employee is null");
        }else if (null == employee.getFirstName()) {
            throw new IllegalArgumentException();
        }else if ((null == employee.getLastName())||("".equals(employee.getLastName()))) {
            throw new IllegalArgumentException();
        }else if ((null == employee.getAddress())|| (employee.getAddress().length()==0)) {
            throw new IllegalArgumentException();
        }else if ((null == employee.getPosition()) || ("".equals(employee.getPosition()))) {
            throw new IllegalArgumentException();
        }else if ((null == employee.getDepartmentId())) {
            throw new IllegalArgumentException();
        } else {
            List<Employee> exEmployee = employeeDao.getEmployeeByFirstName(employee.getFirstName());
            if ((exEmployee != null) && (exEmployee.size()>0)) {
                throw new IllegalArgumentException();
            }
        }
        return employeeDao.insertEmployee(employee);
    }

    @Override
    public void updateEmployee(final Employee employee) {
        if (employee.getFirstName() != null & employee.getLastName() != null) {
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
        if (exEmployee == null) {
            throw new IllegalArgumentException("Object is not existing in Employee database");
        }
        return exEmployee;
    }

    public void setEmployeeDao(com.epam.dao.employee.EmployeeDaoImpl employeeDao) {
    }
}
