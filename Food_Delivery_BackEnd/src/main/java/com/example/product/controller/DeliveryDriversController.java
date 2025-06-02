package com.example.product.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.dto.ExceptionMessage;
import com.example.product.entity.DeliveryDrivers;
import com.example.product.entity.Orders;
import com.example.product.exception.DriverIdNotFoundException;
import com.example.product.service.DeliveryDriversService;

@RestController
@RequestMapping("/api/drivers")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})

public class DeliveryDriversController {
	
	@Autowired
	DeliveryDriversService service;

	@GetMapping("/")
	public ResponseEntity<List<DeliveryDrivers>> getAllDrivers() throws DriverIdNotFoundException{
	    List<DeliveryDrivers> drivers = service.getAllDrivers();
	    return new ResponseEntity<>(drivers, HttpStatus.OK);
	}

    @GetMapping("/{driver_id}")
    public ResponseEntity<DeliveryDrivers> getDriverDetails(@PathVariable("driver_id") int driver_id) throws DriverIdNotFoundException{
        DeliveryDrivers drivers = service.getDriverDetails(driver_id);
        if (drivers != null) {
            return new ResponseEntity<>(drivers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{order_id}/assignDriver/{driver_id}")
    public ResponseEntity<String> assignDriverToOrder(@PathVariable("order_id") String order_id, @PathVariable("driver_id") int driver_id) {
        try {
            service.assignDriverToOrder(order_id, driver_id);
            return new ResponseEntity<>("Driver assigned to order successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{driver_id}/orders")	
	public ResponseEntity<List<Orders>> getDriverOrders(@PathVariable int driver_id) throws DriverIdNotFoundException{
        List<Orders> orders = service.getDriverOrders(driver_id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    
    @PutMapping("/updateDriver/{order_id}/{driver_id}")
    public ResponseEntity<String> updateDriverAfterPayment(
            @PathVariable("order_id") String order_id,
            @PathVariable("driver_id") int driver_id) {
        try {
            service.updateDriverAfterPayment(order_id, driver_id);
            return ResponseEntity.status(HttpStatus.OK).body("Driver updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update driver: " + e.getMessage());
        }
    }
    
    @PutMapping("/updateDriverAfterDeliver/{order_id}/{driver_id}")
    public ResponseEntity<String> updateDriverAfterDelivered(
            @PathVariable("order_id") String order_id,
            @PathVariable("driver_id") int driver_id) {
        try {
            service.updateDriverAfterDelivered(order_id, driver_id);
            return ResponseEntity.status(HttpStatus.OK).body("Driver updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update driver: " + e.getMessage());
        }
    }
    
    @ExceptionHandler({DriverIdNotFoundException.class})
	public ResponseEntity<ExceptionMessage> handleBalanceLowException(DriverIdNotFoundException ex){
		ExceptionMessage message = new ExceptionMessage(ex.getMessage(), LocalDate.now());
		return new ResponseEntity<ExceptionMessage>(message, HttpStatus.NOT_FOUND);
	}    
}