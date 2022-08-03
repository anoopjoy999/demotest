package com.example.demotest.repository;

import com.example.demotest.entity.NaceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface NaceRepository extends JpaRepository<NaceOrder, String> {
	
	@Query("SELECT u FROM NaceOrder u WHERE u.orderId = ?1")
	NaceOrder findByOrder(String order);

}