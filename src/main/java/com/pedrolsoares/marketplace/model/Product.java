package com.pedrolsoares.marketplace.model;

import com.fasterxml.jackson.annotation.*;
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

    public Product(String name, BigDecimal price, Integer quantity, Storage storage, ProductCategory category, AppUser user) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.storage = storage;
        this.category = category;
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

    @ManyToOne
    @JoinColumn(name = "storage_id", nullable = true)
    @JsonIgnoreProperties(value = "products")
    private Storage storage;

    @Column
    private ProductCategory category;

    @Transient
    private Integer quantityRequested = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"password"})
    @JsonBackReference
    private AppUser user;


}
