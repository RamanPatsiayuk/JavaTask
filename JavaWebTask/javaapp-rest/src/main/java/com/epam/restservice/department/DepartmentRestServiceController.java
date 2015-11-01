package com.epam.restservice.department;

import com.epam.model.Department;
import com.epam.service.department.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Roman
 */

@RestController
@RequestMapping("/department")
public class DepartmentRestServiceController {

    static final Logger log = Logger.getLogger(DepartmentRestServiceController.class);

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = {"/addDepartment/{departmentId}/{departmentName}/{location}"}, method = RequestMethod.POST)
    public void addDepartment(@PathVariable Integer departmentId, @PathVariable String departmentName,
                              @PathVariable String location) {
        log.info("Start add department");
        departmentService.insertDepartment(new Department(departmentId, departmentName, location));
    }

    @RequestMapping(value = {"/editDepartment/{departmentId}/{departmentName}/{location}"}, method = RequestMethod.PUT)
    public @ResponseBody String updateDepartment(@PathVariable Integer departmentId, @PathVariable String departmentName,
                                   @PathVariable String location) {
        log.info("Start update department");
        Department department = new Department(departmentId, departmentName, location);
        departmentService.updateDepartment(department);
        return "redirect:/javaapp-restservice/departmentService/" + departmentId;
    }

    @RequestMapping(value = {"/{departmentName}"}, method = RequestMethod.GET)
    public @ResponseBody List<Department> getDepartment(@PathVariable String departmentName) {
        log.info("Start getEmployee by name=" + departmentName);
        return departmentService.getDepartmentByName(departmentName);
    }

    @RequestMapping(value = {"/delete/{id}"}, method = RequestMethod.PUT)
    public @ResponseBody String deleteDepartment(@PathVariable int id) {
        log.info("Start delete Employees.");
        departmentService.deleteDepartment(id);
        return "Department " + id + " deleted";
    }

    @RequestMapping(value = {"/listDepartment"}, method = RequestMethod.GET)
    public @ResponseBody List<Department> listDepartments() {
        log.info("Start getAllDepartments.");
        return departmentService.getDepartments();
    }

    @RequestMapping(value = {"/averageSalaryInDepartment"}, method = RequestMethod.GET)
    public @ResponseBody Map<String, Double> getAverageSalaryInDepartment() {
        log.info("Start getAllDepartments.");
        return departmentService.getAverageSalaryInDepartment();
    }
}
