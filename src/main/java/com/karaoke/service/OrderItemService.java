package com.karaoke.service;

import java.util.List;

import com.karaoke.model.OrderItem;

public interface OrderItemService {

	List<OrderItem> getAllOrderItem();
	
	OrderItem addNewOrderItem(OrderItem orderItem);
	
	OrderItem updateOrderItem(OrderItem orderItem);
}
