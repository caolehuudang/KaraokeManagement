package com.karaoke.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karaoke.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{

}
