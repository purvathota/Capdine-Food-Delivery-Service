package com.example.product.service;

import com.example.product.entity.Customers;

public interface OtpVerifyService {

	String generateOTP(String email);
	void sendEmail(String email, String otp);
	boolean verifyOTP(String email, String otp);
	boolean customerExists(String customer_email);
	Customers getCustomer(String customer_email);
	
}
