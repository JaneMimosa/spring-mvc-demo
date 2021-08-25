package com.springmvc.demo.service.impl;

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
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findByCategoryNameLike(String category) {
        return productRepository.findByCategoryNameLike(category);
    }

    @Override
    public List<Product> findByPriceGreaterThanEqualAndPriceLessThanEqual(int minPrice, int maxPrice) {
        return productRepository.findByPriceGreaterThanEqualAndPriceLessThanEqual(minPrice, maxPrice);
    }

    @Override
    public List<Product> findByNameStartingWith(String text) {
        return productRepository.findByNameStartingWith(text);
    }
}
