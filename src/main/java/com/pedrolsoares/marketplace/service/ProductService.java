package com.pedrolsoares.marketplace.service;

import com.pedrolsoares.marketplace.dto.request.ProductDTO;
import com.pedrolsoares.marketplace.enums.ProductCategory;
import com.pedrolsoares.marketplace.model.AppUser;
import com.pedrolsoares.marketplace.model.ESProduct;
import com.pedrolsoares.marketplace.model.Product;
import com.pedrolsoares.marketplace.repository.ProductRepository;
import com.pedrolsoares.marketplace.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.PropertyNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final com.pedrolsoares.marketplace.repository.elasticsearch.EsProductRepository esProductRepository;
    private final UserRepository userRepository;

    public Product findOne(Long id){
        return productRepository.findById(id).orElseThrow(() -> new PropertyNotFoundException("Product not found"));
    }

    public List<ESProduct> listAll(){
        List<ESProduct> products = new ArrayList<>();
        esProductRepository.findAll().forEach(products::add);

        return products;
    }

    public List<ESProduct> listAll(String query) {
        return esProductRepository.findAllByName(query);
    }


    public Product registerProduct(ProductDTO newProduct){
        Optional<AppUser> user = userRepository.findByUser_name(newProduct.getSellerUserName());

        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }

        Product product = new Product(
                newProduct.getName(),
                newProduct.getUnitPrice(),
                newProduct.getQuantity(),
                user.get()
        );

        ESProduct esProduct = new ESProduct(
                newProduct.getName(),
                ProductCategory.OTHER,
                newProduct.getUnitPrice()
        );

        esProductRepository.save(esProduct);
        return productRepository.save(product);
    }

    public Product updateProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> findAllById(Long id){
        return  productRepository.findAllByUser_Id(id);
    }

    public List<Product> findAllByUsername(String userName){
        AppUser user = loadUser(userName);

        return  findAllById(user.getId());

    }

    private AppUser loadUser(String userName){
        Optional<AppUser> appUserOptional = userRepository.findByUser_name(userName);

        if(appUserOptional.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }

        return appUserOptional.get();
    }

}
