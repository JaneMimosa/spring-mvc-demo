package com.springmvc.demo.service.impl;

import com.springmvc.demo.domain.Product;
import com.springmvc.demo.repository.ProductRepository;
import com.springmvc.demo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    public Optional<Product> getProductById(long id) {
        return productRepository.getProductById(id);
    }
}
