package com.karaoke.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karaoke.model.Vip;
import com.karaoke.service.VipService;

@RestController
public class VipController {

	@Autowired
	VipService vipService;
	
	@GetMapping("/getAllVip")
	public List<Vip> getAllVip(){
		return vipService.getAllVip();
	}
	
	@PostMapping("/findVipById")
	public Vip findVipById(@RequestParam(value = "id", required = true) Long id) {
		return vipService.findById(id);
	}
	
	@PostMapping("/updateVip")
	public Vip updateVip(@RequestBody Vip vip) {
		return vipService.updateVip(vip);
	}
	
}
