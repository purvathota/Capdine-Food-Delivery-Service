package com.example.product.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.entity.MenuItems;
import com.example.product.entity.OrderItems;
import com.example.product.entity.Orders;
import com.example.product.exception.OrderIdNotFoundException;
import com.example.product.exception.RestaurantIdNotFoundException;
import com.example.product.service.MenuItemsService;
import com.example.product.service.OrderItemsService;
import com.example.product.service.OrdersService;

@RestController
@RequestMapping("/api/orderitems")
@CrossOrigin("http://localhost:3000/")
public class OrderItemsController {
	@Autowired
	OrderItemsService service;
	
	@Autowired
	OrdersService oservice;
	
	@Autowired
	MenuItemsService mservice;

	@GetMapping("/{order_item_id}")
	public ResponseEntity<OrderItems> getOrderItemDetails(@PathVariable("order_item_id") int order_item_id) {
		Optional<OrderItems> orderItemsOptional = service.getOrderItemsDetails(order_item_id);
		if (orderItemsOptional.isPresent()) {
			return new ResponseEntity<>(orderItemsOptional.get(), HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/orderItems/{order_id}/{item_id}")
	public ResponseEntity<String> saveOrderItem(@RequestBody OrderItems orderitems,
			@PathVariable("order_id") String order_id, @PathVariable("item_id") Integer item_id) {
		try {
			Orders order = oservice.getOrderDetails(order_id);
			MenuItems item;
			try {
				item = mservice.getMenuDetails(item_id);
				orderitems.setMenuitems(item);
			} catch (RestaurantIdNotFoundException e) {
				e.printStackTrace();
			}
			orderitems.setOrders(order);
			service.saveOrderItems(orderitems);
		} catch (OrderIdNotFoundException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Orderitem added successfully!");
	}
}