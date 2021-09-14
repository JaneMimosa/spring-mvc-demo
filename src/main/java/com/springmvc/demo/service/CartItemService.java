package com.springmvc.demo.service;

import com.springmvc.demo.domain.CartItem;
import com.springmvc.demo.domain.Customer;

import java.util.List;

public interface CartItemService {


    List<CartItem> findAllByCustomer(Customer customer);
    CartItem addProduct(Customer customer, Long productId, int quantity);
    void deleteByCustomerAndProduct(Customer customer, Long productId);
    CartItem updateQuantity(Customer customer, Long productId, int quantity);
}
