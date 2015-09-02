package com.epam.dao;

import com.epam.dao.department.DepartmentDao;
import com.epam.model.Department;
import org.junit.After;
import org.junit.Before;
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
import static org.junit.Assert.*;

/**
 * Created by Roman
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/testDaoSpringContext.xml"})
public class DepartmentDaoTest {

    @Autowired
    public DepartmentDao departmentDao;

    List<Department> departments;
    Department testDepartment;
    Department testDepartment1;

    @Before
    public void setUp(){
        departments = departmentDao.getDepartments();
        testDepartment = new Department(7,"Scala", "Gomel");
        testDepartment1 = new Department(9,"Haskell", "Gomel");
    }

    @Test
    public void insertDepartment() {
        departments = departmentDao.getDepartments();
        int sizeBefore = departments.size();
        departmentDao.insertDepartment(testDepartment);
        departments = departmentDao.getDepartments();
        assertThat(sizeBefore + 1,greaterThanOrEqualTo(departments.size()));
    }

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
    public void getDepartmentByName() {
        String department = "Scala";
        departmentDao.addDepartment(testDepartment);
        List<Department> dep = departmentDao.getDepartmentByName(department);
        assertTrue(dep.size()>0);
    }

    @Test
    @Ignore
    public void getDepartmentById() {
        departmentDao.addDepartment(testDepartment1);
        departments = departmentDao.getDepartments();
        Department dep = departmentDao.getDepartmentById(9);
        assertTrue(dep.equals(testDepartment1));
    }

    @Test
    public void deleteDepartment() {
        int sizeBefore = departments.size();
        departmentDao.deleteDepartment(8);
        departments = departmentDao.getDepartments();
        assertThat("Delete employee", sizeBefore - 1, lessThanOrEqualTo(departments.size()));
    }

    @Test
    public void getDepartments() {
        assertNotNull(departments);
        assertFalse(departments.isEmpty());
    }

    @After
    public void tearDown(){
        departments = null;
        testDepartment = null;
        testDepartment1 = null;
    }

}
