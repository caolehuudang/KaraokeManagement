package com.karaoke.service;

import java.util.Date;
import java.util.List;

public interface StatisticService {
	
	List<String> getTotalPriceByDate(Date date);
	
	List<String> getTotalPriceByMonth(Date month);

}
