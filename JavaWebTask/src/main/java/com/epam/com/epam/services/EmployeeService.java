package com.epam.com.epam.services;

import com.epam.model.Employee;

import java.util.List;

/**
 * Created by Roman
 */
public interface EmployeeService {
    public void addEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public Employee getEmployee(int id);
    public void deleteEmployee(int id);
    public List<Employee> getEmployees();
}
