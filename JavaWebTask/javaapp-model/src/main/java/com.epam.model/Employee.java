package com.epam.model;

/**
 * Created by Roman
 */

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String title;
    private String department;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, String address, String title, String department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.title = title;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (other == null || (this.getClass() != other.getClass())) {
            return false;
        }

        Employee empl = (Employee) other;
        return (this.id == empl.id) && (this.firstName != null && firstName.equals(empl.firstName)) &&
                (this.lastName != null && lastName.equals(empl.lastName)) && (this.address != null && address.equals(empl.address))
                && (this.title != null && title.equals(empl.title)) && (this.department != null && department.equals(empl.department));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + (firstName != null ? firstName.hashCode() : 0);
        result = prime * result + (lastName != null ? lastName.hashCode() : 0);
        result = prime * result + (address != null ? address.hashCode() : 0);
        result = prime * result + (title != null ? title.hashCode() : 0);
        result = prime * result + (department != null ? department.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee firstName=" + this.firstName + ", lastName=" + this.lastName + ", address=" + this.address + ", title=" + this.title + ", department=" + this.department + '}';
    }
}
