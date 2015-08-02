package com.epam.service.employee;

import com.epam.dao.employee.EmployeeDao;
import com.epam.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Roman
 */
@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        if(employee.getFirstName() != null){
            employeeDao.updateEmployee(employee);
        }
    }

    @Override
    public List<Employee> getEmployee(String name) {
        return employeeDao.getEmployeeByFirstName(name);
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
