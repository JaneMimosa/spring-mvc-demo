package com.springmvc.demo.repository;

import com.springmvc.demo.domain.Category;
import com.springmvc.demo.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> getProducts();
    Optional<Product> getProductById(long id);
    boolean addProduct(Product product);
    List<Category> getCategories();
    List<Product> getProductsByCategory(String category);
}
