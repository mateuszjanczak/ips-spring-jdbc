package com.mateuszjanczak.ips.service;

import com.mateuszjanczak.ips.domain.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAll();
}
