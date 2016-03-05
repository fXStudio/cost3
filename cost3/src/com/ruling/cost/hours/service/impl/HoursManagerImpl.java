package com.ruling.cost.hours.service.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.helper.JavaEval;
import com.ruling.cost.hours.domain.Hour;
import com.ruling.cost.hours.service.HoursManager;
import com.ruling.cost.item.domain.Item;
import com.ruling.cost.item.domain.ItemUser;
import com.ruling.cost.item.service.ItemManager;
import com.ruling.cost.overtime.domain.Overtime;
import com.ruling.cost.subsidy.domain.Subsidy;
import com.ruling.cost.subsidy.domain.SubsidyHistory;
import com.ruling.cost.timemanage.domain.CWorkTime;
import com.ruling.cost.user.domain.User;
import com.ruling.cost.util.dao.ObjectDao;

@SuppressWarnings("unchecked")
public class HoursManagerImpl implements HoursManager {
	ObjectDao objDao;

	public void setObjDao(ObjectDao objDao) {
		this.objDao = objDao;
	}

	private ItemManager itemService;

	public void setItemService(ItemManager itemService) {
		this.itemService = itemService;
	}

	public List getallHoursManager(Integer userid, String[] arr) {
		String hql = "";
		if (null == arr[0]) {
			String date = getnowDateManager().split("-")[0].toString() + "-"
					+ getnowDateManager().split("-")[1].toString() + "-01";
			hql = " from Hour where userid=? and todaydate>='" + date + "'and todaydate<='" + getnowDateManager() + "'";
		} else {
			hql = " from Hour where userid=? and todaydate>='" + arr[0] + "'and todaydate<='" + arr[1] + "'";
		}
		Object[] values = { userid };
		return objDao.getByParm(hql, values);
	}

	public void addHoursManager(Hour h) {
		objDao.save(h);
	}

	public void updateHoursManager(Hour h) {
		objDao.update(h);

	}

	public Hour getHourById(Integer id) {
		return (Hour) objDao.getById(Hour.class, id);

	}

	public String getnowDateManager() {
		return objDao.getsysdate();
	}

	public List getItmeidlistManager(int userid, String starttime, String endtime) {
		String hql = "";
		String date = getnowDateManager().split("-")

		[0].toString() + "-" + getnowDateManager().split("-")[1].toString()

		+ "-01";
		Object[] values = { userid };
		if (date.equals(starttime)) {
			hql = "select  distinct  itmeid from Hour where userid=? and todaydate>='" + starttime
					+ "' and todaydate<'" + endtime + "'";
		} else {
			hql = "select  distinct  itmeid from Hour where userid=? and todaydate>='" + starttime
					+ "' and todaydate<'" + endtime + "'";
		}

		return objDao.getByParm(hql, values);

	}

	public List getweekhoursManager(int itmeid, int

	userid, String starttime, String endtime) {
		String hql = "";
		String date = getnowDateManager().split("-")[0].toString() + "-" + getnowDateManager().split("-")[1].toString()
				+ "-01";
		Object[] values = { itmeid, userid };
		if (date.equals(starttime)) {
			hql = "select hour from Hour where  itmeid=? and userid=? and todaydate>='" + starttime
					+ "' and todaydate<'" + endtime + "'";
		} else {
			hql = "select hour from Hour where  itmeid=? and userid=? and todaydate>='" + starttime
					+ "' and todaydate<'" + endtime + "'";
		}
		return objDao.getByParm(hql, values);
	}

	public void deleteHouserbyTime(int userid, String startime, String endtime) {
		String hql = "from Hour where  userid=? and todaydate>='" + startime + "' and todaydate<'" + endtime + "'";
		Object[] values = { userid };
		List list = objDao.getByParm(hql, values);
		if (null != list && 0 != list.size()) {
			for (int i = 0; i < list.size(); i++) {
				objDao.delete(list.get(i));
			}
		}

	}

