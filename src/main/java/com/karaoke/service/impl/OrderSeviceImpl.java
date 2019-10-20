package com.karaoke.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karaoke.bo.TotalMoneyInMonth;
import com.karaoke.common.Contants;
import com.karaoke.dao.OrderDao;
import com.karaoke.dao.UserDao;
import com.karaoke.model.Order;
import com.karaoke.model.User;
import com.karaoke.service.OrderService;

@Service
public class OrderSeviceImpl implements OrderService{
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	UserDao userDao;

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

	@Override
	public Order pay(Long id) {
		
		Order order = orderDao.findById(id).get();
		
		order.getOrderItems().forEach(item ->{
			item.setOrder(null);
		});
		
		List<Order> listOrder = new ArrayList<Order>();
		
		if(order.getUser().getId() != null) {
			
			User user = userDao.findById(order.getUser().getId()).get();
			
			//order1 = getOrderByUserIdAndStatus(order.getUser().getId(), Contants.ACTIVE);
			
			listOrder = orderDao.getAllOrderByUserId(user.getId());
			
		}
		
//		Order orderOld = orderDao.findById(order.getId()).get();

		
		return listOrder.get(0);
	}

	@Override
	public Order getOrderByIdAndStatus(Long id, String status) {
		return orderDao.getOrderByIdAndStatus(id, status);
	}

	@Override
	public Order getOrderByUserIdAndStatus(Long id, String status) {
		return orderDao.getOrderByUserIdAndStatus(id, status);
	}

	@Override
	public Double getCalculatorTotalUser(List<Order> order) {
		return null;
	}

	@Override
	public List<Order> getAllOrderByUserId(Long id) {
		return orderDao.getAllOrderByUserId(id);
	}

}
