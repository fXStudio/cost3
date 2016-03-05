package com.ruling.cost.department.action;

import java.util.List;

import com.ruling.cost.department.domain.Department;
import com.ruling.cost.department.domain.DeptComp;
import com.ruling.cost.department.service.DepartmentManager;
import com.ruling.cost.utils.BaseAction;

@SuppressWarnings("unchecked")
public class DepartmentAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	DepartmentManager departmentservice;

	private Department dept;

	private DeptComp dc;

	public DeptComp getDc() {
		return dc;
	}

	public void setDc(DeptComp dc) {
		this.dc = dc;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public void setDepartmentservice(DepartmentManager departmentservice) {
		this.departmentservice = departmentservice;
	}

	public String addInput() {
		return "comp_add_input";
	}

	public String deptAddInput() {
		Department d = this.departmentservice.getDepartmentById(dept.getId());
		dept.setPid(d.getDepartmentcode());

		return "dept_add_input";
	}

	public String add() throws Exception {
		this.departmentservice.addDepartment(dept);

		return "comp_add_success";
	}

	public String addDept() throws Exception {
		this.departmentservice.addDepartment(dept);

		return "dept_add_success";
	}

	public String delete() throws Exception {
		List list = this.departmentservice.getAllDepartments(dept);
		
		this.departmentservice.delDepartmentManager(list);

		return "comp_del_success";
	}
	
	public String deleteDept() throws Exception {
		List list = this.departmentservice.getAllDepartments(dept);
		
		this.departmentservice.delDepartmentManager(list);

		return "dept_del_success";
	}

	public String updateInput() {
		this.getServletRequest().setAttribute("dept", this.departmentservice.getDepartmentById(dept.getId()));

		return "comp_update_input";
	}

	public String deptupdateInput() {
		Department d = this.departmentservice.getDepartmentById(dept.getId());
		d.setCompid(dept.getCompid());
		
		this.getServletRequest().setAttribute("dept", d);

		return "dept_update_input";
	}
	
	public String update() {
		try {
			this.departmentservice.updateDepartmentManager(dept);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "comp_update_success";
	}
	
	public String updateDept() {
		try {
			this.departmentservice.updateDepartmentManager(dept);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dept_update_success";
	}

	public String browe() {

		this.getServletRequest().setAttribute("pm", this.departmentservice.getAllDepartment(dept));

		this.getServletRequest().setAttribute("dept", dept);

		return "dept_browe";
	}

	public String selectBrowe() {
		this.getServletRequest().setAttribute("pm", this.departmentservice.listDepartments(dept));

		return "select_browe";
	}

	public String listDepts() {
		Department d = this.departmentservice.getDepartmentById(dept.getId());
		
		dept.setCompname(d.getDepartmentname());
		
		this.getServletRequest().setAttribute("pm",
				this.departmentservice.viewDepts(d));

		return "dept_page";
	}
}
