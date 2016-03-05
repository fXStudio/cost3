package com.ruling.cost.item.domain;

public class Item {

	private int id;

	private String itemname;

	private String itemcode;

	private String starttime;

	private String endtime;

	private String itemstate;

	private String deleteid;
	
	private Integer timelong;

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

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getItemstate() {
		return itemstate;
	}

	public void setItemstate(String itemstate) {
		this.itemstate = itemstate;
	}

	public Integer getTimelong() {
		return timelong;
	}

	public void setTimelong(Integer timelong) {
		this.timelong = timelong;
	}

}
