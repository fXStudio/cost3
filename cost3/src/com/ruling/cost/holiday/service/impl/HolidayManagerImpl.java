package com.ruling.cost.holiday.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruling.cost.holiday.domain.Holiday;
import com.ruling.cost.holiday.service.HolidayManager;
import com.ruling.cost.util.dao.ObjectDao;
import com.ruling.cost.utils.PageModel;
import com.ruling.cost.utils.SystemException;

public class HolidayManagerImpl implements HolidayManager {

	private ObjectDao objectDao;

	public ObjectDao getObjectDao() {
		return objectDao;
	}

	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}

	public void add(Holiday holiday) throws Exception {

		// 检查年份和日期是否有重复
		Boolean flag = checkYearDate(holiday);

		if (!flag) {

			throw new SystemException("年份和日期有重复，不允许添加！");
		}

		this.objectDao.save(holiday);
	}

	private Boolean checkYearDate(Holiday holiday) {

		StringBuffer sb = new StringBuffer();
		sb.append(" from Holiday where 1=1");
		if (!"".equals(holiday.getHoliyear())) {
			sb.append(" and holiyear='");
			sb.append(holiday.getHoliyear());
			sb.append("'");
		}
		if (!"".equals(holiday.getHolidate())) {
			sb.append(" and holidate='");
			sb.append(holiday.getHolidate());
			sb.append("'");
		}

		List list = this.objectDao.getByParm(sb.toString(), null);
		if (list.size() == 0) {
			return true;
		}
		return false;
	}

	public void deleteHoliday(int holidayid) {
		Holiday h = (Holiday) this.objectDao.getById(Holiday.class, holidayid);
		this.objectDao.delete(h);
	}

	public PageModel findAllHolidayForPageModel(Holiday h) {
		PageModel pm = new PageModel();

		StringBuffer sb = new StringBuffer(" from Holiday where 1=1 ");

		if (h == null) {

			pm = this.objectDao.searchPaginated(sb.toString());
		}
		convertQuerySqlHoliday(h, sb);

		pm = this.objectDao.searchPaginated(sb.toString());

		return pm;
	}

	// 拼装查询条件
	private String convertQuerySqlHoliday(Holiday h, StringBuffer sb) {

		if (h == null) {
			return sb.toString();
		}
		if (!"".equals(h.getHoliyear())) {
			sb.append(" and holiyear like'%");
			sb.append(h.getHoliyear());
			sb.append("&'");
		} else if (!"".equals(h.getHolidayname())) {
			sb.append(" and holidayname like'%");
			sb.append(h.getHolidayname());
			sb.append("%'");
		} else if (!"".equals(h.getHolidate())) {
			sb.append(" and holidate='");
			sb.append(h.getHolidate());
			sb.append("'");
		}
		return sb.toString();
	}

	public void updateHoliday(Holiday holiday) throws Exception {

		boolean flag = this.checkYearDate(holiday);

		if (!flag) {

			throw new SystemException("年份和日期有重复，不允许保存！");
		}
		this.objectDao.update(holiday);
	}

	public void deleteHolidayArray(String[] ids) {

		for (int i = 0; i < ids.length; i++) {

			Holiday h = (Holiday) this.objectDao.getById(Holiday.class, Integer
					.parseInt(ids[i].trim()));

			this.objectDao.delete(h);
		}
	}

	public Holiday findHolidayById(int holidayid) {

		return (Holiday) this.objectDao.getById(Holiday.class, holidayid);
	}

	public Map<String, String> findAllHoliday() {

		Calendar cal = Calendar.getInstance();
		String year = (cal.get(Calendar.YEAR) + "").trim();
		StringBuffer sb = new StringBuffer();
		sb.append(" from Holiday where holiyear='");
		sb.append(year);
		sb.append("'");
		List<String> list = this.objectDao.getByParm(sb.toString(), null);
		Map<String, String> holidayMap = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			holidayMap.put(list.get(i).toString(), list.get(i).toString());
		}
		return holidayMap;
	}

}
