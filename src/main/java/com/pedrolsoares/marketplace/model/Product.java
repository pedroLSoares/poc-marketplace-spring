package com.pedrolsoares.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pedrolsoares.marketplace.enums.ProductCategory;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    public Product(String name, BigDecimal price, Integer quantity, AppUser user) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;


    @Column(nullable = false)
    private Integer quantity;

    @Column
    private ProductCategory category;

    @Transient
    private Integer quantityRequested = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("id")
    @JsonIgnoreProperties({"password"})
    private AppUser user;


}