	public List getItmesidByuserid(Integer userid) {
		Object[] values = { userid };
		String hql = "from ItemUser where userid=? order by itemid";
		List<ItemUser> itmeuserlist = objDao.getByParm(hql, values);
		List<ItemUser> returnList = new ArrayList<ItemUser>();
		for (int i = 0; i < itmeuserlist.size(); i++) {
			Item item = itemService.findItemById(itmeuserlist.get(i).getItemid());
			if (item.getItemstate().equals("end") || item.getItemstate().equals("stop")) {
				// itmeuserlist.remove(i);
			} else {
				returnList.add(itmeuserlist.get(i));
			}
		}
		return returnList;
	}

	public List getalluser() {
		return objDao.getAll("User");
	}

	public int gethoursByuseriditemid(int userid, int itemid, String[] arr) {
		int a = 0;
		String hql = "";
		if (null == arr[0]) {
			String date = getnowDateManager().split("-")[0].toString() + "-"
					+ getnowDateManager().split("-")[1].toString() + "-01";
			hql = "from Hour where  userid=? and itmeid=? and todaydate>='" + date + "' and todaydate<='"
					+ getnowDateManager() + "'";
		} else {
			hql = "from Hour where  userid=? and itmeid=? and todaydate>='" + arr[0] + "' and todaydate<='" + arr[1]
					+ "'";
		}
		Object[] values = { userid, itemid };
		List list = objDao.getByParm(hql, values);
		if (null != list && 0 != list.size()) {
			for (int i = 0; i < list.size(); i++) {
				Hour h = (Hour) list.get(i);
				int b = Integer.parseInt(h.getHour());
				a += b;
			}
		}
		return a;
	}

	public int getHourByuseridtime(int userid, String date) {
		Object[] values = { userid, date };
		int a = 0;
		String hql = "from Hour where  userid=? and todaydate=?";
		List list = objDao.getByParm(hql, values);
		if (null != list && 0 != list.size()) {
			for (int i = 0; i < list.size(); i++) {
				Hour h = (Hour) list.get(i);
				int b = Integer.parseInt(h.getHour());
				a += b;
			}
		}
		return a;
	}

	public List getHourByitemslistbyuseridweek(int userid, String starttime, String enddtime) {
		String hql = "";
		String date = getnowDateManager().split("-")

		[0].toString() + "-" + getnowDateManager().split("-")[1].toString()

		+ "-01";
		Object[] values = { userid };
		if (date.equals(starttime)) {
			hql = "select    itmeid from Hour where userid=? and todaydate>='" + starttime + "' and todaydate<'"
					+ enddtime + "'";
		} else {
			hql = "select    itmeid from Hour where userid=? and todaydate>='" + starttime + "' and todaydate<'"
					+ enddtime + "'";
		}

		return objDao.getByParm(hql, values);

	}

	@SuppressWarnings("null")
	public int getHoursbyItemid(int itemid, String[] arr) {
		int a = 0;
		String hql = "";
		if (null == arr[0]) {
			String date = getnowDateManager().split("-")[0].toString() + "-"
					+ getnowDateManager().split("-")[1].toString() + "-01";
			hql = "from Hour where itmeid=? and todaydate>='" + date + "' and todaydate<='" + getnowDateManager() + "'";
		} else {
			hql = "from Hour where itmeid=? and todaydate>='" + arr[0] + "'and todaydate<='" + arr[1] + "'";
		}
		Object[] values = { itemid };
		List list = objDao.getByParm(hql, values);
		if (null != list || 0 != list.size()) {
			for (int i = 0; i < list.size(); i++) {
				Hour h = (Hour) list.get(i);
				int b = Integer.parseInt(h.getHour());
				a += b;
			}
		}
		return a;
	}

