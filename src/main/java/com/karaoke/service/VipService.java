package com.karaoke.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.karaoke.model.Vip;

public interface VipService {

	Vip findById(Long id);
	
	List<Vip> getAllVip();
	
	Vip updateVip(Vip vip);
	
	Vip addNewVip(Vip vip);
	
	Vip updateImageVip(MultipartFile file, Long id) throws IOException;
}
