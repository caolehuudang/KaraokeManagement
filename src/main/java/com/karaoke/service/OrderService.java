package com.karaoke.service;

import java.util.List;

import com.karaoke.bo.TotalMoneyInMonth;
import com.karaoke.model.Order;

public interface OrderService {

	List<Order> getAllOrder();
	
	Order getOrderById(Long id);
	
	Order getOrderByIdAndStatus(Long id, String status);
	
	Order getOrderByUserIdAndStatus(Long id, String status);
	
	List<Order> getOrderByUserId(Long id);
	
	Order addNewOrder(Order order);
	
	Order updateOrder(Order order);
	
	List<Order> getOrderByName(String name);
	
	TotalMoneyInMonth getTotalMonth(int month);
	
	Order pay(Order order);
	
	List<Order> getAllOrderByUserId(Long id);
	
	Double getCalculatorTotalUser(List<Order> order);
	
	void upLevelCustomer(Long id);
	
	Order getOrderByRoom(Long id , String status);
	
	Order addNewOrderItem(Order order);
	
	Order payment(Order order);
	
	List<String> getTotalPriceYear(int year);
	
}
