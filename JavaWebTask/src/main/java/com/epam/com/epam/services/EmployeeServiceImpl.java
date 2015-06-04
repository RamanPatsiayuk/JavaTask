package com.epam.com.epam.services;

import com.epam.dao.EmployeeDao;
import com.epam.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Roman
 */
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    @Override
    public Employee getEmployee(String name) {
        return employeeDao.getEmployee(name);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeDao.deleteEmployee(id);
    }

    @Override
    public List<Employee> getEmployees() {
         return employeeDao.getEmployees();
    }
}
