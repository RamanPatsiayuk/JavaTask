package com.epam.restservice.employee;

import com.epam.model.Employee;
import com.epam.service.employee.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Roman
 */

@RestController
@RequestMapping("/employee")
public class EmployeeServiceController {

    static final Logger log = Logger.getLogger(EmployeeServiceController.class);

    @Qualifier("employeeService")
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping(value = { "/addEmployee/{employeeId}/{firstName}/{lastName}/{address}/{position}/{departmentId}/{salary}" }, method = RequestMethod.POST)
    public void addEmployee(@PathVariable Integer employeeId,@PathVariable String firstName,
                                                    @PathVariable String lastName,@PathVariable String address,
                                                    @PathVariable String position,@PathVariable Integer departmentId,
                                                    @PathVariable double salary) {
        log.info("Start add employee");
        employeeService.insertEmployee(new Employee(employeeId,firstName,lastName,address,position,departmentId,salary));
    }

    @RequestMapping(value = { "/editEmployee/{employeeId}/{firstName}/{lastName}/{address}/{position}/{departmentId}/{salary}" }, method = RequestMethod.PUT)
    public String updateEmployee( @PathVariable Integer employeeId,@PathVariable String firstName,
                                                        @PathVariable String lastName,@PathVariable String address,
                                                        @PathVariable String position,@PathVariable Integer departmentId,
                                                        @PathVariable double salary) {
        log.info("Start update employee");
        Employee employee = new Employee(employeeId,firstName,lastName,address,position,departmentId,salary);
        employeeService.updateEmployee(employee);
        return "redirect:/javaapp-restservice/employeeService/" + employeeId;
    }

    @RequestMapping(value = {"/{firstName}" }, method = RequestMethod.GET)
    public List<Employee> getEmployee(@PathVariable String firstName) {
            log.info("Start getEmployee by name="+ firstName);
            return employeeService.getEmployeeByFirstName(firstName);
    }

    @RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.PUT)
    public String deleteEmployee(@PathVariable int id) {
            log.info("Start delete Employees.");
            employeeService.deleteEmployee(id);
            return "Employee " + id + " deleted";
        }

    @RequestMapping(value = {"/listEmployee" }, method = RequestMethod.GET)
    public List<Employee> listEmployees() {
        log.info("Start getAllEmployees.");
        return employeeService.getEmployees();
    }
}
