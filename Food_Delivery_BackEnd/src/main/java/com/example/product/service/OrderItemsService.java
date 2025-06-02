package com.example.product.service;


import java.util.Optional;

import com.example.product.entity.OrderItems;

public interface OrderItemsService {
	
	Optional<OrderItems> getOrderItemsDetails(int order_item_id);
	void saveOrderItems(OrderItems orderitems);

}
