package com.mateuszjanczak.ips.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CustomerDto {
    private int id_uzytkownika;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String telefon;
}
