package com.epam.dao;

import com.epam.dao.employee.EmployeeDao;
import com.epam.model.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;

/**
 * Created by Raman_Patsiayuk
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/testDaoSpringContext.xml"})
public class EmployeeDaoTest extends Assert {

    @Autowired
    public EmployeeDao employeeDao;

    Employee testEmployee;
    Employee testEmployee1;
    Employee testEmployee2;
    Employee testEmployee3;
    Employee testEmployee4;

    @Before
    public void setUp(){
        testEmployee =  new Employee(10, "Vasia", "Pupkin", "Brest, Green Street", "SE", 2,600);
        testEmployee1 = new Employee(7, "Dima", "Pupkin", "Brest, Green Street", "SE", 4, 600);
        testEmployee2 = new Employee(11, "Dasha", "Sidorova", "Brest, Sovetskaya street", "SSE", 3,700);
        testEmployee3 = new Employee(14, "Masha", "Dudkina", "Brest, Krasnogvardeiskaya street", "LSE", 5,720);
        testEmployee4 = new Employee(15, "Sasha", "Baranov", "Minsk, Voronianskogo street", "JTAE", 5,420);
    }

    @Test
    public void insertEmployee() {
        List<Employee> employees = employeeDao.getEmployees();
        int sizeBefore = employees.size();
        employeeDao.insertEmployee(testEmployee2);
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
    public void getEmployeeByFirstName() {
        String firstName = "Masha";
        employeeDao.insertEmployee(testEmployee3);
        List<Employee> empByFirstName = employeeDao.getEmployeeByFirstName(firstName);
        assertThat(empByFirstName.size(), greaterThanOrEqualTo(1));
    }

    @Test
    public void getEmployeeById() {
        employeeDao.insertEmployee(testEmployee4);
        Employee emp = employeeDao.getEmployeeById(testEmployee4.getEmployeeId());
        assertTrue(emp.equals(testEmployee4));
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
        testEmployee1 = null;
        testEmployee2 = null;
        testEmployee3 = null;
        testEmployee4 = null;
    }
}
