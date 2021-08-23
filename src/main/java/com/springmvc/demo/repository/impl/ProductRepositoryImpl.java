package com.springmvc.demo.repository.impl;

import com.springmvc.demo.domain.Product;
import com.springmvc.demo.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    public List<Product> productList;

    @PostConstruct
    public void init() {
        Random rand = new Random();
        productList = new ArrayList<>();
        int price;
        for (int i = 1; i <= 10; i++) {
            price = rand.nextInt(10000);
            productList.add(new Product(i, "title " + i, price));
        }
    }

    @Override
    public List<Product> getProducts() {
        return productList;
    }

    @Override
    public Optional<Product> getProductById(long id) {
        for (Product p : productList) {
            if (p.getId() == id) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean addProduct(Product product) {
        productList.add(product);
        return true;
    }
}
