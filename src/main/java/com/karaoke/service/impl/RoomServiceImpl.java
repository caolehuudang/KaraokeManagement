package com.karaoke.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.karaoke.common.Contants;
import com.karaoke.dao.RoomDao;
import com.karaoke.model.Room;
import com.karaoke.model.RoomImage;
import com.karaoke.service.RoomService;

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
		
		List<Room> list = roomDao.findAll();
		
		if(list.size() > 0) {
			list.forEach(item ->{
				item.getRoomImages().forEach(item2 ->{
					item2.setRoom(null); 
				});
			});
		}
		return list;
	}

	@Override
	public Room addNewRoom(Room room) {
		room.setName(room.getName().strip());
		return roomDao.save(room);
	}

	@Override
	public Room updateRoom(Room room) {
		Room roomOld = findRoomById(room.getId());
		roomOld.setCapacity(room.getCapacity());
		roomOld.setName(room.getName().strip());
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

	@Override
	public Boolean isDuplicateName(Room room) {
		if(room.getId() == null) {
			Room roomm = roomDao.findRoomByName(room.getName().strip());
			return roomm != null;
		}else {
			List<Room> list = roomDao.findRoomDuplicate(room.getId(), room.getName().strip());
			return list.size() > 0;
		}
	}

	@Override
	public Room uploadImageRoom(MultipartFile[] files, Long id) throws IOException {
		
		Room roomOld = roomDao.findById(id).get();
		
		List<RoomImage> roomImageOld = new ArrayList<RoomImage>();
		
		List<RoomImage> roomImageNew = new ArrayList<RoomImage>();
		
		if(roomOld.getRoomImages().size() > 0) {
			roomOld.getRoomImages().forEach(item ->{
				item.setRoom(null);
				roomImageOld.add(item);
			});
		}
		
		roomOld.getRoomImages().removeAll(roomImageOld);
		
		
	   for(MultipartFile uploadedFile : files) {
		   RoomImage ri = new RoomImage();
		   StringBuilder sb = new StringBuilder();
		   
		   sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(uploadedFile.getBytes(), false)));
		   
		   ri.setRoom(roomOld);
		   ri.setImage(sb.toString());
		   
		   roomImageNew.add(ri);
       }
	   
	   roomOld.getRoomImages().addAll(roomImageNew);
	   
	   roomDao.save(roomOld);
		
	   return roomOld;
	}

}
