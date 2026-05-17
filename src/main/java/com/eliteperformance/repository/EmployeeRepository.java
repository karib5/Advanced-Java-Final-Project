package com.eliteperformance.repository;

import com.eliteperformance.model.Employee;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class EmployeeRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public EmployeeRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Employee> findById(Long employeeId) {
        String sql = "SELECT * FROM employees WHERE employee_id = :employeeId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("employeeId", employeeId);

        return jdbcTemplate.query(sql, params, rs -> {
            if (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getLong("employee_id"));
                emp.setName(rs.getString("name"));
                emp.setDesignation(rs.getString("designation"));
                emp.setBaseSalary(rs.getBigDecimal("base_salary"));
                emp.setRole(rs.getString("role"));
                emp.setUsername(rs.getString("username"));
                emp.setPassword(rs.getString("password"));
                return Optional.of(emp);
            }
            return Optional.empty();
        });
    }

    public Optional<Employee> findByUsername(String username) {
        String sql = "SELECT * FROM employees WHERE username = :username";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);

        return jdbcTemplate.query(sql, params, rs -> {
            if (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getLong("employee_id"));
                emp.setName(rs.getString("name"));
                emp.setDesignation(rs.getString("designation"));
                emp.setBaseSalary(rs.getBigDecimal("base_salary"));
                emp.setRole(rs.getString("role"));
                emp.setUsername(rs.getString("username"));
                emp.setPassword(rs.getString("password"));
                return Optional.of(emp);
            }
            return Optional.empty();
        });
    }

    public void updateLastPromotionDate(Long employeeId) {
        String sql = "UPDATE employees SET last_promotion_date = CURDATE() WHERE employee_id = :employeeId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("employeeId", employeeId);
        jdbcTemplate.update(sql, params);
    }
}