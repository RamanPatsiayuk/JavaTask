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
    private final List<Integer> TEST_DATA = new ArrayList<Integer>();

    private Employee employee;

    @Before
    public void setUpTestData() {
        TEST_DATA.add(42);
        employee = new Employee();
    }

    @Test(expected = NullPointerException.class)
    public void testForNPEAnnotation() {
        throw new NullPointerException();
    }

    @After
    public void cleanUpTestData() {
        TEST_DATA.clear();
    }
}
