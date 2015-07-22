package com.epam.dao;

import com.epam.dao.department.DepartmentDao;
import com.epam.model.Department;
import com.epam.model.Employee;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Roman
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/testDaoSpringContext.xml"})
public class DepartmentDaoTest {

    @Autowired
    public DepartmentDao departmentDao;
    List<Department> departments = departmentDao.getDepartments();
    Department testDepartment = new Department(2,"Java", "Gomel");

    @Test
    public void addDepartment() {
        int sizeBefore = departments.size();
        departmentDao.addDepartment(testDepartment);
        departments = departmentDao.getDepartments();
        assertThat(sizeBefore + 1,greaterThanOrEqualTo(departments.size()));
    }

    @Test
    public void updateDepartment() {
        departmentDao.updateDepartment(testDepartment);
        List<Department> newDepartments = departmentDao.getDepartments();
        assertThat(departments.size(), equalTo(newDepartments.size()));
    }

    @Test
    @Ignore
    public void getDepartment() {
        String department = "Java";
        departments.add(testDepartment);
        Department newDepartment = departmentDao.getDepartment(department);
        //assertThat();
    }

    @Test
    public void deleteDepartment() {
        int sizeBefore = departments.size();
        departmentDao.deleteDepartment(2);
        departments = departmentDao.getDepartments();
        assertThat("Delete employee", sizeBefore - 1, lessThanOrEqualTo(departments.size()));
    }

    @Test
    public void getDepartments() {
        assertNotNull(departments);
        assertFalse(departments.isEmpty());
    }
}
