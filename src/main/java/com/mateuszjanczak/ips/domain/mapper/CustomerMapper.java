package com.mateuszjanczak.ips.domain.mapper;

import com.mateuszjanczak.ips.domain.dto.CustomerDto;
import com.mateuszjanczak.ips.domain.dto.CustomerRequest;
import com.mateuszjanczak.ips.domain.entity.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerMapper implements RowMapper<Customer> {

    public Customer mapRow(ResultSet rs, int i) throws SQLException {

        return Customer.builder()
                .id_klienta(rs.getInt("id_klienta"))
                .id_adresu(rs.getInt("id_adresu"))
                .id_uzytkownika(rs.getInt("id_uzytkownika"))
                .imie(rs.getString("imie"))
                .nazwisko(rs.getString("nazwisko"))
                .pesel(rs.getString("pesel"))
                .telefon(rs.getString("telefon"))
                .build();
    }

    public static CustomerDto customerToDto(Customer customer) {

        return CustomerDto.builder()
                .id_uzytkownika(customer.getId_uzytkownika())
                .imie(customer.getImie())
                .nazwisko(customer.getNazwisko())
                .pesel(customer.getPesel())
                .telefon(customer.getTelefon())
                .build();
    }

    public static Customer dtoToCustomer(CustomerDto customerDto) {

        return Customer.builder()
                .id_uzytkownika(customerDto.getId_uzytkownika())
                .imie(customerDto.getImie())
                .nazwisko(customerDto.getNazwisko())
                .pesel(customerDto.getPesel())
                .telefon(customerDto.getTelefon())
                .build();
    }

    public static CustomerDto requestToDto(CustomerRequest customerRequest) {

        return CustomerDto.builder()
                .imie(customerRequest.getImie())
                .nazwisko(customerRequest.getNazwisko())
                .pesel(customerRequest.getPesel())
                .telefon(customerRequest.getTelefon())
                .build();
    }
}