package com.example.product;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.product.entity.Orders;
import com.example.product.exception.OrderIdNotFoundException;
import com.example.product.repository.OrdersRepository;
import com.example.product.service.OrdersServiceImpl;

@ExtendWith(MockitoExtension.class)

public class OrdersServiceImplTests {

    @Mock
    private OrdersRepository ordersRepositoryMock;

    @InjectMocks
    private OrdersServiceImpl ordersService;

    private Orders order;

    @BeforeEach
    void setUp() {
        order = new Orders();
        order.setOrder_id("ORDER123");
        order.setOrder_status("Pending");
        // Set other order properties as needed
    }

    @Test
    void testPlaceOrder() {
        assertDoesNotThrow(() -> ordersService.placeOrder(order));
    }

    @Test
    void testGetOrderDetails() throws OrderIdNotFoundException {
        when(ordersRepositoryMock.findById("ORDER123")).thenReturn(java.util.Optional.ofNullable(order));

        assertEquals(order, ordersService.getOrderDetails("ORDER123"));
    }

    @Test
    void testUpdateOrderStatus() throws Exception {
        when(ordersRepositoryMock.findById("ORDER123")).thenReturn(java.util.Optional.ofNullable(order));

        assertDoesNotThrow(() -> ordersService.updateOrderStatus("ORDER123"));
        assertEquals("Delivered", order.getOrder_status()); // Assuming the initial status is "Pending"
    }

    @Test
    void testCancelOrder() {
        when(ordersRepositoryMock.findById("ORDER123")).thenReturn(java.util.Optional.ofNullable(order));

        assertDoesNotThrow(() -> ordersService.cancelOrder("ORDER123"));
        assertEquals("Cancelled", order.getOrder_status());
    }
}
