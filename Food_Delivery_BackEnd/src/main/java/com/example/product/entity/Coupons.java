package com.example.product.entity;
import java.math.BigDecimal;
import java.util.Date;
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
@Table(name = "coupons")
public class Coupons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coupon_id;

    @Column(name="coupon_code" ,length = 50)
    private String coupon_code;
    
    @Column(name="discount_amount")
    private BigDecimal discount_amount;

    @Column(name="expiry_date")
    private Date expiry_date;
    
    @OneToMany(mappedBy = "coupons", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JsonIgnore
	private List<OrdersCoupons> ordercoupons;
    
    public Coupons(){}

	public Coupons(int coupon_id, String coupon_code, BigDecimal discount_amount, Date expiry_date,
			List<OrdersCoupons> ordercoupons) {
		super();
		this.coupon_id = coupon_id;
		this.coupon_code = coupon_code;
		this.discount_amount = discount_amount;
		this.expiry_date = expiry_date;
		this.ordercoupons = ordercoupons;
	}

	public int getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}

	public String getCoupon_code() {
		return coupon_code;
	}

	public void setCoupon_code(String coupon_code) {
		this.coupon_code = coupon_code;
	}

	public BigDecimal getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(BigDecimal discount_amount) {
		this.discount_amount = discount_amount;
	}

	public Date getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

	public List<OrdersCoupons> getOrdercoupons() {
		return ordercoupons;
	}

	public void setOrdercoupons(List<OrdersCoupons> ordercoupons) {
		this.ordercoupons = ordercoupons;
	}

	@Override
	public String toString() {
		return "Coupons [coupon_id=" + coupon_id + ", coupon_code=" + coupon_code + ", discount_amount="
				+ discount_amount + ", expiry_date=" + expiry_date + ", ordercoupons=" + ordercoupons + "]";
	}

	
    
    
}
