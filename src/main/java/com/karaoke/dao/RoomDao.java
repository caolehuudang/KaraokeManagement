package com.karaoke.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.karaoke.model.Room;

@Repository
public interface RoomDao extends JpaRepository<Room, Long>{

	Room findRoomByName(String name);
	
	@Query("SELECT r FROM Room r WHERE r.id <> :id AND r.name =:name")
	List<Room> findRoomDuplicate(@Param("id") Long id ,@Param("name") String name);
	
}
