package com.example.product.exception;

public class RestaurantIdNotFoundException  extends Exception{
	private String message;

	public RestaurantIdNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
