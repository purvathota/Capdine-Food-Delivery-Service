package com.example.product.entity;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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
@Table(name = "customers")
public class Customers {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customer_id;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must be less than 255 characters")
    @Column(name = "customer_name", length = 255)
    private String customer_name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(name = "customer_email", length = 255)
    private String customer_email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Invalid phone number")
    @Column(name = "customer_phone", length = 20)
    private String customer_phone;
    
    @OneToMany(mappedBy = "customers", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Orders> orders;
    
    @OneToMany(mappedBy = "customers", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JsonIgnore
	private List<DeliveryAddresses> deliveryaddresses;
    
    @OneToMany(mappedBy = "customers", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Ratings> ratings;
    
    public Customers(){}

	public Customers(int customer_id, String customer_name, String customer_email, String customer_phone,
			List<Orders> orders, List<DeliveryAddresses> deliveryaddresses, List<Ratings> ratings) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_email = customer_email;
		this.customer_phone = customer_phone;
		this.orders = orders;
		this.deliveryaddresses = deliveryaddresses;
		this.ratings = ratings;
		//this.restaurants = restaurants;
	}



	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public List<DeliveryAddresses> getDeliveryaddresses() {
		return deliveryaddresses;
	}

	public void setDeliveryaddresses(List<DeliveryAddresses> deliveryaddresses) {
		this.deliveryaddresses = deliveryaddresses;
	}

	public List<Ratings> getRatings() {
		return ratings;
	}

	public void setRatings(List<Ratings> ratings) {
		this.ratings = ratings;
	}

	@Override
	public String toString() {
		return "Customers [customer_id=" + customer_id + ", customer_name=" + customer_name + ", customer_email="
				+ customer_email + ", customer_phone=" + customer_phone + ", orders=" + orders + ", deliveryaddresses="
				+ deliveryaddresses + ", ratings=" + ratings + "]";
	}
}