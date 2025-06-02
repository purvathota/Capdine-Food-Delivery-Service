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
@Table(name = "orderitems")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private int order_item_id;

    @Column(name = "order_id", insertable = false, updatable = false)
    private String order_id;

    @Column(name = "item_id", insertable = false, updatable = false)
    private int item_id;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinColumn(name="order_id")
	@JsonIgnore
	private Orders orders;
    
    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JoinColumn(name="item_id")
	@JsonIgnore
	private MenuItems menuitems;

    public OrderItems(){}

	public OrderItems(int order_item_id, String order_id, int item_id, Integer quantity, Orders orders,
			MenuItems menuitems) {
		super();
		this.order_item_id = order_item_id;
		this.order_id = order_id;
		this.item_id = item_id;
		this.quantity = quantity;
		this.orders = orders;
		this.menuitems = menuitems;
	}

	public int getOrder_item_id() {
		return order_item_id;
	}

	public void setOrder_item_id(int order_item_id) {
		this.order_item_id = order_item_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public MenuItems getMenuitems() {
		return menuitems;
	}

	public void setMenuitems(MenuItems menuitems) {
		this.menuitems = menuitems;
	}

	@Override
	public String toString() {
		return "OrderItems [order_item_id=" + order_item_id + ", order_id=" + order_id + ", item_id=" + item_id
				+ ", quantity=" + quantity + ", orders=" + orders + ", menuitems=" + menuitems + "]";
	}
}