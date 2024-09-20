package com.example.demo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class CustomerRepository {

    private static final Logger log = LoggerFactory.getLogger(CustomerRepository.class);

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
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from customers where id=?",
                new BeanPropertyRowMapper < Customer > (Customer.class), id));
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from customers where id=?", id);
    }

    public int insert(Customer employee) {
        return jdbcTemplate.update("insert into customers (first_name, last_name) " + "values(?, ?)",
                employee.getFirstName(), employee.getLastName());
    }

    public int update(Customer employee) {
        return jdbcTemplate.update("update customers " + " set first_name = ?, last_name = ? " + " where id = ?",
                employee.getFirstName(), employee.getLastName(), employee.getId());
    }

    public void createData() {
        log.info("Creating tables");
        jdbcTemplate.execute("DROP TABLE IF EXISTS customers");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS customers(" +
                "id INT NOT NULL AUTO_INCREMENT, first_name VARCHAR(255), last_name VARCHAR(255), PRIMARY KEY (id))");

        // Split up the array of whole names into an array of first/last names
        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
    }

    public void checkOperations() {
        log.info("Inserting -> {}", insert(new Customer("Ramesh", "Fadatare")));
        log.info("Inserting -> {}", insert(new Customer("Suresh", "Fadatare")));

        log.info("Employee id 5 -> {}", findById(5));
        log.info("Employee id 6 -> {}", findById(6));

        log.info("Update 10011L -> {}", update(new Customer(5, "Ram", "Stark")));
        log.info("Update 10012L -> {}", update(new Customer(6, "Shyam", "Stark")));

        log.info("Employee id 5 -> {}", findById(5));
        log.info("Employee id 6 -> {}", findById(6));

        log.info("Delete Employee id 5 -> {}", findById(5));
        deleteById(5);

        log.info("All customers -> {}", findAll());
    }
}