package com.example.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "otpverify")
public class OtpVerify {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", insertable = false)
	private int id;
	
	@Column(name="email_id")
	private String email_id;
	
	@Column(name="otp")
	private String otp;
	
	public OtpVerify() {}

	public OtpVerify(String email_id, String otp) {
		super();
		
		this.email_id = email_id;
		this.otp = otp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
}
