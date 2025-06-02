package com.example.product.service;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.example.product.entity.MenuItems;
import com.example.product.exception.RestaurantIdNotFoundException;
import com.example.product.repository.MenuItemsRepository;
 
@Service
public class MenuItemsServiceImpl implements MenuItemsService {
	
	@Autowired
	MenuItemsRepository repo;

	@Override
    public List<MenuItems> getMenuItemsByRestaurantId(int restaurant_id) throws RestaurantIdNotFoundException{
		
		List<MenuItems> menuitems = repo.findAll(); // Fetch all menu items
	    List<MenuItems> menuItemsForRestaurant = new ArrayList<>();
	    for (MenuItems menuItem : menuitems) {
	        if (menuItem.getRestaurant_id() == restaurant_id) {
	            menuItemsForRestaurant.add(menuItem);
	        }
	    }
	    if(menuitems.isEmpty()) {
	    	throw new RestaurantIdNotFoundException(restaurant_id+" didn't match");
	    }
	    return menuItemsForRestaurant;
    }
 
    @Override
    public void addMenuItem(int restaurant_id, MenuItems menuitems) {
        menuitems.setRestaurant_id(restaurant_id); // Set the restaurant ID for the menu item
        repo.save(menuitems);
    }
 
    @Override
    public void updateMenuItem(int restaurant_id, int item_id, MenuItems menuitems) {
    	Optional<MenuItems> existingMenuItemOptional = repo.findById(item_id);
        if (existingMenuItemOptional.isPresent()) {
            MenuItems existingMenuItem = existingMenuItemOptional.get();
            
            if (existingMenuItem.getRestaurant_id() == restaurant_id) {
                existingMenuItem.setItem_name(menuitems.getItem_name());
                existingMenuItem.setItem_description(menuitems.getItem_description());
                existingMenuItem.setItem_price(menuitems.getItem_price());
                
                repo.save(existingMenuItem);
            } else {
                throw new IllegalArgumentException("Menu item with ID " + item_id + " does not belong to restaurant with ID " + restaurant_id);
            }
        } else {
            throw new IllegalArgumentException("Menu item with ID " + item_id + " does not exist");
        }
    }
 
    @Override
    public void deleteMenuItem(int restaurant_id, int item_id) {
    	repo.deleteMenuItemByRestaurantIdAndMenuItemId(restaurant_id, item_id);
    }
    
    @Override
	public MenuItems getMenuDetails(int item_id) throws RestaurantIdNotFoundException {
		MenuItems menuitems = repo.findById(item_id).orElse(null);
        if (menuitems == null) {
            throw new RestaurantIdNotFoundException("Order with ID " + item_id + " not found");
        }
        return menuitems;
	}
}