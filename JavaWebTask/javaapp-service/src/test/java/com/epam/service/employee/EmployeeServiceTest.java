package com.epam.service.employee;

import com.epam.model.Employee;
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

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
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
public class EmployeeServiceTest extends Assert {

    @Autowired
    public EmployeeService employeeService;

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
        List<Employee> employees = employeeService.getEmployees();
        int sizeBefore = employees.size();
        employeeService.insertEmployee(testEmployee2);
        employees = employeeService.getEmployees();
        assertThat(sizeBefore + 1,greaterThanOrEqualTo(employees.size()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullEmployee() throws Exception{
        employeeService.insertEmployee(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullEmployeeFirstName() throws Exception{
        employeeService.insertEmployee(new Employee(null, null, "Pupkin", "Brest, Green Street", "SE", null, 600));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullEmployeeLastName() throws Exception{
        employeeService.insertEmployee(new Employee(null, "Kostia", null, "Brest, Green Street", "SE", null,600));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullEmployeeAddress() throws Exception{
        employeeService.insertEmployee(new Employee(null, "Kostia", "Pupkin", null, "SE", null,600));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullEmployeePosition() throws Exception{
        employeeService.insertEmployee(new Employee(null, "Kostia", "Pupkin", "Brest, Green Street", null, null,600));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEmptyEmployeeFirstName() throws Exception{
        employeeService.insertEmployee(new Employee(null, "", "Pupkin", "Brest, Green Street", "SE", null,600));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEmptyEmployeeLastName() throws Exception{
        employeeService.insertEmployee(new Employee(null, "Kostia", "", "Brest, Green Street", "SE", null,600));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEmptyEmployeeAddress() throws Exception{
        employeeService.insertEmployee(new Employee(null, "Kostia", "Pupkin", "", "SE", null,600));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEmptyEmployeePosition() throws Exception{
        employeeService.insertEmployee(new Employee(null, "Kostia", "Pupkin", "Brest, Green Street", "", null,600));
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertExistingEmployee() throws Exception{
        employeeService.insertEmployee(new Employee(10, "Vasia", "Pupkin", "Brest, Green Street", "SE", 2,600));
    }

    @Test
    public void updateEmployee() {
        List<Employee> employees = employeeService.getEmployees();
        employeeService.updateEmployee(testEmployee);
        List<Employee> newEmployees = employeeService.getEmployees();
        assertThat(employees.size(), equalTo(newEmployees.size()));
    }

    @Test
    public void getEmployeeByFirstName() {
        String firstName = "Masha";
        employeeService.insertEmployee(testEmployee3);
        List<Employee> empByFirstName = employeeService.getEmployeeByFirstName(firstName);
        assertThat(empByFirstName.size(), greaterThanOrEqualTo(1));
    }

    @Test
    public void getEmployeeById() {
        employeeService.insertEmployee(testEmployee4);
        Employee emp = employeeService.getEmployeeById(4);
        assertTrue(emp.equals(testEmployee4));
    }

    @Test
    public void deleteEmployee() {
        List<Employee> employees = employeeService.getEmployees();
        int sizeBefore = employees.size();
        employeeService.deleteEmployee(2);
        employees = employeeService.getEmployees();
        assertThat("Delete employee", sizeBefore - 1, lessThanOrEqualTo(employees.size()));
    }

    @Test
    public void getEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        assertNotNull(employees);
        assertFalse(employees.isEmpty());
    }

    @After
    public void tearDown(){
        employeeService = null;
        testEmployee = null;
        testEmployee1 = null;
        testEmployee2 = null;
        testEmployee3 = null;
        testEmployee4 = null;
    }
}
