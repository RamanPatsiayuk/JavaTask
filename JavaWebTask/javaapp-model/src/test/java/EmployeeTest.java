import com.epam.model.Employee;
import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Roman
 */

public class EmployeeTest extends Assert {
    private final List<Employee> listEmployee= new ArrayList<Employee>();
    Employee employee;


    @Before
    public void setUp() {
        employee = new Employee();
        listEmployee.add(new Employee(1,"Vasiya","Petrov","Minsk","SE",1,600));
        listEmployee.add(new Employee(2,"Petia","Orlov","Gomel","JSE",2,400));
        listEmployee.add(new Employee(3,"Kolia","Drozdov","Vitebsk","SSE",3,1100));
        listEmployee.add(new Employee(4,"Misha","Tyrkov","Minsk","LSE",1,1200));
    }

    @Test
    public void testGetEmployeeName() throws Exception {
        employee.setFirstName("Roma");
        assertThat(employee.getFirstName(), IsEqual.equalTo("Roma"));
    }

    @Test
    public void testListSize() throws Exception{
        assertThat(listEmployee.size(), IsEqual.equalTo(4));
    }

    @Test
    public void testGetFullName() throws Exception {
        employee.setFirstName("Vitaliy");
        employee.setLastName("Bublikov");
        assertThat(employee.getFirstName()+" "+employee.getLastName(),IsEqual.equalTo("Vitaliy Bublikov"));
    }

    @Test(expected = NullPointerException.class)
    public void testNPEAnnotation() {
        throw new NullPointerException();
    }

    @Test
    public void testEqualsEmployeesOperation(){
        Employee employee1 = new Employee(1,"Vasiya","Petrov","Minsk","SE",1,500);
        Employee employee2 = new Employee(1,"Vasiya","Petrov","Minsk","SE",1,500);
        assertTrue(employee1.equals(employee2));
    }

    @Test(expected = AssertionError.class)
    public void testAssertionErrorException(){
        Employee employee1 = new Employee(1,"Kolya","Petrov","Minsk","SE",1,500);
        Employee employee2 = new Employee(1,"Vasiya","Petrov","Minsk","SE",1,600);
        assertTrue(employee1.equals(employee2));
    }

    @After
    public void tearDown() {
        employee = null;
        listEmployee.clear();
    }
}
