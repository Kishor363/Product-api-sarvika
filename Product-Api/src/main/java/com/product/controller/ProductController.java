package com.product.controller;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.Product;
import com.product.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {
	
	private static final org.slf4j.Logger log =LoggerFactory.getLogger(ProductController.class);

	
	@Autowired
	private ProductService productService;
	
	@Operation(summary = "List products (paginated)")
	 @GetMapping("/getAll")
	    public ResponseEntity<Page<Product>> getAll(Pageable pageable) {
		log.info("Fetching all products", pageable.getPageNumber(), pageable.getPageSize());
	        return ResponseEntity.ok(productService.getAll(pageable));
	    }

	@Operation(summary = "Get product by ID")
	    @GetMapping("/{id}")
	    public ResponseEntity<Product> getById(@PathVariable Long id) {
		log.info("Fetching product with id={}", id);
	        return ResponseEntity.ok(productService.getById(id));
	    }

	@Operation(summary = "Create a new product")
	    @PostMapping("/save")
	    public ResponseEntity<Product> create(@RequestBody Product product) {
		log.info("Creating product with name={}", product.getName());
	        Product created = productService.create(product);
	        log.debug("Created product: {}", created);
	        return ResponseEntity.status(HttpStatus.CREATED).body(created);
	    }

	@Operation(summary = "Update an existing product")
	    @PutMapping("/update/{id}")
	    public ResponseEntity<Product> update(@PathVariable Long id,@RequestBody Product product) {
		log.info("Updating product id={}", id);
	        return ResponseEntity.ok(productService.update(id, product));
	    }

	@Operation(summary = "Delete a product by ID")
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id) {
		log.warn("Deleting product id={}", id);
	    	productService.delete(id);
	        return ResponseEntity.noContent().build();
	    }

}
