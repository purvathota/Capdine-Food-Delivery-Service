package com.example.product.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.product.entity.Customers;
 
@Repository
public interface CustomersRepository extends JpaRepository<Customers, Integer>{

	@Query("SELECT count(c) FROM Customers c WHERE c.customer_email = :customer_email")
    int findCustomerByEmailCount(@Param("customer_email") String customer_email);
	
	@Query("SELECT c FROM Customers c WHERE c.customer_email = :customer_email")
    Customers findCustomerByEmail(@Param("customer_email") String customer_email);
}
