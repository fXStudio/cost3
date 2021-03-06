package com.ruling.cost.holiday.action;

import com.ruling.cost.holiday.domain.Holiday;
import com.ruling.cost.holiday.service.HolidayManager;
import com.ruling.cost.utils.BaseAction;
import com.ruling.cost.utils.DateUtil;
import com.ruling.cost.utils.PageModel;

public class HolidayAction extends BaseAction {

	private Holiday holiday;

	private HolidayManager holidayService;

	public String addInput() {

		this.getServletRequest().setAttribute("yearlist",
				DateUtil.getHolidayList());

		return "add_input";

	}

	public String add() throws Exception {

		this.holidayService.add(holiday);

		return "add_success";
	}

	public String delete() {

		String[] ids = holiday.getDeleteid().split(",");

		this.holidayService.deleteHolidayArray(ids);

		return "del_success";
	}

	public String updateInput() {

		this.getServletRequest().setAttribute("yearlist",
				DateUtil.getHolidayList());

		this.getServletRequest().setAttribute("holiday",
				this.holidayService.findHolidayById(holiday.getId()));

		return "update_input";
	}

	public String update() throws Exception {

		this.holidayService.updateHoliday(holiday);

		return "update_success";
	}

	public String browe() {

		PageModel pm = this.holidayService.findAllHolidayForPageModel(holiday);

		this.getServletRequest().setAttribute("pm", pm);

		this.getServletRequest().setAttribute("holiday", holiday);

		return "browe";
	}

	public Holiday getHoliday() {
		return holiday;
	}

	public void setHoliday(Holiday holiday) {
		this.holiday = holiday;
	}

	public HolidayManager getHolidayService() {
		return holidayService;
	}

	public void setHolidayService(HolidayManager holidayService) {
		this.holidayService = holidayService;
	}

}
