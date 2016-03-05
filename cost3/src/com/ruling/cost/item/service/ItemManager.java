package com.ruling.cost.item.service;

import java.util.List;

import com.ruling.cost.item.domain.Item;
import com.ruling.cost.item.domain.ItemUser;
import com.ruling.cost.utils.PageModel;

public interface ItemManager {

	public void addItem(Item item);

	public void delItem(List itemIds) throws Exception;

	public void updateItem(Item item);

	public PageModel findAllItem(Item item);

	public Item findItemById(int itemid);

	public void addUserItem(List list, ItemUser iu);

	public void updateUserItem(List list, ItemUser iu);

	// query item by userid from itemuser param:userId
	public List findItemByUserId(int userId);

	// query user by itemid from itemuser param:itemid
	public List findUserByItemId(int itemId);

	public void deleteItemUser(List itemIds, int itemid);

	public List<Item> queryItemall();

	public List queryUseritem(int userid);

	public List<ItemUser> queryItemByMangerid(int mangerid);

}
