import com.epam.dao.EmployeeDaoImpl;
import com.epam.model.Employee;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Raman_Patsiayuk
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/testDaoSpringContext.xml" })
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDaoImpl employeeDao;

    @Test
    public void addEmployee(){
        List<Employee> employees = employeeDao.getEmployees();
        Employee newEmployee = new Employee(4,"Vasia","Pupkin","Brest, Green Street","SE","Java");
        int sizeBefore = employees.size();
        employeeDao.addEmployee(newEmployee);
        employees = employeeDao.getEmployees();
        assertEquals(sizeBefore+1, employees.size());
    }

    @Test
    public void updateEmployee(){

    }

    @Test
    public void getEmployee(){
        Employee testEmployee = new Employee(4,"Vasia","Pupkin","Brest, Green Street","SE","Java");
        List<Employee> employees = employeeDao.getEmployees();
        employees.add(testEmployee);
        String firstName = "Vasia";
        for(Employee employee : employees) {
            if(employee.getFirstName() == firstName)
            assertThat(employee.getFirstName()+" "+ employee.getLastName(),IsEqual.equalTo("Vasia Pupkin"));
        } throw new IllegalStateException("Employee: " + firstName + " is not in the list");
    }

    @Test
    public void deleteEmployee(){
        List<Employee> employees = employeeDao.getEmployees();
        int sizeBefore = employees.size();
        employeeDao.deleteEmployee(2);
        employees = employeeDao.getEmployees();
        assertThat("Delete employee",sizeBefore-1, IsEqual.equalTo(employees.size()));
    }

    @Test
    public void getEmployees(){
        List<Employee> employees = employeeDao.getEmployees();
        assertNotNull(employees);
        assertFalse(employees.isEmpty());
    }
}
