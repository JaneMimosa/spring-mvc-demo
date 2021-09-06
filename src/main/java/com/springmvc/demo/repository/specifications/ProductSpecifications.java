package com.springmvc.demo.repository.specifications;

import com.springmvc.demo.domain.Product;
import org.apache.commons.collections4.MultiValuedMap;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

public class ProductSpecifications {

    private static Specification<Product> priceGreaterOrEquals(int minPrice) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
    }

    private static Specification<Product> priceLessThanOrEquals(int maxPrice) {
        return (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice)));
    }

    private static Specification<Product> nameLike(String text) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%%", text)));
    }

    public static Specification<Product> build(MultiValueMap<String, String> params) {
        Specification<Product> spec = Specification.where(null);

        if(params.containsKey("min_price") && !params.getFirst("min_price").isBlank()) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEquals(Integer.parseInt(params.getFirst("min_price"))));
        }
        if(params.containsKey("max_price") && !params.getFirst("max_price").isBlank()) {
            spec = spec.and(ProductSpecifications.priceLessThanOrEquals(Integer.parseInt(params.getFirst("max_price"))));
        }
        if(params.containsKey("text") && !params.getFirst("text").isBlank()) {
            spec = spec.and(ProductSpecifications.nameLike(params.getFirst("text")));
        }
        return spec;
    }
}
