package com.epam.service.employee;

import com.epam.model.Employee;

import java.util.List;

/**
 * Created by Roman
 */
public interface EmployeeService {
    public int insertEmployee(Employee employee);

    //public void addEmployee(Employee employee);

    public void updateEmployee(Employee employee);

    public List<Employee> getEmployeeByFirstName(String name);

    public void deleteEmployee(int id);

    public List<Employee> getEmployees();

    public Employee getEmployeeById(int id);

}
