package com.epam.model;

import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
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
    public void testDepartmentId(){
        department.setDepartmentId(1);
        int id = department.getDepartmentId();
        assertThat(id, equalTo(1));
    }

    @Test
    public void testGetDepartment() throws Exception {
        String expected = ".Net";
        department.setDepartmentName(".Net");
        assertThat(department.getDepartmentName(), IsEqual.equalTo(expected));
    }

    @Test
    public void testDepartmentLocation(){
        department.setLocation("Brest,Kyubisheva 13");
        assertThat(department.getLocation(), equalTo("Brest,Kyubisheva 13"));
    }

    @Test
    public void testEqualDepartments(){
        Department dep1 = listDepartment.get(0);
        Department dep2 = null;
        assertFalse(dep1.equals(dep2));
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

    @Test
    public void testHashCodes(){
        Department dep1 = new Department(4,"Java","Brest");
        Department dep2 = new Department(4,"Java","Brest");
        assertTrue(dep1.hashCode() == dep2.hashCode());
    }

    @After
    public void tearDown() {
        department = null;
        listDepartment.clear();
    }
}
