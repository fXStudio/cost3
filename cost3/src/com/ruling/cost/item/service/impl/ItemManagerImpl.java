package com.ruling.cost.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruling.cost.hours.service.HoursManager;
import com.ruling.cost.item.domain.Item;
import com.ruling.cost.item.domain.ItemUser;
import com.ruling.cost.item.service.ItemManager;
import com.ruling.cost.util.dao.ObjectDao;
import com.ruling.cost.utils.PageModel;
import com.ruling.cost.utils.SystemException;

public class ItemManagerImpl implements ItemManager {

	private ObjectDao objectDao;

	private HoursManager hoursservice;

	public HoursManager getHoursservice() {
		return hoursservice;
	}

	public void setHoursservice(HoursManager hoursservice) {
		this.hoursservice = hoursservice;
	}

	public void addItem(Item item) {
		this.objectDao.save(item);
	}

	public void delItem(List itemIds) throws Exception {

		for (int i = 0; i < itemIds.size(); i++) {

			int itemid = (Integer) itemIds.get(i);

			List list = this.objectDao.getByParm(
					" from ItemUser iu where iu.itemid=?",
					new Object[] { itemid });

			List useridsList = new ArrayList();

			List itemidsList = new ArrayList();

			itemidsList.add(itemid);

			// 先删除项目添加的人员信息
			for (int j = 0; j < list.size(); j++) {

				this.objectDao.delete((ItemUser) list.get(j));
				useridsList.add(((ItemUser) list.get(j)).getUserid());
			}
			// 在删除人员的工时
			if (useridsList.size() > 0) {

				this.hoursservice.deletehourby(useridsList, itemidsList);
			}
			this.objectDao.delete(this.objectDao.getById(Item.class, itemid));
		}

	}

	public PageModel findAllItem(Item item) {

		PageModel pm = new PageModel();

		String hql = "from Item";

		if (item == null) {

			pm = objectDao.searchPaginated(hql, null);

			return pm;
		}

		if (!item.getItemname().trim().equals("")
				&& !item.getItemcode().trim().equals("")) {

			Object[] obj = new Object[2];

			hql = " from Item item where item.itemname like ? and item.itemcode like ?";

			obj[0] = "%" + item.getItemname() + "%";
			obj[1] = "%" + item.getItemcode() + "%";

			pm = objectDao.searchPaginated(hql, obj);

			return pm;
		}

		if (item.getItemname().trim().equals("")
				&& item.getItemcode().trim().equals("")) {

			hql = " from Item";
			pm = objectDao.searchPaginated(hql, null);

			return pm;
		}

		if (!item.getItemname().trim().equals("")
				&& item.getItemcode().trim().equals("")) {

			Object[] obj = new Object[1];

			hql = " from Item item where item.itemname like ?";

			obj[0] = "%" + item.getItemname() + "%";
			pm = objectDao.searchPaginated(hql, obj);

			return pm;
		}

		if (item.getItemname().trim().equals("")
				&& !item.getItemcode().trim().equals("")) {

			Object[] obj = new Object[1];

			hql = " from Item item where item.itemcode like ?";

			obj[0] = "%" + item.getItemcode() + "%";
			pm = objectDao.searchPaginated(hql, obj);

			return pm;
		}

		return pm;
	}

	public Item findItemById(int itemid) {
		return (Item) this.objectDao.getById(Item.class, itemid);
	}

	public void updateItem(Item item) {
		this.objectDao.update(item);
	}

	public ObjectDao getObjectDao() {
		return objectDao;
	}

	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}

	public void addUserItem(List list, ItemUser iu) {

		for (int i = 0; i < list.size(); i++) {

			int userid = (Integer) list.get(i);

			List object = this.objectDao.getByParm(
					" from ItemUser iu where iu.itemid=? and iu.userid=?",
					new Object[] { iu.getItemid(), userid });

			if (object.size() == 0) {

				ItemUser itemuser = new ItemUser();

				itemuser.setItemid(iu.getItemid());

				itemuser.setUserid(userid);

				this.objectDao.save(itemuser);
			}
		}
	}

	public List findItemByUserId(int userId) {

		List itemIds = this.objectDao.getByParm(
				" from ItemUser iu where iu.userid=?", new Object[] { userId });

		List items = new ArrayList();

		for (int i = 0; i < itemIds.size(); i++) {

			items.add(((List) itemIds.get(i)).get(1));
		}
		return items;
	}

	public List findUserByItemId(int itemId) {

		List itemIds = this.objectDao.getByParm(
				" from ItemUser iu where iu.itemid=?", new Object[] { itemId });

		List users = new ArrayList();

		for (int i = 0; i < itemIds.size(); i++) {

			users.add(((List) itemIds.get(i)).get(0));
		}
		return users;
	}

	public void deleteItemUser(List userIds, int itemid) {

		List useridsList = new ArrayList();

		List itemidsList = new ArrayList();

		itemidsList.add(itemid);

		for (int i = 0; i < userIds.size(); i++) {

			int userid = (Integer) userIds.get(i);

			useridsList.add(userid);

			List list = this.objectDao.getByParm(
					" from ItemUser iu where iu.userid=? and iu.itemid='"
							+ itemid + "'", new Object[] { userid });

			// 先删除项目添加的人员信息
			for (int j = 0; j < list.size(); j++) {

				this.objectDao.delete((ItemUser) list.get(j));
			}
		}

		// 删除人员的工时
		this.hoursservice.deletehourby(useridsList, itemidsList);
	}

	public List<Item> queryItemall() {
		// TODO Auto-generated method stub
		return objectDao.getAll("Item");
	}

	public void updateUserItem(List list, ItemUser iu) {
		for (int i = 0; i < list.size(); i++) {
			String useriss = list.get(i).toString();
			int userid = Integer.valueOf(useriss);

			List object = this.objectDao.getByParm(
					" from ItemUser iu where iu.itemid=? and iu.userid=?",
					new Object[] { iu.getItemid(), userid });

			if (object.size() == 0) {

				ItemUser itemuser = new ItemUser();

				itemuser.setItemid(iu.getItemid());

				itemuser.setUserid(userid);
				itemuser.setMangerid(useriss);

				this.objectDao.save(itemuser);
			} else {
				ItemUser itemuser = (ItemUser) object.get(0);
				itemuser.setMangerid(useriss);
				this.objectDao.update(itemuser);
			}
		}

	}

	public List queryUseritem(int userid) {
		Object[] arr = new Object[] { userid };
		return this.objectDao.getByParm(
				" select mangerid from ItemUser iu where  iu.userid=" + userid
						+ " and mangerid <> null", null);
	}

	public List<ItemUser> queryItemByMangerid(int mangerid) {
		// Object[] arr=new Object[]{mangerid};
		return this.objectDao.getByParm("from ItemUser iu where  iu.mangerid='"
				+ mangerid + "'", null);
	}

}
