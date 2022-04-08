package com.pedrolsoares.marketplace.service;

import com.pedrolsoares.marketplace.model.Product;
import com.pedrolsoares.marketplace.model.ShoppingCart;
import com.pedrolsoares.marketplace.repository.ProductRepository;
import com.pedrolsoares.marketplace.repository.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.hibernate.PropertyNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;

    public ShoppingCart addProduct(String username, Long productId){
        Optional<Product> productOptional = productRepository.findById(productId);

        if(productOptional.isEmpty()){
            throw new PropertyNotFoundException("Product not found");
        }

        Optional<ShoppingCart> existentCart = shoppingCartRepository.findById(username);

        ShoppingCart cart = existentCart.isEmpty() ? new ShoppingCart(username, new ArrayList<>()) : existentCart.get();

        cart.addProduct(productOptional.get());

        shoppingCartRepository.save(cart);

        return cart;

    }
}
