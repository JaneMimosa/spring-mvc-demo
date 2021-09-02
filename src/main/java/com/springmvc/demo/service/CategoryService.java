package com.springmvc.demo.service;

import com.springmvc.demo.domain.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category save(Category category);

    Category findById(Long id);
}
