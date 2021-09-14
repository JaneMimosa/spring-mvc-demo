package com.springmvc.demo.service.impl;

import com.springmvc.demo.domain.CartItem;
import com.springmvc.demo.domain.Customer;
import com.springmvc.demo.domain.Product;
import com.springmvc.demo.repository.CartItemRepository;
import com.springmvc.demo.repository.ProductRepository;
import com.springmvc.demo.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Override
    public List<CartItem> findAllByCustomer(Customer customer) {
        return cartItemRepository.findAllByCustomer(customer);
    }

    @Override
    public CartItem addProduct(Customer customer, Long productId, int quantity) {
        Product product = productRepository.findById(productId).get();
        CartItem cartItem = cartItemRepository.findByCustomerAndProduct(customer, product);

        if(cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setCustomer(customer);
            cartItem.setProduct(product);
        }
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteByCustomerAndProduct(Customer customer, Long productId) {
        Product product = productRepository.findById(productId).get();
        cartItemRepository.deleteByCustomerAndProduct(customer, product);
    }

    @Override
    public CartItem updateQuantity(Customer customer, Long productId, int quantity) {
        return cartItemRepository.updateQuantity(customer.getId(), productId, quantity);
    }
}
