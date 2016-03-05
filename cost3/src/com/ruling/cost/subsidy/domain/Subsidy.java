package com.ruling.cost.subsidy.domain;

import com.ruling.cost.user.domain.Post;
import com.ruling.cost.user.domain.User;

public class Subsidy {

	private int id;
	/**
	 * ����
	 */
	private String basepay;
	private String hourspaly;
	private String exportyear;
	/**
	 * ����
	 */
	private String subsidy1;

	private String subsidy2;

	private String subsidy3;

	private String subsidy4;

	private String subsidy5;

	private String subsidy6;

	private String subsidy7;

	private String subsidy8;

	private String subsidy9;

	private String subsidy10;

	private Post post;

	private int userid;

	/**
	 * ��Ա
	 */
	private User user;

	private String username;

	private String deleteid;

	private String overtimepay;

	private SubsidyHistory subsidyhistory;

	public SubsidyHistory getSubsidyhistory() {
		return subsidyhistory;
	}

	public void setSubsidyhistory(SubsidyHistory subsidyhistory) {
		this.subsidyhistory = subsidyhistory;
	}

	public String getDeleteid() {
		return deleteid;
	}

	public void setDeleteid(String deleteid) {
		this.deleteid = deleteid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBasepay() {
		return basepay == null ? "0" : basepay;
	}

	public void setBasepay(String basepay) {
		this.basepay = basepay;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSubsidy1() {
		return subsidy1 == null ? "0" : subsidy1;
	}

	public void setSubsidy1(String subsidy1) {
		this.subsidy1 = subsidy1;
	}

	public String getSubsidy2() {
		return subsidy2 == null ? "0" : subsidy2;
	}

	public void setSubsidy2(String subsidy2) {
		this.subsidy2 = subsidy2;
	}

	public String getSubsidy3() {
		return subsidy3 == null ? "0" : subsidy3;
	}

	public void setSubsidy3(String subsidy3) {
		this.subsidy3 = subsidy3;
	}

	public String getSubsidy4() {
		return subsidy4;
	}

	public void setSubsidy4(String subsidy4) {
		this.subsidy4 = subsidy4;
	}

	public String getSubsidy5() {
		return subsidy5;
	}

	public void setSubsidy5(String subsidy5) {
		this.subsidy5 = subsidy5;
	}

	public String getSubsidy6() {
		return subsidy6;
	}

	public void setSubsidy6(String subsidy6) {
		this.subsidy6 = subsidy6;
	}

	public String getSubsidy7() {
		return subsidy7;
	}

	public void setSubsidy7(String subsidy7) {
		this.subsidy7 = subsidy7;
	}

	public String getSubsidy8() {
		return subsidy8;
	}

	public void setSubsidy8(String subsidy8) {
		this.subsidy8 = subsidy8;
	}

	public String getSubsidy9() {
		return subsidy9;
	}

	public void setSubsidy9(String subsidy9) {
		this.subsidy9 = subsidy9;
	}

	public String getSubsidy10() {
		return subsidy10;
	}

	public void setSubsidy10(String subsidy10) {
		this.subsidy10 = subsidy10;
	}

	public String getHourspaly() {
		return hourspaly;
	}

	public void setHourspaly(String hourspaly) {
		this.hourspaly = hourspaly;
	}

	public String getOvertimepay() {
		return overtimepay;
	}

	public void setOvertimepay(String overtimepay) {
		this.overtimepay = overtimepay;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getExportyear() {
		return exportyear;
	}

	public void setExportyear(String exportyear) {
		this.exportyear = exportyear;
	}
}
