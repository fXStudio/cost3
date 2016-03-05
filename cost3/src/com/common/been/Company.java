package com.common.been;

import java.util.ArrayList;
import java.util.List;

public class Company {
	private int id;
	private String companyName;
	private String companyCode;
	private List<Dept> depts = new ArrayList<Dept>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public List<Dept> getDepts() {
		return java.util.Collections.unmodifiableList(depts);
	}

	public void addDept(Dept dept) {
		depts.add(dept);
	}
}
