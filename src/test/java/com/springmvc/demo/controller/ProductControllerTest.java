package com.springmvc.demo.controller;

import com.springmvc.demo.SpringMvcDemoApplicationTests;

import com.springmvc.demo.domain.Product;
import com.springmvc.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class ProductControllerTest extends SpringMvcDemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    Product product = new Product(1,"title", 100);

    @BeforeEach
    public void setUp() {
        Mockito.when(productService.getProducts())
                .thenReturn(Collections.singletonList(product));

        Mockito.when(productService.getProductById(Mockito.anyLong()))
                .thenReturn(java.util.Optional.ofNullable(product));

        Mockito.when(productService.addProduct(Mockito.any()))
                .thenReturn(true);

    }

    @Test
    void getProducts() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(model().attribute("products", Collections.singletonList(product)));
    }

    @Test
    void getProductById() throws Exception {
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("productById"))
                .andExpect(model().attribute("product", java.util.Optional.ofNullable(product)));
    }

    @Test
    void addProductForm() throws Exception {
        mockMvc.perform(get("/products/addProduct"))
                .andExpect(status().isOk())
                .andExpect(view().name("addProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void createNewProduct() throws Exception {

    }
}