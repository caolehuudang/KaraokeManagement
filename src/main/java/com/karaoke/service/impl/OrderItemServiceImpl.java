package com.karaoke.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karaoke.dao.OrderItemDao;
import com.karaoke.model.OrderItem;
import com.karaoke.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService{

	@Autowired
	OrderItemDao orderItemDao;
	
	@Override
	public List<OrderItem> getAllOrderItem() {
		return orderItemDao.findAll();
	}

	@Override
	public OrderItem addNewOrderItem(OrderItem orderItem) {
		return orderItemDao.save(orderItem);
	}

	@Override
	public OrderItem updateOrderItem(OrderItem orderItem) {
		OrderItem orderItemOld = orderItemDao.findById(orderItem.getId()).get();
		
		orderItemOld.setItem(orderItem.getItem());
		orderItemOld.setOrder(orderItem.getOrder());
		orderItemDao.save(orderItemOld);
		
		return orderItemOld;
	}

	
	
}
