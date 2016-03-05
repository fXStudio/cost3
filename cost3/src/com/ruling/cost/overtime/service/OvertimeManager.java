package com.ruling.cost.overtime.service;

import java.util.List;
import java.util.Map;

import com.ruling.cost.item.domain.Item;
import com.ruling.cost.overtime.domain.Overtime;
import com.ruling.cost.user.domain.User;

@SuppressWarnings("unchecked")
public interface OvertimeManager {
	public List<Item> showAllItems();
	
	public void addOverTime(Overtime o);
	
	public Map listOvers(String begin, String end);

	public List<User> getItemUsers(Integer itemid);
	
	public Map listMonthOvers(Overtime overtime);
	
	public List<Item> showItemWithUser(int userid);
}
