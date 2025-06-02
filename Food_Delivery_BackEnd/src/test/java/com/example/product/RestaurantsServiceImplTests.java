package com.example.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.product.entity.DeliveryAddresses;
import com.example.product.entity.Ratings;
import com.example.product.entity.Restaurants;
import com.example.product.exception.RestaurantIdNotFoundException;
import com.example.product.repository.RestaurantsRepository;
import com.example.product.service.RestaurantsServiceImpl;

class RestaurantsServiceImplTests {

    @Mock
    RestaurantsRepository repo;

    @InjectMocks
    RestaurantsServiceImpl service;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllRestaurants_NotEmpty() throws RestaurantIdNotFoundException {
        // Arrange
        List<Restaurants> restaurantsList = new ArrayList<>();
        restaurantsList.add(new Restaurants());
        when(repo.findAll()).thenReturn(restaurantsList);

        // Act
        List<Restaurants> result = service.getAllRestaurants();

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(restaurantsList.size(), result.size());
    }

    @Test
    void testGetAllRestaurants_Empty() {
        // Arrange
        when(repo.findAll()).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(RestaurantIdNotFoundException.class, () -> service.getAllRestaurants());
    }

    @Test
    void testGetRestaurantById_Found() throws RestaurantIdNotFoundException {
        // Arrange
        Restaurants restaurant = new Restaurants();
        when(repo.findById(1)).thenReturn(Optional.of(restaurant));

        // Act
        Restaurants result = service.getRestaurantById(1);

        // Assert
        assertNotNull(result);
        assertEquals(restaurant.getRestaurant_name(), result.getRestaurant_name());
    }

    @Test
    void testGetRestaurantById_NotFound() {
        // Arrange
        when(repo.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RestaurantIdNotFoundException.class, () -> service.getRestaurantById(1));
    }

    @Test
    void testCreateRestaurant() {
        // Arrange
        Restaurants restaurant = new Restaurants();
        when(repo.save(restaurant)).thenReturn(restaurant);

        // Act
        Restaurants result = service.createRestaurant(restaurant);

        // Assert
        assertNotNull(result);
        assertEquals(restaurant.getRestaurant_name(), result.getRestaurant_name());
    }

    @Test
    void testUpdateRestaurant() {
        // Arrange
        Restaurants existingRestaurant = new Restaurants();
        Restaurants updatedRestaurant = new Restaurants();
        when(repo.findById(1)).thenReturn(Optional.of(existingRestaurant));
        when(repo.save(existingRestaurant)).thenReturn(updatedRestaurant);

        // Act
        Restaurants result = service.updateRestaurant(1, updatedRestaurant);

        // Assert
        assertNotNull(result);
        assertEquals(updatedRestaurant.getRestaurant_name(), result.getRestaurant_name());
        assertEquals(updatedRestaurant.getRestaurant_address(), result.getRestaurant_address());
        assertEquals(updatedRestaurant.getRestaurant_phone(), result.getRestaurant_phone());
    }

    @Test
    void testDeleteRestaurant() {
        // Arrange
        int restaurantId = 1;

        // Act
        service.deleteRestaurant(restaurantId);

        // Assert
        verify(repo, times(1)).deleteById(restaurantId);
    }

    @Test
    void testGetReviewsByRestaurantId_Found() throws RestaurantIdNotFoundException {
        // Arrange
        int restaurantId = 1;
        List<Ratings> ratingsList = new ArrayList<>();
        ratingsList.add(new Ratings());
        Restaurants restaurant = new Restaurants();
        restaurant.setRatings(ratingsList); // Set the ratings list for the restaurant
        when(repo.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        // Act
        List<Ratings> result = service.getReviewsByRestaurantId(restaurantId);

        // Assert
        assertEquals(ratingsList.size(), result.size());
    }


    @Test
    void testGetReviewsByRestaurantId_NotFound() {
        // Arrange
        int restaurantId = 1;
        when(repo.findById(restaurantId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RestaurantIdNotFoundException.class, () -> service.getReviewsByRestaurantId(restaurantId));
    }

    @Test
    void testGetDeliveryAreasByRestaurantId_Found() throws RestaurantIdNotFoundException {
        // Arrange
        int restaurantId = 1;
        List<DeliveryAddresses> deliveryAddressesList = new ArrayList<>();
        deliveryAddressesList.add(new DeliveryAddresses());
        Restaurants restaurant = new Restaurants();
        restaurant.setDeliveryaddresses(deliveryAddressesList); // Set the delivery addresses list for the restaurant
        when(repo.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        // Act
        List<DeliveryAddresses> result = service.getDeliveryAreasByRestaurantId(restaurantId);

        // Assert
        assertEquals(deliveryAddressesList.size(), result.size());
    }


    @Test
    void testGetDeliveryAreasByRestaurantId_NotFound() {
        // Arrange
        int restaurantId = 1;
        when(repo.findById(restaurantId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RestaurantIdNotFoundException.class, () -> service.getDeliveryAreasByRestaurantId(restaurantId));
    }
}
