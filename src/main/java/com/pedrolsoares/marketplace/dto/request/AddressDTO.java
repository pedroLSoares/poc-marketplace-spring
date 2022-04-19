package com.pedrolsoares.marketplace.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Data
public class AddressDTO {

    private String country = "Brazil";

    @NotEmpty
    private Integer zipcode;

    @NotEmpty
    private Integer number;

}
