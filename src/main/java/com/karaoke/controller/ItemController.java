package com.karaoke.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.karaoke.model.Item;
import com.karaoke.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	ItemService itemService;
	
	@GetMapping("/getAllItem")
	public List<Item> getAllItem(){
		return itemService.getAllItem();
	}
	
	@PostMapping(value = "/addNewItem",produces = "application/json; charset=UTF-8")
	public Item addNewItem(@RequestBody Item item) {
		return itemService.addNewItem(item);
	}
	
	@PostMapping(value = "/updateItem",produces = "application/json; charset=UTF-8")
	public Item updateItem(@RequestBody Item item) {
		return itemService.updateItem(item);
	}
}
