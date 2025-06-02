package com.example.product.exception;

public class DriverIdNotFoundException extends Exception{
	private String message;

	public DriverIdNotFoundException(String message) {
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
