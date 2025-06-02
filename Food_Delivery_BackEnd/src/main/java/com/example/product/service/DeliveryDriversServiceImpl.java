package com.example.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.product.entity.DeliveryDrivers;
import com.example.product.entity.Orders;
import com.example.product.exception.DriverIdNotFoundException;
import com.example.product.repository.DeliveryDriversRepository;
import com.example.product.repository.OrdersRepository;

import jakarta.transaction.Transactional;

@Service
public class DeliveryDriversServiceImpl implements DeliveryDriversService {

	@Autowired
	DeliveryDriversRepository ddrepo;

	@Autowired
	OrdersRepository orepo;

	@Override
	public List<DeliveryDrivers> getAllDrivers() throws DriverIdNotFoundException {
		if (ddrepo.findAll().isEmpty()) {
			throw new DriverIdNotFoundException("DeliveryDrivers list is Empty");
		}
		return ddrepo.findAll();
	}

	@Override
	public DeliveryDrivers getDriverDetails(int driver_id) throws DriverIdNotFoundException {
		// DeliveryDrivers deliver=).get();
		if (ddrepo.findById(driver_id).isEmpty()) {
			throw new DriverIdNotFoundException("DeliveryDriver with Id " + driver_id + " is not found.");
		}
		return ddrepo.findById(driver_id).get();
	}

	@Override
	public void assignDriverToOrder(String order_id, int driver_id) throws Exception {
		Orders orders = orepo.findById(order_id).orElse(null);
		DeliveryDrivers drivers = ddrepo.findById(driver_id).orElse(null);

		if (orders != null && drivers != null) {
			orepo.save(orders);
		} else {
			if (orders == null && drivers == null) {
				// Both order and driver not found
				throw new Exception("Order and driver not found");
			} else if (orders == null) {
				// Order not found
				throw new Exception("Order not found");
			} else {
				// Driver not found
				throw new Exception("Driver not found");
			}
		}
	}

	@Override
	public List<Orders> getDriverOrders(int driver_id) throws DriverIdNotFoundException {
		if (ddrepo.findOrdersByOrderId(driver_id).isEmpty()) {
			throw new DriverIdNotFoundException("List is Empty.");
		}
		return ddrepo.findOrdersByOrderId(driver_id);
	}

	@Transactional
	public void updateDriverAfterPayment(String order_id, int driver_id) {
		// Retrieve the driver information based on the driverId
		Optional<DeliveryDrivers> optionalDriver = ddrepo.findById(driver_id);

		// Extract the actual DeliveryDrivers object from the Optional
		DeliveryDrivers driver = optionalDriver.get();

		// Update driver availability to true (1)
		driver.setAvailable(true);
		// Store the order ID
		driver.setOrder_id(order_id);
		// Save the changes
		ddrepo.save(driver);

	}

	@Transactional
	public void updateDriverAfterDelivered(String order_id, int driver_id) {
		// Retrieve the driver information based on the driverId
		Optional<DeliveryDrivers> optionalDriver = ddrepo.findById(driver_id);

		// Extract the actual DeliveryDrivers object from the Optional
		DeliveryDrivers driver = optionalDriver.get();

		// Update driver availability to true (1)
		driver.setAvailable(false);
		// Store the order ID
		driver.setOrder_id(null);
		// Save the changes
		ddrepo.save(driver);

	}
}