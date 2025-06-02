package com.example.product.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.entity.OrderItems;
import com.example.product.repository.OrderItemsRepository;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {

	@Autowired
    OrderItemsRepository repo;
	
	@Override
	public Optional<OrderItems> getOrderItemsDetails(int order_item_id) {
		// TODO Auto-generated method stub
		return repo.findById(order_item_id);
	}

	@Override
	public void saveOrderItems(OrderItems orderitems) {
		repo.save(orderitems);	
	}
}