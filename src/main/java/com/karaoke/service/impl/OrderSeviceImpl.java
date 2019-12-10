package com.karaoke.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karaoke.bo.TotalMoneyInMonth;
import com.karaoke.common.Contants;
import com.karaoke.dao.OrderDao;
import com.karaoke.dao.RoomDao;
import com.karaoke.dao.UserDao;
import com.karaoke.dao.VipDao;
import com.karaoke.model.Order;
import com.karaoke.model.OrderItem;
import com.karaoke.model.Room;
import com.karaoke.model.User;
import com.karaoke.model.Vip;
import com.karaoke.service.OrderService;

@Service
public class OrderSeviceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;

	@Autowired
	UserDao userDao;

	@Autowired
	VipDao vipDao;
	
	@Autowired
	RoomDao roomDao;

	@Override
	public List<Order> getAllOrder() {
		List<Order> list = orderDao.findAll();
		list.forEach(item -> {
			item.getRoom().setOrders(null);
			item.getOrderItems().forEach(item2 -> {
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
		Room room = roomDao.findById(order.getRoom().getId()).get();
		room.setStatus(Contants.DE_ACTIVE);
		roomDao.save(room);
		order.getRoom().setStatus(Contants.DE_ACTIVE); 
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
		List<Order> list = orderDao.findAll();
		TotalMoneyInMonth totalInMonth = new TotalMoneyInMonth();
		// Order or = list.stream().filter(item-> (8 ==
		// item.getStart().getHours())).findAny().orElse(null);
		Double total = 0.0;
		for (Order or : list) {
			if (or.getStart().getMonth() + 1 == 10) {
				total += or.getTotalPrice();
			}
		}

		totalInMonth.setMonth(month);

		totalInMonth.setTotal(total);

		totalInMonth.setAverage(total / list.size());

		return totalInMonth;
	}

	@Override
	public Order pay(Order or) {

		Order order = orderDao.findById(or.getId()).get();

		order.getOrderItems().forEach(item -> {
			item.setOrder(null);
		});

		if (order.getUser().getId() != null) {

			upLevelCustomer(order.getUser().getId());

		}
		
		order.setEnd(or.getEnd());
		order.setStatus(Contants.DE_ACTIVE);
		order.setTotalPrice(or.getTotalPrice());

		orderDao.save(order);
		return order;
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

	@Override
	public List<Order> getOrderByUserId(Long id) {
		return orderDao.getAllOrderByUserId(id);
	}

	@Override
	public void upLevelCustomer(Long id) {
		
		User user = userDao.findById(id).get();
		
		List<Order> payHistory = orderDao.getAllOrderByUserId(id);

		payHistory.forEach(item -> {
			item.getOrderItems().forEach(item2 -> {
				item2.setOrder(null);
			});
		});

		List<Order> listPaid = new ArrayList<Order>();

		payHistory.forEach(item -> {
			if (item.getStatus().equals(Contants.DE_ACTIVE)) {
				listPaid.add(item);
			}
		});

		Double totalMoney = 0.0;

		for (Order or : payHistory) {
			totalMoney += or.getTotalPrice();
		}

		List<Vip> listVip = vipDao.findAllByOrderByTotalAsc();

		int j = 0;

		for (int i = 0; i < listVip.size(); i++) {

			if (totalMoney.intValue() >= listVip.get(i).getTotal().intValue()) {
				j = i;
				if (i == listVip.size() - 1) {
					//msg = listVip.get(j).getLevel();
					user.setVip(listVip.get(j));
				}
				continue;
			} else {
				if (i < 1) {
					break;
				} else {
					//msg = listVip.get(j).getLevel();
					user.setVip(listVip.get(j));
				}
			}
		}

		userDao.save(user);
	}

	@Override
	public Order getOrderByRoom(Long id, String status) { 
		
		Order order = orderDao.getOrderByRoom(id, status);
		
		if (order != null) {
			
			order.setRoom(null);
			
			order.getOrderItems().forEach(item ->{
				item.setOrder(null);
			});
		}
		
		return order;
	}

	@Override
	public Order addNewOrderItem(Order order) {
		
		Order orderOld = orderDao.findById(order.getId()).get();
		
		List<OrderItem> listOrderItem = new ArrayList<OrderItem>();
		
		List<OrderItem> listRemove = new ArrayList<OrderItem>();
		
		orderOld.getOrderItems().forEach(item ->{
			item.setOrder(null);
			listRemove.add(item);
		});

		
		order.getOrderItems().forEach(item -> {
			item.setOrder(order);
			listOrderItem.add(item);
		});
		
		orderOld.getOrderItems().removeAll(listRemove);
		
		orderOld.getOrderItems().addAll(listOrderItem);
		
		Order o = orderDao.save(orderOld);
		
		o.setRoom(null);
		o.getOrderItems().forEach(item -> {
			item.setOrder(null);
			
		});

		return o;
	}

	@Override
	public Order payment(Order order) {
		
		Order orderOld = orderDao.findById(order.getId()).get();
		
		orderOld.setEnd(order.getEnd());
		orderOld.setTotalPrice(order.getTotalPrice());
		orderOld.setUser(order.getUser());
		orderOld.setName(order.getName());
		orderOld.setStatus(Contants.DE_ACTIVE);
		orderOld.setPhone(order.getPhone());
		
		Room room = roomDao.findById(order.getRoom().getId()).get();
		
		room.setStatus(Contants.ACTIVE);
		roomDao.save(room);
		
		User user = null;
		
		if(order.getUser() != null) {
			 user = userDao.findById(order.getUser().getId()).get();
		}
		
		
		List<Vip> listVip = vipDao.findAllByOrderByTotalAsc();
		
		if(null != user) {
			Double b = (user.getTotalPrice() != null ? Double.valueOf(user.getTotalPrice()) : 0.0) + order.getTotalPrice();
			
			user.setTotalPrice(b.toString());
			
			int j = 0;

			for (int i = 0; i < listVip.size(); i++) {

				if (b.intValue() >= listVip.get(i).getTotal().intValue()) {
					j = i;
					if (i == listVip.size() - 1) {
						user.setVip(listVip.get(j));
					}
					continue;
				} else {
					if (i < 1) {
						break;
					} else {
						user.setVip(listVip.get(j));
					}
				}
			}
			
			userDao.save(user);
		}
		Order o = orderDao.save(orderOld);
		
		o.getRoom().getRoomImages().forEach(item -> {
			item.setRoom(null);
		});
		
		o.getOrderItems().forEach(item -> {
			item.setOrder(null);
			
		});

		return o;
		
	}
	

}
