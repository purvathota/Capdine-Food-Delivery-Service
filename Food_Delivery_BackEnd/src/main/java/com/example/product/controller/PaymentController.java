package com.example.product.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.product.service.DeliveryDriversService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000") // Use origins for specific control
public class PaymentController {

	@Autowired
	DeliveryDriversService service;

	private static final Logger logger = Logger.getLogger(PaymentController.class.getName());

	@Value("${razorpay.key-id}")
	private String razorpayKeyId;

	@Value("${razorpay.key-secret}")
	private String razorpayKeySecret;

	@PostMapping("/checkout")
	public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Map<String, String> requestBody) {
		try {
			RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

//			double totalAmount = products.stream()
//					.mapToDouble(product -> ((Number) product.get("item_price")).doubleValue()
//							* ((Number) product.get("quantity")).intValue())
//					.sum();
			
			double totalAmount = Double.parseDouble(requestBody.get("total"));

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", Math.round(totalAmount * 100)); // Convert to smallest currency unit
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "order_rcpt_1");

			Order order = razorpayClient.Orders.create(orderRequest);
			Map<String, Object> response = new HashMap<>();
			response.put("key", razorpayKeyId); // Including keyId in the response
			response.put("orderId", order.get("id"));
			response.put("amount", order.get("amount"));
			response.put("currency", order.get("currency"));
			response.put("receipt", order.get("receipt"));

			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (RazorpayException e) {
			logger.severe("Failed to create Razorpay order: " + e.getMessage());
			Map<String, Object> error = new HashMap<>();
			error.put("error", "Internal Server Error: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		}
	}
}