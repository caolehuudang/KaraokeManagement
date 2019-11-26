package com.karaoke.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.karaoke.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
	
	User findUserByPhone(String phone);
	
	@Query("SELECT u FROM User u WHERE u.id <> :id AND u.phone =:phone")
	User findUserDuplicatePhone(@Param("id") Long id ,@Param("phone") String phone);
	
	@Query("SELECT u FROM User u WHERE u.fullName LIKE %:txtSearch% OR u.username LIKE %:txtSearch%")
	List<User> search(@Param("txtSearch") String txtSearch);
	
}