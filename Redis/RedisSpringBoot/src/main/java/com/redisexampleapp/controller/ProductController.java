package com.redisexampleapp.controller;

import com.redisexampleapp.model.ProductModel;
import com.redisexampleapp.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<ProductModel> save(@RequestBody ProductModel product) {
        log.info("handling save controller request");
        return ResponseEntity.ok(productService.save(product));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductModel> findById(@PathVariable Integer id) {
        log.info("handling find by id from controller");
        return ResponseEntity.ok(productService.findById(id));
    }

    @CacheEvict(value = "product", allEntries = true)
    @GetMapping("/clearCache")
    public ResponseEntity<Void> clearCache() {
        return ResponseEntity.ok().build();
    }
}
