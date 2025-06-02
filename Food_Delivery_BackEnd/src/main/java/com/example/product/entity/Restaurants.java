
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

import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;

@Entity

@Table(name = "restaurants")

public class Restaurants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "restaurant_id")
	private int restaurant_id;

	@Column(name = "restaurant_name", length = 255)
	private String restaurant_name;

	@Column(name = "restaurant_address", length = 255)
	private String restaurant_address;

	@Column(name = "restaurant_phone", length = 20)
	private String restaurant_phone;

	@OneToMany(mappedBy = "restaurants", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Orders> orders;

	@OneToMany(mappedBy = "restaurants", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<MenuItems> menuitems;

	@OneToMany(mappedBy = "restaurants", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Ratings> ratings;

	@OneToMany(mappedBy = "restaurants", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<DeliveryAddresses> deliveryaddresses;

	public Restaurants() {
	}

	public Restaurants(int restaurant_id, String restaurant_name, String restaurant_address, String restaurant_phone,

			List<Orders> orders, List<MenuItems> menuitems, List<Ratings> ratings,

			List<DeliveryAddresses> deliveryaddresses) {

		super();

		this.restaurant_id = restaurant_id;

		this.restaurant_name = restaurant_name;

		this.restaurant_address = restaurant_address;

		this.restaurant_phone = restaurant_phone;

		this.orders = orders;

		this.menuitems = menuitems;

		this.ratings = ratings;

		this.deliveryaddresses = deliveryaddresses;

	}

	public int getRestaurant_id() {

		return restaurant_id;

	}

	public void setRestaurant_id(int restaurant_id) {

		this.restaurant_id = restaurant_id;

	}

	public String getRestaurant_name() {

		return restaurant_name;

	}

	public void setRestaurant_name(String restaurant_name) {

		this.restaurant_name = restaurant_name;

	}

	public String getRestaurant_address() {

		return restaurant_address;

	}

	public void setRestaurant_address(String restaurant_address) {

		this.restaurant_address = restaurant_address;

	}

	public String getRestaurant_phone() {

		return restaurant_phone;

	}

	public void setRestaurant_phone(String restaurant_phone) {

		this.restaurant_phone = restaurant_phone;

	}

	public List<Orders> getOrders() {

		return orders;

	}

	public void setOrders(List<Orders> orders) {

		this.orders = orders;

	}

	public List<MenuItems> getMenuitems() {

		return menuitems;

	}

	public void setMenuitems(List<MenuItems> menuitems) {

		this.menuitems = menuitems;

	}

	public List<Ratings> getRatings() {

		return ratings;

	}

	public void setRatings(List<Ratings> ratings) {

		this.ratings = ratings;

	}

	public List<DeliveryAddresses> getDeliveryaddresses() {

		return deliveryaddresses;

	}

	public void setDeliveryaddresses(List<DeliveryAddresses> deliveryaddresses) {

		this.deliveryaddresses = deliveryaddresses;

	}

	@Override

	public String toString() {

		return "Restaurants [restaurant_id=" + restaurant_id + ", restaurant_name=" + restaurant_name

				+ ", restaurant_address=" + restaurant_address + ", restaurant_phone=" + restaurant_phone + ", orders="

				+ orders + ", menuitems=" + menuitems + ", ratings=" + ratings

				+ ", deliveryaddresses=" + deliveryaddresses + "]";

	}

}