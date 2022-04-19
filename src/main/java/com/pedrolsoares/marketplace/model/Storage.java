package com.pedrolsoares.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Storage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Address location;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products;

    public Storage(String name, Address location, List<Product> products) {
        this.name = name;
        this.location = location;
        this.products = products;
    }
}
