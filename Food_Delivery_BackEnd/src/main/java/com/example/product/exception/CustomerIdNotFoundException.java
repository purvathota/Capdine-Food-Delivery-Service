package com.example.product.exception;

public class CustomerIdNotFoundException extends Exception{
	private String message;

	public CustomerIdNotFoundException(String message) {
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
