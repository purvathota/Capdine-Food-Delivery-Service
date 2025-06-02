package com.example.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.entity.Customers;
import com.example.product.entity.Orders;
import com.example.product.entity.Ratings;
import com.example.product.exception.CustomerIdNotFoundException;
import com.example.product.repository.CustomersRepository;
import com.example.product.repository.RestaurantsRepository;

@Service
public class CustomersServiceImpl implements CustomersService {

	@Autowired
	CustomersRepository repo;

	@Autowired
	RestaurantsRepository repo1;

	@Override
	public List<Customers> getAllCustomers() throws CustomerIdNotFoundException {
		if (repo.findAll().isEmpty()) {
			throw new CustomerIdNotFoundException("Customer list is empty");
		}
		return repo.findAll();
	}

	@Override
	public Customers getCustomerDetails(int customer_id) throws CustomerIdNotFoundException {
		if (repo.findById(customer_id).isEmpty()) {
			throw new CustomerIdNotFoundException("Customer with ID " + customer_id + " not found");
		}
		return repo.findById(customer_id).get();
	}

	@Override
	public Customers newCustomer(Customers customers) {
		return repo.save(customers);
	}

	@Override
	public void updateCustomerDetails(int customerId, Customers customers) {
		repo.findById(customerId).ifPresent(existingCustomer -> {
			existingCustomer.setCustomer_name(customers.getCustomer_name());
			existingCustomer.setCustomer_email(customers.getCustomer_email());

			repo.save(existingCustomer);
		});
	}

	@Override
	public List<Orders> getCustomerOrders(int customer_id) throws CustomerIdNotFoundException {
		Customers customers = repo.findById(customer_id).orElse(null);
		if (customers == null) {
			throw new CustomerIdNotFoundException("Customer with ID " + customer_id + " is not found");
		}
		return customers.getOrders();
	}

	@Override
	public List<Ratings> getCustomerReviews(int customer_id) throws CustomerIdNotFoundException {
		List<Ratings> customerReviews = new ArrayList<>();

		Optional<Customers> optionalCustomer = repo.findById(customer_id);
		if (optionalCustomer.isEmpty()) {
			throw new CustomerIdNotFoundException("Customer with ID " + customer_id + " is not found");
		} else {
			if (optionalCustomer.isPresent()) {
				Customers customers = optionalCustomer.get();

				List<Orders> orders = customers.getOrders();
				for (Orders order : orders) {
					List<Ratings> ratings = order.getRatings();
					if (ratings != null) {
						customerReviews.addAll(ratings);
					}
				}
			}
		}
		return customerReviews;
	}
	
	
	@Override
    public boolean emailExists(String customer_email) {
        return repo.findCustomerByEmailCount(customer_email) > 0;
    }
}