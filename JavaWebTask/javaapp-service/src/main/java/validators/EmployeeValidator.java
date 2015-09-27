package validators;

import com.epam.dao.employee.EmployeeDao;
import com.epam.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by Roman
 */
@Component
public class EmployeeValidator implements Validator {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return Employee.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.empty");
        Employee employee = (Employee) o;
        if (employee == null) {
            throw new IllegalArgumentException("Employee is null");
        }else if (null == employee.getFirstName()||("".equals(employee.getFirstName().trim()))) {
            throw new IllegalArgumentException();
        }else if ((null == employee.getLastName())||("".equals(employee.getLastName().trim()))) {
            throw new IllegalArgumentException();
        }else if ((null == employee.getAddress())|| (employee.getAddress().trim().length() == 0)) {
            throw new IllegalArgumentException();
        }else if ((null == employee.getPosition()) || ("".equals(employee.getPosition().trim()))) {
            throw new IllegalArgumentException();
        }else if ((null == employee.getDepartmentId())) {
            throw new IllegalArgumentException();
        } else {
            List<Employee> exEmployee = employeeDao.getEmployeeByFirstName(employee.getFirstName());
            if ((exEmployee != null) && (exEmployee.size()>0)) {
                throw new IllegalArgumentException();
            }
        }
    }
}
