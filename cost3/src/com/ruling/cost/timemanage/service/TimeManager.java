package com.ruling.cost.timemanage.service;

import java.util.List;

import com.ruling.cost.item.domain.Item;
import com.ruling.cost.user.domain.User;

public interface TimeManager {
	public List<User> getAllUsers(User user);
	public User findUserAndItem(Integer id, Item item);
	public void save(User user, Item item);
	public Item findItemById(Integer id);
	public User findUserCWorktime(int userid, int itemid);
}
