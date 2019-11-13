package com.karaoke.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.karaoke.common.Contants;
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
		vipOld.setStatus(vip.getStatus());
		
		vipDao.save(vipOld);
		return vipOld;
	}

	@Override
	public Vip findById(Long id) {
		return vipDao.findById(id).get();
	}

	@Override
	public Vip addNewVip(Vip vip) {
		vip.setStatus(Contants.ACTIVE);
		return vipDao.save(vip);
	}

	@Override
	public Vip updateImageVip(MultipartFile file, Long id) throws IOException {
		
		Vip vipOld = vipDao.findById(id).get();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(file.getBytes(), false)));
		
		vipOld.setImage(sb.toString());
        
        vipDao.save(vipOld);
        
		return vipOld;
	}

}
