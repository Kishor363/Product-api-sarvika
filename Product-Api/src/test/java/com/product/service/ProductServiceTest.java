package com.product.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.product.entity.Product;
import com.product.exception.ResourceNotFoundException;
import com.product.repository.ProductRepository;

class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductServiceImpl service;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product(1L, "Phone", "Smartphone", 799.99);
    }

    @Test
    void testGetAllWithPagination() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Product> page = new PageImpl<>(Arrays.asList(product), pageable, 1);
        when(repository.findAll(pageable)).thenReturn(page);
        Page<Product> products = service.getAll(pageable);
        assertThat(products.getContent()).hasSize(1);
        assertThat(products.getContent().get(0).getName()).isEqualTo("Phone");
        verify(repository, times(1)).findAll(pageable);
    }


    @Test
    void testGetByIdFound() {
        when(repository.findById(1L)).thenReturn(Optional.of(product));
        Product found = service.getById(1L);
        assertThat(found.getName()).isEqualTo("Phone");
    }

    @Test
    void testGetByIdNotFound() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getById(2L));
    }


}
