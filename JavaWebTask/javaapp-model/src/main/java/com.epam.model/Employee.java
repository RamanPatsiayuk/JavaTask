package com.epam.model;

/**
 * Created by Roman
 */

public class Employee {

    private int employee_id;
    private String firstName;
    private String lastName;
    private String address;
    private String position;
    private int departmentId;
    private double salary;

    public Employee() {
    }

    public Employee(int employee_id, String firstName, String lastName, String address, String title, int departmentId,double salary) {
        this.employee_id = employee_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.position = title;
        this.departmentId = departmentId;
        this.salary = salary;
    }

    public Integer getId() {
        return employee_id;
    }

    public void setId(int employee_id) {
        this.employee_id = employee_id;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (other == null || (this.getClass() != other.getClass())) {
            return false;
        }

        Employee empl = (Employee) other;
        return (this.employee_id == empl.employee_id) && (this.firstName != null && firstName.equals(empl.firstName)) &&
                (this.lastName != null && lastName.equals(empl.lastName)) && (this.address != null && address.equals(empl.address))
                && (this.position != null && position.equals(empl.position)) && (departmentId == empl.departmentId)&&(this.salary == empl.salary);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + employee_id;
        result = prime * result + (firstName != null ? firstName.hashCode() : 0);
        result = prime * result + (lastName != null ? lastName.hashCode() : 0);
        result = prime * result + (address != null ? address.hashCode() : 0);
        result = prime * result + (position != null ? position.hashCode() : 0);
        result = prime * result + departmentId;
        result = prime * result + (int)salary;
        return result;
    }

    @Override
    public String toString() {
        return "Employee firstName=" + this.firstName + ", lastName=" + this.lastName + ", address=" + this.address + ", position=" + this.position + ", departmentId=" + this.departmentId + ", salary=" + this.salary + '}';
    }
}
