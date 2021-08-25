package com.springmvc.demo.service.impl;

import com.springmvc.demo.domain.Category;
import com.springmvc.demo.domain.Product;
import com.springmvc.demo.repository.ProductRepository;
import com.springmvc.demo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

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

    @Override
    public boolean addProduct(Product product) {
        productRepository.addProduct(product);
        return true;
    }
    public List<Category> getCategories() {
        return productRepository.getCategories();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.getProductsByCategory(category);
    }

}
