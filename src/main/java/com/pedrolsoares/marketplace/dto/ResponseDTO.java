package com.pedrolsoares.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDTO {

    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String service;
}
