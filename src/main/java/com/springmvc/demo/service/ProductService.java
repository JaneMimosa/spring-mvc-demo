package com.springmvc.demo.service;

import com.springmvc.demo.domain.Category;
import com.springmvc.demo.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getProducts();
    Optional<Product> getProductById(long id);
    boolean addProduct(Product product);
    List<Category> getCategories();
    List<Product> getProductsByCategory(String category);
}
