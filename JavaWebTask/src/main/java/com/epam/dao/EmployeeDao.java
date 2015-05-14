package com.epam.dao;

import com.epam.entity.Employee;

import java.util.List;

/**
 * Created by Roman
 */
public interface EmployeeDao {
    public void addEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public Employee getEmployee(int id);
    public void deleteEmployee(int id);
    public List<Employee> getEmployees();
}
