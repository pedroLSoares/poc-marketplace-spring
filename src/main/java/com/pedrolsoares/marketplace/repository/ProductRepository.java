package com.pedrolsoares.marketplace.repository;

import com.pedrolsoares.marketplace.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    @Query("SELECT u FROM Product u WHERE u.id = ?1")
    Optional<Product> findById(Long aLong);

    List<Product> findAllByUser_Id(Long userId)


;}
