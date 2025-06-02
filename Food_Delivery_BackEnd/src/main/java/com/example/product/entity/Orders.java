package com.example.product.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@Column(name = "order_id")
	private String order_id;

	@Column(name = "order_date")
	private LocalDateTime order_date;

	@Column(name = "customer_id", insertable = false, updatable = false)
	private int customer_id;

	@Column(name = "restaurant_id", insertable = false, updatable = false)
	private int restaurant_id;
	
	@Column(name = "order_status")
	private String order_status;

	@OneToMany(mappedBy = "orders", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<OrdersCoupons> ordercoupons;

	@OneToMany(mappedBy = "orders", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<OrderItems> orderitems;

	@OneToMany(mappedBy = "orders", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Ratings> ratings;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	private Customers customers;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "driver_id")
	@JsonIgnore
	private DeliveryDrivers deliverydrivers;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "restaurant_id")
	@JsonIgnore
	private Restaurants restaurants;

	public Orders() {}

	public Orders(String order_id, LocalDateTime order_date, int customer_id, int restaurant_id,
			String order_status, List<OrdersCoupons> ordercoupons, List<OrderItems> orderitems, List<Ratings> ratings,
			Customers customers, DeliveryDrivers deliverydrivers, Restaurants restaurants) {
		super();
		this.order_id = order_id;
		this.order_date = order_date;
		this.customer_id = customer_id;
		this.restaurant_id = restaurant_id;
		this.order_status = order_status;
		this.ordercoupons = ordercoupons;
		this.orderitems = orderitems;
		this.ratings = ratings;
		this.customers = customers;
		this.deliverydrivers = deliverydrivers;
		this.restaurants = restaurants;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
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

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public List<OrdersCoupons> getOrdercoupons() {
		return ordercoupons;
	}

	public void setOrdercoupons(List<OrdersCoupons> ordercoupons) {
		this.ordercoupons = ordercoupons;
	}

	public List<OrderItems> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(List<OrderItems> orderitems) {
		this.orderitems = orderitems;
	}

	public List<Ratings> getRatings() {
		return ratings;
	}

	public void setRatings(List<Ratings> ratings) {
		this.ratings = ratings;
	}

	public Customers getCustomers() {
		return customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	public DeliveryDrivers getDeliverydrivers() {
		return deliverydrivers;
	}

	public void setDeliverydrivers(DeliveryDrivers deliverydrivers) {
		this.deliverydrivers = deliverydrivers;
	}

	public Restaurants getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Restaurants restaurants) {
		this.restaurants = restaurants;
	}

	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", order_date=" + order_date + ", customer_id=" + customer_id
				+ ", restaurant_id=" + restaurant_id + ", order_status="
				+ order_status + ", ordercoupons=" + ordercoupons + ", orderitems=" + orderitems + ", ratings="
				+ ratings + ", customers=" + customers + ", deliverydrivers=" + deliverydrivers + ", restaurants="
				+ restaurants + "]";
	}
}