package com.ruling.cost.overtime.action;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.ruling.cost.overtime.domain.Overtime;
import com.ruling.cost.overtime.service.OvertimeManager;
import com.ruling.cost.user.domain.User;
import com.ruling.cost.utils.BaseAction;

/**
 * 超时管理
 * 
 * @author FXStudio.com.cn(Ajaxfan)
 */
public class OvertimeAction extends BaseAction {
	/** 日期格式化工具 */
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	/** 数据库管理工具 */
	private OvertimeManager overtimeManager;

	/** 超时Bean工具类 */
	private Overtime overtime;

	public Overtime getOvertime() {
		return overtime;
	}

	public void setOvertime(Overtime overtime) {
		this.overtime = overtime;
	}

	public void setOvertimeManager(OvertimeManager overtimeManager) {
		this.overtimeManager = overtimeManager;
	}

	/**
	 * 列出所有的超时时间
	 */
	public String getAllOverTimes() {
		return showItemOvers();
	}

	/**
	 * 汇总的初始化页面
	 * 
	 * @return
	 */
	public String statisticsMain() {
		User u = (User)getServletRequest().getSession().getAttribute("login");
		
		if("system".equals(u.getType())){
			// 所有可执行的项目
			getServletRequest().setAttribute("items", overtimeManager.showAllItems());
		}else{
			// 所有可执行的项目
			getServletRequest().setAttribute("items", overtimeManager.showItemWithUser(u.getId()));
		}
		return "indexPage";
	}

	/**
	 * 汇总超时时间
	 * 
	 * @return
	 */
	public String showItemOvers() {
		// 查询日期
		getServletRequest().setAttribute("itemdate", overtime.getDate());
		// 项目名称
		getServletRequest().setAttribute("itemname", overtime.getItemname());
		// 项目ID
		getServletRequest().setAttribute("itemid", overtime.getItemid());

		DateFormat dformat = new SimpleDateFormat("yyyy-MM");
		try {
			Date date = dformat.parse(overtime.getDate());
			Calendar cal = Calendar.getInstance();// 日历工具实例
			cal.setTime(date);// 设置日期
			cal.set(Calendar.DATE, 1);// 把日期设置为当月第一天
			cal.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天

			getServletRequest().setAttribute("maxdate", cal.get(Calendar.DATE));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 项目参与人员
		getServletRequest().setAttribute("itemusers", overtimeManager.getItemUsers(overtime.getItemid()));

		return "statistics";
	}

	/**
	 * 保存修改数据
	 * 
	 * @return
	 */
	public String saveUpdate() {
		// 客户端参数
		String paramstr = overtime.getClientparam();

		// 遍历参数生成Overtime项目对象
		if (paramstr.trim().length() > 0) {
			List<Overtime> list = getModifiedRecords(paramstr);

			for (Overtime o : list) {
				// 设置项目ID
				o.setItemid(overtime.getItemid());
				// 保存数据
				overtimeManager.addOverTime(o);
			}
		}
		return "saved";
	}

	/** 返回的Json串 */
	private String res;

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	/**
	 * 列出所有的加班信息
	 * 
	 * @return
	 */
	public String listOvers() {
		// 日历工具
		Calendar cal = Calendar.getInstance();
		// 设置日历的模式，以星期天为一周的起始
		cal.setFirstDayOfWeek(Calendar.SUNDAY);

		cal.set(Calendar.DAY_OF_WEEK, 1);
		String begin = df.format(cal.getTime());

		cal.set(Calendar.DAY_OF_WEEK, 7);
		String end = df.format(cal.getTime());

		res = JSONObject.fromObject(overtimeManager.listOvers(begin, end)).toString();

		return "listOvers";
	}

	private String lres;

	public String getLres() {
		return lres;
	}

	public void setLres(String lres) {
		this.lres = lres;
	}

	public String listMonthOvers() {
		lres = JSONObject.fromObject(overtimeManager.listMonthOvers(overtime)).toString();

		return "listMonthOvers";
	}

	/**
	 * @return 当前登陆用户名
	 */
	private String getLoginUserName() {
		return ((User) this.getServletRequest().getSession().getAttribute("login")).getUsername();
	}

	private int getLoginUserId() {
		return ((User) this.getServletRequest().getSession().getAttribute("login")).getId();
	}

	/**
	 * @return 当前月
	 */
	private int getCurMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/**
	 * 本周的日期
	 */
	private List getDayList() {
		List<String> daylist = new ArrayList<String>();

		// 日历工具
		Calendar cal = Calendar.getInstance();
		// 设置日历的模式，以星期天为一周的起始
		cal.setFirstDayOfWeek(Calendar.SUNDAY);

		// 遍历出本周的所有日期
		for (int i = 1; i <= 7; i++) {
			cal.set(Calendar.DAY_OF_WEEK, i);

			daylist.add(df.format(cal.getTime()));
		}
		return java.util.Collections.unmodifiableList(daylist);
	}

	/**
	 * 当前周列表
	 * 
	 * @return
	 */
	private List<String> weekList() {
		List<String> weekList = new ArrayList<String>();

		for (int i = 1; i <= 52; i++) {
			weekList.add(MessageFormat.format("第{0}周", i));
		}
		return java.util.Collections.unmodifiableList(weekList);
	}

	/**
	 * 后的要保存的数据队列
	 * 
	 * @param paramstr
	 * @return
	 */
	private List<Overtime> getModifiedRecords(String paramstr) {
		// 要保存的数据队列对象
		List<Overtime> list = new ArrayList<Overtime>();

		// 分割参数表达式
		for (String sub : paramstr.split("&")) {
			Overtime o = new Overtime();

			String[] itemid_date_over = sub.split("_");
			o.setUserid(Integer.parseInt(itemid_date_over[0]));

			String[] date_over = itemid_date_over[1].split("=");
			o.setDate(overtime.getDate() + "-" + (date_over[0].length() == 1 ? "0" + date_over[0] : date_over[0]));
			o.setHour(date_over[1]);

			list.add(o);
		}
		return java.util.Collections.unmodifiableList(list);
	}
}
