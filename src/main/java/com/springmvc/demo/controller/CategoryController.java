package com.springmvc.demo.controller;

import com.springmvc.demo.domain.Category;
import com.springmvc.demo.domain.Product;
import com.springmvc.demo.domain.dto.ProductDto;
import com.springmvc.demo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    private final int pageSize = 10;


    @GetMapping("/{category}")
    public Page<ProductDto> getProductByCategory(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                                 @PathVariable(value = "category") String category) {

        return categoryService.findProductsByAlias(category, pageNum, pageSize);
    }


    @PostMapping
    public Category createProduct(@RequestBody Category category) {
        category.setId(0L);
        return categoryService.save(category);
    }

    @PutMapping
    public Category updateProduct(@RequestBody Category category) {
        return categoryService.save(category);
    }
}
