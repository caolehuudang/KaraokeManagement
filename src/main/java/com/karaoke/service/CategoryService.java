package com.karaoke.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.karaoke.model.Category;

public interface CategoryService {

	Category findCategoryById(Long id);
	
	Category addNewCategory(Category category);
	
	Category updateCategory(Category category);
	
	List<Category> getAllCategory();
	
	Boolean isDuplicateName(Category category);
	
	Category updateImageCategory(MultipartFile file, Long id) throws IOException;
	
}
