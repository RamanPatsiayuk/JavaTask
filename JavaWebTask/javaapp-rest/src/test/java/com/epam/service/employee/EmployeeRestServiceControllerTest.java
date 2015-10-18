package com.epam.service.employee;

import com.epam.model.Employee;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Roman
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/testRestServiceSpringContext.xml"})
@WebAppConfiguration
public class EmployeeRestServiceControllerTest {

    static final Logger log = Logger.getLogger(EmployeeRestServiceControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private EmployeeService employeeRestService;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
        //this.mockMvc = MockMvcBuilders.standaloneSetup(employeeRestServiceController).build();
    }

    @After
    public void tearDown() throws Exception {
        reset(employeeRestService);
    }

    @Test
    public void getEmployeeNotPresentTest() throws Exception {
        log.info("---------------Get employee is not present in employee table--------------------");
        expect(employeeRestService.getEmployeeByFirstName("Vladimir")).andThrow(new NotFoundException("Employee is not present"));
        replay(employeeRestService);
        this.mockMvc.perform(get("/employee/Vladimir")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(employeeRestService);
    }

    @Test
    public void getEmployeeByFirstNameTest() throws Exception {

        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1, "Ivan", "Sidorov","Brest, Orlovskaya street","SE", 1,700));
        list.add(new Employee(2, "Petr", "Ivanov","Brest, Kyibisheva 100","JTAE", 2,750));

        expect(employeeRestService.getEmployeeByFirstName("Ivan")).andReturn(list);

        replay(employeeRestService);
        this.mockMvc.perform(get("/employee/Ivan")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
                //.andExpect();

        verify(employeeRestService);
    }

    //http://localhost:8080/javaapp-rest/employee/listEmployee
    //[{"employeeId":1,"firstName":"Ivan","lastName":"Sidorov","address":"Brest, Orlovskaya street","position":"SE","departmentId":1,"salary":700.0},{"employeeId":2,"firstName":"Petr","lastName":"Ivanov","address":"Brest, Kyibisheva 100","position":"JTAE","departmentId":2,"salary":750.0},{"employeeId":3,"firstName":"Vitaliy","lastName":"Petrov","address":"Minsk, Voronianskogo 24","position":"SSE","departmentId":3,"salary":1200.0}]

    //http://localhost:8080/javaapp-rest/employee/Ivan
    //[{"employeeId":1,"firstName":"Ivan","lastName":"Sidorov","address":"Brest, Orlovskaya street","position":"SE","departmentId":1,"salary":700.0}]
}
