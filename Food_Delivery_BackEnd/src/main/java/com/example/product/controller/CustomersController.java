package com.example.product.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.example.product.entity.Orders;
import com.example.product.entity.Ratings;
import com.example.product.exception.CustomerIdNotFoundException;
import com.example.product.service.CustomersService;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})

public class CustomersController {

	@Autowired
	CustomersService service;

	@GetMapping("/")
	public ResponseEntity<List<Customers>> getAllCustomers() throws CustomerIdNotFoundException {
		List<Customers> customers = service.getAllCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	@GetMapping("/{customer_id}")
	public ResponseEntity<Customers> getCustomerDetails(@PathVariable("customer_id") int customer_id)
			throws CustomerIdNotFoundException {
		Customers customer = service.getCustomerDetails(customer_id);
		if (customer != null) {
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

//	@PostMapping("/")
//	public ResponseEntity<Customers> newCustomer(@RequestBody Customers customers) {
//		Customers newCustomer = service.newCustomer(customers);
//		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
//	}

	@PutMapping("/{customer_id}")
	public ResponseEntity<String> updateCustomerDetails(@PathVariable("customer_id") int customer_id,
			@RequestBody Customers customers) {
		service.updateCustomerDetails(customer_id, customers);
		String message = "Customer details updated successfully for customer ID: " + customer_id;
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@GetMapping("/{customer_id}/orders")
	public ResponseEntity<List<Orders>> getCustomerOrders(@PathVariable("customer_id") int customer_id)
			throws CustomerIdNotFoundException {
		List<Orders> orders = service.getCustomerOrders(customer_id);
		if (orders != null) {
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{customer_id}/reviews")
	public ResponseEntity<List<Ratings>> getCustomerReviews(@PathVariable("customer_id") int customer_id)
			throws CustomerIdNotFoundException {
		List<Ratings> reviews = service.getCustomerReviews(customer_id);
		if (reviews != null) {
			return new ResponseEntity<>(reviews, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ExceptionHandler({ CustomerIdNotFoundException.class })
	public ResponseEntity<ExceptionMessage> handleBalanceLowException(CustomerIdNotFoundException ex) {
		ExceptionMessage message = new ExceptionMessage(ex.getMessage(), LocalDate.now());
		return new ResponseEntity<ExceptionMessage>(message, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/")
	public ResponseEntity<String> validateEmail(@RequestBody Map<String, String> requestBody) {
	    try {
	        String email = requestBody.get("customer_email");
	        String name = requestBody.get("customer_name");
	        String phone = requestBody.get("customer_phone");
	        boolean exists = service.emailExists(email); // Assuming customerService is your service class
	        if (exists) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
	        } else {
	            // Create a new customer object
	            Customers customer = new Customers();
	            customer.setCustomer_email(email);
	            customer.setCustomer_name(name);
	            customer.setCustomer_phone(phone);
	            
	            // You might want to set other customer information here

	            // Save the new customer
	            service.newCustomer(customer);

	            return ResponseEntity.status(HttpStatus.OK).body("Registration Successful");
	        }
	    } catch (Exception e) {
	        // Log the exception for debugging purposes
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }
	}

}