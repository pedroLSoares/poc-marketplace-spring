package com.pedrolsoares.marketplace.service;

import com.pedrolsoares.marketplace.dto.request.ShoppingCartDTO;
import com.pedrolsoares.marketplace.model.Product;
import com.pedrolsoares.marketplace.model.ShoppingCart;
import com.pedrolsoares.marketplace.repository.ProductRepository;
import com.pedrolsoares.marketplace.repository.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.hibernate.PropertyNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;

    public ShoppingCart addProduct(String username, ShoppingCartDTO cartRequest){
        Optional<Product> productOptional = productRepository.findById(cartRequest.getProductId());

        if(productOptional.isEmpty()){
            throw new PropertyNotFoundException("Product not found");
        }

        Optional<ShoppingCart> existentCart = shoppingCartRepository.findById(username);

        ShoppingCart cart = existentCart.isEmpty() ? new ShoppingCart(username, new ArrayList<>()) : existentCart.get();

        productOptional.get().setQuantityRequested(cartRequest.getQuantity());
        cart.addProduct(productOptional.get());

        shoppingCartRepository.save(cart);

        return cart;

    }

    public List<Product> getCart(String username){
        Optional<ShoppingCart> cart = shoppingCartRepository.findById(username);

        if(cart.isEmpty()){
            return new ArrayList<>();
        }

        return cart.get().getProducts();
    }

    public BigDecimal calculateCartPrice(String username){
        BigDecimal price = BigDecimal.ZERO;
        ShoppingCart cart = shoppingCartRepository.findById(username).orElseThrow();
        List<Product> products = cart.getProducts();

        for (Product p:products){
            price = price.add(p.getPrice().multiply(new BigDecimal(p.getQuantity())));
        }

        return price;
    }

    public BigDecimal calculateCartPrice(List<Product> products) {
        BigDecimal price = BigDecimal.ZERO;

        for (Product p:products){
            price = price.add(p.getPrice().multiply(new BigDecimal(p.getQuantity())));
        }

        return price;
    }
}
