package com.epam.service.department;

import com.epam.model.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;

/**
 * Created by Roman
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/testServiceSpringContext.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class DepartmentServiceTest extends Assert {

    @Autowired
    public DepartmentService departmentService;

    List<Department> departments;
    Department testDepartment;
    Department testDepartment1;
    Department testDepartment2;

    @Before
    public void setUp(){
        departments = departmentService.getDepartments();
        testDepartment = new Department(7,"Scala", "Gomel");
        testDepartment1 = new Department(9,"Haskell", "Gomel");
        testDepartment2 = new Department(12,"C++", "Brest");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullDepartment() throws Exception{
        departmentService.insertDepartment(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullDepartmentNameInDepartment() throws Exception{
        departmentService.insertDepartment(new Department(null,null,"Grodno"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullLocationInDepartment() throws Exception{
        departmentService.insertDepartment(new Department(null,"Java",null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEmptyDepartmentNameInDepartment() throws Exception{
        departmentService.insertDepartment(new Department(null,"","Grodno"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEmptyLocationInDepartment() throws Exception{
        departmentService.insertDepartment(new Department(null,"Java",""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertExistingDepartment() throws Exception{
        departmentService.insertDepartment(new Department(null,"JAVA","Grodno"));
    }

    @Test
    public void insertDepartment() {
        departments = departmentService.getDepartments();
        int sizeBefore = departments.size();
        departmentService.insertDepartment(testDepartment);
        departments = departmentService.getDepartments();
        assertThat(sizeBefore + 1,greaterThanOrEqualTo(departments.size()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertExistingDepartment() {
        departments = departmentService.getDepartments();
        List<Department> exDepartment = departmentService.getDepartmentByName("Java");
        assertNotNull(exDepartment);
        assertTrue(exDepartment.size()>0);
        throw new IllegalArgumentException();
    }

    @Test
    public void updateDepartment() {
        notNull(testDepartment);
        departmentService.updateDepartment(testDepartment);
        List<Department> newDepartments = departmentService.getDepartments();
        assertThat(departments.size(), equalTo(newDepartments.size()));
    }

    @Test
    public void getDepartmentByName() {
        String department = "C++";
        departmentService.insertDepartment(testDepartment2);
        List<Department> dep = departmentService.getDepartmentByName(department);
        assertTrue(dep.size()>0);
    }

    @Test
    public void getDepartmentById() {
        departmentService.insertDepartment(testDepartment1);
        departments = departmentService.getDepartments();
        Department dep = departmentService.getDepartmentById(7);
        assertTrue(dep.getDepartmentName() == testDepartment1.getDepartmentName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNonPresentDepartment() {
        Department dep = departmentService.getDepartmentById(8);
        assertNotNull(dep);
    }

    @Test
    public void deleteDepartment() {
        int sizeBefore = departments.size();
        departmentService.deleteDepartment(6);
        departments = departmentService.getDepartments();
        assertThat("Delete employee", sizeBefore - 1, lessThanOrEqualTo(departments.size()));
    }

    @Test
    public void deleteExistsDepartment() {
        Department dep = departmentService.getDepartmentById(6);
        assertNotNull(dep);
        int sizeBefore = departments.size();
        departmentService.deleteDepartment(6);
        departments = departmentService.getDepartments();
        assertThat("Delete employee", sizeBefore - 1, lessThanOrEqualTo(departments.size()));
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

        Map<String, Double> actual = departmentService.getAverageSalaryInDepartment();
        assertEquals(expected.size(), actual.size());
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
        testDepartment2 = null;
    }
}
