package com.springmvc.demo.controller;

import com.springmvc.demo.domain.CartItem;
import com.springmvc.demo.domain.Customer;
import com.springmvc.demo.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartController {

    private CartItemService cartItemService;

    @GetMapping
    public String showCartItems(Model model, Customer customer) {
        List<CartItem> cart = cartItemService.findAllByCustomer(customer);
        model.addAttribute("cartItems", cart);
        return "cart";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable(value = "productId") Long productId,
                            @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
                            Customer customer) {
        cartItemService.addProduct(customer, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/update/{productId}")
    public String updateQuantity(@PathVariable(value = "productId") Long productId,
                            @RequestParam(value = "quantity") int quantity,
                            Customer customer) {
        cartItemService.updateQuantity(customer, productId, quantity);
        return "redirect:/cart";
    }

    @DeleteMapping("/delete/{productId}")
    public String deleteFromCart(@PathVariable(value = "productId") Long productId,
                                 Customer customer) {
        cartItemService.deleteByCustomerAndProduct(customer, productId);
        return "redirect:/cart";
    }
}
