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

    @GetMapping("/limit/{limit}/page/{page}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getAllCustomersWithPagination(@PathVariable int limit, @PathVariable int page) {
        return customerService.getAll(limit, page);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomerById(@PathVariable int id) {
        return customerService.getById(id);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerRequest customerRequest) {
        Optional<CustomerDto> optionalCustomer = customerService.createCustomer(CustomerMapper.requestToDto(customerRequest));
        return optionalCustomer.map(customerDto -> new ResponseEntity<>(customerDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/count")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public CountDto getCountCustomers() {
        return customerService.getCount();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCustomer(@PathVariable int id) {
        if(customerService.removeCustomer(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable int id, @RequestBody CustomerRequest customerRequest) {
        Optional<CustomerDto> optionalCustomer = customerService.updateCustomer(id, CustomerMapper.requestToDto(customerRequest));
        return optionalCustomer.map(customerDto -> new ResponseEntity<>(customerDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/generatePhones")
    public void generatePhones() {
        customerService.generate();
    }
}
