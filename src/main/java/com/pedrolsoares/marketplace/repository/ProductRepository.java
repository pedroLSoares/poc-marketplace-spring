package com.pedrolsoares.marketplace.repository;

import com.pedrolsoares.marketplace.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByUser_Id(Long userId)
;}
