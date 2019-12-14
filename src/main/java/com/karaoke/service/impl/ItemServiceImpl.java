package com.karaoke.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
		item.setName(item.getName().strip());
		return itemDao.save(item);
	}

	@Override
	public Item updateItem(Item item) {
		Item itemOld = findById(item.getId());
		
		itemOld.setName(item.getName().strip());
		itemOld.setPrice(item.getPrice());
		itemOld.setCategory(item.getCategory());
		itemOld.setStatus(item.getStatus());
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

	@Override
	public Boolean isDuplicateName(Item item) {
		if(item.getId() == null) {
			Item itemDup = itemDao.findItemByName(item.getName().strip());
			return itemDup != null;
		}else {
			List<Item> list = itemDao.findItemDuplicate(item.getId(), item.getName().strip());
			return list.size() > 0;
		}
		
	}

	@Override
	public Item updateImageItem(MultipartFile file, Long id) throws IOException {		
		
		Item itemOld = itemDao.findById(id).get();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(file.getBytes(), false)));
		
		itemOld.setImage(sb.toString());
        
        itemDao.save(itemOld);
        
		return itemOld;
	}
	
}
