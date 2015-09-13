package com.epam.employee;

import com.epam.model.Employee;
import com.epam.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Roman
 */

@Controller
public class EmployeePageController {

    @Qualifier("employeeService")
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = { "/addedEmployee" }, method = RequestMethod.POST)
    public String addEmployee(Employee employee) {
        if (employee.getFirstName() != null)
            employeeService.insertEmployee(employee);
        return "redirect:/getEmployeeList";
    }

    @RequestMapping(value = { "/updateEmployee" }, method = RequestMethod.PUT)
    public String updateEmployee(@Valid Employee employee) {
        if(employee.getFirstName() != null){
            employeeService.updateEmployee(employee);
        }
        return "redirect:/getEmployeeList";
    }

    @RequestMapping(value = {"/employee/{name}" }, method = RequestMethod.GET)
    public String getEmployee(@PathVariable String name,ModelMap model) {
            List<Employee> employee = employeeService.getEmployeeByFirstName(name);
            model.addAttribute("employee", employee);
            return "redirect:/getEmployeeList";
        }

    @RequestMapping(value = { "/employee/delete/{id}" }, method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable int id) {
            employeeService.deleteEmployee(id);
            return "redirect:/getEmployeeList";
        }

    @RequestMapping(value ={"/getEmployeeList"},method = RequestMethod.GET)
    public ModelAndView listEmployees() {
        List<Employee> employeeList = employeeService.getEmployees();
        return new ModelAndView("employeeList", "employeeList", employeeList);
    }
}
