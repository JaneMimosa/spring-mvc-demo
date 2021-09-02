package com.springmvc.demo.service.impl;

import com.springmvc.demo.domain.Product;
import com.springmvc.demo.repository.ProductRepository;
import com.springmvc.demo.service.ProductService;
import com.springmvc.demo.util.FileUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.nio.file.Path;
import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAllProductByPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Product findById(long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
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


    @Override
    public Page<Product> findByCategoriesNameLike(String category, Pageable pageable) {
        return productRepository.findByCategoriesNameLike(category, pageable);
    }

    @Override
    public Page<Product> findByPriceGreaterThanEqualAndPriceLessThanEqual(int minPrice, int maxPrice, Pageable pageable) {
        return productRepository.findByPriceGreaterThanEqualAndPriceLessThanEqual(minPrice, maxPrice, pageable);
    }

    @Override
    public Page<Product> findByNameStartingWith(String text, Pageable pageable) {
        return productRepository.findByNameStartingWith(text, pageable);
    }
}
