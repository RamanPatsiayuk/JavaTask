package com.epam.service.department;

import com.epam.dao.department.DepartmentDao;
import com.epam.model.Department;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Roman
 */

@Service("departmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    static final Logger log = Logger.getLogger(DepartmentServiceImpl.class);

    /*@Autowired
    private DepartmentValidator departmentValidator;*/

    @Qualifier("departmentDao")
    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public int insertDepartment(final Department department) {
        log.debug("Insert department in department table");

        if(department == null){
            throw new IllegalArgumentException("Department is null");
        }else if((null == department.getDepartmentName()) ||("".equals(department.getDepartmentName().trim()))){
            throw new IllegalArgumentException("Department name is null or empty");
        }else{
            List<Department> exDepartment = departmentDao.getDepartmentByName(department.getDepartmentName());
            if ((exDepartment != null)&&(exDepartment.size()>0)) {
                throw new IllegalArgumentException("Object is existing in Department database");
            }
        }
        return departmentDao.insertDepartment(department);
    }

    @Override
    public void updateDepartment(final Department department) {
        log.debug("Update department in department table");
        Department dep = departmentDao.getDepartmentById(department.getDepartmentId());
        if (dep != null) {
            departmentDao.updateDepartment(department);
        }
    }

    @Override
    public List<Department> getDepartmentByName(final String name) {
        log.debug("Get department from department table");
        List<Department> listDepartment = null;
        try {
            listDepartment = departmentDao.getDepartmentByName(name);
        } catch (EmptyResultDataAccessException e) {
            log.debug("Empty result. Employee is not present in employee database " + name);
        }
        return listDepartment;
    }

    @Override
    public void deleteDepartment(int id) {
        log.debug("Delete department in department table");
        Department dep = departmentDao.getDepartmentById(id);
        if (dep != null) {
            departmentDao.deleteDepartment(id);
        }
    }

    @Override
    public List<Department> getDepartments() {
        log.debug("Get all departments");
        return departmentDao.getDepartments();
    }

    @Override
    public Department getDepartmentById(int id) {
        log.info("Get department by id");
        Department dep = departmentDao.getDepartmentById(id);
        if (dep == null) {
            throw new IllegalArgumentException("Object is not existing in Employee database");
        }
        return dep;
    }

    @Override
    public Map<String, Double> getAverageSalaryInDepartment() {
        log.info("Get average salary in department");
        return departmentDao.getAverageSalaryInDepartment();
    }
}
