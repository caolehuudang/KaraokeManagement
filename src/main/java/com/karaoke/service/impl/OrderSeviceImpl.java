package com.karaoke.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karaoke.dao.OrderDao;
import com.karaoke.model.Order;
import com.karaoke.service.OrderService;

@Service
public class OrderSeviceImpl implements OrderService{
	
	@Autowired
	OrderDao orderDao;

	@Override
	public List<Order> getAllOrder() {
		return orderDao.findAll();
	}

	@Override
	public Order getOrderById(Long id) {
		return orderDao.findById(id).get();
	}

	@Override
	public Order addNewOrder(Order order) {
		return orderDao.save(order);
	}

	@Override
	public Order updateOrder(Order order) {
		Order orderOld = getOrderById(order.getId());
		
		orderOld.setStart(order.getStart());
		orderOld.setEnd(order.getEnd());
		orderOld.setName(order.getName());
		orderOld.setRoom(order.getRoom());
		orderOld.setUser(order.getUser());
		orderOld.setTotalPrice(order.getTotalPrice());
		
		orderDao.save(orderOld);
		
		return orderOld;
	}

	@Override
	public List<Order> getOrderByName(String name) {
		return orderDao.getOrderByName(name);
	}

	@Override
	public Double getTotalMonth() {
		return null;
	}

	@Override
	public String Calculator() {
		return null;
	}

}
