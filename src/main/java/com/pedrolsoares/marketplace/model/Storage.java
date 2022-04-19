package com.pedrolsoares.marketplace.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Address location;

    @OneToMany
    private List<Product> products;

    public Storage(String name, Address location, List<Product> products) {
        this.name = name;
        this.location = location;
        this.products = products;
    }
}
