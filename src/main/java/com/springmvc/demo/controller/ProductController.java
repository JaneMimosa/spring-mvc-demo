package com.springmvc.demo.controller;

import com.springmvc.demo.domain.Category;
import com.springmvc.demo.domain.Product;
import com.springmvc.demo.service.CategoryService;
import com.springmvc.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }
    @GetMapping("/{id}")
    public String getProductById(Model model, @PathVariable(value = "id") Long id) {
        Optional<Product> product = productService.findById(id);
        model.addAttribute("product", product);
        return "productById";
    }

    @GetMapping("categories/{category}")
    public String getProductByCategory(Model model, @PathVariable(value = "category") String category) {
        List<Product> products = productService.findByCategoryNameLike(category);
        model.addAttribute("products", products);
        return "products";
    }
    @GetMapping("/price")
    public String getProductByPrice(Model model, @RequestParam(value = "min-price") int minPrice, @RequestParam(value = "max-price") int maxPrice) {
        List<Product> products = productService.findByPriceGreaterThanEqualAndPriceLessThanEqual(minPrice, maxPrice);
        model.addAttribute("products", products);
        return "products";
    }
    @GetMapping("/name")
    public String getProductByPrice(Model model, @RequestParam(value = "text") String text) {
        List<Product> products = productService.findByNameStartingWith(text);
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/addProduct")
    public String addProductForm(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryList);
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String createNewProduct(@ModelAttribute Product product, Model model) {
        productService.save(product);
        model.addAttribute("product", product);
        return "redirect:/products";
    }
}
