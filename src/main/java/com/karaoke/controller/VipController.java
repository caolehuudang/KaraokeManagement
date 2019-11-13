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
	
	@PostMapping("/addNewVip")
	public Vip addNewVip(@RequestBody Vip vip) {
		return vipService.addNewVip(vip);
	}
	
	@PostMapping("/findVipById")
	public Vip findVipById(@RequestParam(value = "id", required = true) Long id) {
		return vipService.findById(id);
	}
	
	@PostMapping("/updateVip")
	public Vip updateVip(@RequestBody Vip vip) {
		return vipService.updateVip(vip);
	}
	
	@PostMapping(value = "/updateImageVip", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Vip updateImageVip(@RequestParam MultipartFile file, @RequestParam(value = "id") Long id) throws IOException {
        return vipService.updateImageVip(file, id); 
    }
	

}
