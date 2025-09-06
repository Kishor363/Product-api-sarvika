package com.product.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.product.controller.ProductController;
import com.product.entity.Product;
import com.product.exception.ResourceNotFoundException;
import com.product.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {
	
	private static final org.slf4j.Logger log =LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductRepository productRepo;

	@Override
	@Transactional(readOnly = true)
	public Page<Product> getAll(Pageable pageable) {
		log.debug("Fetching products with pageable={}", pageable);
		return productRepo.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Product getById(Long id) {
		log.debug("Looking up product id={}", id);
		return productRepo.findById(id).orElseThrow(() ->
        new ResourceNotFoundException("Product not found with id: " + id));
	}

	@Override
	public Product create(Product product) {
		 log.info("Saving new product name={}", product.getName());
		return productRepo.save(product);
	}

	@Override
	public Product update(Long id, Product product) {
		log.info("Updating product id={}", id);
		Product existing = productRepo.getById(id);
        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        return productRepo.save(existing);
	}

	@Override
	public void delete(Long id) {
		log.info("Deleting product id={}", id);
		Product existing = productRepo.getById(id);
		productRepo.delete(existing);

	}

}
