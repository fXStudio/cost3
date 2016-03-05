package com.ruling.cost.repairhour.action;

import com.ruling.cost.hours.domain.Hour;
import com.ruling.cost.utils.BaseAction;

public class RepairHourAction extends BaseAction {

	private Hour h;

	// 跳转到添加界面
	public String addInput() {
		return "add_input";
	}

	public String add() {

		return "add_success";
	}

	public String delete() {

		return "del_success";
	}

	public Hour getH() {
		return h;
	}

	public void setH(Hour h) {
		this.h = h;
	}

}
