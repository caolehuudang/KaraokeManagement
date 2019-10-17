package com.karaoke.service;

import java.util.List;

import com.karaoke.model.Order;

public interface OrderService {

	List<Order> getAllOrder();
	
	Order getOrderById(Long id);
	
	Order addNewOrder(Order order);
	
	Order updateOrder(Order order);
	
	List<Order> getOrderByName(String name);
	
	Double getTotalMonth();
	
	String Calculator();
	
}
