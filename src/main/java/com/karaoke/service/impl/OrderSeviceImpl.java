package com.karaoke.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karaoke.bo.TotalMoneyInMonth;
import com.karaoke.dao.OrderDao;
import com.karaoke.model.Order;
import com.karaoke.service.OrderService;

@Service
public class OrderSeviceImpl implements OrderService{
	
	@Autowired
	OrderDao orderDao;

	@Override
	public List<Order> getAllOrder() {
		List<Order> list =  orderDao.findAll();
		list.forEach(item ->{
			item.getRoom().setOrders(null);
			item.getOrderItems().forEach(item2 ->{
				item2.setOrder(null);
			});
		});
		return list;
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

	@SuppressWarnings("deprecation")
	@Override
	public TotalMoneyInMonth getTotalMonth(int month) {
		List<Order> list =  orderDao.findAll();
		TotalMoneyInMonth totalInMonth = new TotalMoneyInMonth();
		//Order or = list.stream().filter(item-> (8 == item.getStart().getHours())).findAny().orElse(null);
		Double total = 0.0;
		for(Order or : list) {
			if(or.getStart().getMonth() + 1 == 10) {
				total += or.getTotalPrice();
			}
		}
		
		totalInMonth.setMonth(month);
		
		totalInMonth.setTotal(total);
		
		totalInMonth.setAverage(total / list.size());
		
		return totalInMonth;
	}

	@Override
	public String Calculator() {
		return null;
	}

}
