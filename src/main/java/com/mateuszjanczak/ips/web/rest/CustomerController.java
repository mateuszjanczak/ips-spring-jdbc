package com.mateuszjanczak.ips.web.rest;

import com.mateuszjanczak.ips.domain.dto.CountDto;
import com.mateuszjanczak.ips.domain.dto.CustomerDto;
import com.mateuszjanczak.ips.domain.dto.CustomerRequest;
import com.mateuszjanczak.ips.domain.mapper.CustomerMapper;
import com.mateuszjanczak.ips.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customers")
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAll();
    }

    @GetMapping("/limit/{limit}/offset/{offset}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getAllCustomersWithPagination(@PathVariable int limit, @PathVariable int offset) {
        return customerService.getAll(limit, offset);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomer(@PathVariable int id) {
        return customerService.getById(id);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerRequest customerRequest) {
        Optional<CustomerDto> optionalCustomer = customerService.createCustomer(CustomerMapper.requestToDto(customerRequest));
        return optionalCustomer.map(customerDto -> new ResponseEntity<>(customerDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/count")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public CountDto getCountCustomers() {
        return customerService.getCount();
    }

    /*@PutMapping
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerRequest customerRequest) {
        Optional<CustomerDto> optionalCustomer = customerService.createCustomer(CustomerMapper.requestToDto(customerRequest));
        return optionalCustomer.map(customerDto -> new ResponseEntity<>(customerDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/
}
