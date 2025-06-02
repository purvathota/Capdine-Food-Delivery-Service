package com.example.product.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.dto.ExceptionMessage;
import com.example.product.entity.Customers;
import com.example.product.entity.DeliveryDrivers;
import com.example.product.entity.Orders;
import com.example.product.entity.Restaurants;
import com.example.product.exception.CustomerIdNotFoundException;
import com.example.product.exception.DriverIdNotFoundException;
import com.example.product.exception.OrderIdNotFoundException;
import com.example.product.exception.RestaurantIdNotFoundException;
import com.example.product.service.CustomersService;
import com.example.product.service.DeliveryDriversService;
import com.example.product.service.OrdersService;
import com.example.product.service.RestaurantsService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})

public class OrdersController {

	@Autowired
	OrdersService ordersService;

	@Autowired
	RestaurantsService rservice;

	@Autowired
	DeliveryDriversService dservice;

	@Autowired
	CustomersService cservice;

	// Place a new order
	@PostMapping("/restaurant/{restaurant_id}/driver/{driver_id}/customer/{customer_id}")
	public ResponseEntity<String> placeOrder(@RequestBody Orders order,
			@PathVariable("restaurant_id") int restaurant_id, @PathVariable("driver_id") int driver_id,
			@PathVariable("customer_id") int customer_id) {
		try {
			Restaurants restaurant = rservice.getRestaurantById(restaurant_id);
			DeliveryDrivers ddriver;
			try {
				ddriver = dservice.getDriverDetails(driver_id);
				order.setDeliverydrivers(ddriver);
			} catch (DriverIdNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Customers customer;
			try {
				customer = cservice.getCustomerDetails(customer_id);
				order.setCustomers(customer);
				order.setRestaurants(restaurant);
				order.setOrder_date(LocalDateTime.now());
				ordersService.placeOrder(order);

			} catch (CustomerIdNotFoundException e) {
				e.printStackTrace();
			}

		} catch (RestaurantIdNotFoundException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Order placed successfully!");

	}
	
	

	// Retrieve details of a specific order
	@GetMapping("/{order_id}")
	public ResponseEntity<Orders> getOrderDetails(@PathVariable String order_id) throws OrderIdNotFoundException {
		Orders order = ordersService.getOrderDetails(order_id);
		if (order != null) {
			return ResponseEntity.ok(order);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PutMapping("/{order_id}/status")
	public ResponseEntity<String> updateOrderStatus(@PathVariable("order_id") String order_id) {
		try {
			ordersService.updateOrderStatus(order_id);
			return ResponseEntity.ok("Order status updated successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// Cancel a specific order
	@DeleteMapping("/{order_id}")
	public ResponseEntity<String> cancelOrder(@PathVariable String order_id) {
		ordersService.cancelOrder(order_id);
		return ResponseEntity.status(HttpStatus.OK).body("Order cancelled successfully");
	}

	@ExceptionHandler({ OrderIdNotFoundException.class })
	public ResponseEntity<ExceptionMessage> handleBalanceLowException(OrderIdNotFoundException ex) {
		ExceptionMessage message = new ExceptionMessage(ex.getMessage(), LocalDate.now());
		return new ResponseEntity<ExceptionMessage>(message, HttpStatus.NOT_FOUND);
	}
	
	
}