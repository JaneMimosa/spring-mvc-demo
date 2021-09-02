package com.springmvc.demo.service;

import com.springmvc.demo.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
    Page<Product> findAllProductByPage(Pageable pageable);

    Product findById(long id);

    Product save(Product product);
    Product saveWithImage(Product product, MultipartFile image);
    void deleteProductById(Long id);

    Page<Product> findByCategoriesNameLike(String category, Pageable pageable);
    Page<Product> findByPriceGreaterThanEqualAndPriceLessThanEqual(int minPrice, int maxPrice, Pageable pageable);
    Page<Product> findByNameStartingWith(String text, Pageable pageable);

}
