package com.ruling.cost.timemanage.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ruling.cost.item.domain.Item;
import com.ruling.cost.timemanage.domain.CWorkTime;
import com.ruling.cost.timemanage.service.TimeManager;
import com.ruling.cost.user.domain.User;
import com.ruling.cost.util.dao.ObjectDao;

public class TimeManagerImpl implements TimeManager {
	private ObjectDao objectDao;

	public ObjectDao getObjectDao() {
		return objectDao;
	}

	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}

	/**
	 * 所有的用户信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers(User user) {
		return (List<User>) objectDao.getByParm(getQuerySql(user), new Object[] {});
	}

	private String getQuerySql(User user) {
		StringBuilder sb = new StringBuilder("from User where employtype != '离职'");

		if (user != null) {
			if (user.getUsername() != null && !"".equals(user.getUsername().trim())) {
				sb.append(" and username like '%");
				sb.append(user.getUsername());
				sb.append("%'");
			}

			if (user.getUsercode() != null && !"".equals(user.getUsercode().trim())) {
				sb.append(" and usercode like '%");
				sb.append(user.getUsercode());
				sb.append("%'");
			}
		}
		return sb.toString();
	}

	public User findUserAndItem(Integer id, Item item) {
		User u = (User) objectDao.getById(User.class, id);
		List<Item> items = objectDao.getByParm(getItemSql(item), new Object[] { id });
		u.setItems(items);

		return u;
	}

	private String getItemSql(Item item) {
		StringBuilder sb = new StringBuilder(
				"from Item where id in (select itemid from ItemUser where userid = ?) and itemstate = 'activity'");

		if (item != null) {
			if (item.getItemname() != null && !"".equals(item.getItemname().trim())) {
				sb.append(" and itemname like '%");
				sb.append(item.getItemname());
				sb.append("%'");
			}

			if (item.getItemcode() != null && !"".equals(item.getItemcode().trim())) {
				sb.append(" and usercode like '%");
				sb.append(item.getItemcode());
				sb.append("%'");
			}
		}
		return sb.toString();
	}

	public void save(User user, Item item) {
		List<CWorkTime> list = objectDao.getByParm("from CWorkTime where userId = ? and itemid = ?", new Object[] {
				user.getId(), item.getId() });

		CWorkTime c = new CWorkTime();
		c.setUserId(user.getId());
		c.setCreated(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		c.setTimelong(user.getCworktime().getTimelong());
		c.setItemId(item.getId());
		c.setResidTime(c.getTimelong());

		if (list.size() > 0) {
			CWorkTime o = list.get(0);

			if (c.getTimelong() > 9999) {
				c.setResidTime(c.getTimelong());
			} else {
				c.setResidTime(c.getTimelong() - o.getTimelong() + o.getResidTime());
			}
			objectDao.delete(o);
		}
		objectDao.save(c);
	}

	/**
	 * 获得项目对象
	 * 
	 * @param id
	 * @return
	 */
	public Item findItemById(Integer id) {
		return (Item) objectDao.getById(Item.class, id);
	}

	/**
	 * 获得用户项目的工作时间
	 * 
	 * @param userid
	 * @param itemid
	 * @return
	 */
	public User findUserCWorktime(int userid, int itemid) {
		// 用户信息
		User u = (User) objectDao.getById(User.class, userid);

		// 在项目定义的时间
		List<CWorkTime> wtimes = objectDao.getByParm("from CWorkTime where userid = ? and itemid = ?", new Object[] {
				userid, itemid });

		if (wtimes.size() > 0) {
			CWorkTime c = wtimes.get(0);

			if (c.getTimelong() > 9999) {
				List usedTime = objectDao.querySQL("select ifnull(sum(hour), 0) from Hour where userid = ? and itmeid = ? and substring(todaydate, 1, 7) = ?", new Object[] {
						userid, itemid, new SimpleDateFormat("yyyy-MM").format(new Date()) }, 0, 1);
				
				c.setUsedTime(usedTime.size() > 0 ? ((Double)usedTime.get(0)).intValue() : 0);
			} else {
				c.setUsedTime(c.getTimelong() - c.getResidTime());
			}
			u.setCworktime(c);
		}
		return u;
	}
}
