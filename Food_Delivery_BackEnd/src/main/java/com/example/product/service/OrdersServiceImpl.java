package com.example.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.entity.Orders;
import com.example.product.exception.OrderIdNotFoundException;
import com.example.product.repository.OrdersRepository;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	OrdersRepository repo;

	@Override
	public void placeOrder(Orders orders) {
		// TODO Auto-generated method stub
		repo.save(orders);
	}

	@Override
	public Orders getOrderDetails(String order_id) throws OrderIdNotFoundException {
		Orders orders = repo.findById(order_id).orElse(null);
		if (orders == null) {
			throw new OrderIdNotFoundException("Order with ID " + order_id + " not found");
		}
		return orders;
	}

	@Override
	public void updateOrderStatus(String order_id) throws Exception {
		Orders existingOrders = repo.findById(order_id).orElse(null);
		if (existingOrders != null) {
			String currentStatus = existingOrders.getOrder_status();
			// Logic to update order status from delivered to pending and vice versa
			if ("Delivered".equalsIgnoreCase(currentStatus)) {
				existingOrders.setOrder_status("Pending");
			} else if ("Pending".equalsIgnoreCase(currentStatus)) {
				existingOrders.setOrder_status("Delivered");
			} else {
				// Throw exception if the current status is neither delivered nor pending
				throw new Exception("Invalid order status transition.");
			}
			// Save the updated order
			repo.save(existingOrders);
		} else {
			// Throw exception if order doesn't exist
			throw new Exception("Order not found.");
		}
	}

	@Override
	public void cancelOrder(String order_id) {
		Orders existingOrders = repo.findById(order_id).orElse(null);
		if (existingOrders != null) {
			existingOrders.setOrder_status("Cancelled");
			repo.save(existingOrders);
		}
	}
	
	
}