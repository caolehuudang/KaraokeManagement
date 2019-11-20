package com.karaoke.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.karaoke.bo.ResponseMessage;
import com.karaoke.common.Contants;
import com.karaoke.model.Room;
import com.karaoke.service.RoomService;

@RestController
public class RoomController {

	@Autowired
	RoomService roomService;
	
	@GetMapping("/getAllRoom")
	public List<Room> getAllRoom(){
		return roomService.getAllRoom();
	}
	
	
	@PostMapping(value = "/addNewRoom", produces = "application/json; charset=UTF-8")
	public ResponseMessage<Room> addNewRoom(@RequestBody Room room) {
		if(roomService.isDuplicateName(room)) {
			return new ResponseMessage<Room>(null, Contants.DUPLICATE_NAME);
		}else {
			Room roomm = roomService.addNewRoom(room);
			
			return new ResponseMessage<Room>(roomm, Contants.SUCCESSFULLY);
		}
	}
	
	@PostMapping(value = "/updateRoom", produces = "application/json; charset=UTF-8")
	public ResponseMessage<Room> updateRoom(@RequestBody Room room) { 
		if(roomService.isDuplicateName(room)) {
			return new ResponseMessage<Room>(null, Contants.DUPLICATE_NAME);
		}else {
			Room roomm = roomService.updateRoom(room);
			if(roomm.getRoomImages() != null) {
				roomm.getRoomImages().forEach(item ->{
					item.setRoom(null);
				});
			}
			return new ResponseMessage<Room>(roomm, Contants.SUCCESSFULLY);
		}
	}
	
   @PostMapping(value = "/uploadImageRoom", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public Room uploadFile(@RequestParam("files") MultipartFile[] files, @RequestParam(value = "id") Long id) throws IOException {
       	
        return roomService.uploadImageRoom(files, id);
    }
	
	@PostMapping(value = "/changeStatusRoom")
	public Boolean changeStatusRoom(@RequestParam(value = "id", required = true) Long id) {
		return roomService.changeStatusRoom(id);
	}
}
