package com.karaoke.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karaoke.bo.TotalMoneyInMonth;
import com.karaoke.model.Order;
import com.karaoke.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@GetMapping("/getAllOrders")
	public List<Order> getAllOrders(){
		return orderService.getAllOrder();
	}
	
	@PostMapping(value = "/addNewOrder", produces = "application/json; charset=UTF-8")
	public Order addNewRoom(@RequestBody Order order) {
		return orderService.addNewOrder(order);
	}
	
	@PostMapping(value = "/updateOrder", produces = "application/json; charset=UTF-8")
	public Order updateRoom(@RequestBody Order order) {
		return orderService.updateOrder(order);
	}
	
	@PostMapping(value = "/getOrderByName", produces = "application/json; charset=UTF-8")
	public List<Order> getOrderByName(@RequestParam(value = "name", required = true) String name){
		return orderService.getOrderByName(name);
	}
	
	@GetMapping("/getTotalMonth")
	public TotalMoneyInMonth getTotalMonth() {
		return orderService.getTotalMonth(10);
	}
	
	@PostMapping(value = "/pay", produces = "application/json; charset=UTF-8")
	public Order pay(@RequestBody Order order) {
		return orderService.pay(order);
	}
	
	@PostMapping(value = "/getOrderByRoom", produces = "application/json; charset=UTF-8") 
	public Order getOrderByRoom(@RequestBody Order order) {
		 
		return orderService.getOrderByRoom(order.getRoom().getId(), order.getStatus());
	}
	
	@PostMapping(value = "/addNewOrderItem", produces = "application/json; charset=UTF-8") 
	public Order addNewOrderItem(@RequestBody Order order) {
		 
		return orderService.addNewOrderItem(order);
	}
	
	@PostMapping(value = "/payment", produces = "application/json; charset=UTF-8") 
	public Order payment(@RequestBody Order order) {
		 
		return orderService.payment(order);
	}
	
	@GetMapping(value = "/getTotalPriceYear", produces = "application/json; charset=UTF-8") 
	public List<String> getTotalPriceYear() {
		Date date = new Date();
		return orderService.getTotalPriceYear(date.getYear());
	}
	
}  
