package com.example.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.product.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, String> {

}
