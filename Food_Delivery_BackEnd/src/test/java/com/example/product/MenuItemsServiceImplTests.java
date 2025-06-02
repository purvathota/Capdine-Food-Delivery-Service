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

import com.example.product.entity.MenuItems;
import com.example.product.repository.MenuItemsRepository;
import com.example.product.service.MenuItemsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MenuItemsServiceImplTests {

    @Mock
    private MenuItemsRepository menuItemsRepositoryMock;

    @InjectMocks
    private MenuItemsServiceImpl menuItemsService;

    private MenuItems menuItem;

    @BeforeEach
    void setUp() {
        menuItem = new MenuItems();
        menuItem.setItem_id(1); // Corrected from setItem_id to setId
        menuItem.setRestaurant_id(1);
        menuItem.setItem_name("Burger");
        menuItem.setItem_description("Delicious burger");
        menuItem.setItem_price((float) 10.99);
    }

    @Test
    void testGetMenuItemsByRestaurantId() {
        List<MenuItems> menuItemsList = new ArrayList<>();
        menuItemsList.add(menuItem);

        when(menuItemsRepositoryMock.findAll()).thenReturn(menuItemsList);

        assertDoesNotThrow(() -> {
            List<MenuItems> result = menuItemsService.getMenuItemsByRestaurantId(1);
            assertEquals(menuItemsList, result);
        });
    }


    @Test
    void testAddMenuItem() {
        assertDoesNotThrow(() -> menuItemsService.addMenuItem(1, menuItem));
    }

    @Test
    void testUpdateMenuItem() {
        Optional<MenuItems> existingMenuItemOptional = Optional.of(menuItem);

        when(menuItemsRepositoryMock.findById(1)).thenReturn(existingMenuItemOptional);

        assertDoesNotThrow(() -> menuItemsService.updateMenuItem(1, 1, menuItem));
    }

    @Test
    void testDeleteMenuItem() {
        assertDoesNotThrow(() -> menuItemsService.deleteMenuItem(1, 1));
    }

    @Test
    void testGetMenuDetails() {
        Optional<MenuItems> menuItemOptional = Optional.of(menuItem);

        when(menuItemsRepositoryMock.findById(1)).thenReturn(menuItemOptional);

        assertDoesNotThrow(() -> {
            MenuItems result = menuItemsService.getMenuDetails(1);
            assertEquals(menuItem, result);
        });
    }
}
