package com.epam.dao.department;

import com.epam.model.Department;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Roman
 */

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
    static final Logger log = Logger.getLogger(DepartmentDaoImpl.class);

    private static final String insertDepartmentSql = "insert into department (department, location) values (:department, :location)";
    //private static final String addDepartmentSql = "insert into department (department, location) values (?,?)";
    private static final String updateDepartmentSql = "update department set department=?,location=? where departmentId=?";
    private static final String deleteDepartmentSql = "delete from department where departmentId=?";
    private static final String getDepartmentSql = "select * from department";
    private static final String getDepartmentByNameSql = "select * from department where LOWER(department)=?";
    private static final String getDepartmentById = "select * from department where departmentId=?";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public int insertDepartment(final Department department) {
        log.debug("Add department in department table");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertDepartmentSql, new BeanPropertySqlParameterSource(department), keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void updateDepartment(final Department department) {
        log.debug("Update department in department table");
        jdbcTemplate.update(updateDepartmentSql, department.getDepartment().toLowerCase(), department.getLocation(), department.getDepartmentId());
    }

    @Override
    public List<Department> getDepartmentByName(final String name) {
        log.debug("Get department from department table");
        return jdbcTemplate.query(getDepartmentByNameSql, new Object[]{name}, new BeanPropertyRowMapper(Department.class));
    }

    @Override
    public void deleteDepartment(int id) {
        log.debug("Delete department in department table");
        jdbcTemplate.update(deleteDepartmentSql, id);
    }

    @Override
    public List<Department> getDepartments() {
        return jdbcTemplate.query(getDepartmentSql, new BeanPropertyRowMapper(Department.class));
    }

    @Override
    public Department getDepartmentById(int id) {
        log.debug("Get department by id");
        return jdbcTemplate.queryForObject(getDepartmentById, new DepartmentRowMapper(), id);
    }
}
