package com.springmvc.demo.service;

import com.springmvc.demo.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
}
