package com.karaoke.service;

import java.util.List;

import com.karaoke.model.Room;

public interface RoomService {

	Room findRoomById(Long id);
	
	List<Room> getAllRoom();
	
	Room addNewRoom(Room room);
	
	Room updateRoom(Room room);
	
	Boolean changeStatusRoom(Long id);
	
}
