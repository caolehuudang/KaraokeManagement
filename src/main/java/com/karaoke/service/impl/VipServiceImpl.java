package com.karaoke.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karaoke.dao.VipDao;
import com.karaoke.model.Vip;
import com.karaoke.service.VipService;

@Service
public class VipServiceImpl implements VipService{

	@Autowired
	VipDao vipDao;
	
	@Override
	public List<Vip> getAllVip() {
		return vipDao.findAll();
	}

	@Override
	public Vip updateVip(Vip vip) {
		Vip vipOld = findById(vip.getId());
		
		vipOld.setLevel(vip.getLevel());
		vipOld.setTotal(vip.getTotal());
		vipOld.setPercentDiscount(vip.getPercentDiscount());
		
		vipDao.save(vipOld);
		return vipOld;
	}

	@Override
	public Vip findById(Long id) {
		return vipDao.findById(id).get();
	}

}
