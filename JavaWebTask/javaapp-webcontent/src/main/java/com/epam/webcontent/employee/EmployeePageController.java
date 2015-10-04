package com.epam.webcontent.employee;

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

import java.util.List;

/**
 * Created by Roman
 */

@Controller
@RequestMapping("/employee")
public class EmployeePageController {

    @Qualifier("employeeService")
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = { "/addEmployee" }, method = RequestMethod.POST)
    public String addEmployee(Employee employee) {
        if (employee.getFirstName() != null)
            employeeService.insertEmployee(employee);
        return "redirect:/getEmployeeList";
    }

    @RequestMapping(value = { "/updateEmployee" }, method = RequestMethod.PUT)
    public String updateEmployee(Employee employee) {
        if(employee.getFirstName() != null){
            employeeService.updateEmployee(employee);
        }
        return "redirect:/getEmployeeList";
    }

    @RequestMapping(value = {"/{firstName}" }, method = RequestMethod.GET)
    public String getEmployee(String firstName,ModelMap model) {
            List<Employee> employee = employeeService.getEmployeeByFirstName(firstName);
            model.addAttribute("employee", employee);
            return "redirect:/getEmployeeList";
        }

    @RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.PUT)
    public String deleteEmployee(@PathVariable int id) {
            employeeService.deleteEmployee(id);
            return "redirect:/getEmployeeList";
        }

    @RequestMapping(value ={"/employeeList"},method = RequestMethod.GET)
    public ModelAndView listEmployees() {
        List<Employee> employeeList = employeeService.getEmployees();
        return new ModelAndView("employeeList", "employeeList", employeeList);
    }
}
