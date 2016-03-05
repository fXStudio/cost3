package com.ruling.cost.repairhour.service.impl;

import java.util.List;

import com.ruling.cost.hours.domain.Hour;
import com.ruling.cost.repairhour.service.RepairHourService;
import com.ruling.cost.util.dao.ObjectDao;
import com.ruling.cost.utils.PageModel;
import com.ruling.cost.utils.SystemException;

public class RepairHourServiceImpl implements RepairHourService {
	ObjectDao objDao;

	public ObjectDao getObjDao() {
		return objDao;
	}

	public void setObjDao(ObjectDao objDao) {
		this.objDao = objDao;
	}

	public void addRepairHour(Hour h) {

		Hour hour = this.findByParm(h);

		if (hour == null) {
			throw new SystemException("还没有" + h.getTodaydate() + "的工时");
		}

		int totalHour = this.findHourByUserIdAndToday(h.getUserid(), h
				.getTodaydate());

		int todayHour = Integer.parseInt(h.getHour());

		if ((totalHour + todayHour) > 8) {
			throw new SystemException(h.getTodaydate() + "的工时大于8小时，不允许补录");
		}
		hour.setHour(h.getHour());

		this.objDao.update(hour);
	}

	public void deleteRepairHour(int id) {

		Hour h = (Hour) this.objDao.getById(Hour.class, id);

		this.objDao.delete(h);

	}

	public PageModel findAllRepairHour(Hour h) {
		PageModel pm = new PageModel();

		String hql = " from Hour where repairhour='Y'";

		if (h == null) {

			pm = this.objDao.searchPaginated(hql);
		}
		return null;
	}

	// 查询工时表是否有今天的工时
	public Hour findByParm(Hour h) {

		StringBuffer sb = new StringBuffer();
		sb.append(" from Hour where userid='");
		sb.append(h.getUserid());
		sb.append("' and itmeid='");
		sb.append(h.getItmeid());
		sb.append("' and todaydate='");
		sb.append(h.getTodaydate());
		sb.append("'");

		List list = this.objDao.getByParm(sb.toString(), null);
		if (list.size() == 0) {
			return null;
		}
		return (Hour) list.get(0);
	}

	// 查询工时表用户今天的所有工时
	public int findHourByUserIdAndToday(int userid, String today) {
		StringBuffer sb = new StringBuffer();
		sb.append("select hour from Hour where userid='");
		sb.append(userid);
		sb.append("' and todaydate='");
		sb.append(today);
		sb.append("'");
		List list = this.objDao.getByParm(sb.toString(), null);
		if (list.size() == 0) {
			return 0;
		}
		int totalHour = 0;
		for (int i = 0; i < list.size(); i++) {
			totalHour += Integer.parseInt(list.get(i).toString());
		}
		return totalHour;
	}

}
