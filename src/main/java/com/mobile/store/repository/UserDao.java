package com.mobile.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mobile.store.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
	
	User findByUserName(String username);
	
	User findUserByPhoneNumber(String phone);
	
	@Query("SELECT u FROM User u WHERE u.id <> :id AND u.phoneNumber =:phone")
	User findUserDuplicatePhone(@Param("id") Long id ,@Param("phone") String phone);
	
}