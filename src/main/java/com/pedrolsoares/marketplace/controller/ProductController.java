package com.pedrolsoares.marketplace.controller;

import com.pedrolsoares.marketplace.dto.request.ProductDTO;
import com.pedrolsoares.marketplace.dto.response.PersonalProductsDTO;
import com.pedrolsoares.marketplace.model.ESProduct;
import com.pedrolsoares.marketplace.model.Product;
import com.pedrolsoares.marketplace.service.ProductService;
import com.pedrolsoares.marketplace.util.ProductEnumUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Object> listAll(@RequestParam String search){
        List<ESProduct> products = productService.listAll(search);

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<PersonalProductsDTO>> listAllById(@PathVariable Long userId){
        List<Product> products = productService.findAllById(userId);
        return ResponseEntity.ok(PersonalProductsDTO.modelToDTO(products));
    }

    @GetMapping("/user")
    public ResponseEntity<List<PersonalProductsDTO>> listAllUserProducts(Authentication authentication){
        List<Product> products = productService.findAllByUsername(authentication.getName());
        return ResponseEntity.ok(PersonalProductsDTO.modelToDTO(products));
    }

    @GetMapping("/category")
    public ResponseEntity<Object> listAllCategories(){
        return ResponseEntity.ok(ProductEnumUtils.categoryMap());
    }

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody ProductDTO newProduct, Authentication authentication, UriComponentsBuilder uriBuilder){
        newProduct.setSellerUserName(authentication.getName());

        Product createdProduct = productService.registerProduct(newProduct);

        URI uri = uriBuilder
                .path("/api/v1/products/{id}")
                .buildAndExpand(createdProduct.getId())
                .toUri();


        return ResponseEntity.created(uri).build();

    }
}
