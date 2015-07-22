package com.epam.dao;

import com.epam.dao.department.DepartmentDao;
import com.epam.model.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Roman
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/testDaoSpringContext.xml"})
public class DepartmentDaoTest {

    @Autowired
    public DepartmentDao departmentDao;

    Department testDepartment = new Department(2,"Java", "Gomel");

    @Test
    public void addDepartment() {
        List<Department> departments = departmentDao.getDepartments();
        int sizeBefore = departments.size();
        departmentDao.addDepartment(testDepartment);
        departments = departmentDao.getDepartments();
        assertThat(sizeBefore + 1,greaterThanOrEqualTo(departments.size()));
    }
}
