package com.example.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "deliveryaddresses")
public class DeliveryAddresses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int address_id;

    @Column(name = "customer_id", insertable = false, updatable = false)
    private int customer_id;

    @Column(name = "address_line1", length = 255)
    private String address_line1;

    @Column(name = "address_line2", length = 255)
    private String address_line2;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "state", length = 100)
    private String state;

    @Column(name = "postal_code", length = 20)
    private String postal_code;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinColumn(name="customer_id")
	@JsonIgnore
	private Customers customers;
    
    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinColumn(name="restaurant_id")
	@JsonIgnore
	private Restaurants restaurants;
    
    public DeliveryAddresses(){}

	public DeliveryAddresses(int address_id, int customer_id, String address_line1, String address_line2, String city,
			String state, String postal_code, Customers customers, Restaurants restaurants) {
		super();
		this.address_id = address_id;
		this.customer_id = customer_id;
		this.address_line1 = address_line1;
		this.address_line2 = address_line2;
		this.city = city;
		this.state = state;
		this.postal_code = postal_code;
		this.customers = customers;
		this.restaurants = restaurants;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getAddress_line1() {
		return address_line1;
	}

	public void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}

	public String getAddress_line2() {
		return address_line2;
	}

	public void setAddress_line2(String address_line2) {
		this.address_line2 = address_line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public Customers getCustomers() {
		return customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	public Restaurants getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Restaurants restaurants) {
		this.restaurants = restaurants;
	}

	@Override
	public String toString() {
		return "DeliveryAddresses [address_id=" + address_id + ", customer_id=" + customer_id + ", address_line1="
				+ address_line1 + ", address_line2=" + address_line2 + ", city=" + city + ", state=" + state
				+ ", postal_code=" + postal_code + ", customers=" + customers + ", restaurants=" + restaurants + "]";
	}
}