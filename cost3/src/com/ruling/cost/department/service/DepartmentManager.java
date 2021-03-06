package com.ruling.cost.department.service;

import java.util.List;

import com.ruling.cost.department.domain.Department;
import com.ruling.cost.utils.PageModel;

public interface DepartmentManager {
	public PageModel getAllDepartment(Department dept, int currentPage,
			int pageSize);

	public void addDepartment(Department de) throws Exception;

	public void delDepartmentManager(List deptIds) throws Exception;

	public void updateDepartmentManager(Department de);

	public Department getDepartmentById(int id);

	public PageModel getAllDepartment(Department dept);

	public PageModel listDepartments(Department dept);
	
	public PageModel viewDepts(Department dept);
	
	public List getAllDepartments(Department dept);
}