package com.karaoke.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public Room addNewRoom(@RequestBody Room room) {
		return roomService.addNewRoom(room);
	}
	
	@PostMapping(value = "/updateRoom", produces = "application/json; charset=UTF-8")
	public Room updateRoom(@RequestBody Room room) {
		return roomService.updateRoom(room);
	}
	
	@PostMapping(value = "/changeStatusRoom")
	public Boolean changeStatusRoom(@RequestParam(value = "id", required = true) Long id) {
		return roomService.changeStatusRoom(id);
	}
}
