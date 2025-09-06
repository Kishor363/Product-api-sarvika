package com.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.product.entity.Product;

public interface ProductService {
	
	Page<Product> getAll(Pageable pageable);
    Product getById(Long id);
    Product create(Product product);
    Product update(Long id, Product product);
    void delete(Long id);

}
