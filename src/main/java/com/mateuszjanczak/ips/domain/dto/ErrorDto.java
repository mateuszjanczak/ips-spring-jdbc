package com.mateuszjanczak.ips.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ErrorDto {
    public String message;
}
