package com.ruling.cost.holiday.domain;

public class Holiday {

	private int id;

	private String holidayname;

	private String holidate;

	private String holiyear;

	private String deleteid;

	public String getDeleteid() {
		return deleteid;
	}

	public void setDeleteid(String deleteid) {
		this.deleteid = deleteid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHolidayname() {
		return holidayname;
	}

	public void setHolidayname(String holidayname) {
		this.holidayname = holidayname;
	}

	public String getHolidate() {
		return holidate;
	}

	public void setHolidate(String holidate) {
		this.holidate = holidate;
	}

	public String getHoliyear() {
		return holiyear;
	}

	public void setHoliyear(String holiyear) {
		this.holiyear = holiyear;
	}

}
