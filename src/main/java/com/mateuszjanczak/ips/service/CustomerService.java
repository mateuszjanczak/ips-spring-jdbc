package com.mateuszjanczak.ips.service;

import com.mateuszjanczak.ips.domain.dto.CustomerDto;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerDto> getAll();
    CustomerDto getById(int id);
    Optional<CustomerDto> createCustomer(CustomerDto customerDto);
    List<CustomerDto> getAll(int limit, int offset);
}
