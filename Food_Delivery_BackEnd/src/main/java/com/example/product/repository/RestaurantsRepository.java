package com.example.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.product.entity.Restaurants;

@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurants, Integer>{
	
}
