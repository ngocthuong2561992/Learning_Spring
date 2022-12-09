package com.javatechie.prs.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.prs.api.common.ProductRequest;
import com.javatechie.prs.api.common.ProductResponse;
import com.javatechie.prs.api.entity.Product;
import com.javatechie.prs.api.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public void createProduct(ProductRequest productRequest) throws JsonProcessingException {
		Product product = Product.builder().name(productRequest.getName())
				.description(productRequest.getDescription()).price(productRequest.getPrice()).build();
		log.info("Payment-Service Request : {}", new ObjectMapper().writeValueAsString(product));
		productRepository.save(product);
		log.info("Product {} is saved", product.getId());
	}

	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();

		return products.stream().map(this::mapToProductResponse).toList();
	}

	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder().id(product.getId()).name(product.getName())
				.description(product.getDescription()).price(product.getPrice()).build();
	}

}
