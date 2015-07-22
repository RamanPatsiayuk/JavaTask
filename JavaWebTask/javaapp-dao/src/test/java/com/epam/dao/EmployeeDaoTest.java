package com.epam.dao;

import com.epam.dao.employee.EmployeeDao;
import com.epam.model.Department;
import com.epam.model.Employee;
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
 * Created by Raman_Patsiayuk
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/testDaoSpringContext.xml"})
public class EmployeeDaoTest {

    @Autowired
    public EmployeeDao employeeDao;

    Employee testEmployee;

    @Before
    public void setUp(){
        testEmployee =  new Employee(10, "Vasia", "Pupkin", "Brest, Green Street", "SE", 2,600);
    }

    @Test
    public void addEmployee() {
        List<Employee> employees = employeeDao.getEmployees();
        int sizeBefore = employees.size();
        employeeDao.addEmployee(testEmployee);
        employees = employeeDao.getEmployees();
        assertThat(sizeBefore + 1,greaterThanOrEqualTo(employees.size()));
    }

    @Test
    public void updateEmployee() {
        List<Employee> employees = employeeDao.getEmployees();
        employeeDao.updateEmployee(testEmployee);
        List<Employee> newEmployees = employeeDao.getEmployees();
        assertThat(employees.size(), equalTo(newEmployees.size()));
    }

    @Test
    //@Ignore
    public void getEmployee() {
        String firstName = "Vasia";
        List<Employee> employees = employeeDao.getEmployees();
        employeeDao.addEmployee(testEmployee);
        assertTrue(employeeDao.getEmployeeByFirstName(firstName).contains(testEmployee));
        //assertThat(employees, contains(testEmployee));//equalTo(testEmployee));
    }

    @Test
    public void deleteEmployee() {
        List<Employee> employees = employeeDao.getEmployees();
        int sizeBefore = employees.size();
        employeeDao.deleteEmployee(2);
        employees = employeeDao.getEmployees();
        assertThat("Delete employee", sizeBefore - 1, lessThanOrEqualTo(employees.size()));
    }

    @Test
    public void getEmployees() {
        List<Employee> employees = employeeDao.getEmployees();
        assertNotNull(employees);
        assertFalse(employees.isEmpty());
    }

    @After
    public void tearDown(){
        employeeDao = null;
        testEmployee = null;
    }
}
