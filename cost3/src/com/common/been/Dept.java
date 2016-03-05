package com.common.been;

import java.util.ArrayList;
import java.util.List;

public class Dept {
	private int id;
	private String deptName;
	private String deptCode;
	private List<Employee> emps = new ArrayList<Employee>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public List<Employee> getEmps() {
		return java.util.Collections.unmodifiableList(emps);
	}

	public void addEmployee(Employee emp) {
		this.emps.add(emp);
	}
}
