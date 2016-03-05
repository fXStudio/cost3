package com.ruling.cost.total.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.ruling.cost.hours.service.HoursManager;
import com.ruling.cost.item.domain.Item;
import com.ruling.cost.item.domain.ItemUser;
import com.ruling.cost.item.service.ItemManager;
import com.ruling.cost.total.domain.Total;
import com.ruling.cost.total.service.TotalManager;
import com.ruling.cost.user.domain.User;
import com.ruling.cost.utils.BaseAction;

public class BudgetAction extends BaseAction {
	private static final Logger log = Logger.getLogger(BudgetAction.class);
	TotalManager totalService;
	private Total total;
	private ItemManager itemService;
	private List<Item> listitem;
	private String id;
	HoursManager hoursservice;
	private String itemid;
	private String date;

	// 查询方法
	public String browe() {
		log.info("进入项目统计查询的方法！");
		ActionContext ct = ActionContext.getContext();
		Map session = ct.getSession();
		User u = (User) session.get("login");
		System.out.println(u.getUsername());
		String itemname = "";
		int[] arr = null;
		if (null != total) {
			if (total.getItemname() != null && !total.getItemname().equals("")) {
				itemname = total.getItemname();
			}
		}
		log.info("查询列表结束");
		if (u.getType().equals("system")) {
			this.getServletRequest().setAttribute("pm",
					totalService.querylist(0, itemname, null, 1));
		} else {
			if (u.getWorklevel().equals("manager")) {
				List list = itemService.queryItemByMangerid(u.getId());
				;
				arr = new int[list.size()];
				for (int i = 0; i < list.size(); i++) {
					ItemUser iu = (ItemUser) list.get(i);
					System.out.println(iu.getItemid() + "******");
					arr[i] = iu.getItemid();
					// Object[] obj=(Object[]) list.get(i);
					// arr[i]=Integer.parseInt(obj[0].toString());
				}
				this.getServletRequest().setAttribute("pm",
						totalService.querylist(0, itemname, arr, 1));
			}
		}

		return "browe";
	}

	public String add() {
		ActionContext ct = ActionContext.getContext();
		Map session = ct.getSession();
		User u = (User) session.get("login");
		total.setUserid(u.getId());
		total.setStatus("1");
		total.setItemname(itemService.findItemById(total.getItemid())
				.getItemname());
		totalService.addtotal(total);
		return "add";
	}

	// 删除记录
	public String delete() {
		String[] arr = id.split(",");
		for (int i = 0; i < arr.length; i++) {
			totalService.deletetotal(arr[i].trim());
		}
		return "delete";
	}

	// 查找记录
	public String find() {
		Total t = new Total();
		t.setId(Integer.parseInt(id.trim()));
		total = totalService.findtotal(t);
		return "find";
	}

	// 跳转到修改页面
	public String toupdate() {
		listitem = itemService.queryItemall();
		Total t = new Total();
		t.setId(Integer.parseInt(id.trim()));
		total = totalService.findtotal(t);
		return "toupdate";
	}

	// 修改动作
	public String update() {
		total.setItemname(itemService.findItemById(total.getItemid())
				.getItemname());
		totalService.updatetotal(total);
		return "update";
	}

	// 跳转添加页面
	public String toadd() {
		listitem = itemService.queryItemall();// 获取所有的项目。
		for (int i = 0; i < listitem.size(); i++) {
			Total total = new Total();
			total.setStatus("1");
			total.setItemid(listitem.get(i).getId());
			Total toal = totalService.findtotal1(total);
			if (toal != null && !toal.equals("")) {
				listitem.remove(i);
			}
		}
		return "toadd";
	}

	public TotalManager getTotalService() {
		return totalService;
	}

	public void setTotalService(TotalManager totalService) {
		this.totalService = totalService;
	}

	public Total getTotal() {
		return total;
	}

	public void setTotal(Total total) {
		this.total = total;
	}

	public ItemManager getItemService() {
		return itemService;
	}

	public void setItemService(ItemManager itemService) {
		this.itemService = itemService;
	}

	public List<Item> getListitem() {
		return listitem;
	}

	public void setListitem(List<Item> listitem) {
		this.listitem = listitem;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HoursManager getHoursservice() {
		return hoursservice;
	}

	public void setHoursservice(HoursManager hoursservice) {
		this.hoursservice = hoursservice;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
