package com.ruling.cost.department.service.impl;

import java.util.List;

import com.ruling.cost.department.domain.Department;
import com.ruling.cost.department.service.DepartmentManager;
import com.ruling.cost.util.dao.ObjectDao;
import com.ruling.cost.utils.PageModel;
import com.ruling.cost.utils.SystemException;

public class DepartmentManagerImpl implements DepartmentManager {

	ObjectDao objectDao;

	public PageModel listDepartments(Department dept) {
		StringBuilder sb = new StringBuilder();

		sb.append("from DeptComp");

		return objectDao.searchPaginated(sb.toString(), null);
	}

	public PageModel viewDepts(Department dept) {
		return objectDao.searchPaginated("from Department where pid = " + dept.getDepartmentcode(), null);
	}

	public PageModel getAllDepartment(Department dept) {
		PageModel pm = new PageModel();

		String hql = "from Department where pid = 0";

		if (dept == null) {

			pm = objectDao.searchPaginated(hql, null);

			return pm;
		}
		dept = convertDept(dept);

		if (!dept.getDepartmentname().trim().equals("") && !dept.getDepartmentcode().trim().equals("")) {

			Object[] obj = new Object[2];

			hql = " from Department dept where pid = 0 and dept.departmentname like ? or dept.departmentcode like ?";

			obj[0] = "%" + dept.getDepartmentname() + "%";
			obj[1] = "%" + dept.getDepartmentcode() + "%";

			pm = objectDao.searchPaginated(hql, obj);

			return pm;
		}

		if (dept.getDepartmentname().trim().equals("") && dept.getDepartmentcode().trim().equals("")) {

			hql = " from Department where pid = 0";
			pm = objectDao.searchPaginated(hql, null);

			return pm;
		}

		if (!dept.getDepartmentname().trim().equals("") && dept.getDepartmentcode().trim().equals("")) {

			Object[] obj = new Object[1];

			hql = " from Department dept where pid = 0 and dept.departmentname like ?";

			obj[0] = "%" + dept.getDepartmentname() + "%";
			pm = objectDao.searchPaginated(hql, obj);

			return pm;
		}

		if (dept.getDepartmentname().trim().equals("") && !dept.getDepartmentcode().trim().equals("")) {

			Object[] obj = new Object[1];

			hql = " from Department dept where pid = 0 and dept.departmentcode like ?";

			obj[0] = "%" + dept.getDepartmentcode() + "%";
			pm = objectDao.searchPaginated(hql, obj);

			return pm;
		}
		return pm;
	}

	private Department convertDept(Department dept) {

		if (dept.getDepartmentname() == null) {
			dept.setDepartmentname("");
		}
		if (dept.getDepartmentcode() == null) {
			dept.setDepartmentcode("");
		}
		if (dept.getDepartmentcode().indexOf("?") != -1) {
			String code = dept.getDepartmentcode().substring(0, dept.getDepartmentcode().indexOf("?"));
			dept.setDepartmentcode(code);
		}
		if (dept.getDepartmentname().indexOf("?") != -1) {
			String name = dept.getDepartmentname().substring(0, dept.getDepartmentname().indexOf("?"));
			dept.setDepartmentname(name);
		}
		return dept;
	}

	public Department getDepartmentById(int id) {
		return (Department) objectDao.getById(Department.class, id);
	}

	public void addDepartment(Department de) throws Exception {
		String code = de.getDepartmentcode();

		List codelist = this.objectDao.getByParm("from Department d where d.departmentcode = ?", new Object[] { code });

		if (codelist.size() > 0) {
			throw new SystemException("组织编码重复");
		}
		objectDao.save(de);
	}

	public void updateDepartmentManager(Department de) {
		objectDao.update(de);

		for (Object obj : getAllDepartments(de.getDeleteid())) {
			((Department) obj).setPid(de.getDepartmentcode());
			objectDao.update(obj);
		}
	}

	public ObjectDao getObjectDao() {
		return objectDao;
	}

	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}

	public void delDepartmentManager(List depts) throws Exception {
		for (int i = 0; i < depts.size(); i++) {
			Department dept = (Department) depts.get(i);

			List userlist = this.objectDao.getByParm("from User u where u.dept.id=?", new Object[] { dept.getId() });

			if (userlist != null && userlist.size() > 0) {
				throw new SystemException(dept.getDepartmentname() + "下已添加人员信息，不允许删除");
			}
			this.objectDao.delete(dept);
		}
	}

	public PageModel getAllDepartment(Department dept, int page, int pageSize) {
		int allRow = objectDao.getAll("Department").size();
		int totalPage = objectDao.getTotalPage("Department", pageSize);
		final int offset = PageModel.countOffset(pageSize, page);
		final int length = pageSize;
		final int currentPage = PageModel.countCurrentPage(page);

		List list = objectDao.findPage("Department", offset, length);

		PageModel pageBean = new PageModel();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotal(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setDatas(list);
		pageBean.init();

		return pageBean;
	}

	public List getAllDepartments(Department dept) {
		int deptId = Integer.parseInt(dept.getDeleteid());

		Department d = this.getDepartmentById(deptId);

		return this.objectDao.getByParm("from Department where pid = ? or id = ?", new Object[] {
				d.getDepartmentcode(), deptId });
	}

	private List getAllDepartments(String deptcode) {
		return this.objectDao.getByParm("from Department where pid = ? ", new Object[] { deptcode });
	}
}
