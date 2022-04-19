package com.pedrolsoares.marketplace.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class StorageProductsDTO {

    private List<Long> products;
}
