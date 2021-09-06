package com.springmvc.demo.controller;

import com.springmvc.demo.domain.Product;
import com.springmvc.demo.service.CategoryService;
import com.springmvc.demo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    private final int pageSize = 10;


    @GetMapping
    public String getProducts(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/page")
    public String findPaginated(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, Model model) {

        Pageable paging = PageRequest.of(pageNum - 1, pageSize);
        Page<Product> page = productService.findAllProductByPage(paging);
        List<Product> productList = page.getContent();

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("page", page);
        model.addAttribute("productList", productList);
        return "products";
    }


    @GetMapping("/{id}")
    public String getProductById(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);

        return "productById";
    }

    @GetMapping("/delete")
    public String deleteProductById(@RequestParam(value = "id") Long id) {
        productService.deleteProductById(id);

        return "redirect:/products";
    }

    @GetMapping("category/{category}")
    public String getProductByCategory(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                       @PathVariable(value = "category") String category, Model model) {

        Pageable paging = PageRequest.of(pageNum - 1, pageSize);
        Page<Product> page = productService.findByCategoriesNameLike(category,paging);
        List<Product> productList = page.getContent();

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("page", page);
        model.addAttribute("productList", productList);

        return "products";
    }


    @GetMapping("/price")
    public String getProductByName(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                   @RequestParam(value = "min-price") int minPrice,
                                   @RequestParam(value = "max-price") int maxPrice, Model model) {

        Pageable paging = PageRequest.of(pageNum - 1, pageSize);
        Page<Product> page = productService.findByPriceGreaterThanEqualAndPriceLessThanEqual(minPrice, maxPrice, paging);
        List<Product> productList = page.getContent();

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("page", page);
        model.addAttribute("productList", productList);

        return "products";
    }


    @GetMapping("/name")
    public String getProductByName(@RequestParam(value = "text") String text,
                                   @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, Model model) {

        Pageable paging = PageRequest.of(pageNum - 1, pageSize);
        Page<Product> page = productService.findByNameStartingWith(text, paging);
        List<Product> productList = page.getContent();

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("page", page);
        model.addAttribute("productList", productList);

        return "products";
    }

    @GetMapping("/addProduct")
    public String addProductForm(@RequestParam(value ="id", required = false) Long id, Model model) {

        if(id != null) {
            Product product = productService.findById(id);
            model.addAttribute("product", product);
        } else {
            model.addAttribute("product", new Product());
        }
        model.addAttribute("categories", categoryService.findAll());
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String createNewProduct(@ModelAttribute("product") Product product,
                                   @RequestParam(value = "image", required = false) MultipartFile image) {

        if(product.getImageLink() != null) {
            productService.save(product);
        } else {
            productService.saveWithImage(product, image);
        }
        return "redirect:/products";
    }
}
