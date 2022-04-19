package com.pedrolsoares.marketplace.dto.request;

import com.pedrolsoares.marketplace.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class StorageDTO {

    @NotNull
    @NotEmpty
    private String name;

    private AddressDTO location;
}
