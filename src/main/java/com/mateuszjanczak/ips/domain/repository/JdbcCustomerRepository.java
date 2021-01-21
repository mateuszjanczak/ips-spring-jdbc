package com.mateuszjanczak.ips.domain.repository;

import com.mateuszjanczak.ips.domain.entity.Customer;
import com.mateuszjanczak.ips.domain.mapper.CustomerMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCustomerRepository implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CustomerMapper customerMapper;

    public JdbcCustomerRepository(JdbcTemplate jdbcTemplate, CustomerMapper customerMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM ips.klient ORDER BY id_uzytkownika";
        return jdbcTemplate.query(sql, customerMapper);
    }

    @Override
    public Customer findById(int id) {
        String sql = "SELECT * FROM ips.klient WHERE id_uzytkownika = ?";
        return jdbcTemplate.queryForObject(sql, customerMapper, id);
    }
}
