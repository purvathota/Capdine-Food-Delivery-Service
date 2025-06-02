package com.example.product.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.product.entity.DeliveryDrivers;
import com.example.product.entity.Orders;

@Repository
public interface DeliveryDriversRepository extends JpaRepository<DeliveryDrivers, Integer>{

	@Query("SELECT th FROM DeliveryDrivers u JOIN u.orders th WHERE u.driver_id= :driver_id")
	List<Orders> findOrdersByOrderId(Integer driver_id);

}
