package com.example.product.dto;

import java.time.LocalDate;

public class ExceptionMessage {
	private String message;
	private LocalDate date;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public ExceptionMessage(String message, LocalDate date) {
		super();
		this.message = message;
		this.date = date;
	}
	
    public ExceptionMessage() {}

}
