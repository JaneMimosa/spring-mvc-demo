package com.springmvc.demo.domain.dto;

import com.springmvc.demo.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private double price;

    private Set<CategoryDto> categories;

    public ProductDto(Product p) {
        this.id = p.getId();
        this.name = p.getName();
        this.price = p.getPrice();
    }
}
