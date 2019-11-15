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
	public ResponseMessage<Category> addNewCategory(@RequestBody Category category) {
		if(categoryService.isDuplicateName(category)) {
			return new ResponseMessage<Category>(null, Contants.DUPLICATE_NAME);
		}else {
			Category cate = categoryService.addNewCategory(category);
			return new ResponseMessage<Category>(cate, Contants.SUCCESSFULLY);
		}
		
	}
	
	@PostMapping(value = "/updateCategory", produces = "application/json; charset=UTF-8")
	public ResponseMessage<Category> updateCategory(@RequestBody Category category) {
		if(categoryService.isDuplicateName(category)) {
			return new ResponseMessage<Category>(null, Contants.DUPLICATE_NAME);
		}else {
			Category cate = categoryService.updateCategory(category);
			return new ResponseMessage<Category>(cate, Contants.SUCCESSFULLY);
		}
	}
	
	@PostMapping(value = "/updateImageCategory", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Category updateImageVip(@RequestParam MultipartFile file, @RequestParam(value = "id") Long id) throws IOException {
        return categoryService.updateImageCategory(file, id); 
    }
	
}
