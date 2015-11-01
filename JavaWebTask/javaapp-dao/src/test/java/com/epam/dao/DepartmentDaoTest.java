package com.epam.dao;

import com.epam.dao.department.DepartmentDao;
import com.epam.model.Department;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;

/**
 * Created by Roman
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/testDaoSpringContext.xml"})
public class DepartmentDaoTest extends Assert {

    @Autowired
    public DepartmentDao departmentDao;

    List<Department> departments;
    Department testDepartment;
    Department testDepartment1;
    Department testDepartment2;

    @Before
    public void setUp(){
        departments = departmentDao.getDepartments();
        testDepartment = new Department(7,"Scala", "Gomel");
        testDepartment1 = new Department(9,"Haskell", "Gomel");
        testDepartment2 = new Department(10,"PHP", "Brest");
    }

    @Test
    public void getAverageSalary() {
        Map<String, Double> averageSalary = departmentDao.getAverageSalaryInDepartment();

        for (Map.Entry<String, Double> entry : averageSalary.entrySet())
        {
            System.err.println(entry.getKey() + "/" + entry.getValue());
        }
    }

    @Test
    public void getAverageSalaryMapSize(){
        Map<String,Double> expected = new HashMap<>();
        expected.put("Java",700.0);
        expected.put("Javascript",750.0);
        expected.put("Clojure",0.0);
        expected.put("Scala",0.0);
        expected.put("Groovy",1200.0);
        expected.put("Python",0.0);

        Map<String, Double> actual = departmentDao.getAverageSalaryInDepartment();
        assertEquals(expected.size(), actual.size());
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
    public void updateDepartment() {
        departmentDao.updateDepartment(testDepartment);
        List<Department> newDepartments = departmentDao.getDepartments();
        assertThat(departments.size(), equalTo(newDepartments.size()));
    }

    @Test
    public void getDepartmentByName() {
        String department = "PHP";
        departmentDao.insertDepartment(testDepartment2);
        List<Department> dep = departmentDao.getDepartmentByName(department);
        assertTrue(dep.size()>0);
    }

    @Test
    public void getDepartmentById() {
        departmentDao.insertDepartment(testDepartment1);
        departments = departmentDao.getDepartments();
        Department dep = departmentDao.getDepartmentById(7);
        assertTrue(dep.getDepartmentName() == testDepartment1.getDepartmentName());
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

    @Test(expected = EmptyResultDataAccessException.class)
    public void getDepartmentByNonPresentId() {
        throw new EmptyResultDataAccessException(0);
    }

    @After
    public void tearDown(){
        departments = null;
        testDepartment = null;
        testDepartment1 = null;
        testDepartment2 = null;
    }

}
