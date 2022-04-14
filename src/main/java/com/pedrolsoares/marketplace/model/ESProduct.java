package com.pedrolsoares.marketplace.model;

import com.pedrolsoares.marketplace.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Document(indexName = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ESProduct {

    @Id
    private String id;
    private String name;
    private ProductCategory category;
    private BigDecimal price;

    public ESProduct(String name, ProductCategory category, BigDecimal price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
