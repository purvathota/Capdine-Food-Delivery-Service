package com.example.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity

@Table(name = "ORDERSCOUPONS")

public class OrdersCoupons {

	@Id
	@Column(name = "coupon_id", insertable = false, updatable = false)
	private int coupon_id;
	
	@Column(name = "order_id", insertable = false, updatable = false)
	private String order_id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "coupon_id")
	@JsonIgnore
	private Coupons coupons;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	@JsonIgnore
	private Orders orders;

	public OrdersCoupons() {}

	public OrdersCoupons(String order_id, int coupon_id, Coupons coupons, Orders orders) {
		super();
		this.order_id = order_id;
		this.coupon_id = coupon_id;
		this.coupons = coupons;
		this.orders = orders;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public int getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}

	public Coupons getCoupons() {
		return coupons;
	}

	public void setCoupons(Coupons coupons) {
		this.coupons = coupons;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "OrdersCoupons [order_id=" + order_id + ", coupon_id=" + coupon_id + ", coupons=" + coupons + ", orders="
				+ orders + "]";
	}
}
