package com.springmvc.demo.controller;

import com.springmvc.demo.domain.Product;
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

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getProducts(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "products";
    }
    @GetMapping("/{id}")
    public String getProductById(Model model, @PathVariable(value = "id") Long id) {
        Optional<Product> product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "productById";
    }

    @GetMapping("/addProduct")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String createNewProduct(@ModelAttribute Product product, Model model) {
        productService.addProduct(product);
        model.addAttribute("product", product);
        return "redirect:/products";
    }
}
