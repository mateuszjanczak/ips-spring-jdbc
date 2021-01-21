package com.mateuszjanczak.ips.domain.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Customer {
    private int id_klienta;
    private int id_adresu;
    private int id_uzytkownika;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String telefon;
}
