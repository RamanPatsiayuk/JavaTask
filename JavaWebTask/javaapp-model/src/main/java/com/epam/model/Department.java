package com.epam.model;

/**
 * Created by Roman
 */
public class Department {

    private Integer departmentId;
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

    public void setDepartmentId(Integer departmentId) {
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

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (other == null || (this.getClass() != other.getClass())) {
            return false;
        }

        Department department = (Department) other;
        return (this.departmentId == department.departmentId) && (this.department != null && department.equals(department.department)) &&
                (this.location != null && location.equals(department.location));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + departmentId;
        result = prime * result + (department != null ? department.hashCode() : 0);
        result = prime * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
