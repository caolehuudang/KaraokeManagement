package com.karaoke.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karaoke.bo.TotalMoneyInMonth;
import com.karaoke.common.Contants;
import com.karaoke.dao.OrderDao;
import com.karaoke.dao.UserDao;
import com.karaoke.dao.VipDao;
import com.karaoke.model.Order;
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

}
