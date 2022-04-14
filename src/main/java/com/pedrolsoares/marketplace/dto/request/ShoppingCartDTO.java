package com.pedrolsoares.marketplace.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class ShoppingCartDTO {

    @NotEmpty
    @NotNull
    private Long productId;

    @NotNull
    @Min(1)
    private Integer quantity;
}
