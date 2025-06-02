package com.example.product.service;

import java.util.List;
import com.example.product.entity.DeliveryDrivers;
import com.example.product.entity.Orders;
import com.example.product.exception.DriverIdNotFoundException;
 
public interface DeliveryDriversService {
 
	List<DeliveryDrivers> getAllDrivers() throws DriverIdNotFoundException;
	DeliveryDrivers getDriverDetails(int driver_id) throws DriverIdNotFoundException;
	void assignDriverToOrder(String order_id, int driver_id) throws Exception;
	List<Orders> getDriverOrders(int driver_id) throws DriverIdNotFoundException;
	void updateDriverAfterPayment(String order_id, int driver_id);
	void updateDriverAfterDelivered(String order_id, int driver_id);
}
