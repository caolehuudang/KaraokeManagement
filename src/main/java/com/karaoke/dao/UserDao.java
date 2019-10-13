package com.karaoke.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.karaoke.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
	
	User findByUsername(String username);
	
}