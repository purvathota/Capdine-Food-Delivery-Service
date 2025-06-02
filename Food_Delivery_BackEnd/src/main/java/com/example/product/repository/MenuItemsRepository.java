package com.example.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.product.entity.MenuItems;

import jakarta.transaction.Transactional;

@Repository
public interface MenuItemsRepository extends JpaRepository<MenuItems, Integer>{

	@Modifying
    @Transactional
    @Query("DELETE FROM MenuItems m WHERE m.restaurant_id = :restaurant_id AND m.item_id = :item_id")
    void deleteMenuItemByRestaurantIdAndMenuItemId(@Param("restaurant_id") int restaurant_id, @Param("item_id") int item_id);
	
}
