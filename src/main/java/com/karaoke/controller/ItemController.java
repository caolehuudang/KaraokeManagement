package com.karaoke.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
