package com.springmvc.demo.repository;

import com.springmvc.demo.domain.CartItem;
import com.springmvc.demo.domain.Customer;
import com.springmvc.demo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findAllByCustomer(Customer customer);
    CartItem findByCustomerAndProduct(Customer customer, Product product);

    void deleteByCustomerAndProduct(Customer customer, Product product);

    @Modifying
    @Query("UPDATE CartItem c SET c.quantity = :quantity WHERE c.customer.id = :customerId AND c.product.id = :productId")
    CartItem updateQuantity(@Param(value="customerId") Long customerId,
                            @Param(value = "productId") Long productId,
                            @Param(value = "quantity") int quantity);
}
