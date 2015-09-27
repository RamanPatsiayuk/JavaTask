package validators;

import com.epam.dao.department.DepartmentDao;
import com.epam.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by Roman
 */
public class DepartmentValidator implements Validator {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.empty");
        Department department = (Department) o;
        if(department == null){
            throw new IllegalArgumentException("Department is null");
        }else if((null == department.getDepartmentName()) ||("".equals(department.getDepartmentName()))){
            throw new IllegalArgumentException("Department name is null or empty");
        }else{
            List<Department> exDepartment = departmentDao.getDepartmentByName(department.getDepartmentName());
            if ((exDepartment != null)&&(exDepartment.size()>0)) {
                throw new IllegalArgumentException("Object is existing in Department database");
            }
        }
    }
}
