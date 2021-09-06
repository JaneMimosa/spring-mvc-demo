package com.springmvc.demo.service.impl;

import com.springmvc.demo.domain.Product;
import com.springmvc.demo.domain.dto.ProductDto;
import com.springmvc.demo.repository.ProductRepository;
import com.springmvc.demo.service.ProductService;
import com.springmvc.demo.util.FileUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public Page<ProductDto> findAll(Specification<Product> spec, int pageNum, int pageSize) {
        return productRepository.findAll(spec, PageRequest.of(pageNum -1, pageSize)).map(ProductDto::new);
    }

    @Override
    public Optional<ProductDto> findById(long id) {
        return productRepository.findById(id).map(ProductDto::new);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product saveWithImage(Product product, MultipartFile image) {
        if(!productRepository.existsById(product.getId())) {
            productRepository.save(product);
        }
        if(image != null) {
            Path pathImage = FileUtils.saveProductImage(image);
            product.setImageLink(pathImage.toString());

            productRepository.save(product);
        }
        return product;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
