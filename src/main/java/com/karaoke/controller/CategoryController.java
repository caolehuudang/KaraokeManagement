package com.karaoke.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.karaoke.model.Category;
import com.karaoke.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/getAllCategory")
	public List<Category> getAllCategory(){
		return categoryService.getAllCategory();
	}
	
	@PostMapping(value = "/addNewCategory", produces = "application/json; charset=UTF-8")
	public Category addNewCategory(@RequestBody Category category) {
		return categoryService.addNewCategory(category);
	}
	
	@PostMapping(value = "/updateCategory", produces = "application/json; charset=UTF-8")
	public Category updateCategory(@RequestBody Category category) {
		return categoryService.updateCategory(category);
	}
	
}
