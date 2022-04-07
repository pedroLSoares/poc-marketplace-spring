package com.pedrolsoares.marketplace.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class ProductDTO {

    private String name;
    private Integer quantity;
    private BigDecimal unitPrice;
    private String sellerUserName;
}
