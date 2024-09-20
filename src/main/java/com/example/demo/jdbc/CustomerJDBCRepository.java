package com.example.demo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class CustomerJDBCRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class CustomerRowMapper implements RowMapper < Customer > {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer employee = new Customer();
            employee.setId(rs.getLong("id"));
            employee.setFirstName(rs.getString("first_name"));
            employee.setLastName(rs.getString("last_name"));
            return employee;
        }
    }

    public List < Customer > findAll() {
        return jdbcTemplate.query("select * from customers", new CustomerRowMapper());
    }

    public Optional < Customer > findById(long id) {
        return Optional.of(jdbcTemplate.queryForObject("select * from customers where id=?", new Object[] {
                        id
                },
                new BeanPropertyRowMapper < Customer > (Customer.class)));
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from customers where id=?", new Object[] {
                id
        });
    }

    public int insert(Customer employee) {
        return jdbcTemplate.update("insert into customers (id, first_name, last_name) " + "values(?, ?, ?)",
                new Object[] {
                        employee.getId(), employee.getFirstName(), employee.getLastName()
                });
    }

    public int update(Customer employee) {
        return jdbcTemplate.update("update customers " + " set first_name = ?, last_name = ? " + " where id = ?",
                new Object[] {
                        employee.getFirstName(), employee.getLastName(), employee.getId()
                });
    }
}