package com.springmvc.demo.controller;

import com.springmvc.demo.domain.Category;
import com.springmvc.demo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/products/addCategory")
    public String addCategoryForm(@RequestParam(value ="id", required = false) Long id, Model model) {
        if(id != null) {
            Category category = categoryService.findById(id);
            model.addAttribute("category", category);
        } else {
            model.addAttribute("category", new Category());
        }

        model.addAttribute("categories", categoryService.findAll());

        return "addCategory";
    }

    @PostMapping("products/addCategory")
    public String createNewCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/products";
    }
}
