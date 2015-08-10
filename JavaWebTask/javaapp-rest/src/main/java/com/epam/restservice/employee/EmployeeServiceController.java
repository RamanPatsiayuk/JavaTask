package com.epam.restservice.employee;

import com.epam.dao.employee.EmployeeDao;
import com.epam.model.Employee;
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
    private EmployeeDao employeeDao;

    @RequestMapping(value = { "/employee/addedEmployee" }, method = RequestMethod.POST)
    public @ResponseBody List<Employee> addEmployee(@RequestBody Employee emp,ModelMap model) {
        log.info("Start add employee");
        employeeDao.addEmployee(emp);
        List<Employee> employees = employeeDao.getEmployees();
        model.addAttribute("employee", employees);
        model.addAttribute("edit", false);
        return employees;
    }

    @RequestMapping(value = { "/employee/editEmployee" }, method = RequestMethod.POST)
    public @ResponseBody List<Employee> updateEmployee(@Valid Employee employee, BindingResult result, ModelMap model) {
        log.info("Start update employee");
        List<Employee> employees = employeeDao.getEmployees();
        if (result.hasErrors()) {
            return employees;
        }
        if(employee.getFirstName() != null){
            employeeDao.updateEmployee(employee);
        }
        model.addAttribute("success", "Employee " + employee.getFirstName()  + " updated successfully");
        return employees;
    }

    @RequestMapping(value = {"/employee/{name}" }, method = RequestMethod.GET)
    public @ResponseBody List<Employee> getEmployee(@PathVariable String name,ModelMap model) {
            log.info("Start getEmployee by name="+name);
            List<Employee> employee = employeeDao.getEmployeeByFirstName(name);
            model.addAttribute("employee", employee);
            return employee;
        }

    @RequestMapping(value = { "/employee/delete/{id}" }, method = RequestMethod.PUT)
    public @ResponseBody List<Employee> deleteEmployee(@PathVariable int id) {
            List<Employee> employee = employeeDao.getEmployees();
            employeeDao.deleteEmployee(id);
            return employee;
        }

    @RequestMapping(value = {"/employee/listEmployee" }, method = RequestMethod.GET)
    public @ResponseBody List<Employee> listEmployees(ModelMap model) {
        log.info("Start getAllEmployees.");
        List<Employee> employees = employeeDao.getEmployees();
        model.addAttribute("employees", employees);
        return employees;
    }
}
