package com.epam.dao.department;

import com.epam.model.Department;

import java.util.List;

/**
 * Created by Roman
 */
public interface DepartmentDao {

    public void addDepartment(Department department);

    public void updateDepartment(Department department);

    public List<Department> getDepartmentByDepartment(String name);

    public void deleteDepartment(int id);

    public List<Department> getDepartments();

}
