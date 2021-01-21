package com.mateuszjanczak.ips.service;

import com.mateuszjanczak.ips.domain.dto.CustomerDto;
import com.mateuszjanczak.ips.domain.entity.Customer;
import com.mateuszjanczak.ips.domain.mapper.CustomerMapper;
import com.mateuszjanczak.ips.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public CustomerDto getById(int id) {
        return CustomerMapper.customerToDto(customerRepository.findById(id));
    }

    @Override
    public Optional<CustomerDto> createCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.save(CustomerMapper.dtoToCustomer(customerDto));
        if(customer == null) {
            return Optional.empty();
        } else {
            return Optional.of(CustomerMapper.customerToDto(customer));
        }
    }

    @Override
    public List<CustomerDto> getAll(int limit, int offset) {
        List<Customer> customerList = customerRepository.findAll(limit, offset);

        return customerList.stream()
                .map(CustomerMapper::customerToDto)
                .collect(Collectors.toList());
    }
}
