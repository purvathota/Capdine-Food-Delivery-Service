package com.example.product.controller;

import com.example.product.entity.Coupons;
import com.example.product.service.CouponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coupons")
@CrossOrigin("http://localhost:3000/")

public class CouponsController {

    @Autowired
    CouponsService service;
    
    @GetMapping("/")
    public ResponseEntity<List<Coupons>> getAllCoupons() {
        List<Coupons> coupons = service.getAllCoupons();
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    @GetMapping("/{coupon_code}")
    public ResponseEntity<List<Coupons>> getCouponsByCode(@PathVariable("coupon_code") String coupon_code) {
        List<Coupons> coupons = service.getCouponsByCode(coupon_code);
        if (coupons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(coupons, HttpStatus.OK);
        }
    }
}