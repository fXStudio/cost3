package com.ruling.cost.overtime.domain;

/**
 * Overtime entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Overtime implements java.io.Serializable {
	// Fields

	private String id;
	private Integer userid;
	private Integer itemid;
	private String date;
	private String hour;
	private String clientparam;
	private String itemname;

	// Constructors

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getClientparam() {
		return clientparam;
	}

	public void setClientparam(String clientparam) {
		this.clientparam = clientparam;
	}

	/** default constructor */
	public Overtime() {
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHour() {
		return this.hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

}