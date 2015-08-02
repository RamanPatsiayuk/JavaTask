package com.epam.service.department;

import com.epam.model.Department;

import java.util.List;

/**
 * Created by Roman
 */
public interface DepartmentService {

    public void addDepartment(Department department);

    public void updateDepartment(Department department);

    public List<Department> getDepartmentByName(String name);

    public void deleteDepartment(int id);

    public List<Department> getDepartments();

}
