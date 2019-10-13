package com.karaoke.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karaoke.common.Contants;
import com.karaoke.dao.ItemDao;
import com.karaoke.model.Item;
import com.karaoke.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	ItemDao itemDao;

	@Override
	public Item findById(Long id) {
		return itemDao.findById(id).get();
	}

	@Override
	public List<Item> getAllItem() {
		return itemDao.findAll();
	}

	@Override
	public Item addNewItem(Item item) {
		return itemDao.save(item);
	}

	@Override
	public Item updateItem(Item item) {
		Item itemOld = findById(item.getId());
		
		itemOld.setName(item.getName());
		itemOld.setPrice(item.getPrice());
		itemOld.setCategory(item.getCategory());
		
		itemDao.save(itemOld);
		
		return itemOld;
	}

	@Override
	public Boolean changeStatusItem(Long id) {
		Item itemOld = findById(id);
		if(itemOld.getStatus().equals(Contants.ACTIVE)) {
			itemOld.setStatus(Contants.DE_ACTIVE);
			itemDao.save(itemOld);
			return true;
		}else {
			itemOld.setStatus(Contants.ACTIVE);
			itemDao.save(itemOld);
			return false;
		}
	}
}
