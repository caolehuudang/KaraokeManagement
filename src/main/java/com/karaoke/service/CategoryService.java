package com.karaoke.service;

import java.util.List;

import com.karaoke.model.Category;

public interface CategoryService {

	Category findCategoryById(Long id);
	
	Category addNewCategory(Category category);
	
	Category updateCategory(Category category);
	
	List<Category> getAllCategory();
	
}
