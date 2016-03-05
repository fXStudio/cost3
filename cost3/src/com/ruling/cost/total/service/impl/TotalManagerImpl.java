package com.ruling.cost.total.service.impl;

import java.util.List;

import com.ruling.cost.subsidy.domain.Subsidy;
import com.ruling.cost.total.domain.Total;
import com.ruling.cost.total.service.TotalManager;
import com.ruling.cost.util.dao.ObjectDao;
import com.ruling.cost.utils.PageModel;

public class TotalManagerImpl implements TotalManager {
	private ObjectDao objectDao;

	public void addtotal(Total total) {
		this.objectDao.save(total);

	}

	public void deletetotal(String id) {
		this.objectDao.delete(this.objectDao.getById(Total.class, Integer
				.parseInt(id)));

	}

	public Total findtotal(Total total) {
		return (Total) this.objectDao.getById(Total.class, total.getId());
	}

	public PageModel querylist(int userid, String date, int[] itemids,
			int status) {

		String hql = " from Total where status=" + status;
		if (userid != 0) {
			hql += " and userid=" + userid;
		}
		if (date != null && !date.equals("")) {
			hql += " and itemname='" + date + "'";
		}
		if (itemids != null && itemids.length > 0) {

			for (int i = 0; i < itemids.length; i++) {
				if (i == 0) {
					hql += "  and itemid=" + itemids[i];
				} else {
					hql += " or itemid=" + itemids[i];

				}
			}
		}
		// obj[1]=date;

		return this.objectDao.searchPaginated(hql, null);
		// return null;
	}

	public List<Total> queryallList(int userid, String date, int status) {
		String hql = " from Total where status=" + status;
		if (date != null && !date.equals("")) {
			hql += " and createdate='" + date + "'";
		}
		if (userid != 0) {
			hql += "and itemid=" + userid;
		}
		return this.objectDao.getByParm(hql, null);
	}

	public void updatetotal(Total total) {
		this.objectDao.update(total);

	}

	public ObjectDao getObjectDao() {
		return objectDao;
	}

	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}

	public List<Total> queryalllist1(int userid, String date) {
		String hql = "select itemid,itemname,htvalue,sum(peoplevalue),sum(palyvalue),mv,sum(extl) ,sum(chengben) ,sum(revenue),sum(sj) from Total where status=0";
		if (userid != 0) {
			hql += " and itemid=" + userid;
		}
		if (date != null && !date.equals("")) {
			hql += " and createdate='" + date + "'";
		}
		hql += " group by itemname";
		return this.objectDao.getByParm(hql, null);
	}

	public List<Total> queryalllistbyYear(int userid, String date, int itemid) {
		String hql = "select itemid,itemname,htvalue,sum(peoplevalue),sum(palyvalue),mv,sum(extl) ,sum(chengben) ,sum(revenue),createdate,sum(sj) from Total where status=0 ";
		if (date != null && !date.equals("")) {
			hql += " and createdate>='" + date + "-01'" + " and createdate<='"
					+ date + "-12'";
		}
		if (itemid != 0) {
			hql += " and itemid=" + itemid;

		}
		if (userid != 0) {
			hql += "and userid=" + userid;
		}

		hql += "  group by itemname";
		return this.objectDao.getByParm(hql, null);
	}

	public List<Total> queryalllistbyYearitemid(int itemid, String date,
			int userid) {
		String hql = " from Total where status=0 and userid=" + userid
				+ " and itemid=" + itemid;
		if (date != null && !date.equals("")) {
			hql += " and createdate>='" + date + "-01' and createdate<='"
					+ date + "-12'";
		}
		return this.objectDao.getByParm(hql, null);
	}

	public Total findtotal1(Total total) {
		Total total1 = null;
		String hql = " from Total where status=1";
		if (total != null && !total.equals("")) {
			if (total.getItemid() != null) {
				hql += " and itemid=" + total.getItemid();
			}
		}
		List<Total> list = this.objectDao.getByParm(hql, null);
		if (list != null && list.size() > 0) {
			total1 = list.get(0);
		}
		return total1;
	}

}
