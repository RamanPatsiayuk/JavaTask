import com.epam.model.Employee;
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
    private Employee employee;


    @Before
    public void setUp() {
        employee = new Employee();
    }

    @Test(expected = NullPointerException.class)
    public void testForNPEAnnotation() {
        throw new NullPointerException();
    }

    @After
    public void tearDown() {
        listEmployee.clear();
    }
}
