package com.karaoke.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.karaoke.model.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Long>{

	List<Order> getOrderByName(String name);
	
	Order getOrderByIdAndStatus(Long id, String status);
	
	Order getOrderByUserIdAndStatus(Long id, String status);
	
	List<Order> getAllOrderByUserId(Long id);
	
	@Query("SELECT order FROM Order order WHERE order.room.id =:id AND order.status =:status")
	Order getOrderByRoom(@Param("id") Long id ,@Param("status") String status);
	
	
}
