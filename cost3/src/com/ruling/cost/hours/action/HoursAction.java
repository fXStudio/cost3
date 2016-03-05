package com.ruling.cost.hours.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ruling.cost.hours.domain.Hour;
import com.ruling.cost.hours.service.HoursManager;
import com.ruling.cost.item.domain.Item;
import com.ruling.cost.user.domain.User;
import com.ruling.cost.utils.BaseAction;

@SuppressWarnings("unchecked")
public class HoursAction extends BaseAction {
	private HoursManager hmservice;
	private User user;
	private Hour hour;
	private String res;

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public Hour getHour() {
		return hour;
	}

	public void setHour(Hour hour) {
		this.hour = hour;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String browe() {
		user = (User) getServletRequest().getSession().getAttribute("login");

		/** ************************ 设置日历 *************************** */
		Calendar cal = Calendar.getInstance();// 日历工具实例
		cal.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		cal.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天

		getServletRequest().setAttribute("maxdate", cal.get(Calendar.DATE));

		/** ************************ 设置日历 *************************** */

		// 参与的所有项目
		getServletRequest().setAttribute("items", hmservice.listItems(user.getId()));

		return "hour_browe";
	}

	public HoursManager getHmservice() {
		return hmservice;
	}

	public void setHmservice(HoursManager hmservice) {
		this.hmservice = hmservice;
	}
	
	public String listHours(){
		User u = (User) getServletRequest().getSession().getAttribute("login");
		
		res = JSONObject.fromObject(hmservice.listHours(u.getId())).toString();
		
		return "listHours";
	}

	public String saveUpdate() {
		User u = (User) getServletRequest().getSession().getAttribute("login");

		// 保存变更
		List<Hour> list = getModifiedRecords(hour.getClientparam(), u.getId());
		
		hmservice.save(list);

		return "saved";
	}

	/**
	 * 后的要保存的数据队列
	 * 
	 * @param paramstr
	 * @return
	 */
	private List<Hour> getModifiedRecords(String paramstr, Integer userid) {
		// 要保存的数据队列对象
		List<Hour> list = new ArrayList<Hour>();

		// 分割参数表达式
		for (String sub : paramstr.split("&")) {
			Hour o = new Hour();
			o.setUserid(userid);

			String[] itemid_date_over = sub.split("_");
			o.setItmeid(Integer.parseInt(itemid_date_over[0]));

			String[] date_over = itemid_date_over[1].split("=");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date_over[0]));
			o.setTodaydate(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
			o.setHour(date_over[1]);

			list.add(o);
		}
		return java.util.Collections.unmodifiableList(list);
	}
	
	@SuppressWarnings("null")
	public String toExceljsp() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User u = (User) request.getSession().getAttribute("login");
		// PageModel p=itemService.findAllItem(null);//查询所有的项目id
		List list = hmservice.getallitem();
		List userlist = hmservice.getalluser();// 获得所有用户
		List userlit = new ArrayList();
		List itemlist = new ArrayList();
		if (null != userlist || 0 != userlist.size()) {
			for (int i = 0; i < userlist.size(); i++) {
				User u1 = (User) userlist.get(i);
				Map m = new HashMap();
				m.put("key", u1.getUsername());
				m.put("value", u1.getId());
				userlit.add(m);
			}
		}
		if (null != list || 0 != list.size()) {
			for (int i = 0; i < list.size(); i++) {
				Item t = (Item) list.get(i);
				Map m = new HashMap();
				m.put("key", t.getItemname());
				m.put("value", t.getId());
				itemlist.add(m);

			}
		}

		List riqilist = new ArrayList();
		for (int i = 0; i < 12; i++) {
			Map m = new HashMap();
			m.put("key", i + 1);
			m.put("value", i + 1);
			riqilist.add(m);
		}
		request.setAttribute("itmelist", itemlist);
		request.setAttribute("riqi", riqilist);
		request.setAttribute("userlit", userlit);
		
		return "searchexcel";
	}
	
	InputStream excelStream;

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	/**
	 * 导出工时统计报表
	 * 
	 * @return
	 */
	public String excelWorkTime() {
		// 缓存IO
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		sDate = getServletRequest().getParameter("searchDate");

		// 生成保镖的二进制IO流
		this.hmservice.exportWorkTime(sDate, out);
		// 创建到客户端的二进制数据库
		excelStream = new ByteArrayInputStream(out.toByteArray());

		// 输出报表
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "excelWorkTime";
	}

	/**
	 * @return 统计报表
	 */
	public String excelStatistics() {
		// 缓存IO
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		sDate = getServletRequest().getParameter("searchDate");

		// 生成保镖的二进制IO流
		this.hmservice.excelStatistics(sDate, out);
		// 创建到客户端的二进制数据库
		excelStream = new ByteArrayInputStream(out.toByteArray());

		// 输出报表
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "excelStatistics";
	}

	private String sDate;

	public String getSDate() {
		return sDate;
	}
}
