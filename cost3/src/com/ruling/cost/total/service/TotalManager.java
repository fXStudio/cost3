package com.ruling.cost.total.service;

import java.util.List;

import com.ruling.cost.total.domain.Total;
import com.ruling.cost.utils.PageModel;

public interface TotalManager {
	public PageModel querylist(int userid, String date, int[] itemids,
			int status);

	public void addtotal(Total total);

	public void deletetotal(String id);

	public Total findtotal(Total total);

	public void updatetotal(Total total);

	public List<Total> queryallList(int userid, String date, int status);// 查询页面上的list的方法

	public List<Total> queryalllist1(int userid, String date);

	public List<Total> queryalllistbyYear(int userid, String date, int itemid);// 按年查询

	public List<Total> queryalllistbyYearitemid(int itemid, String date,
			int userid);// 按年查询

	public Total findtotal1(Total total);// 项目预算专用
}
