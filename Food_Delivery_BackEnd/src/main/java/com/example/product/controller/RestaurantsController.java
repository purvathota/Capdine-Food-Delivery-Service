package com.example.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.product.dto.ExceptionMessage;
import com.example.product.entity.DeliveryAddresses;
import com.example.product.entity.Ratings;
import com.example.product.entity.Restaurants;
import com.example.product.exception.RestaurantIdNotFoundException;
import com.example.product.service.RestaurantsService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})

public class RestaurantsController {

	@Autowired
	RestaurantsService service;

	@GetMapping("/")
	public ResponseEntity<List<Restaurants>> getAllRestaurants() throws RestaurantIdNotFoundException {
		List<Restaurants> restaurants = service.getAllRestaurants();
		return new ResponseEntity<>(restaurants, HttpStatus.OK);
	}

	@GetMapping("/{restaurant_id}")
	public ResponseEntity<Restaurants> getRestaurantById(@PathVariable("restaurant_id") int restaurant_id)
			throws RestaurantIdNotFoundException {
		return new ResponseEntity<Restaurants>(service.getRestaurantById(restaurant_id), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Restaurants> createRestaurant(@RequestBody Restaurants restaurant) {
		Restaurants createdRestaurant = service.createRestaurant(restaurant);
		return new ResponseEntity<>(createdRestaurant, HttpStatus.CREATED);
	}

	@PutMapping("/{restaurant_id}")
	public ResponseEntity<Restaurants> updateRestaurant(@PathVariable("restaurant_id") int restaurant_id,
			@RequestBody Restaurants restaurants) {
		Restaurants updatedRestaurants = service.updateRestaurant(restaurant_id, restaurants);
		if (updatedRestaurants != null) {
			return new ResponseEntity<>(updatedRestaurants, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{restaurant_id}")
	public ResponseEntity<Void> deleteRestaurant(@PathVariable("restaurant_id") int restaurant_id) {
		service.deleteRestaurant(restaurant_id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{restaurant_id}/reviews")
	public ResponseEntity<List<Ratings>> getReviewsByRestaurantId(@PathVariable("restaurant_id") int restaurant_id)
			throws RestaurantIdNotFoundException {
		List<Ratings> ratings = service.getReviewsByRestaurantId(restaurant_id);
		return new ResponseEntity<>(ratings, HttpStatus.OK);
	}

	@GetMapping("/{restaurant_id}/deliveryareas")
	public ResponseEntity<List<DeliveryAddresses>> getDeliveryAreasByRestaurantId(
			@PathVariable("restaurant_id") int restaurant_id) throws RestaurantIdNotFoundException {
		List<DeliveryAddresses> deliveryAddresses = service.getDeliveryAreasByRestaurantId(restaurant_id);
		return new ResponseEntity<>(deliveryAddresses, HttpStatus.OK);
	}

	@ExceptionHandler({ RestaurantIdNotFoundException.class })
	public ResponseEntity<ExceptionMessage> handleBalanceLowException(RestaurantIdNotFoundException ex) {
		ExceptionMessage message = new ExceptionMessage(ex.getMessage(), LocalDate.now());
		return new ResponseEntity<ExceptionMessage>(message, HttpStatus.NOT_FOUND);
	}

}