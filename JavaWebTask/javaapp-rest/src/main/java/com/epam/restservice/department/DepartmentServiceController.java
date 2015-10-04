package com.epam.restservice.department;

import com.epam.model.Department;
import com.epam.service.department.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Roman
 */

@RestController
@RequestMapping("/department")
public class DepartmentServiceController {

    static final Logger log = Logger.getLogger(DepartmentServiceController.class);

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = {"/addDepartment/{departmentId}/{departmentName}/{location}"}, method = RequestMethod.POST)
    public void addDepartment(@PathVariable Integer departmentId, @PathVariable String departmentName,
                              @PathVariable String location) {
        log.info("Start add department");
        departmentService.insertDepartment(new Department(departmentId, departmentName, location));
    }

    @RequestMapping(value = {"/editDepartment/{departmentId}/{departmentName}/{location}"}, method = RequestMethod.PUT)
    public String updateDepartment(@PathVariable Integer departmentId, @PathVariable String departmentName,
                                   @PathVariable String location) {
        log.info("Start update department");
        Department department = new Department(departmentId, departmentName, location);
        departmentService.updateDepartment(department);
        return "redirect:/javaapp-restservice/departmentService/" + departmentId;
    }

    @RequestMapping(value = {"/{departmentName}"}, method = RequestMethod.GET)
    public List<Department> getDepartment(@PathVariable String departmentName) {
        log.info("Start getEmployee by name=" + departmentName);
        return departmentService.getDepartmentByName(departmentName);
    }

    @RequestMapping(value = {"/delete/{id}"}, method = RequestMethod.PUT)
    public String deleteDepartment(@PathVariable int id) {
        log.info("Start delete Employees.");
        departmentService.deleteDepartment(id);
        return "Department " + id + " deleted";
    }

    @RequestMapping(value = {"/listDepartment"}, method = RequestMethod.GET)
    public List<Department> listDepartments() {
        log.info("Start getAllDepartments.");
        return departmentService.getDepartments();
    }
}
