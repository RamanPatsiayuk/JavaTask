package com.epam.model;

/**
 * Created by Roman
 */
public class Department {

    private Integer departmentId;
    private String departmentName;
    private String location;

    public Department(){

    }

    public Department(Integer id, String departmentName, String location) {
        this.departmentId = id;
        this.departmentName = departmentName;
        this.location = location;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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
        return (this.departmentId == department.departmentId) && (this.departmentName != null && department.equals(department.departmentName)) &&
                (this.location != null && location.equals(department.location));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + departmentId;
        result = prime * result + (departmentName != null ? departmentName.hashCode() : 0);
        result = prime * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
