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
@Table(name = "ratings")
public class Ratings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private int rating_id;

    @Column(name = "order_id", insertable = false, updatable = false)
    private String order_id;

    @Column(name = "restaurant_id", insertable = false, updatable = false)
    private int restaurant_id;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "review", columnDefinition = "TEXT")
    private String review;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinColumn(name="order_id")
	@JsonIgnore
	private Orders orders;
    
    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinColumn(name="restaurant_id")
	@JsonIgnore
	private Restaurants restaurants;
    
    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinColumn(name="customer_id")
	@JsonIgnore
	private Customers customers;
    
    public Ratings(){}

	public Ratings(int rating_id, String order_id, int restaurant_id, Integer rating, String review, Orders orders,
			Restaurants restaurants, Customers customers) {
		super();
		this.rating_id = rating_id;
		this.order_id = order_id;
		this.restaurant_id = restaurant_id;
		this.rating = rating;
		this.review = review;
		this.orders = orders;
		this.restaurants = restaurants;
		this.customers = customers;
	}

	public int getRating_id() {
		return rating_id;
	}

	public void setRating_id(int rating_id) {
		this.rating_id = rating_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Restaurants getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Restaurants restaurants) {
		this.restaurants = restaurants;
	}

	public Customers getCustomers() {
		return customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "Ratings [rating_id=" + rating_id + ", order_id=" + order_id + ", restaurant_id=" + restaurant_id
				+ ", rating=" + rating + ", review=" + review + ", orders=" + orders + ", restaurants=" + restaurants
				+ ", customers=" + customers + "]";
	}
}