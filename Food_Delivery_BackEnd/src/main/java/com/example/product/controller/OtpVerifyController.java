package com.example.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.entity.Customers;
import com.example.product.service.OtpVerifyServiceImpl;

import java.util.*;

@RestController
@RequestMapping("/api/otp")
@CrossOrigin("http://localhost:3000/")
public class OtpVerifyController {

	@Autowired
	private OtpVerifyServiceImpl otpService;

	@PostMapping("/request")
	public ResponseEntity<String> requestOTP(@RequestBody Map<String, String> requestBody) {
		String email = requestBody.get("email");
		boolean isExist = otpService.customerExists(email);

		if (isExist) {
			String otp = otpService.generateOTP(email);
			return ResponseEntity.ok("OTP sent to your email");
		} else {
			// Use either HttpStatus.NOT_FOUND or HttpStatus.FORBIDDEN
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or not registered");
		}
	}

	@PostMapping("/verify")
	public ResponseEntity<?> verifyOTP(@RequestBody Map<String, String> requestBody) {
		String email = requestBody.get("email");
		String otp = requestBody.get("otp");

		// Check if either email or otp is null or empty
		if (email == null || email.isEmpty() || otp == null || otp.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email and OTP must be provided");
		}

		try {
			if (otpService.verifyOTP(email, otp)) {
				Customers user = otpService.getCustomer(email);
				if (user != null) {
					return ResponseEntity.ok(user);
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
				}
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP");
			}
		} catch (Exception e) {
			// Log the exception details here using logger
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while verifying OTP");
		}
	}
}