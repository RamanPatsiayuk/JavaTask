package com.epam.dao.department;

import com.epam.model.Department;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Roman
 */

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
    List<Department> departmentList;
    static final Logger log = Logger.getLogger(DepartmentDaoImpl.class);
    private static final String addDepartmentSql = "insert into department (department, location) values (?,?)";
    private static final String updateDepartmentSql = "update department set department=?,location=? where departmentId=?";
    private static final String deleteDepartmentSql = "delete from department where departmentId=?";
    private static final String getDepartmentSql = "select * from department";
    private static final String getDepartmentByFirstNameSql = "select * from department where departmentId=?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addDepartment(Department department) {
        log.debug("Add department in department table");
        jdbcTemplate.update(addDepartmentSql, new Object[]{department.getDepartment(), department.getLocation()});
    }

    @Override
    public void updateDepartment(Department department) {
        log.debug("Update department in department table");
    }

    @Override
    public Department getDepartment(String name) {
        log.debug("Get department from department table");
        return null;
    }

    @Override
    public void deleteDepartment(int id) {
        log.debug("Delete department in department table");

    }

    @Override
    public List<Department> getDepartments() {
        departmentList = jdbcTemplate.query(getDepartmentSql, new DepartmentRowMapper());
        return departmentList;
    }
}
