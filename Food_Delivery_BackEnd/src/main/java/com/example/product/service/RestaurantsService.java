package com.example.product.service;

import java.util.List;

import com.example.product.entity.DeliveryAddresses;
import com.example.product.entity.Ratings;
import com.example.product.entity.Restaurants;
import com.example.product.exception.RestaurantIdNotFoundException;

public interface RestaurantsService {
	List<Restaurants> getAllRestaurants() throws RestaurantIdNotFoundException;
	Restaurants getRestaurantById(int restaurant_id) throws RestaurantIdNotFoundException;
	Restaurants createRestaurant(Restaurants restaurant);
	Restaurants updateRestaurant(int restaurant_id, Restaurants restaurant_details);
	void deleteRestaurant(int restaurant_id);
	List<Ratings> getReviewsByRestaurantId(int restaurant_id)throws RestaurantIdNotFoundException;
	List<DeliveryAddresses> getDeliveryAreasByRestaurantId(int restaurant_id)throws RestaurantIdNotFoundException;
}
