package com.ruling.cost.hours.domain;

/**
 * Hour entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Hour implements java.io.Serializable {

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getItmeid() {
		return itmeid;
	}

	public void setItmeid(Integer itmeid) {
		this.itmeid = itmeid;
	}

	public String getTodaydate() {
		return todaydate;
	}

	public void setTodaydate(String todaydate) {
		this.todaydate = todaydate;
	}

	private Integer userid;
	private Integer itmeid;
	private String todaydate;
	private String hour;
	private String repairhour;
	private String clientparam;

	public String getClientparam() {
		return clientparam;
	}

	public void setClientparam(String clientparam) {
		this.clientparam = clientparam;
	}

	public String getRepairhour() {
		return repairhour;
	}

	public void setRepairhour(String repairhour) {
		this.repairhour = repairhour;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}
}
