package com.karaoke.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karaoke.common.Contants;
import com.karaoke.dao.RoomDao;
import com.karaoke.model.Room;

@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	RoomDao roomDao;
	
	@Override
	public Room findRoomById(Long id) {
		return roomDao.findById(id).get();
	}

	@Override
	public List<Room> getAllRoom() {
		return roomDao.findAll();
	}

	@Override
	public Room addNewRoom(Room room) {
		return roomDao.save(room);
	}

	@Override
	public Room updateRoom(Room room) {
		Room roomOld = findRoomById(room.getId());
		roomOld.setCapacity(room.getCapacity());
		roomOld.setImage(room.getImage());
		roomOld.setNumber(room.getNumber());
		roomOld.setPrice(room.getPrice());
		roomOld.setStatus(room.getStatus());
		
		roomDao.save(roomOld);
		
		return roomOld;
	}

	@Override
	public Boolean changeStatusRoom(Long id) {
		Room roomOld = findRoomById(id);
		if(roomOld.getStatus().equals(Contants.ACTIVE)) {
			roomOld.setStatus(Contants.DE_ACTIVE);
			return true;
		}else {
			roomOld.setStatus(Contants.ACTIVE);
			return false;
		}
	}

}
