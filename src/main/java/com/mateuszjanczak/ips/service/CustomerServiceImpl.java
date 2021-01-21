package com.mateuszjanczak.ips.service;

import com.mateuszjanczak.ips.domain.dto.CustomerDto;
import com.mateuszjanczak.ips.domain.entity.Customer;
import com.mateuszjanczak.ips.domain.mapper.CustomerMapper;
import com.mateuszjanczak.ips.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> getAll() {
        List<Customer> customerList = customerRepository.findAll();

        return customerList.stream()
                .map(CustomerMapper::customerToDto)
                .collect(Collectors.toList());
    }
}
