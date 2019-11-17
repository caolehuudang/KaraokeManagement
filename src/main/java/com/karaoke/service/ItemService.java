package com.karaoke.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.karaoke.model.Item;

public interface ItemService {

	Item findById(Long id);
	
	List<Item> getAllItem();
	
	Item addNewItem(Item item);
	
	Item updateItem(Item item);
	
	Boolean changeStatusItem(Long id);
	
	Boolean isDuplicateName(Item item);
	
	Item updateImageItem(MultipartFile file, Long id) throws IOException;
	
	
}
