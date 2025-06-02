package com.example.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.product.entity.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {

}
