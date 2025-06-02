package com.example.product.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.product.dto.ExceptionMessage;
import com.example.product.entity.MenuItems;
import com.example.product.entity.Restaurants;
import com.example.product.exception.RestaurantIdNotFoundException;
import com.example.product.service.MenuItemsService;
import com.example.product.service.RestaurantsService;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin("http://localhost:3000/")
public class MenuItemsController {

	@Autowired
	MenuItemsService service;
	@Autowired
	RestaurantsService rservice;

	@GetMapping("/{restaurant_id}/menu")
	public ResponseEntity<List<MenuItems>> getMenuItemsByRestaurantId(@PathVariable("restaurant_id") int restaurant_id)
			throws RestaurantIdNotFoundException {
		List<MenuItems> menuitems = service.getMenuItemsByRestaurantId(restaurant_id);
		return new ResponseEntity<>(menuitems, HttpStatus.OK);
	}

	@PostMapping("/{restaurant_id}/menu")
	public ResponseEntity<String> addMenuItem(@PathVariable("restaurant_id") int restaurant_id,
			@RequestBody MenuItems menuitems) {

		try {
			Restaurants restaurant;
			restaurant = rservice.getRestaurantById(restaurant_id);
			menuitems.setRestaurants(restaurant);
			service.addMenuItem(restaurant_id, menuitems);
		} catch (RestaurantIdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@PutMapping("/{restaurant_id}/menu/{item_id}")
	public ResponseEntity<Void> updateMenuItem(@PathVariable("restaurant_id") int restaurant_id,
			@PathVariable("item_id") int item_id, @RequestBody MenuItems menuitems) {
		try {
			Restaurants restaurant;
			restaurant = rservice.getRestaurantById(restaurant_id);
			menuitems.setRestaurants(restaurant);
			service.updateMenuItem(restaurant_id, item_id, menuitems);
		} catch (RestaurantIdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{restaurant_id}/menu/{item_id}")
	public ResponseEntity<String> deleteMenuItem(@PathVariable("restaurant_id") int restaurant_id,
			@PathVariable("item_id") int item_id) {
		try {
			service.deleteMenuItem(restaurant_id, item_id);
			return ResponseEntity.status(HttpStatus.OK).body("Menu item deleted successfully.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@ExceptionHandler({ RestaurantIdNotFoundException.class })
	public ResponseEntity<ExceptionMessage> handleBalanceLowException(RestaurantIdNotFoundException ex) {
		ExceptionMessage message = new ExceptionMessage(ex.getMessage(), LocalDate.now());
		return new ResponseEntity<ExceptionMessage>(message, HttpStatus.NOT_FOUND);
	}
}