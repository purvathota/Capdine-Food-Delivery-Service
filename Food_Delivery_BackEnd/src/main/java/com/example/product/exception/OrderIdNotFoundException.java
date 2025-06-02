package com.example.product.exception;

public class OrderIdNotFoundException extends Exception{
	private String message;

	public OrderIdNotFoundException(String message) {
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
