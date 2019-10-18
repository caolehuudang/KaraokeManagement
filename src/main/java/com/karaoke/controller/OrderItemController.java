package com.karaoke.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.karaoke.model.OrderItem;
import com.karaoke.service.OrderItemService;

@RestController
public class OrderItemController {

	@Autowired
	OrderItemService orderItemService;
	
	@GetMapping("/getAllOrderItem")
	public List<OrderItem> getAllOrderItem() {
		return orderItemService.getAllOrderItem();
	}
	
	@PostMapping(value = "/addNewOrderItem", produces = "application/json; charset=UTF-8")
	public OrderItem addNewOrderItem(@RequestBody OrderItem orderItem) {
		return orderItemService.addNewOrderItem(orderItem);
	}
	
	@PostMapping(value = "/updateOrderItem", produces = "application/json; charset=UTF-8")
	public OrderItem updateOrderItem(@RequestBody OrderItem orderItem) {
		return orderItemService.updateOrderItem(orderItem);
	}
	
}
