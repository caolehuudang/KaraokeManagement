package com.karaoke.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.karaoke.model.Room;

public interface RoomService {

	Room findRoomById(Long id);
	
	List<Room> getAllRoom();
	
	Room addNewRoom(Room room);
	
	Room updateRoom(Room room);
	
	Boolean isDuplicateName(Room room);
	
	Boolean changeStatusRoom(Long id);
	
	Room uploadImageRoom(MultipartFile[] files, Long id) throws IOException;
	
}
