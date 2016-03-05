package com.ruling.cost.itemreport.domain;

/**
 * ItemReport entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ItemReport implements java.io.Serializable {

	// Fields

	private String id;
	private String itemcode;
	private String itemname;
	private double contractMoney;
	private String startDate;
	private double voMoney;
	private String voDate;
	private String engineerConfig;
	private int itemId;

	// Constructors

	/** default constructor */
	public ItemReport() {
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemcode() {
		return this.itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getItemname() {
		return this.itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getVoDate() {
		return this.voDate;
	}

	public void setVoDate(String voDate) {
		this.voDate = voDate;
	}

	public String getEngineerConfig() {
		return this.engineerConfig;
	}

	public void setEngineerConfig(String engineerConfig) {
		this.engineerConfig = engineerConfig;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public double getContractMoney() {
		return contractMoney;
	}

	public void setContractMoney(double contractMoney) {
		this.contractMoney = contractMoney;
	}

	public double getVoMoney() {
		return voMoney;
	}

	public void setVoMoney(double voMoney) {
		this.voMoney = voMoney;
	}

}