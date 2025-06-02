package com.example.product.entity;

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
@Table(name = "menuitems")
public class MenuItems {

	@Id
	@Column(name = "ITEM_ID")
	private int item_id;

	@Column(name = "ITEM_NAME")
	private String item_name;

	@Column(name = "ITEM_DESCRIPTION")
	private String item_description;

	@Column(name = "ITEM_PRICE")
	private float item_price;

	@Column(name = "restaurant_id", insertable = false, updatable = false)
	private int restaurant_id;
	@OneToMany(mappedBy = "menuitems", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<OrderItems> orderitems;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "restaurant_id")
	@JsonIgnore
	private Restaurants restaurants;
	
	public MenuItems() {}

	public MenuItems(int item_id, String item_name, String item_description, float item_price) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_description = item_description;
		this.item_price = item_price;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	public float getItem_price() {
		return item_price;
	}

	public void setItem_price(float item_price) {
		this.item_price = item_price;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public List<OrderItems> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(List<OrderItems> orderitems) {
		this.orderitems = orderitems;
	}

	public Restaurants getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Restaurants restaurants) {
		this.restaurants = restaurants;
	}

	@Override
	public String toString() {
		return "MenuItems [item_id=" + item_id + ", item_name=" + item_name + ", item_description=" + item_description
				+ ", item_price=" + item_price + ", restaurant_id=" + restaurant_id + ", orderitems=" + orderitems
				+ ", restaurants=" + restaurants + "]";
	}
}