package com.pedrolsoares.marketplace.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Address {

    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private Integer number;
    private Integer zipcode;

}
