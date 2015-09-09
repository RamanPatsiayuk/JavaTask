package com.epam.service.employee;

import com.epam.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
}