	// 按项目分组得到一个项目所有人员的所有工时
	public Map<String, Integer> getHourByItemAllUser(String[] arr) {

		String hql = "";
		Map<String, Integer> hourMap = new HashMap<String, Integer>();
		if (null == arr[0]) {
			String date = getnowDateManager().split("-")[0].toString() + "-"
					+ getnowDateManager().split("-")[1].toString() + "-01";
			hql = "select itmeid,sum(hour)from Hour where todaydate>='" + date + "' and todaydate<='"
					+ getnowDateManager() + "' group by itmeid";
		} else {
			hql = "select itmeid,sum(hour)from Hour where todaydate>='" + arr[0] + "' and todaydate<='" + arr[1]
					+ "' group by itmeid";
		}
		List list = objDao.getByParm(hql, null);
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			String itemid = obj[0].toString();
			Integer hour = Integer.parseInt(obj[1].toString());
			hourMap.put(itemid, hour);
		}

		return hourMap;
	}

	public Map<String, Subsidy> getSubsidy() {
		Subsidy sub = null;
		List list = objDao.getAll("Subsidy");
		Map<String, Subsidy> subMap = new HashMap<String, Subsidy>();
		for (int i = 0; i < list.size(); i++) {
			Subsidy ss = (Subsidy) list.get(i);
			subMap.put((ss.getUser().getId() + "").trim(), ss);
		}
		return subMap;
	}

	public List getallitem() {
		return objDao.getAll("Item");

	}

	public void deletehourby(List user, List item) {
		String hql = " from Hour where userid=? and itmeid=?";
		for (int i = 0; i < user.size(); i++) {
			for (int j = 0; j < item.size(); j++) {
				Object[] values = { Integer.parseInt(user.get(i).toString()), Integer.parseInt(item.get(j).toString()) };
				List list = objDao.getByParm(hql, values);
				for (int k = 0; k < list.size(); k++) {
					objDao.delete(list.get(k));
				}
			}
		}

	}

	public void addHoursManager(List hourList) {
		this.objDao.save(hourList);
	}

	public void updateHoursManager(List h) {

		this.objDao.update(h);
	}

	public Map getHourByItemUser(String[] arr) {
		Map<String, Integer> hourMap = new HashMap<String, Integer>();
		String hql = "";
		if (null == arr[0]) {
			String date = getnowDateManager().split("-")[0].toString() + "-"
					+ getnowDateManager().split("-")[1].toString() + "-01";
			hql = "select itmeid,userid,sum(hour) as hour from Hour where todaydate>='" + date + "' and todaydate<='"
					+ getnowDateManager() + "' GROUP BY userid,itmeid";
		} else {
			hql = "select itmeid,userid,sum(hour) as hour from Hour where todaydate>='" + arr[0] + "' and todaydate<='"
					+ arr[1] + "' GROUP BY userid,itmeid";
		}
		List list = objDao.getByParm(hql, null);
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			int itemid = (Integer) obj[0];
			int userid = (Integer) obj[1];
			int hour = Integer.parseInt(obj[2].toString());
			hourMap.put((itemid + "" + userid).trim(), hour);
		}
		return hourMap;
	}

	public List<Item> listItems(Integer id) {
		List<Item> list = objDao.getByParm(
				"from Item where id in (select itemid from ItemUser where userid = ?) and itemstate = 'activity'",
				new Object[] { id });

		for (Item item : list) {
			List<CWorkTime> cls = objDao.getByParm("from CWorkTime where itemId = ? and userId = ?", new Object[] {
					item.getId(), id });

			if (cls.size() > 0) {
				item.setTimelong(cls.get(0).getResidTime());
			}
		}

		return list;
	}

	/**
	 * 保存变更
	 * 
	 * @param param
	 */
	public void save(List<Hour> list) {
		for (Hour h : list) {
			List l = objDao.getByParm("from Hour where userid = ? and itmeid = ? and todaydate = ?", new Object[] {
					h.getUserid(), h.getItmeid(), h.getTodaydate()

			});
			int count = 0;

			if (l.size() > 0) {
				Hour tmp = (Hour) l.get(0);
				count = Integer.parseInt(tmp.getHour());
				objDao.delete(tmp);
			}

			List<CWorkTime> cworks = objDao.getByParm("from CWorkTime where userid = ? and itemid = ?", new Object[] {
					h.getUserid(), h.getItmeid() });

			// 项目的剩余时间
			if (cworks.size() > 0) {
				CWorkTime c = cworks.get(0);
				if (c.getTimelong() <= 9999) {
					c.setResidTime(c.getResidTime() - (Integer.parseInt(h.getHour()) - count));
				}
				objDao.update(c);
			}
			if (!"0".equals(h.getHour())) {
				objDao.save(h);
			}
		}
	}

	public Map<String, String> listHours(int id) {
		List<Hour> list = objDao.getByParm("from Hour where userid=? and substring(todaydate, 1, 7) = ?", new Object[] {
				id, new SimpleDateFormat("yyyy-MM").format(new Date()) });

		Map<String, String> map = new HashMap<String, String>();
		for (Hour h : list) {
			map.put(h.getItmeid() + "_" + getDayInMonth(h.getTodaydate()), h.getHour());
		}
		return map;
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

	public Integer getTimelong(Integer id) {
		List<CWorkTime> list = objDao.getByParm("from CWorkTime where userid = ?", new Object[] { id });

		if (list.size() > 0) {
			CWorkTime c = list.get(0);

			return c.getTimelong();
		}
		return 0;
	}

	/**
	 * 导出工时统计报表
	 * 
	 * @param date
	 * @param out
	 */
	public void exportWorkTime(String date, OutputStream out) {
		exportExcel(out, "from Overtime where substring(date, 1, 7)", date);
	}

	private final String[] cols = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
			"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };

	/**
	 * 导出报表
	 * 
	 * @param out
	 * @param sql
	 */
	private void exportExcel(OutputStream out, String sql, String date) {
		try {
			// 加载报表模板
			InputStream in = HoursManagerImpl.class.getResourceAsStream("/empty.xls");
			// 获得工作表
			Workbook work = new HSSFWorkbook(in);
			// 得到excel的第0张表
			Sheet sheet = work.getSheetAt(0);

			// 行列对象
			Row row = null;
			Cell cell = null;

			// 设置左上角的合并单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));

			// 设置合并单元格的内容
			row = sheet.createRow(0);
			cell = row.createCell((short) 0);
			cell.setCellValue("项目");

			List<User> users = objDao.getByParm("from User where employtype != ?", new Object[] { "离职" });
			List<Item> items = objDao.getByParm("from Item where itemstate = ?", new Object[] { "activity" });
			List<Overtime> overTimes = objDao.getByParm("from Overtime where substring(date, 1, 7) = ?",
					new Object[] { date });
			List<Hour> workhours = objDao.getByParm("from Hour where substring(todaydate, 1, 7) = ?",
					new Object[] { date });

			// 设置左上角的合并单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, users.size() - 1));

			CellStyle style = work.createCellStyle();
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

			row = sheet.createRow(1);
			for (int i = 1, n = 0; n < users.size(); i++, n++) {
				cell = row.createCell(i);
				cell.setCellStyle(style);
				cell.setCellValue(users.get(n).getUsername());
			}
			cell = row.createCell(users.size() + 1);
			cell.setCellStyle(style);
			cell.setCellValue("统计");

			for (int i = 2, n = 0; n < items.size(); i++, n++) {
				row = sheet.createRow(i);
				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue(items.get(n).getItemname());

				for (int j = 1; j <= users.size(); j++) {
					cell = row.createCell(j);
					cell.setCellValue(0);
				}
				cell = row.createCell(users.size() + 1);
				cell.setCellFormula("SUM(" + getColsName(1) + (cell.getRowIndex() + 1) + ":"
						+ getColsName(cell.getColumnIndex() - 1) + (cell.getRowIndex() + 1) + ")");
			}

			// 最后一行统计
			row = sheet.createRow(items.size() + 2);
			cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue("总计");
			for (int j = 1; j <= users.size(); j++) {
				cell = row.createCell(j);

				String colName = getColsName(cell.getColumnIndex());

				cell.setCellFormula("SUM(" + colName + "3:" + colName + cell.getRowIndex() + ")");
			}

			outter: for (Overtime o : overTimes) {
				int i = 0;
				step1: for (; i < users.size(); i++) {
					User u = users.get(i);
					if (o.getUserid() == u.getId()) {
						break step1;
					}
				}

				int n = 0;
				step2: for (; n < items.size(); n++) {
					Item item = items.get(n);
					if (o.getItemid() == item.getId()) {
						break step2;
					}
				}
				if (i == users.size() || n == items.size()) {
					continue outter;
				}
				sheet.getRow(n + 2).getCell(i + 1).setCellValue(Integer.parseInt(o.getHour()));
			}
			outter: for (Hour o : workhours) {
				int i = 0;
				step1: for (; i < users.size(); i++) {
					User u = users.get(i);
					if (o.getUserid() == u.getId()) {
						break step1;
					}
				}

				int n = 0;
				step2: for (; n < items.size(); n++) {
					Item item = items.get(n);
					if (o.getItmeid() == item.getId()) {
						break step2;
					}
				}
				if (i == users.size() || n == items.size()) {
					continue outter;
				}
				double val = sheet.getRow(n + 2).getCell(i + 1).getNumericCellValue();
				sheet.getRow(n + 2).getCell(i + 1).setCellValue(Integer.parseInt(o.getHour()) + (int) val);
			}

			work.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getColsName(int rowIndex) {
		int n = rowIndex / 26 - 1;
		int j = rowIndex % 26;

		StringBuilder sb = new StringBuilder();
		sb.append(n > -1 ? cols[n] : "");
		sb.append(cols[j]);

		return sb.toString();
	}

	public void excelStatistics(String date, OutputStream out) {
		try {
			// 加载报表模板
			InputStream in = HoursManagerImpl.class.getResourceAsStream("/empty.xls");
			// 获得工作表
			Workbook work = new HSSFWorkbook(in);
			// 得到excel的第0张表
			Sheet sheet = work.getSheetAt(0);

			// 行列对象
			Row row = null;
			Cell cell = null;

			// 设置左上角的合并单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));

			// 设置合并单元格的内容
			row = sheet.createRow(0);
			cell = row.createCell((short) 0);
			cell.setCellValue("项目");

			List<User> users = objDao.getByParm("from User where employtype != ?", new Object[] { "离职" });
			List<Item> items = objDao.getByParm("from Item where itemstate = ?", new Object[] { "activity" });
			List<Overtime> overTimes = objDao.getByParm("from Overtime where substring(date, 1, 7) = ?",
					new Object[] { date });
			List<Hour> workhours = objDao.getByParm("from Hour where substring(todaydate, 1, 7) = ?",
					new Object[] { date });

			// 设置左上角的合并单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, users.size() - 1));

			CellStyle style = work.createCellStyle();
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

			// 用户名
			row = sheet.createRow(1);
			for (int i = 1, n = 0; n < users.size(); i++, n++) {
				cell = row.createCell(i);
				cell.setCellStyle(style);
				cell.setCellValue(users.get(n).getUsername());

				User u = users.get(n);
				u.setFormula(getFormulaByUserId(date, u));
			}
			cell = row.createCell(users.size() + 1);
			cell.setCellStyle(style);
			cell.setCellValue("统计");

			// 项目名
			for (int i = 2, n = 0; n < items.size(); i++, n++) {
				row = sheet.createRow(i);
				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue(items.get(n).getItemname());

				for (int j = 1; j <= users.size(); j++) {
					cell = row.createCell(j);
					cell.setCellValue(0);
				}
				cell = row.createCell(users.size() + 1);
				cell.setCellFormula("SUM(" + getColsName(1) + (cell.getRowIndex() + 1) + ":"
						+ getColsName(cell.getColumnIndex() - 1) + (cell.getRowIndex() + 1) + ")");
			}

			// 最后一行统计
			row = sheet.createRow(items.size() + 2);
			cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue("总计");
			for (int j = 1; j <= users.size(); j++) {
				cell = row.createCell(j);

				String colName = getColsName(cell.getColumnIndex());

				cell.setCellFormula("SUM(" + colName + "3:" + colName + cell.getRowIndex() + ")");
			}

			outter: for (Overtime o : overTimes) {
				int i = 0;
				step1: for (; i < users.size(); i++) {
					User u = users.get(i);
					if (o.getUserid() == u.getId()) {
						break step1;
					}
				}

				int n = 0;
				step2: for (; n < items.size(); n++) {
					Item item = items.get(n);
					if (o.getItemid() == item.getId()) {
						break step2;
					}
				}
				if (i == users.size() || n == items.size()) {
					continue outter;
				}
				sheet.getRow(n + 2).getCell(i + 1).setCellValue(calc(Integer.parseInt(o.getHour()), users.get(i)));
			}

			outter: for (Hour o : workhours) {
				int i = 0;
				step1: for (; i < users.size(); i++) {
					User u = users.get(i);
					if (o.getUserid() == u.getId()) {
						break step1;
					}
				}

				int n = 0;
				step2: for (; n < items.size(); n++) {
					Item item = items.get(n);
					if (o.getItmeid() == item.getId()) {
						break step2;
					}
				}
				if (i == users.size() || n == items.size()) {
					continue outter;
				}
				double val = sheet.getRow(n + 2).getCell(i + 1).getNumericCellValue();
				sheet.getRow(n + 2).getCell(i + 1).setCellValue(
						numberFormatfuc(calcBase(o.getHour(), users.get(i)) + val));
			}
			work.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getFormulaByUserId(String date, User u) {
		List list = (List) objDao.getByParm("from Subsidy where user.id = ?", new Object[] { u.getId() });
		if (list.size() > 0) {
			Subsidy sub = (Subsidy) list.get(0);

			String formula = "2016-01".compareTo(date) < 0 ? u.getNat().getWelfareBak().getWformula(): u.getNat().getWelfare().getWformula();

			list = (List) objDao.getByParm("from SubsidyHistory where subsidyId = ? order by id desc",
					new Object[] { sub.getId() });

			if (list.size() > 0) {
				SubsidyHistory subhis = (SubsidyHistory) list.get(0);

				formula = formula.replaceAll("\\@\\{basepay\\}", subhis.getBasepay());
				formula = formula.replaceAll("\\@\\{Subsidies\\}", getSubsities(subhis));
				formula = formula.replaceAll("\\s", "");
			} else {
				formula = formula.replaceAll("\\@\\{basepay\\}", sub.getBasepay());
				formula = formula.replaceAll("\\@\\{Subsidies\\}", getSubsities(sub));
				formula = formula.replaceAll("\\s", "");
			}

			return formula;
		}
		return null;
	}

	private double numberFormatfuc(double d) {
		BigDecimal b = new BigDecimal(d);
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		return f1;
	}

	/**
	 * 正常工作时间的成本
	 * 
	 * @param hour
	 * @param u
	 * @return
	 */
	private double calcBase(String hour, User u) {
		if (u.getFormula() != null) {
			return Double.parseDouble(JavaEval.eval(u.getFormula() + "#")) * Integer.parseInt(hour);
		}
		return 0;
	}

	private String getSubsities(SubsidyHistory sub) {
		int res = Integer.parseInt(sub.getSubsidy1());
		res += Integer.parseInt(sub.getSubsidy2());
		res += Integer.parseInt(sub.getSubsidy3());

		return "" + res;
	}

	private String getSubsities(Subsidy sub) {
		int res = Integer.parseInt(sub.getSubsidy1());
		res += Integer.parseInt(sub.getSubsidy2());
		res += Integer.parseInt(sub.getSubsidy3());

		return "" + res;
	}

	/**
	 * 加班时间的成本
	 * 
	 * @param hours
	 * @param u
	 * @return
	 */
	private double calc(int hours, User u) {
		List list = (List) objDao.getByParm("from Subsidy where user.id = ?", new Object[] { u.getId() });

		if (list.size() > 0) {
			return hours * (Double.parseDouble(((Subsidy) list.get(0)).getBasepay()) / 21.75 / 8) * 1.5;
		}
		return 0;
	}
}
