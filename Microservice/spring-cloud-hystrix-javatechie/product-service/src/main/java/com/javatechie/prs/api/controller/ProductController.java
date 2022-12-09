package com.javatechie.prs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javatechie.prs.api.common.ProductRequest;
import com.javatechie.prs.api.common.ProductResponse;
import com.javatechie.prs.api.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	
	@Autowired
    private ProductService productService;

    @PostMapping("/doProduct")
    public void createProduct(@RequestBody ProductRequest productRequest) throws JsonProcessingException {
    	productService.createProduct(productRequest);
    }

    @GetMapping("/getAllProducts")
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
}
