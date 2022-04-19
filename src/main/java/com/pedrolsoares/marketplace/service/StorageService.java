package com.pedrolsoares.marketplace.service;

import com.pedrolsoares.marketplace.model.Product;
import com.pedrolsoares.marketplace.model.Storage;
import com.pedrolsoares.marketplace.repository.ProductRepository;
import com.pedrolsoares.marketplace.repository.StorageRepository;
import lombok.AllArgsConstructor;
import org.hibernate.PropertyNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StorageService {

    private final StorageRepository storageRepository;
    private final ProductRepository productRepository;

    public Storage createStorage(Storage storage){
        return storageRepository.save(storage);
    }

    @Cacheable("listStorage")
    public Page<Storage> listStorage(final Integer page, final Integer size){
        return storageRepository.findAll(PageRequest.of(page, size));
    }

    public Storage findById(final Long id){
        Optional<Storage> optionalStorage = storageRepository.findById(id);

        return optionalStorage.orElseThrow(() -> new PropertyNotFoundException("Storage not found"));
    }

    public Storage addProducts(Long storageId, List<Long> productsIds){
        List<Product> products = productRepository.findAllById(productsIds);
        Storage storage = storageRepository.findById(storageId).orElseThrow(() -> new PropertyNotFoundException("Storage not found"));

        products.forEach(product -> {
            product.setStorage(storage);
            productRepository.save(product);
        });

        storage.getProducts().addAll(products);

        return storage;
    }
}
