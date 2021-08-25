package com.springmvc.demo.service;

import com.springmvc.demo.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(long id);
    Product save(Product product);
    List<Product> findByCategoryNameLike(String category);
    List<Product> findByPriceGreaterThanEqualAndPriceLessThanEqual(int minPrice, int maxPrice);

    List<Product> findByNameStartingWith(String text);
}
