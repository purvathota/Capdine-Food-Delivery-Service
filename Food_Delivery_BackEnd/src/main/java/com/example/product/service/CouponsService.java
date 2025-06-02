package com.example.product.service;

import java.util.List;

import com.example.product.entity.Coupons;

public interface CouponsService {
	
	List<Coupons> getAllCoupons();
	List<Coupons> getCouponsByCode(String coupon_code);

}
