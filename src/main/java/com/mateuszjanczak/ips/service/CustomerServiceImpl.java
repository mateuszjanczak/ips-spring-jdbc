package com.mateuszjanczak.ips.service;

import com.mateuszjanczak.ips.domain.dto.CountDto;
import com.mateuszjanczak.ips.domain.dto.CustomerDto;
import com.mateuszjanczak.ips.domain.entity.Customer;
import com.mateuszjanczak.ips.domain.mapper.CustomerMapper;
import com.mateuszjanczak.ips.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public List<CustomerDto> getAll(int limit, int page) {
        List<Customer> customerList = customerRepository.findAll(limit, page);

        return customerList.stream()
                .map(CustomerMapper::customerToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CountDto getCount() {
        return new CountDto(customerRepository.count());
    }

    @Override
    public boolean removeCustomer(int id) {
        return customerRepository.remove(id);
    }

    @Override
    public Optional<CustomerDto> updateCustomer(int id, CustomerDto customerDto) {
        Customer customer = customerRepository.update(id, CustomerMapper.dtoToCustomer(customerDto));
        if(customer == null) {
            return Optional.empty();
        } else {
            return Optional.of(CustomerMapper.customerToDto(customer));
        }
    }

    @Override
    public void generate() {
        List<Customer> customerList = customerRepository.findAll();
        int size = customerList.size();

        Set<Integer> randomNumbers = new HashSet<>(size);
        while(randomNumbers.size() < size) {
            randomNumbers.add(ThreadLocalRandom.current().nextInt(100_000_000, 1_000_000_000));
        }

        List<Integer> list = new ArrayList<>(randomNumbers);

        IntStream.range(0, size).forEach(i -> {
            Customer customer = customerList.get(i);
            customer.setTelefon(String.valueOf(list.get(i)));
            customerRepository.update(customer.getId_uzytkownika(), customer);
        });
    }
}
