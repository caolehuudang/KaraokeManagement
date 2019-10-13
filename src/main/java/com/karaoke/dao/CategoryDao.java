package com.karaoke.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karaoke.model.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {

}
