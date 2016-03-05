package com.ruling.cost.total.domain;

public class Total {
	private int id;
	private Integer itemid;// 项目id
	private String itemname;// 项目名称
	private String htvalue;// 合同总额
	private String peoplevalue;// 人工成本
	private String palyvalue;// 花费金额
	private String extl;// extl费用
	private String mv;// M值
	private String sj;// 税金
	private String bfb;// 百分比

	private String chengben;// 总成本
	private String revenue;// 收益
	private Integer userid;// 项目经理的id
	private String createdate;// 创建日期
	private String status;// 0为项目统计，1为项目预算

	public String getMv() {
		return mv;
	}

	public void setMv(String mv) {
		this.mv = mv;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getHtvalue() {
		return htvalue;
	}

	public void setHtvalue(String htvalue) {
		this.htvalue = htvalue;
	}

	public String getPeoplevalue() {
		return peoplevalue;
	}

	public void setPeoplevalue(String peoplevalue) {
		this.peoplevalue = peoplevalue;
	}

	public String getPalyvalue() {
		return palyvalue;
	}

	public void setPalyvalue(String palyvalue) {
		this.palyvalue = palyvalue;
	}

	public String getExtl() {
		return extl;
	}

	public void setExtl(String extl) {
		this.extl = extl;
	}

	public String getChengben() {
		return chengben;
	}

	public void setChengben(String chengben) {
		this.chengben = chengben;
	}

	public String getRevenue() {
		return revenue;
	}

	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBfb() {
		return bfb;
	}

	public void setBfb(String bfb) {
		this.bfb = bfb;
	}

}
