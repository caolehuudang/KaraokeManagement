package com.karaoke.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.karaoke.model.Category;		

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {

	Category findCategoryByName(String name);
	
	@Query("SELECT c FROM Category c WHERE c.id <> :id AND c.name =:name")
	List<Category> findCategoryDuplicate(@Param("id") Long id ,@Param("name") String name);
}
