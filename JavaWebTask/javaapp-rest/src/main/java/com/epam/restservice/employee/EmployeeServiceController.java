package com.epam.restservice.employee;

import com.epam.model.Employee;
import com.epam.service.employee.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Roman
 */

@RestController
public class EmployeeServiceController {

    static final Logger log = Logger.getLogger(EmployeeServiceController.class);

    @Qualifier("employeeService")
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = { "/employee/employeeId={employeeId}&firstName={firstName}&lastName={lastName}&address={address}&position={position}&departmentId={departmentId}&salary={salary}" }, method = RequestMethod.POST)
    public @ResponseBody List<Employee> addEmployee(@PathVariable Integer employeeId,@PathVariable String firstName,
                                                    @PathVariable String lastName,@PathVariable String address,
                                                    @PathVariable String position,@PathVariable Integer departmentId,
                                                    @PathVariable double salary,
                                                    ModelMap model) {
        log.info("Start add employee");
        employeeService.insertEmployee(new Employee(employeeId,firstName,lastName,address,position,departmentId,salary));
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employee", employees);
        model.addAttribute("edit", false);
        return employees;
    }

    @RequestMapping(value = { "/employee/editEmployee/employeeId={employeeId}&firstName={firstName}&lastName={lastName}&address={address}&position={position}&departmentId={departmentId}&salary={salary}" }, method = RequestMethod.POST)
    public @ResponseBody List<Employee> updateEmployee( @PathVariable Integer employeeId,@PathVariable String firstName,
                                                        @PathVariable String lastName,@PathVariable String address,
                                                        @PathVariable String position,@PathVariable Integer departmentId,
                                                        @PathVariable double salary,
                                                        ModelMap model) {
        log.info("Start update employee");
        List<Employee> employees = employeeService.getEmployees();
        Employee employee = new Employee(employeeId,firstName,lastName,address,position,departmentId,salary);
        employeeService.updateEmployee(employee);
        model.addAttribute("success", "Employee " + employee.getFirstName()  + " updated successfully");
        return employees;
    }

    @RequestMapping(value = {"/employee/{firstName}" }, method = RequestMethod.GET)
    public @ResponseBody List<Employee> getEmployee(@PathVariable String firstName,ModelMap model) {
            log.info("Start getEmployee by name="+ firstName);
            List<Employee> employee = employeeService.getEmployeeByFirstName(firstName);
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
