package com.springmvc.demo.controller;

import com.springmvc.demo.domain.Product;
import com.springmvc.demo.domain.dto.ProductDto;
import com.springmvc.demo.repository.specifications.ProductSpecifications;
import com.springmvc.demo.service.CategoryService;
import com.springmvc.demo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    private final int pageSize = 10;


    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                           @RequestParam MultiValueMap<String, String> params) {

        return productService.findAll(ProductSpecifications.build(params), pageNum, pageSize);
    }


    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable(value = "id") Long id) {
        return productService.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @DeleteMapping
    public int deleteProductById(@RequestParam(value = "id") Long id) {
        productService.deleteProductById(id);
        return HttpStatus.OK.value();
    }


    @PostMapping
    public Product createProduct(@RequestBody Product product,
                                   @RequestParam(value = "image", required = false) MultipartFile image) {
        product.setId(0L);
        if(product.getImageLink() != null) {
            return productService.save(product);
        } else {
             return productService.saveWithImage(product, image);
        }
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.save(product);
    }
}
