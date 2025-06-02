package com.example.product.service;

import com.example.product.entity.Orders;
import com.example.product.exception.OrderIdNotFoundException;

public interface OrdersService {
 
	void placeOrder(Orders order);
	Orders getOrderDetails(String order_id) throws OrderIdNotFoundException;
	void updateOrderStatus(String order_id) throws Exception;
	void cancelOrder(String order_id);
	
 
}
 
