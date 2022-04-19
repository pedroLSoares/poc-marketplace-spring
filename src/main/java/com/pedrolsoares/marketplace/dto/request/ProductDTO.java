package com.pedrolsoares.marketplace.dto.request;

import com.pedrolsoares.marketplace.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class ProductDTO {

    @NotNull
    @NotBlank
    private String name;

    @Min(1)
    private Integer quantity;

    @DecimalMin("1.0")
    private BigDecimal unitPrice;

    @NotNull
    @NotBlank
    private String sellerUserName;

    private ProductCategory category;

    private Long storage_id;
}
