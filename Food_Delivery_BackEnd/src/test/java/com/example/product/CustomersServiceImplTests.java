package com.example.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.product.entity.Customers;
import com.example.product.entity.Orders;
import com.example.product.entity.Ratings;
import com.example.product.exception.CustomerIdNotFoundException;
import com.example.product.repository.CustomersRepository;
import com.example.product.repository.RestaurantsRepository;
import com.example.product.service.CustomersServiceImpl;

public class CustomersServiceImplTests {

    @Mock
    private CustomersRepository customersRepository;

    @Mock
    private RestaurantsRepository restaurantsRepository;

    @InjectMocks
    private CustomersServiceImpl customersService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCustomers() throws CustomerIdNotFoundException {
        // Arrange
        List<Customers> customersList = new ArrayList<>();
        customersList.add(new Customers());
        customersList.add(new Customers());
        when(customersRepository.findAll()).thenReturn(customersList);

        // Act
        List<Customers> result = customersService.getAllCustomers();

        // Assert
        assertEquals(customersList.size(), result.size());
    }

    @Test
    public void testGetCustomerDetails() throws CustomerIdNotFoundException {
        // Arrange
        Customers customer = new Customers();
        customer.setCustomer_id(1);
        customer.setCustomer_name("Test Customer");
        customer.setCustomer_email("test@example.com");
        when(customersRepository.findById(1)).thenReturn(Optional.of(customer));

        // Act
        Customers result = customersService.getCustomerDetails(1);

        // Assert
        assertEquals(customer, result);
    }

    @Test
    public void testNewCustomer() {
        // Arrange
    	Customers customer = new Customers();
        when(customersRepository.save(any(Customers.class))).thenReturn(customer);

        // Act
        Customers result = customersService.newCustomer(customer);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testUpdateCustomerDetails() {
        // Arrange
        Customers existingCustomer = new Customers();
        existingCustomer.setCustomer_name("Old Name");
        existingCustomer.setCustomer_email("old@example.com");
        when(customersRepository.findById(1)).thenReturn(Optional.of(existingCustomer));

        Customers newCustomer = new Customers();
        newCustomer.setCustomer_name("New Name");
        newCustomer.setCustomer_email("new@example.com");

        // Act
        customersService.updateCustomerDetails(1, newCustomer);

        // Assert
        assertEquals("New Name", existingCustomer.getCustomer_name());
        assertEquals("new@example.com", existingCustomer.getCustomer_email());
    }

    @Test
    public void testGetCustomerOrders() throws CustomerIdNotFoundException {
        // Arrange
        Customers customer = new Customers();
        customer.setCustomer_id(1);
        Orders order1 = new Orders();
        Orders order2 = new Orders();
        List<Orders> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        customer.setOrders(orders);
        when(customersRepository.findById(1)).thenReturn(Optional.of(customer));

        // Act
        List<Orders> result = customersService.getCustomerOrders(1);

        // Assert
        assertEquals(orders.size(), result.size());
    }

    @Test
    public void testGetCustomerReviews() throws CustomerIdNotFoundException {
        // Arrange
        Customers customer = new Customers();
        customer.setCustomer_id(1);
        Orders order1 = new Orders();
        Orders order2 = new Orders();
        Ratings rating1 = new Ratings();
        Ratings rating2 = new Ratings();
        List<Ratings> ratings1 = new ArrayList<>();
        ratings1.add(rating1);
        List<Ratings> ratings2 = new ArrayList<>();
        ratings2.add(rating2);
        order1.setRatings(ratings1);
        order2.setRatings(ratings2);
        List<Orders> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        customer.setOrders(orders);
        when(customersRepository.findById(1)).thenReturn(Optional.of(customer));

        // Act
        List<Ratings> result = customersService.getCustomerReviews(1);

        // Assert
        assertEquals(2, result.size());
    }
}
