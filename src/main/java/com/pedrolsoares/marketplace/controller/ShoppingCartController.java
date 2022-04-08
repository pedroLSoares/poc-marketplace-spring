package com.pedrolsoares.marketplace.controller;

import com.pedrolsoares.marketplace.dto.response.ProductDTO;
import com.pedrolsoares.marketplace.model.ShoppingCart;
import com.pedrolsoares.marketplace.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
