package com.ruling.cost.item.domain;


public class ItemUser {

	private int id;

	private int userid;

	private int itemid;
	private String mangerid;

	private String selectUserId;

	public String getSelectUserId() {
		return selectUserId;
	}

	public void setSelectUserId(String selectUserId) {
		this.selectUserId = selectUserId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public String getMangerid() {
		return mangerid;
	}

	public void setMangerid(String mangerid) {
		this.mangerid = mangerid;
	}
}
