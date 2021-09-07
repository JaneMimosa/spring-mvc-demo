package com.springmvc.demo.repository;

import com.springmvc.demo.domain.Category;
import com.springmvc.demo.domain.Product;
import com.springmvc.demo.domain.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Product> findProductsByAlias(String alias, Pageable pageable);
}
