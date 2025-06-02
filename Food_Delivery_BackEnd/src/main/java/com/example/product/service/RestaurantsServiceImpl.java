package com.example.product.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.product.entity.DeliveryAddresses;
import com.example.product.entity.Ratings;
import com.example.product.entity.Restaurants;
import com.example.product.exception.RestaurantIdNotFoundException;
import com.example.product.repository.RestaurantsRepository;

@Service
public class RestaurantsServiceImpl implements RestaurantsService {
	
	@Autowired
	RestaurantsRepository repo;

	@Override
    public List<Restaurants> getAllRestaurants() throws RestaurantIdNotFoundException{
		if (repo.findAll().isEmpty()) {
			throw new RestaurantIdNotFoundException("Restaurant list is empty");
		}
        return repo.findAll();
    }

    
    @Override
    public Restaurants getRestaurantById(int restaurant_id) throws RestaurantIdNotFoundException{
    	if(repo.findById(restaurant_id).isEmpty()) {
    		throw new RestaurantIdNotFoundException("Restaurant with Id" +restaurant_id+ "is not found");
    	}
    	return repo.findById(restaurant_id).get();
    }
    
    

    @Override
    public Restaurants createRestaurant(Restaurants restaurant) {
        return repo.save(restaurant);
    }

    @Override
    public Restaurants updateRestaurant(int restaurant_id, Restaurants restaurant_details) {
        Optional<Restaurants> optionalRestaurant = repo.findById(restaurant_id);
        if (optionalRestaurant.isPresent()) {
            Restaurants restaurants = optionalRestaurant.get();

            restaurants.setRestaurant_name(restaurant_details.getRestaurant_name());
            restaurants.setRestaurant_address(restaurant_details.getRestaurant_address());
            restaurants.setRestaurant_phone(restaurant_details.getRestaurant_phone());

            return repo.save(restaurants);
        } else {
            return null; // Restaurant with given ID not found
        }
    }

    @Override
    public void deleteRestaurant(int restaurant_id){
        repo.deleteById(restaurant_id);
    }
  
	@Override
	public List<Ratings> getReviewsByRestaurantId(int restaurant_id) throws RestaurantIdNotFoundException {
		Optional<Restaurants> optionalRestaurant = repo.findById(restaurant_id);
		if (optionalRestaurant.isEmpty()) {
			throw new RestaurantIdNotFoundException("Restaurant with Id " +restaurant_id+ "is not found");
		} else {
			if (optionalRestaurant.isPresent()) {
				Restaurants restaurant = optionalRestaurant.get();
				return restaurant.getRatings(); // Assuming getRatings() returns a list of ratings/reviews associated
												// with the restaurant
			} else {
				return Collections.emptyList(); // Return empty list if restaurant not found
			}
		}
	}

	@Override
	public List<DeliveryAddresses> getDeliveryAreasByRestaurantId(int restaurant_id)
			throws RestaurantIdNotFoundException {
		Optional<Restaurants> optionalRestaurant = repo.findById(restaurant_id);
		if (optionalRestaurant.isEmpty()) {
			throw new RestaurantIdNotFoundException("Restaurant with Id " +restaurant_id+ "is not found");
		} else {
			if (optionalRestaurant.isPresent()) {
				Restaurants restaurant = optionalRestaurant.get();
				return restaurant.getDeliveryaddresses(); // Assuming getDeliveryAreas() returns a list of delivery
															// areas associated with the restaurant
			} else {
				return Collections.emptyList(); // Return empty list if restaurant not found
			}
		}
}
}
