package com.springmvc.demo.repository;

import com.springmvc.demo.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByCategoriesNameLike(String category, Pageable pageable);

    Page<Product> findByPriceGreaterThanEqualAndPriceLessThanEqual(int minPrice, int maxPrice, Pageable pageable);

    Page<Product> findByNameStartingWith(String text, Pageable pageable);

}
