package com.pedrolsoares.marketplace.dto.response;

import com.pedrolsoares.marketplace.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class ProductDTO {

    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String seller;

    public static ProductDTO modelToDTO(Product product){
        return new ProductDTO(
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getUser().getUser_name()
        );
    }


}
