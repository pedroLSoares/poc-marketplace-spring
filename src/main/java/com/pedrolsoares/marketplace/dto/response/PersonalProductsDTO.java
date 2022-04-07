package com.pedrolsoares.marketplace.dto.response;

import com.pedrolsoares.marketplace.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class PersonalProductsDTO {

    private String name;
    private Integer quantity;
    private BigDecimal unitPrice;

    public static List<PersonalProductsDTO> modelToDTO(List<Product> productList) {
        return productList.stream().map(p -> new PersonalProductsDTO(
                p.getName(),
                p.getQuantity(),
                p.getPrice()
        )).collect(Collectors.toList());
    }
}
