package com.redisexampleapp.service;

import com.redisexampleapp.model.ProductModel;
import com.redisexampleapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    @CachePut(value = "product", key = "#product.id")
    public ProductModel save(ProductModel product) {
        log.info("execute save product to DB: {}", product);
        return productRepository.save(product);
    }

    @Cacheable(value = "product", key = "#id")
    public ProductModel findById(Integer id) {
        log.info("execute find by id from the DB: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product id is incorrect"));
    }
}
