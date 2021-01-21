package com.mateuszjanczak.ips.domain.repository;

import com.mateuszjanczak.ips.domain.entity.Customer;
import com.mateuszjanczak.ips.domain.mapper.CustomerMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCustomerRepository implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> findAll() {

        String sql = "SELECT * FROM ips.klient";

        return jdbcTemplate.query(sql, (rs, rowNum) -> CustomerMapper.bindCustomer(rs));
    }
}
