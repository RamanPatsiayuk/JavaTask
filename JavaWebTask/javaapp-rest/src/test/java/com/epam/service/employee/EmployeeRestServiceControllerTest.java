package com.epam.service.employee;

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

   /* @Resource
    private EmployeeRestServiceController employeeRestServiceController;*/

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
        this.mockMvc.perform(get("/employeeService/Vladimir")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(employeeRestService);
    }
}
