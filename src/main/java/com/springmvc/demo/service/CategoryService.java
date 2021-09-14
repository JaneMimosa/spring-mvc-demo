package com.springmvc.demo.service;

import com.springmvc.demo.domain.Category;
import com.springmvc.demo.domain.dto.ProductDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category save(Category category);

    Category findById(Long id);

    Page<ProductDto> findProductsByAlias(String alias, int pageNum, int pageSize);
}
