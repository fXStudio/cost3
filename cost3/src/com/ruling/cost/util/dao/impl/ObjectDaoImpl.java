package com.ruling.cost.util.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.ruling.cost.util.dao.ObjectDao;
import com.ruling.cost.utils.PageModel;

public class ObjectDaoImpl extends HibernateDaoSupport implements ObjectDao {
	List list;

	public List getAll(Object o) {
		List list = this.getHibernateTemplate().find("from " + o);
		return list;
	}

	public Object getById(Class o, int id) {
		Object obj = (Object) this.getHibernateTemplate().load(o, id);
		return obj;
	}

	public Object getById(Class o, String id) {
		Object obj = (Object) this.getHibernateTemplate().load(o, id);
		return obj;
	}

	public void save(Object o) {
		this.getHibernateTemplate().save(o);
	}

	public int getTotalPage(Object o, int pagesize) {
		int total = this.getAll(o).size();
		int totalPage = 0;
		if (total % pagesize == 0) {
			totalPage = total / pagesize;
		} else {
			totalPage = total / pagesize + 1;
		}

		if (totalPage == 0) {
			totalPage = 1;
		}
		return totalPage;
	}

	public void update(Object o) {
		this.getHibernateTemplate().update(o);

	}

	public List findPage(final Object o, final int currentPage,
			final int pagesize) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query q = session.createQuery("from " + o);
				q.setFirstResult((currentPage - 1) * pagesize);
				q.setMaxResults(pagesize);
				list = q.list();
				return list;
			}

		});
		return list;
	}

	public List findPageByParm(final Class c, final String hql,
			final int currentPage, final int pagesize) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				SQLQuery q = session.createSQLQuery(hql);
				q.addEntity(c);
				q.setFirstResult((currentPage - 1) * pagesize);
				q.setMaxResults(pagesize);
				list = q.list();
				return list;
			}

		});
		return list;
	}

	public void delete(Object o) {
		this.getHibernateTemplate().delete(o);

	}

	public List getByParm(String hql, Object[] values) {
		List list = this.getHibernateTemplate().find(hql, values);
		return list;
	}

	public int getTotalPage(List list, int pagesize) {
		int total = list.size();
		int totalPage = 0;
		if (total % pagesize == 0) {
			totalPage = total / pagesize;
		} else {
			totalPage = total / pagesize + 1;
		}

		if (totalPage == 0) {
			totalPage = 1;
		}
		return totalPage;
	}

	public PageModel searchPaginated(String hql) {
		return searchPaginated(hql, null);
	}

	public PageModel searchPaginated(String hql, Object param) {
		return searchPaginated(hql, new Object[] { param });
	}

	public PageModel searchPaginated(String hql, Object[] params) {
		return searchPaginated(hql, params, com.ruling.cost.utils.SystemContext
				.getOffset(), com.ruling.cost.utils.SystemContext.getPagesize());
	}

	public PageModel searchPaginated(String hql, int offset, int pagesize) {
		return searchPaginated(hql, null, offset, pagesize);
	}

	public PageModel searchPaginated(String hql, Object param, int offset,
			int pagesize) {
		return searchPaginated(hql, new Object[] { param }, offset, pagesize);
	}

	public PageModel searchPaginated(String hql, Object[] params, int offset,
			int pagesize) {
		// 获取记录总数
		String countHql = getCountQuery(hql);
		Query query = getSession().createQuery(countHql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		int total = ((Long) query.uniqueResult()).intValue();

		// 获取结果集
		query = getSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		query.setFirstResult(offset);
		query.setMaxResults(pagesize);
		List datas = query.list();

		// 返回PagerModel
		PageModel pm = new PageModel();
		pm.setDatas(datas);
		pm.setTotal(total);
		return pm;
	}

	/**
	 * 根据HQL语句，获得查询总记录数的HQL语句 如： select ... from Organization o where o.parent
	 * is null 经过转换，可以得到： select count(*) from Organziation o where o.parent is
	 * null
	 * 
	 * @param hql
	 * @return
	 */
	private String getCountQuery(String hql) {
		int index = hql.indexOf("from");
		if (index != -1) {
			return "select count(*) " + hql.substring(index);
		}
		return null;
	}

	public String getsysdate() {
		String sql = "select CURDATE() from dual";
		Query query = getSession().createSQLQuery(sql);
		List list = query.list();

		return list.get(0).toString();
	}

	public void save(List objs) {

		for (int i = 0; i < objs.size(); i++) {
			this.getHibernateTemplate().save(objs.get(i));
		}
	}

	public void update(List objs) {
		for (int i = 0; i < objs.size(); i++) {
			this.getHibernateTemplate().update(objs.get(i));
		}
	}

	public List querySQL(String sql, Object values[], int firstResult,
			int maxResults) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		if (firstResult >= 0) {
			sqlQuery.setFirstResult(firstResult);
		}
		if (maxResults > 0) {
			sqlQuery.setMaxResults(maxResults);
		}
		if (values != null) {
			for (int i = 0, length = values.length; i < length; i++) {
				sqlQuery.setParameter(i, values[i]);
			}
		}
		return sqlQuery.list();
	}

	public List query(String hql, Object values[], int firstResult,
			int maxResults) {
		Query query = getSession().createQuery(hql);
		if (firstResult >= 0) {
			query.setFirstResult(firstResult);
		}
		if (maxResults > 0) {
			query.setMaxResults(maxResults);
		}
		if (values != null) {
			for (int i = 0, length = values.length; i < length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}

	public int updateSQL(String sql, Object... values) {
		Query query = getSession().createSQLQuery(sql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.executeUpdate();
	}

	public int updateHQL(String sql, Object... values) {
		Query query = getSession().createQuery(sql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.executeUpdate();
	}
}
