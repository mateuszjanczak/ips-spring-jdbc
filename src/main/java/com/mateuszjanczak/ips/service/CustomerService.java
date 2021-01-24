package com.mateuszjanczak.ips.service;

import com.mateuszjanczak.ips.domain.dto.CountDto;
import com.mateuszjanczak.ips.domain.dto.CustomerDto;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerDto> getAll();
    CustomerDto getById(int id);
    Optional<CustomerDto> createCustomer(CustomerDto customerDto);
    List<CustomerDto> getAll(int limit, int page);
    CountDto getCount();
    boolean removeCustomer(int id);
    Optional<CustomerDto> updateCustomer(int id, CustomerDto customerDto);
    void generate();
}
