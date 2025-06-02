package com.example.product.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "deliverydrivers")
public class DeliveryDrivers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driver_id;
    
    @Column(name="driver_name")
    private String driver_name;
    
    @Column(name="driver_phone")
    private String driver_phone;
    
    @Column(name="driver_vehicle")
    private String driver_vehicle;
    
    @Column(name="order_id")
    @JoinColumn(name="order_id")
    private String order_id;
    
    @OneToMany(mappedBy = "deliverydrivers", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Orders> orders;
    
    @Column(name="driver_available")
    private boolean available;
    
    public DeliveryDrivers(){}

	public DeliveryDrivers(int driver_id, String driver_name, String driver_phone, String driver_vehicle, String order_id, List<Orders> orders, boolean available) {
		super();
		this.driver_id = driver_id;
		this.driver_name = driver_name;
		this.driver_phone = driver_phone;
		this.driver_vehicle = driver_vehicle;
		this.order_id = order_id;
		this.orders = orders;
		this.available = available;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}

	public String getDriver_phone() {
		return driver_phone;
	}

	public void setDriver_phone(String driver_phone) {
		this.driver_phone = driver_phone;
	}

	public String getDriver_vehicle() {
		return driver_vehicle;
	}

	public void setDriver_vehicle(String driver_vehicle) {
		this.driver_vehicle = driver_vehicle;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "DeliveryDrivers [driver_id=" + driver_id + ", driver_name=" + driver_name + ", driver_phone="
				+ driver_phone + ", driver_vehicle=" + driver_vehicle
				+ ", order_id=" + order_id + ", orders=" + orders + ", available=" + available + "]";
	}
}