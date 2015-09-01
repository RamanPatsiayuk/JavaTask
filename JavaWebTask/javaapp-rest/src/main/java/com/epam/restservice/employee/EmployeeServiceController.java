package com.epam.restservice.employee;

import com.epam.model.Employee;
import com.epam.service.employee.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Roman
 */

@RestController
public class EmployeeServiceController {

    static final Logger log = Logger.getLogger(EmployeeServiceController.class);

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = { "/employee/addedEmployee" }, method = RequestMethod.POST)
    public @ResponseBody List<Employee> addEmployee(@RequestBody Employee emp,ModelMap model) {
        log.info("Start add employee");
        employeeService.addEmployee(emp);
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employee", employees);
        model.addAttribute("edit", false);
        return employees;
    }

    @RequestMapping(value = { "/employee/editEmployee" }, method = RequestMethod.POST)
    public @ResponseBody List<Employee> updateEmployee(@Valid Employee employee, BindingResult result, ModelMap model) {
        log.info("Start update employee");
        List<Employee> employees = employeeService.getEmployees();
        if (result.hasErrors()) {
            return employees;
        }
        if(employee.getFirstName() != null){
            employeeService.updateEmployee(employee);
        }
        model.addAttribute("success", "Employee " + employee.getFirstName()  + " updated successfully");
        return employees;
    }

    @RequestMapping(value = {"/employee/{name}" }, method = RequestMethod.GET)
    public @ResponseBody List<Employee> getEmployee(@PathVariable String name,ModelMap model) {
            log.info("Start getEmployee by name="+name);
            List<Employee> employee = employeeService.getEmployeeByFirstName(name);
            model.addAttribute("employee", employee);
            return employee;
        }

    @RequestMapping(value = { "/employee/delete/{id}" }, method = RequestMethod.PUT)
    public @ResponseBody List<Employee> deleteEmployee(@PathVariable int id) {
            log.info("Start delete Employees.");
            List<Employee> employee = employeeService.getEmployees();
            employeeService.deleteEmployee(id);
            return employee;
        }

    @RequestMapping(value = {"/employee/listEmployee" }, method = RequestMethod.GET)
    public @ResponseBody List<Employee> listEmployees(ModelMap model) {
        log.info("Start getAllEmployees.");
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);
        return employees;
    }
}
