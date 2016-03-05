package com.ruling.cost.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.common.been.Company;
import com.common.been.Dept;
import com.common.been.Employee;
import com.common.been.ParameterMap;
import com.ruling.cost.department.domain.Department;
import com.ruling.cost.item.domain.ItemUser;
import com.ruling.cost.user.domain.Post;
import com.ruling.cost.user.domain.User;
import com.ruling.cost.user.service.UserManager;
import com.ruling.cost.util.dao.ObjectDao;
import com.ruling.cost.utils.PageModel;
import com.ruling.cost.utils.SystemException;

public class UserManagerImpl implements UserManager {
	ObjectDao objectDao;

	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}

	public void addUser(User u) {
		objectDao.save(u);
	}

	public void delUser(List usersid) {

		for (int i = 0; i < usersid.size(); i++) {

			int uid = (Integer) usersid.get(i);

			List subsidyList = this.objectDao.getByParm(" from Subsidy s where s.user.id=?", new Object[] { uid });
			List itemuserList = this.objectDao.getByParm("from ItemUser iu where iu.userid=?", new Object[] { uid });

			if (subsidyList.size() > 0) {

				throw new SystemException("已为该员工添加工资不允许删除");
			}
			if (itemuserList.size() > 0) {
				throw new SystemException("该人员已经添加到项目中不允许删除");
			}

			objectDao.delete(objectDao.getById(User.class, uid));
		}
	}

	public PageModel findAllUser(User user) {
		PageModel pm = new PageModel();
		String hql = "from User u where 1=1 ";
		if (user == null) {
			pm = objectDao.searchPaginated(hql, null);
		} else {
			List paraList = new ArrayList();
			if (!user.getUsername().trim().equals("")) {
				hql += " and u.username like ?";
				paraList.add("%" + user.getUsername() + "%");
			}
			if (!user.getUsercode().trim().equals("")) {
				hql += " and u.usercode like ?";
				paraList.add("%" + user.getUsercode() + "%");
			}
			if (!user.getTel().trim().equals("")) {
				hql += " and u.tel like ?";
				paraList.add("%" + user.getTel() + "%");
			}
			if (user.getDept() != null && user.getDept().getId() != 0) {
				hql += " and u.dept.id =?";
				paraList.add(user.getDept().getId());
			}
			try {
				pm = objectDao.searchPaginated(hql, paraList.toArray());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for(Object o : pm.getDatas()){
			User u = (User)o;
			String pid = u.getDept().getPid();
			
			List ls = objectDao.getByParm("from Department where departmentcode = ?", new Object[]{pid});
			
			
			if(ls.size() > 0){
				Department d = (Department)ls.get(0);

				u.setDepartmentName(u.getDept().getDepartmentname() + "-" + d.getDepartmentname());
			}
		}
		return pm;
	}

	public User findUserById(int uid) {
		User u = (User) objectDao.getById(User.class, uid);
		String pid = u.getDept().getPid();
		
		List ls = objectDao.getByParm("from Department where departmentcode = ?", new Object[]{pid});
		
		
		if(ls.size() > 0){
			Department d = (Department)ls.get(0);

			u.setDepartmentName(u.getDept().getDepartmentname() + "-" + d.getDepartmentname());
		}
		
		return u;
	}

	public void updateUser(User u) {
		objectDao.update(u);
	}

	public PageModel findUserByItemId(int itemid) {
		PageModel pm = this.objectDao.searchPaginated(" from User u, ItemUser i where u.id=i.userid and i.itemid=?",
				new Object[] { itemid });// 查找所有的人员
		PageModel pm1 = this.objectDao.searchPaginated(" from User u, ItemUser i where u.id=i.mangerid and i.itemid=?",
				new Object[] { itemid });// 查出项目经理

		List objectlist = pm.getDatas();
		// List objectlist1=pm1.getDatas();
		List userlist = new ArrayList();
		for (int i = 0; i < objectlist.size(); i++) {
			Object[] obj = (Object[]) objectlist.get(i);
			// for(int j=0;j<objectlist1.size();j++){
			// Object[] obj1 = (Object[])objectlist1.get(j);
			// User u=(User) obj1[0];
			// }
			userlist.add(obj);
		}
		pm.setDatas(userlist);
		return pm;
	}

	public User login(String username, String password) throws Exception {

		List userList = this.objectDao.getAll("User");

		if (userList.size() == 0) {

			throw new SystemException("没有此用户");
		}

		boolean flag = false;

		User u = new User();

		for (int i = 0; i < userList.size(); i++) {

			User user = (User) userList.get(i);

			if (username.equalsIgnoreCase(user.getEasycode())) {

				flag = true;

				u = (User) userList.get(i);
			}
		}

		if (!flag) {

			throw new SystemException("没有此用户");
		}

		if (!u.getPassword().equals(password)) {

			throw new SystemException("密码不正确");
		}

		return u;
	}

	public void updatePwd(User u) throws Exception {

		int uid = u.getId();

		User currUser = this.findUserById(uid);

		if (u.getPassword().equals(currUser.getPassword())) {

			currUser.setPassword(u.getNewpwd());

			this.objectDao.update(currUser);
		} else {
			throw new Exception("原密码错误");
		}
	}

	public PageModel findUserForItem(int itemid) {
		PageModel itemUserPm = this.objectDao.searchPaginated("from ItemUser iu where iu.itemid=" + itemid, 0, 100);
		String hql = " from User";
		if (itemUserPm.getDatas().size() > 0) {

			String uIds = "(";

			for (int i = 0; i < itemUserPm.getDatas().size(); i++) {

				ItemUser iu = (ItemUser) itemUserPm.getDatas().get(i);

				if (i != (itemUserPm.getDatas().size() - 1)) {

					uIds += iu.getUserid() + ",";

				} else {

					uIds += iu.getUserid() + ")";
				}

			}

			hql = " from User u where u.id not in" + uIds;
		}

		return this.objectDao.searchPaginated(hql);
	}

	public List<Post> findallpost() {
		return objectDao.getByParm("from Post order by postname", null);
	}

	public List<User> finduserbyPost(int postid) {
		return objectDao.getByParm(" from User where post=" + postid + " order by id", null);
	}

	/**
	 * 查询部门人员列表
	 */
	public List<Company> zzjg(ParameterMap pm) {
		// 查询公司信息
		List compList = objectDao.querySQL("Select id, departmentname, departmentcode from department where pid = 0",
				null, 0, 0);

		// 公司信息队列，里面保存了系统中维护的所有公司信息
		List<Company> comps = new ArrayList<Company>();
		// 遍历集合，创建公司信息队列
		for (Object obj : compList) {
			Object[] cvals = (Object[]) obj;
			Company comp = new Company();
			comp.setId((Integer) cvals[0]);
			comp.setCompanyName((String) cvals[1]);
			comp.setCompanyCode((String) cvals[2]);

			// 填充员工信息
			fillDepartment(comp);
			// 像公司列表中添加一个公司信息
			comps.add(comp);
		}
		return comps;
	}

	/**
	 * 填充部门信息
	 * 
	 * @param comp
	 */
	private void fillDepartment(Company comp) {
		// 查询部门信息
		List empList = objectDao.querySQL("Select id, departmentname, departmentcode from department where pid = '"
				+ comp.getCompanyCode() + "'", null, 0, 0);

		// 遍历集合，创建部门信息队列
		for (Object obj : empList) {
			Object[] cvals = (Object[]) obj;
			Dept dept = new Dept();
			dept.setId((Integer) cvals[0]);
			dept.setDeptName((String) cvals[1]);
			dept.setDeptCode((String) cvals[2]);

			// 填充员工信息
			fillEmployee(dept);
			// 像公司列表中添加一个公司信息
			comp.addDept(dept);
		}
	}

	/**
	 * 填充员工信息
	 * 
	 * @param dept
	 */
	private void fillEmployee(Dept dept) {
		// 查询公司信息
		List empList = objectDao.querySQL(
				"select username, postname from `user` inner join post on `user`.post = post.id where dept = '"
						+ dept.getId() + "' and employtype != '离职'", null, 0, 0);

		// 遍历集合，创建员工信息队列
		for (Object obj : empList) {
			Object[] cvals = (Object[]) obj;
			Employee emp = new Employee();
			emp.setEmpName((String) cvals[0]);
			emp.setEmpPost((String) cvals[1]);

			// 像公司列表中添加一个公司信息
			dept.addEmployee(emp);
		}
	}

	/**
	 * 查询所有未注册工资信息的员工
	 */
	public PageModel findAllUnregisterUser(User user) {
		return objectDao.searchPaginated(
				"from User as user where not exists (from Subsidy as subsidy where subsidy.user = user)", null);
	}
}
