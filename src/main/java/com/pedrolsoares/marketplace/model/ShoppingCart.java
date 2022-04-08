package com.pedrolsoares.marketplace.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@RedisHash("ShoppingCart")
@AllArgsConstructor
@Data
public class ShoppingCart implements Serializable {

    private String id;
    private List<Product> products;

    public void addProduct(Product product){
        products.add(product);
    }

}
