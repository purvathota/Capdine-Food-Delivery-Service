package com.example.product.service;

import java.util.List;

import com.example.product.entity.Customers;
import com.example.product.entity.Orders;
import com.example.product.entity.Ratings;
import com.example.product.exception.CustomerIdNotFoundException;

public interface CustomersService {
	List<Customers> getAllCustomers()throws CustomerIdNotFoundException;
	Customers getCustomerDetails(int customer_id) throws CustomerIdNotFoundException;
	Customers newCustomer(Customers customers);
	boolean emailExists(String customer_email);
	void updateCustomerDetails(int customerId, Customers customers);
	List<Orders> getCustomerOrders(int customer_id) throws CustomerIdNotFoundException;
	List<Ratings> getCustomerReviews(int customer_id) throws CustomerIdNotFoundException;
	
}
