<%--
  Created by IntelliJ IDEA.
  User: Roman
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring mvc. Employee home page</title>
    </head>
<body>
    <b>Employee list</b>
    <table border="1">
        <tr><td colspan="7"><a href="addedEmployee">Add New Employee</a></td></tr>
        <tr>
            <td>Employee Id</td>
            <td>First Name</td>
            <td>Last Name</td>
            <td>Address</td>
            <td>Position</td>
            <td>Department Id</td>
            <td>Salary</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        <c:forEach var="employee" items="${employeeList}">
            <tr>
                <td>${employee.employeeId}</td>
                <td>${employee.firstName}</td>
                <td>${employee.lastName}</td>
                <td>${employee.address}</td>
                <td>${employee.position}</td>
                <td>${employee.departmentId}</td>
                <td>${employee.salary}</td>
                <td><a href="edit?id=${employee.employeeId}">Edit</a></td>
                <td><a href="delete?id=${employee.employeeId}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

