package com.example.product.dto;

import java.time.LocalDateTime;

public class OrdersDto {
    
    private int order_id;
    private LocalDateTime order_date;
    private int customer_id;
    private int restaurant_id;
    private int delivery_driver_id;
    private String order_status;
    private int driver_id;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public LocalDateTime getOrder_date() {
		return order_date;
	}

	public void setOrder_date(LocalDateTime order_date) {
		this.order_date = order_date;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public int getDelivery_driver_id() {
		return delivery_driver_id;
	}

	public void setDelivery_driver_id(int delivery_driver_id) {
		this.delivery_driver_id = delivery_driver_id;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	@Override
	public String toString() {
		return "OrdersDto [order_id=" + order_id + ", order_date=" + order_date + ", customer_id=" + customer_id
				+ ", restaurant_id=" + restaurant_id + ", delivery_driver_id=" + delivery_driver_id + ", order_status="
				+ order_status + ", driver_id=" + driver_id + "]";
	}
    
    
    
    
}
