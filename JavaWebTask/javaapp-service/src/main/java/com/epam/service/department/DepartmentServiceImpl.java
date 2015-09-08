package com.epam.service.department;

import com.epam.dao.department.DepartmentDao;
import com.epam.model.Department;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by Roman
 */

@Service("departmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    static final Logger log = Logger.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public int insertDepartment(final Department department) {
        log.debug("Insert department in department table");
        assertThat(department.getDepartmentName(), is(notNullValue()));
        assertThat(department.getLocation(), is(notNullValue()));
        List<Department> exDepartment = getDepartmentByName(department.getDepartmentName());
        if (exDepartment != null) {
            throw new IllegalArgumentException("Object is existing in Department database");
        }
        return departmentDao.insertDepartment(department);
    }

    @Override
    public void updateDepartment(Department department) {
        log.debug("Update department in department table");
        departmentDao.updateDepartment(department);
    }

    @Override
    public List<Department> getDepartmentByName(final String name) {
        log.debug("Get department from department table");
        return departmentDao.getDepartmentByName(name);
    }

    @Override
    public void deleteDepartment(int id) {
        log.debug("Delete department in department table");
        departmentDao.deleteDepartment(id);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentDao.getDepartments();
    }

    @Override
    public Department getDepartmentById(int id) {
        return departmentDao.getDepartmentById(id);
    }

    public void setDepartmentDao(com.epam.dao.department.DepartmentDaoImpl departmentDao) {
    }
}
