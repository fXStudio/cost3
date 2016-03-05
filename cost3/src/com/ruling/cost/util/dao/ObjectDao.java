package com.ruling.cost.util.dao;

import java.util.List;
import com.ruling.cost.utils.PageModel;

public interface ObjectDao {
	public List getAll(Object o);

	public Object getById(Class o, int id);

	public Object getById(Class o, String id);

	public void save(Object o);

	public void save(List objs);

	public void update(Object o);

	public void update(List objs);

	public void delete(Object o);

	public List findPage(Object o, int currentPage, int pagesize);

	public int getTotalPage(Object o, int pagesize);

	public List getByParm(String o, Object[] values);

	public List findPageByParm(final Class c, final String hql,
			final int currentPage, final int pagesize);

	public int getTotalPage(List list, int pagesize);

	public PageModel searchPaginated(String hql);

	public PageModel searchPaginated(String hql, Object param);

	public PageModel searchPaginated(String hql, Object[] params);

	public PageModel searchPaginated(String hql, int offset, int pagesize);

	public PageModel searchPaginated(String hql, Object param, int offset,
			int pagesize);

	public PageModel searchPaginated(String hql, Object[] params, int offset,
			int pagesize);

	public String getsysdate();

	public List querySQL(String sql, Object values[], int firstResult,
			int maxResults);

	public List query(String hql, Object values[], int firstResult,
			int maxResults);

	public int updateSQL(String sql, Object... values);

	public int updateHQL(String sql, Object... values);
}
