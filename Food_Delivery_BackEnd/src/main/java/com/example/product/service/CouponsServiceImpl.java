package com.example.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.entity.Coupons;
import com.example.product.repository.CouponsRepository;

import java.util.List;

@Service
public class CouponsServiceImpl implements CouponsService {

    @Autowired
    private CouponsRepository repo;
    
    @Override
    public List<Coupons> getAllCoupons() {
        return repo.findAll();
    }

	@Override
	public List<Coupons> getCouponsByCode(String coupon_code) {
		return repo.findByCouponCode(coupon_code);
	}
}