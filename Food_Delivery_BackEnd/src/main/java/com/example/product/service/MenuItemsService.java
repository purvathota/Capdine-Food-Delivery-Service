package com.example.product.service;

import java.util.List;

import com.example.product.entity.MenuItems;
import com.example.product.exception.RestaurantIdNotFoundException;

public interface MenuItemsService {
	List<MenuItems> getMenuItemsByRestaurantId(int restaurant_id) throws RestaurantIdNotFoundException;
	void addMenuItem(int restaurant_id, MenuItems menuitems);
	void updateMenuItem(int restaurantId, int itemId, MenuItems menuitems);
	void deleteMenuItem(int restaurant_id, int item_id);
	MenuItems getMenuDetails(int item_id) throws RestaurantIdNotFoundException;
	
}
