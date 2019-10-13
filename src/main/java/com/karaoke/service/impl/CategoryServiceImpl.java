package com.karaoke.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karaoke.dao.CategoryDao;
import com.karaoke.model.Category;
import com.karaoke.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public Category findCategoryById(Long id) {
		return categoryDao.findById(id).get();
	}

	@Override
	public Category addNewCategory(Category category) {
		return categoryDao.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		Category categotyOld = findCategoryById(category.getId());
		
		categotyOld.setName(category.getName());
		categotyOld.setStatus(category.getStatus());
		
		categoryDao.save(categotyOld);
		
		return categotyOld;
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryDao.findAll();
	}

}
