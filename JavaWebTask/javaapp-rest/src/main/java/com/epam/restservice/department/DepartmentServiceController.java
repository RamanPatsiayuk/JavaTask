package com.epam.restservice.department;

import com.epam.dao.department.DepartmentDao;
import com.epam.model.Department;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Roman
 */

@RestController
public class DepartmentServiceController {

    static final Logger log = Logger.getLogger(DepartmentServiceController.class);

    @Autowired
    private DepartmentDao departmentDao;

}
