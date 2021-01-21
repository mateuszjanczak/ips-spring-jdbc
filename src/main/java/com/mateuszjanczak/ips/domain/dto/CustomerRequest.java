package com.mateuszjanczak.ips.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CustomerRequest {
    private String imie;
    private String nazwisko;
    private String pesel;
    private String telefon;
}
