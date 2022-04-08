package com.pedrolsoares.marketplace.controller;

import com.pedrolsoares.marketplace.dto.response.ProductDTO;
import com.pedrolsoares.marketplace.model.Product;
import com.pedrolsoares.marketplace.model.ShoppingCart;
import com.pedrolsoares.marketplace.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping("/{productId}")
    public ResponseEntity<Object> addProductToCart(@PathVariable Long productId, Authentication authentication){
        ShoppingCart cart = shoppingCartService.addProduct(authentication.getName(), productId);

        return ResponseEntity.ok(cart.getProducts().stream().map(ProductDTO::modelToDTO));
    }

    @GetMapping
    public ResponseEntity<Object> getCart(Authentication authentication){
        List<Product> products = shoppingCartService.getCart(authentication.getName());

        return ResponseEntity.ok(products.stream().map(ProductDTO::modelToDTO));
    }
}
