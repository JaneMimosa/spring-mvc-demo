package com.springmvc.demo.service;

import com.springmvc.demo.domain.Product;
import com.springmvc.demo.domain.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Page<ProductDto> findAll(Specification<Product> build, int pageNum, int pageSize);

    Optional<ProductDto> findById(long id);

    Product save(Product product);
    Product saveWithImage(Product product, MultipartFile image);

    void deleteProductById(Long id);


}
