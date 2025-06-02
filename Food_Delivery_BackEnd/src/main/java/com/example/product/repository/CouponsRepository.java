package com.example.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.product.entity.Coupons;

@Repository
public interface CouponsRepository extends JpaRepository<Coupons, Integer> {
	
	@Query("SELECT c FROM Coupons c WHERE c.coupon_code = :coupon_code")
	List<Coupons> findByCouponCode(@Param("coupon_code") String coupon_code);
	
}
