package com.karaoke.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karaoke.dao.OrderDao;
import com.karaoke.model.Order;
import com.karaoke.service.StatisticService;

@Service
public class StatisticServiceImpl implements StatisticService {

	@Autowired
	OrderDao orderDao;
	
	
	@SuppressWarnings("deprecation")
	@Override
	public List<String> getTotalPriceByDate(Date date) {
		
		List<Order> listOrder = orderDao.findAll();
		
		List<String> totalPrice = new ArrayList<String>();
		
		Map<Integer, Long> total = new HashMap<Integer, Long>();
		
		int count = countDateOfMonth(date);
		
		for (int i = 1; i <= count ;i ++ ) {
			total.put(i, 0L);
		}
		
		listOrder.forEach(item -> {
			var key = 0;

			if(null != item.getEnd()) {
				if(date.getMonth() == item.getEnd().getMonth() 
						&& date.getYear() == item.getEnd().getYear()) {
					
					key = item.getEnd().getDate();
					 
					 Long sum = (long) (total.get(key) + item.getTotalPrice());
					 total.replace(key, sum);
				}
			}
		});
		
		for(int i = 1; i <= total.size(); i ++) {
			totalPrice.add(total.get(i) + "");
		}
		
		return totalPrice;
	}

	@Override
	public List<String> getTotalPriceByMonth(Date month) {
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public int countDateOfMonth(Date date) {
		
		int count = 0;
		int month = date.getMonth();
		int year = date.getYear();
		
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			count = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			count = 30;
			break;
		case 2: 
			if(year % 400 == 0 || (year % 4 == 0 && year % 100 == 0 )) {
				count = 29;
			}else {
				count = 28;
			}
			break;
		default:
			break;
		}
		
		return count;
		
	}

}
