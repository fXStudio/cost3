package com.ruling.cost.overtime.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruling.cost.item.domain.Item;
import com.ruling.cost.overtime.domain.Overtime;
import com.ruling.cost.overtime.service.OvertimeManager;
import com.ruling.cost.user.domain.User;
import com.ruling.cost.util.dao.ObjectDao;

@SuppressWarnings("unchecked")
public class OvertimeManagerImpl implements OvertimeManager {
	private ObjectDao objectDao;

	/**
	 * 
	 * @param objectDao
	 */
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}

	/**
	 * @return 所有的项目
	 */
	public List<Item> showItemWithUser(int userid) {
		return objectDao.query(
				"from Item where itemstate ='activity' and id in (select itemid from ItemUser where userid = ?)",
				new Object[] { userid }, 0, 0);
	}

	/**
	 * @return 所有的项目
	 */
	public List<Item> showAllItems() {
		return objectDao.query("from Item where itemstate ='activity'", null, 0, 0);
	}

	/**
	 * 保存变更
	 * 
	 * @param updates
	 */
	public void addOverTime(Overtime o) {
		List list = objectDao.query("from Overtime where itemid = ? and date = ? and userid = ?", new Object[] {
				o.getItemid(), o.getDate(), o.getUserid() }, 0, 0);
		for (Object obj : list) {
			objectDao.delete(obj);
		}
		objectDao.save(o);
	}

	/**
	 * 列出已维护的加班信息
	 * 
	 * @return
	 */
	public Map listOvers(String begin, String end) {
		List list = objectDao.querySQL(
				"select CONCAT(itemid,'_',DATE), hour from `overtime` where date >= ? and date <= ?", new Object[] {
						begin, end }, 0, 0);

		Map map = new HashMap();

		for (Object o : list) {
			Object[] pros = (Object[]) o;
			map.put(pros[0], pros[1]);
		}
		return java.util.Collections.unmodifiableMap(map);
	}

	/**
	 * 查询所有的项目参与人员
	 * 
	 * @param itemid
	 * @return
	 */
	public List<User> getItemUsers(Integer itemid) {
		return objectDao.query("from User a where a.id in (select userid from ItemUser where itemid = ?)",
				new Object[] { itemid }, 0, 0);
	}

	/**
	 * 列出某一项目单月的所有加班记录信息
	 * 
	 * @param overtime
	 * @return
	 */
	public Map listMonthOvers(Overtime overtime) {
		List list = objectDao.query("from Overtime where itemid = ? and substring(date, 1, 7) = ?", new Object[] {
				overtime.getItemid(), overtime.getDate() }, 0, 0);

		Map map = new HashMap();

		for (Object o : list) {
			// 超时对象
			Overtime otime = (Overtime) o;
			// 设置数据
			map.put(otime.getUserid() + "_" + getDayInMonth(otime.getDate()), otime.getHour());
		}
		return java.util.Collections.unmodifiableMap(map);
	}

	/**
	 * 获得日期
	 * 
	 * @param date
	 * @return
	 */
	private int getDayInMonth(String date) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(df.parse(date));

			return cal.get(Calendar.DAY_OF_MONTH);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
