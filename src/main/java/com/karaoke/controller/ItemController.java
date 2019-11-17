package com.karaoke.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.karaoke.bo.ResponseMessage;
import com.karaoke.common.Contants;
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
	public ResponseMessage<Item> addNewItem(@RequestBody Item item) {
		if(itemService.isDuplicateName(item)) {
			return new ResponseMessage<Item>(null, Contants.DUPLICATE_NAME);
		}else {
			Item item1 = itemService.addNewItem(item);
			return new ResponseMessage<Item>(item1, Contants.SUCCESSFULLY);
		}
	}
	
	@PostMapping(value = "/updateItem",produces = "application/json; charset=UTF-8")
	public ResponseMessage<Item> updateItem(@RequestBody Item item) {
		if(itemService.isDuplicateName(item)) {
			return new ResponseMessage<Item>(null, Contants.DUPLICATE_NAME);
		}else {
			Item item1 = itemService.updateItem(item);
			return new ResponseMessage<Item>(item1, Contants.SUCCESSFULLY);
		}
	}
	
	@PostMapping(value = "/updateImageItem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Item updateImageItem(@RequestParam MultipartFile file, @RequestParam(value = "id") Long id) throws IOException {       
		return itemService.updateImageItem(file, id); 
    }
}
