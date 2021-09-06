package com.springmvc.demo.domain.dto;

import com.springmvc.demo.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;
    private String alias;

    private Set<ProductDto> products;

    public CategoryDto(Category c) {
        this.id = c.getId();
        this.name = c.getName();
        this.alias = c.getAlias();
    }
}
