package com.karaoke.service;

import java.util.List;

import com.karaoke.model.Vip;

public interface VipService {

	Vip findById(Long id);
	
	List<Vip> getAllVip();
	
	Vip updateVip(Vip vip);
	
	Vip addNewVip(Vip vip);
}
