package com.karaoke.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.karaoke.model.Order;
import com.karaoke.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@GetMapping("/getAllOrder")
	public List<Order> getAllOrder(){
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
	
	
}
