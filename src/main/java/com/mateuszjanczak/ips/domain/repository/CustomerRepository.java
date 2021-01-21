package com.mateuszjanczak.ips.domain.repository;

import com.mateuszjanczak.ips.domain.entity.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll();

    Customer findById(int id);
}
