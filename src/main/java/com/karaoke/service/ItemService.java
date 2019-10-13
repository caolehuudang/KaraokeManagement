package com.karaoke.service;

import java.util.List;

import com.karaoke.model.Item;

public interface ItemService {

	Item findById(Long id);
	
	List<Item> getAllItem();
	
	Item addNewItem(Item item);
	
	Item updateItem(Item item);
	
	Boolean changeStatusItem(Long id);
}
