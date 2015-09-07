package com.epam.service.department;

import com.epam.model.Department;
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
public class DepartmentServiceTest extends Assert {

    @Autowired
    public DepartmentService departmentService;

    @Test(expected = NullPointerException.class)
    public void addNullDepartment() throws Exception{
        departmentService.insertDepartment(null);
    }

    @Test(expected = AssertionError.class)
    public void addNullDepartmentNameInDepartment() throws Exception{
        departmentService.insertDepartment(new Department(1,null,"Grodno"));
    }

    @Test(expected = AssertionError.class)
    public void addNullLocationInDepartment() throws Exception{
        departmentService.insertDepartment(new Department(1,"Java",null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEmptyDepartmentNameInDepartment() throws Exception{
        departmentService.insertDepartment(new Department(1,"","Grodno"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEmptyLocationInDepartment() throws Exception{
        departmentService.insertDepartment(new Department(1,"Java",""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDepartmentNameInUpperCase() throws Exception{
        departmentService.insertDepartment(new Department(2,"JAVA","Grodno"));
    }



}
