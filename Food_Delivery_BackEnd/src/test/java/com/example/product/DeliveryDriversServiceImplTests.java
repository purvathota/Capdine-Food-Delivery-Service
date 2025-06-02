package com.example.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.product.entity.DeliveryDrivers;
import com.example.product.entity.Orders;
import com.example.product.exception.DriverIdNotFoundException;
import com.example.product.repository.DeliveryDriversRepository;
import com.example.product.repository.OrdersRepository;
import com.example.product.service.DeliveryDriversServiceImpl;

@ExtendWith(MockitoExtension.class)

public class DeliveryDriversServiceImplTests {

    @Mock
    private DeliveryDriversRepository ddRepoMock;

    @Mock
    private OrdersRepository ordersRepoMock;

    @InjectMocks
    private DeliveryDriversServiceImpl driversService;

    private DeliveryDrivers driver;
    private Orders order;

    @BeforeEach
    void setUp() {
        driver = new DeliveryDrivers();
        driver.setDriver_id(1);
        driver.setDriver_name("John");

        order = new Orders();
        order.setOrder_id("ORDER123");
        
    }

    @Test
    void testGetAllDrivers() {
        List<DeliveryDrivers> driversList = new ArrayList<>();
        driversList.add(driver);

        when(ddRepoMock.findAll()).thenReturn(driversList);

        assertDoesNotThrow(() -> driversService.getAllDrivers());
    }

    @Test
    void testGetDriverDetails() throws DriverIdNotFoundException {
        when(ddRepoMock.findById(1)).thenReturn(Optional.of(driver));

        assertEquals(driver, driversService.getDriverDetails(1));
    }

    @Test
    void testAssignDriverToOrder() throws Exception {
        when(ordersRepoMock.findById("ORDER123")).thenReturn(Optional.of(order));
        when(ddRepoMock.findById(1)).thenReturn(Optional.of(driver));

        assertDoesNotThrow(() -> driversService.assignDriverToOrder("ORDER123", 1));
    }

    @Test
    void testGetDriverOrders() throws DriverIdNotFoundException {
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(order);

        when(ddRepoMock.findOrdersByOrderId(1)).thenReturn(ordersList);

        assertDoesNotThrow(() -> driversService.getDriverOrders(1));
        assertEquals(ordersList, driversService.getDriverOrders(1));
    }

    @Test
    void testUpdateDriverAfterPayment() {
        when(ddRepoMock.findById(1)).thenReturn(Optional.of(driver));

        assertDoesNotThrow(() -> driversService.updateDriverAfterPayment("ORDER123", 1));
        assertTrue(driver.isAvailable());
        assertEquals("ORDER123", driver.getOrder_id());
    }
}
