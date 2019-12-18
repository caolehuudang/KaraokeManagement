package com.karaoke.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.karaoke.bo.StatisticBO;
import com.karaoke.service.StatisticService;

@RestController
public class StatisticController {

	@Autowired  
	StatisticService statisticService;
	 
	@PostMapping(value = "/getTotalPriceByDate",produces = "application/json; charset=UTF-8")
	public List<String> getTotalPriceByDate(@RequestBody StatisticBO statistic){
		
		return statisticService.getTotalPriceByDate(statistic.getDate());
	}
}
