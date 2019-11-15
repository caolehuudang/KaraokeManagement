package com.karaoke.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
		category.setName(category.getName().strip());
		return categoryDao.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		Category categotyOld = findCategoryById(category.getId());
		
		categotyOld.setName(category.getName().strip());
		categotyOld.setStatus(category.getStatus());
		
		categoryDao.save(categotyOld);
		
		return categotyOld;
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryDao.findAll();
	}

	@Override
	public Boolean isDuplicateName(Category category) {
		if(category.getId() == null) {
			Category cate = categoryDao.findCategoryByName(category.getName().strip());
			return cate != null;
		}else {
			List<Category> list = categoryDao.findCategoryDuplicate(category.getId(), category.getName().strip());
			return list.size() > 0;
		}
	}
	
	@Override
	public Category updateImageCategory(MultipartFile file, Long id) throws IOException {
		
		Category categoryOld = categoryDao.findById(id).get();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(file.getBytes(), false)));
		
		categoryOld.setImage(sb.toString());
        
        categoryDao.save(categoryOld);
        
		return categoryOld;
	}
}
