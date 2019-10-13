package com.karaoke.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karaoke.model.Room;

@Repository
public interface RoomDao extends JpaRepository<Room, Long>{

	
}
