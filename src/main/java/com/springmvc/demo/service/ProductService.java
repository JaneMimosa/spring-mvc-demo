package com.springmvc.demo.service;

import com.springmvc.demo.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getProducts();
    Optional<Product> getProductById(long id);

}
