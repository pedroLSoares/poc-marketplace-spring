package com.pedrolsoares.marketplace.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Address implements Serializable {

    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private Integer number;
    private Integer zipcode;

}
