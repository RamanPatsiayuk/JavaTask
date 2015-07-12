package com.epam.model;

/**
 * Created by Roman
 */
public class Department {

    private int departmentId;
    private String department;
    private String location;

    public Department(){

    }

    public Department(int id, String department, String location) {
        this.departmentId = id;
        this.department = department;
        this.location = location;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
