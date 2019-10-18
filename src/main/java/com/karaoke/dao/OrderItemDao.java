package com.karaoke.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karaoke.model.OrderItem;

@Repository
public interface OrderItemDao extends JpaRepository<OrderItem, Long>{

}
