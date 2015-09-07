package com.epam.model;

import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * Created by Roman
 */
public class DepartmentTest extends Assert {
    private final List<Department> listDepartment= new ArrayList<Department>();
    Department department;


    @Before
    public void setUp() {
        department = new Department();
        listDepartment.add(new Department(4,"Java","Brest"));
        listDepartment.add(new Department(5,"Groovy","Brest"));
    }

    @Test
    public void testGetDepartment() throws Exception {
        String expected = ".Net";
        department.setDepartmentName(".Net");
        assertThat(department.getDepartmentName(), IsEqual.equalTo(expected));
    }

    @Test
    public void testDepartmentListSize() throws Exception{
        int sizeBefore = listDepartment.size();
        listDepartment.add(new Department(2,"Java","Minsk"));
        int sizeAfter = listDepartment.size();
        assertThat(sizeAfter,greaterThanOrEqualTo(sizeBefore));
    }

    @Test(expected = NullPointerException.class)
    public void testNPEAnnotation() {
        throw new NullPointerException();
    }

    @After
    public void tearDown() {
        department = null;
        listDepartment.clear();
    }
}
