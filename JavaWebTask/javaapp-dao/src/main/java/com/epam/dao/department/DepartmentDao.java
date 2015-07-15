package com.epam.dao.department;

import com.epam.model.Department;

import java.util.List;

/**
 * Created by Roman
 */
public interface DepartmentDao {

    public void addDepartment(Department employee);

    public void updateDepartment(Department employee);

    public Department getDepartment(String name);

    public void deleteDepartment(int id);

    public List<Department> getDepartments();

}
