package com.springmvc.demo.repository;

import com.springmvc.demo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryNameLike(String category);

    List<Product> findByPriceGreaterThanEqualAndPriceLessThanEqual(int minPrice, int maxPrice);

    List<Product> findByNameStartingWith(String text);
}
