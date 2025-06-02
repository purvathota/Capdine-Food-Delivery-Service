package com.example.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.product.entity.OtpVerify;

import jakarta.transaction.Transactional;

@Repository
public interface OtpVerifyRepository extends JpaRepository<OtpVerify, Integer> {
	
	@Query("SELECT o.otp FROM OtpVerify o WHERE o.email_id = :email_id AND o.otp = :otp")
    String findByEmailIdAndOtp(@Param("email_id") String email_id, @Param("otp") String otp);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM OtpVerify o WHERE o.email_id = :email_id")
	void deleteByEmail(@Param("email_id") String email_id);
}
