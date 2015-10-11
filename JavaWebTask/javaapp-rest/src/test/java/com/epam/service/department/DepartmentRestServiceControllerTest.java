package com.epam.service.department;

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
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:/testRestServiceSpringContext.xml"})
public class DepartmentRestServiceControllerTest {
    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private DepartmentService departmentRestService;

    /*@Resource
    private DepartmentRestServiceController departmentRestServiceController;*/

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
        //mockMvc = MockMvcBuilders.standaloneSetup(employeeRestServiceController).build();
    }

    @After
    public void tearDown() throws Exception {
        reset(departmentRestService);
    }

    @Test
    public void getDepartmentNotFoundTest() throws Exception {
        expect(departmentRestService.getDepartmentByName("Fortran")).andThrow(new NotFoundException("Department is not present"));
        replay(departmentRestService);
        this.mockMvc.perform(get("/department/Fortran")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(departmentRestService);
    }
}
