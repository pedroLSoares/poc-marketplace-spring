package com.pedrolsoares.marketplace.dto.response;

import com.pedrolsoares.marketplace.model.Address;
import com.pedrolsoares.marketplace.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
public class StorageResponseDTO implements Serializable {
    private String name;
    private Address location;
    private List<Product> products;
}
