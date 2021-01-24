package com.mateuszjanczak.ips.domain.repository;

import com.mateuszjanczak.ips.domain.entity.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll();

    Customer findById(int id);

    Customer save(Customer customer);

    List<Customer> findAll(int limit, int page);

    int count();

    boolean remove(int id);

    Customer update(int id, Customer customer);
}
