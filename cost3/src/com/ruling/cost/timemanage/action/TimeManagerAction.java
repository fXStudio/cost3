package com.ruling.cost.timemanage.action;

import com.ruling.cost.item.domain.Item;
import com.ruling.cost.timemanage.service.TimeManager;
import com.ruling.cost.user.domain.User;
import com.ruling.cost.utils.BaseAction;

/**
 * 时间分配
 * 
 * @author FXStudio.com.cn(Ajaxfan)
 */
public class TimeManagerAction extends BaseAction {
	private User user;
	private TimeManager timeService;
	private Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public TimeManager getTimeService() {
		return timeService;
	}

	public void setTimeService(TimeManager timeService) {
		this.timeService = timeService;
	}

	/**
	 *  时间配置主页
	 * 
	 * @return
	 */
	public String browe() {
		getServletRequest().setAttribute("users", timeService.getAllUsers(user));

		return "broweIndex";
	}
	
	/**
	 * 调整用户的时间配置
	 * 
	 * @return
	 */
	public String timeItems(){
		user = timeService.findUserAndItem(user.getId(), item);
		
		return "ItemsPage";
	}
	
	public String updateItemTime(){
		user = timeService.findUserCWorktime(user.getId(), item.getId());;
		item = timeService.findItemById(item.getId());
		
		return "updateItemTime";
	}
	
	public String saveUpdate(){
		timeService.save(user, item);
		
		return "saved";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
