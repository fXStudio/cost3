package com.ruling.cost.item.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.ruling.cost.item.domain.Item;
import com.ruling.cost.item.domain.ItemUser;
import com.ruling.cost.item.service.ItemManager;
import com.ruling.cost.utils.BaseAction;

public class ItemAction extends BaseAction {

	private ItemManager itemService;

	private Item item;

	private ItemUser itemuser;

	public String addInput() {

		return "item_add_input";
	}

	public String add() {

		this.itemService.addItem(item);

		return "item_add_success";
	}

	public String delete() throws Exception {

		String[] deleteIds = item.getDeleteid().split(",");

		List list = new ArrayList();

		for (int i = 0; i < deleteIds.length; i++) {

			list.add(Integer.parseInt(deleteIds[i].trim()));
		}

		this.itemService.delItem(list);

		return "item_del_success";
	}

	public String deleteItemUser() {

		String[] deleteIds = item.getDeleteid().split(",");

		List list = new ArrayList();

		for (int i = 0; i < deleteIds.length; i++) {

			list.add(Integer.parseInt(deleteIds[i].trim()));
		}

		this.itemService.deleteItemUser(list, itemuser.getItemid());

		return "delete_itemuser_success";
	}

	public String updateInput() {

		this.getServletRequest().setAttribute("item",
				(Item) this.itemService.findItemById(item.getId()));
		return "item_update_input";
	}

	public String update() {

		this.itemService.updateItem(item);

		return "item_update_success";
	}

	public String browe() {

		this.getServletRequest().setAttribute("pm",
				this.itemService.findAllItem(item));

		this.getServletRequest().setAttribute("item", item);

		return "item_browe";
	}

	public String addItemUser() {

		String[] siuid = itemuser.getSelectUserId().split(",");

		List list = new ArrayList();

		for (int i = 0; i < siuid.length; i++) {

			list.add(Integer.parseInt(siuid[i].trim()));
		}

		this.itemService.addUserItem(list, itemuser);

		return "addItemUserbrowe";
	}

	// 设置项目经理
	public String updateitemuser() {
		String[] siuid = itemuser.getSelectUserId().split(",");

		List list = new ArrayList();

		for (int i = 0; i < siuid.length; i++) {

			list.add(Integer.parseInt(siuid[i].trim()));
		}
		this.itemService.updateUserItem(list, itemuser);
		return "setManger";
	}

	public String selectBrowe() {

		this.getServletRequest().setAttribute("pm",
				this.itemService.findAllItem(null));

		return "select_browe";
	}

	public ItemManager getItemService() {
		return itemService;
	}

	public void setItemService(ItemManager itemService) {
		this.itemService = itemService;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ItemUser getItemuser() {
		return itemuser;
	}

	public void setItemuser(ItemUser itemuser) {
		this.itemuser = itemuser;
	}
}
