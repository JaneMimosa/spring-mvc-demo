package com.springmvc.demo.service.impl;

import com.springmvc.demo.domain.Category;
import com.springmvc.demo.domain.dto.ProductDto;
import com.springmvc.demo.repository.CategoryRepository;
import com.springmvc.demo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Page<ProductDto> findProductsByAlias(String alias, int pageNum, int pageSize) {
        return categoryRepository.findProductsByAlias(alias, PageRequest.of(pageNum - 1, pageSize)).map(ProductDto::new);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

}
