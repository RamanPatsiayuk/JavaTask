package com.epam.dao.department;

import com.epam.model.Department;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Roman
 */

@Repository
public class DepartmentDaoImpl extends NamedParameterJdbcDaoSupport implements DepartmentDao {
    static final Logger log = Logger.getLogger(DepartmentDaoImpl.class);

    private static final String insertDepartmentSql = "insert into department (departmentName, location) values (:departmentName, :location)";
    //private static final String addDepartmentSql = "insert into department (department, location) values (?,?)";
    private static final String updateDepartmentSql = "update department set departmentName=?,location=? where departmentId=?";
    private static final String deleteDepartmentSql = "delete from department where departmentId=?";
    private static final String getDepartmentSql = "select * from department";
    private static final String getDepartmentByNameSql = "select * from department where LOWER(departmentName)=?";
    private static final String getDepartmentById = "select * from department where departmentId=?";

    //private JdbcTemplate jdbcTemplate;
    //private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public DataSource dataSource;

    /*@Autowired
    public DepartmentDaoImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }*/
    //if we do not use NamedParameterJdbcDaoSupport we should use this code
    /*public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }*/

    @Override
    public int insertDepartment(final Department department) {
        log.debug("Add department in department table");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getNamedParameterJdbcTemplate().update(insertDepartmentSql, new BeanPropertySqlParameterSource(department), keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void updateDepartment(final Department department) {
        log.debug("Update department in department table");
        getJdbcTemplate().update(updateDepartmentSql, department.getDepartmentName(), department.getLocation(), department.getDepartmentId());
    }

    @Override
    public List<Department> getDepartmentByName(final String name) {
        log.debug("Get department from department table");
        return getJdbcTemplate().query(getDepartmentByNameSql, new Object[]{name.toLowerCase()}, new BeanPropertyRowMapper(Department.class));
    }

    @Override
    public void deleteDepartment(int id) {
        log.debug("Delete department in department table");
        getJdbcTemplate().update(deleteDepartmentSql, id);
    }

    @Override
    public List<Department> getDepartments() {
        return getJdbcTemplate().query(getDepartmentSql, new BeanPropertyRowMapper(Department.class));
    }

    @Override
    public Department getDepartmentById(int id) {
        log.debug("Get department by id");
        try{
            return getJdbcTemplate().queryForObject(getDepartmentById, new DepartmentRowMapper(), id);
        }
        catch (final EmptyResultDataAccessException e) {
            log.debug("Return empty result data access exception");
            return null;
        }
    }
}
