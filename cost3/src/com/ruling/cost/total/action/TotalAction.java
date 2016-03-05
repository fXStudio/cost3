package com.ruling.cost.total.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.ruling.cost.hours.service.HoursManager;
import com.ruling.cost.item.domain.Item;
import com.ruling.cost.item.domain.ItemUser;
import com.ruling.cost.item.service.ItemManager;
import com.ruling.cost.subsidy.domain.Subsidy;
import com.ruling.cost.total.domain.Total;
import com.ruling.cost.total.service.TotalManager;
import com.ruling.cost.user.domain.User;
import com.ruling.cost.utils.BaseAction;
import com.ruling.cost.utils.DateUtil;
import com.ruling.cost.utils.JxlExceptor;

@SuppressWarnings("serial")
public class TotalAction extends BaseAction {
	private static final Logger log = Logger.getLogger(TotalAction.class);
	TotalManager totalService;
	private Total total;
	private ItemManager itemService;
	private List<Item> listitem;
	private String id;
	HoursManager hoursservice;
	private String itemid;
	private String date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Item> getListitem() {
		return listitem;
	}

	public void setListitem(List<Item> listitem) {
		this.listitem = listitem;
	}

	public String browe() {
		log.info("进入项目统计查询的方法！");
		ActionContext ct = ActionContext.getContext();
		Map session = ct.getSession();
		User u = (User) session.get("login");
		System.out.println(u.getUsername());
		String itemname = "";
		int[] arr = null;
		if (null != total) {
			if (total.getItemname() != null && !total.getItemname().equals("")) {
				itemname = total.getItemname();
			}
		}
		log.info("查询列表结束");
		if (u.getType().equals("system")) {
			this.getServletRequest().setAttribute("pm",
					totalService.querylist(0, itemname, null, 0));
		} else {
			if (u.getWorklevel().equals("manager")) {
				List list = itemService.queryItemByMangerid(u.getId());
				;
				arr = new int[list.size()];
				for (int i = 0; i < list.size(); i++) {
					ItemUser iu = (ItemUser) list.get(i);
					System.out.println(iu.getItemid() + "******");
					arr[i] = iu.getItemid();
					// Object[] obj=(Object[]) list.get(i);
					// arr[i]=Integer.parseInt(obj[0].toString());
				}
				this.getServletRequest().setAttribute("pm",
						totalService.querylist(0, itemname, arr, 0));
			}
		}

		return "browe";
	}

	// 
	public String add() {
		ActionContext ct = ActionContext.getContext();
		Map session = ct.getSession();
		User u = (User) session.get("login");
		total.setUserid(u.getId());
		total.setItemname(itemService.findItemById(total.getItemid())
				.getItemname());
		total.setStatus("0");
		totalService.addtotal(total);
		return "add";
	}

	// 
	public String delete() {
		String[] arr = id.split(",");
		for (int i = 0; i < arr.length; i++) {
			totalService.deletetotal(arr[i].trim());
		}
		return "delete";
	}

	// 
	public String find() {
		Total t = new Total();
		t.setId(Integer.parseInt(id.trim()));
		total = totalService.findtotal(t);
		return "find";
	}

	//
	public String toupdate() {
		listitem = new ArrayList<Item>();
		List<Item> itemlist = itemService.queryItemall();
		for (int i = 0; i < itemlist.size(); i++) {
			Total total = new Total();
			total.setStatus("1");
			total.setItemid(itemlist.get(i).getId());
			Total toal = totalService.findtotal1(total);
			if (toal != null && !toal.equals("")) {
				listitem.add(itemlist.get(i));
			}
		}
		Total t = new Total();
		t.setId(Integer.parseInt(id.trim()));
		total = totalService.findtotal(t);
		return "toupdate";
	}

	public String update() {
		total.setItemname(itemService.findItemById(total.getItemid())
				.getItemname());
		totalService.updatetotal(total);
		return "update";
	}

	public String toadd() {// 添加的必须时候有预算的。
		listitem = new ArrayList<Item>();
		List<Item> itemlist = itemService.queryItemall();
		for (int i = 0; i < itemlist.size(); i++) {
			Total total = new Total();
			total.setStatus("1");
			total.setItemid(itemlist.get(i).getId());
			Total toal = totalService.findtotal1(total);
			if (toal != null && !toal.equals("")) {
				listitem.add(itemlist.get(i));
			}
		}
		return "toadd";
	}

	//
	public void getitemhours() {
		float result = 0.0f;
		String date1 = hoursservice.getnowDateManager();//
		int monthdays = getmonthdays(date + "-" + "01");//

		String[] arr = date1.split("-");
		/*
		 * String datetime=""; if(Integer.parseInt(arr[1])<=9){
		 * datetime+="0"+arr[1]; }else{ datetime=arr[1]; }
		 */
		// Item it=itemService.findItemById(Integer.parseInt(itemid));
		// 获得项目的开始时间
		// String starttime=it.getStarttime();
		// String startyear=starttime.split("-")[0];//项目开始年
		// String startmonth=starttime.split("-")[1];//项目开始月
		String[] arr1 = new String[2];
		arr1[0] = date + "-" + "01";
		arr1[1] = date + "-" + monthdays;
		HttpServletResponse response = this.getServletResponse();

		// int a=hoursservice.getHoursbyItemid(Integer.parseInt(itemid),
		// arr1);//
		Map<String, Subsidy> subMap = this.hoursservice.getSubsidy();//
		log.info("z获得所有的工时");

		Map hourMap = hoursservice.getHourByItemUser(arr1);
		List userlist = hoursservice.getalluser();
		float f = 0.0f;

		for (int i = 0; i < userlist.size(); i++) {
			User u1 = (User) userlist.get(i);
			Subsidy sub = subMap.get((u1.getId() + "").trim());
			if (sub == null) {
				f = 0.0f;
			} else {
				if (null != sub.getHourspaly()) {
					String[] paly = sub.getHourspaly().split("\\|");
					if (paly.length > 1) {
						for (int j = 0; j < paly.length; j++) {
							String palydate = paly[i].split(",")[0];
							String year = palydate.split("-")[0];
							String month = palydate.split("-")[1];
							String newyear = date1.split("-")[0];
							String newmonth = date1.split("-")[1];
							if (Integer.parseInt(year) <= Integer
									.parseInt(newyear)) {
								if (Integer.parseInt(palydate) <= Integer
										.parseInt(date1.split("-")[1])) {
									f = Float.valueOf(paly[i].split(",")[1]);
								}
							}
						}

					}
				}
				// f=Float.parseFloat(sub.getHourspaly());
			}
			int hour = (Integer) (hourMap
					.get((itemid + "" + u1.getId()).trim()) == null ? 0
					: hourMap.get((itemid + "" + u1.getId()).trim()));
			result += (f * hour);
			f = 0.0f;
			// butie=0;

		}
		Total t = new Total();
		t.setItemid(Integer.parseInt(itemid));
		Total toal = totalService.findtotal1(t);
		// / toal.getCreatedate();

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(result + "," + toal.getHtvalue() + "," + toal.getMv());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public int getmonthdays(String date) {
		String tem[] = date.split("-");
		int year = Integer.parseInt(tem[0]);
		int month = Integer.parseInt(tem[1]);
		int arr[] = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if ((year % 400 == 0 || year % 100 != 0) && (year % 4 == 0)) {
			arr[1] = 29;

		}
		return arr[month - 1];
	}

	public String toexcept() {
		log.info("开始进入导出也面的跳转");
		log.info("查询所有的项目！");
		User u = (User) this.getServletRequest().getSession().getAttribute(
				"login");
		if (u.getWorklevel().equals("manager") && u.getType().equals("system")) {// 如果用户是项目经理，而且还是超级管理员，按照超级管理员开始查询。
			listitem = new ArrayList<Item>();
			listitem = itemService.queryItemall();
		} else {// 如果他既不是超级管理员，是项目经理，那么就按项目经理查询
			if (u.getWorklevel().equals("manager")) {
				List<ItemUser> itmuserlist = itemService.queryItemByMangerid(u
						.getId());// 根据用户id查出所有的人员项目中间表，然后根据中间表获得所有的项目id。
				listitem = new ArrayList<Item>();
				for (int i = 0; i < itmuserlist.size(); i++) {
					// Total t=new Total();
					ItemUser iu = itmuserlist.get(i);
					listitem.add(itemService.findItemById(iu.getItemid()));
				}

				// if(u.getType().equals("manager")){
				// listitem=new ArrayList<Item>();
				// listitem=itemService.queryItemall();
				// }
			} else if (u.getType().equals("system")) {
				listitem = itemService.queryItemall();

			}
		}
		log.info("查询所有的项目结束！");
		log.info("开始进入导出也面的跳转完成");
		List riqilist = new ArrayList();
		for (int i = 0; i < 12; i++) {
			Map m = new HashMap();
			m.put("key", i + 1);
			m.put("value", i + 1);
			riqilist.add(m);
		}
		this.getServletRequest().setAttribute("riqi", riqilist);
		return "toecept";
	}

	public void excelhoursByYear() {
		float hetongzonge = 0.f;// 用于存放合同的总额
		float totalextel = 0.f;// 用户extl的费用总额
		float totalsj = 0.f;// 用于计算税金的总额。
		float totalplayvalue = 0.f;// 用于计算所有的花费的总额
		float totalpeoplevalue = 0.f;// 用于计算所有的人工成本。
		float totalchengben = 0.f;// 用于所有的人工成本。
		float totalRevenue = 0.f;// 用于存放所有的收益。
		float totalmv = 0.f;
		User u = (User) this.getServletRequest().getSession().getAttribute(
				"login");
		HttpServletResponse response = ServletActionContext.getResponse();
		ActionContext.getContext().getActionInvocation().getProxy()
				.setExecuteResult(false);
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String date1 = hoursservice.getnowDateManager();
		String year = date1.split("-")[0];// 取得今年的年数。
		String xiangmu = "";
		List<Total> totalist = new ArrayList<Total>();// 所有的对象
		List list = new ArrayList();
		List listitem = new ArrayList();// 项目名称
		List<Item> listitemid = new ArrayList<Item>();// 项目id
		// 第一种情况，项目和日期都为空那么就要把这些所有的一年的总和查出来
		List userlist = new ArrayList();
		userlist.add("项目名称");
		userlist.add("合同总额");
		userlist.add("人工成本");
		userlist.add("支出费用");
		userlist.add("外部分包费用");
		userlist.add("M值");
		userlist.add("Contract Cost");
		userlist.add("REVENUE");
		userlist.add("税金");
		if ((null == itemid) || itemid.equals("")
				&& (null == date || date.equals(""))) {
			xiangmu = "项目";
			if (u.getType().equals("system")) {
				list = totalService.queryalllistbyYear(0, year, 0);
				for (int i = 0; i < list.size(); i++) {
					Object[] t = (Object[]) list.get(i);

					listitem.add(t[1].toString());
					Total t1 = new Total();
					t1.setItemname(t[1].toString());
					if (t[2] != null) {
						hetongzonge += Float.parseFloat(t[2].toString());
						t1.setHtvalue(t[2].toString());
					} else {
						t1.setHtvalue("");
					}
					if (t[3] != null) {
						totalpeoplevalue += Float.parseFloat(t[3].toString());
						t1.setPeoplevalue(t[3].toString());
					} else {
						t1.setPeoplevalue("");
					}
					if (t[4] != null) {
						totalplayvalue += Float.parseFloat(t[4].toString());
						t1.setPalyvalue(t[4].toString());
					} else {
						t1.setPalyvalue("");
					}
					if (t[5] != null) {
						totalmv = Float.parseFloat(t[5].toString());
						t1.setMv(t[5].toString());
					} else {
						t1.setMv("");
					}
					if (t[6] != null) {
						totalextel += Float.parseFloat(t[6].toString());
						t1.setExtl(t[6].toString());
					} else {
						t1.setExtl("");
					}
					if (t[7] != null) {
						totalchengben += Float.parseFloat(t[7].toString());
						t1.setChengben(t[7].toString());
					} else {
						t1.setChengben("");
					}
					if (t[8] != null) {
						totalRevenue += Float.parseFloat(t[8].toString());
						t1.setRevenue(t[8].toString());
					} else {
						t1.setRevenue("");
					}
					if (t[10] != null) {
						totalsj += Float.parseFloat(t[10].toString());
						t1.setSj(t[10].toString());
					} else {
						t1.setSj("");
					}
					totalist.add(t1);
				}
			} else if (u.getWorklevel().equals("manager")) {
				List<ItemUser> itmuserlist = itemService.queryItemByMangerid(u
						.getId());// 根据用户id查出所有的人员项目中间表，然后根据中间表获得所有的项目id。
				// List<ItemUser>
				// itmuserlist=itemService.queryItemByMangerid(u.getId());//根据用户id查出所有的人员项目中间表，然后根据中间表获得所有的项目id。
				// listitem=new ArrayList<Item>();
				for (int i = 0; i < itmuserlist.size(); i++) {
					// Total t=new Total();
					ItemUser iu = itmuserlist.get(i);
					listitemid.add(itemService.findItemById(iu.getItemid()));
				}
				// 以上是当这个人为经理的时候，查询出这个人所管的项目id，然后根据项目id去查找所有的total表中的数据。
				// list=totalService.queryalllistbyYear(0, year,0);//查询所有的数据
			}
			for (int j = 0; j < listitemid.size(); j++) {
				list = totalService.queryalllistbyYear(0, year, listitemid.get(
						j).getId());
				for (int i = 0; i < list.size(); i++) {
					Object[] t = (Object[]) list.get(i);

					listitem.add(t[1].toString());
					Total t1 = new Total();
					t1.setItemname(t[1].toString());
					if (t[2] != null) {
						hetongzonge += Float.parseFloat(t[2].toString());
						t1.setHtvalue(t[2].toString());
					} else {
						t1.setHtvalue("");
					}
					if (t[3] != null) {
						totalpeoplevalue += Float.parseFloat(t[3].toString());
						t1.setPeoplevalue(t[3].toString());
					} else {
						t1.setPeoplevalue("");
					}
					if (t[4] != null) {
						totalplayvalue += Float.parseFloat(t[4].toString());
						t1.setPalyvalue(t[4].toString());
					} else {
						t1.setPalyvalue("");
					}
					if (t[5] != null) {
						totalmv = Float.parseFloat(t[5].toString());
						t1.setMv(t[5].toString());
					} else {
						t1.setMv("");
					}
					if (t[6] != null) {
						totalextel += Float.parseFloat(t[6].toString());
						t1.setExtl(t[6].toString());
					} else {
						t1.setExtl("");
					}
					if (t[7] != null) {
						totalchengben += Float.parseFloat(t[7].toString());
						t1.setChengben(t[7].toString());
					} else {
						t1.setChengben("");
					}
					if (t[8] != null) {
						totalRevenue += Float.parseFloat(t[8].toString());
						t1.setRevenue(t[8].toString());
					} else {
						t1.setRevenue("");
					}
					if (t[10] != null) {
						totalsj += Float.parseFloat(t[10].toString());
						t1.setSj(t[10].toString());
					} else {
						t1.setSj("");
					}
					totalist.add(t1);
				}
			}
		}
		if ((null == itemid) || itemid.equals("")
				&& (null != date && !date.equals(""))) {// 如果日期不能为空，项目为 空
			xiangmu = "项目";
			if (u.getType().equals("system")) {
				list = totalService.queryalllistbyYear(0, date, 0);
				for (int i = 0; i < list.size(); i++) {
					Object[] t = (Object[]) list.get(i);

					listitem.add(t[1].toString());
					Total t1 = new Total();
					t1.setItemname(t[1].toString());
					if (t[2] != null) {
						hetongzonge += Float.parseFloat(t[2].toString());
						t1.setHtvalue(t[2].toString());
					} else {
						t1.setHtvalue("");
					}
					if (t[3] != null) {
						totalpeoplevalue += Float.parseFloat(t[3].toString());
						t1.setPeoplevalue(t[3].toString());
					} else {
						t1.setPeoplevalue("");
					}
					if (t[4] != null) {
						totalplayvalue += Float.parseFloat(t[4].toString());
						t1.setPalyvalue(t[4].toString());
					} else {
						t1.setPalyvalue("");
					}
					if (t[5] != null) {
						totalmv = Float.parseFloat(t[5].toString());
						t1.setMv(t[5].toString());
					} else {
						t1.setMv("");
					}
					if (t[6] != null) {
						totalextel += Float.parseFloat(t[6].toString());
						t1.setExtl(t[6].toString());
					} else {
						t1.setExtl("");
					}
					if (t[7] != null) {
						totalchengben += Float.parseFloat(t[7].toString());
						t1.setChengben(t[7].toString());
					} else {
						t1.setChengben("");
					}
					if (t[8] != null) {
						totalRevenue += Float.parseFloat(t[8].toString());
						t1.setRevenue(t[8].toString());
					} else {
						t1.setRevenue("");
					}
					if (t[10] != null) {
						totalsj += Float.parseFloat(t[10].toString());
						t1.setSj(t[10].toString());
					} else {
						t1.setSj("");
					}
					totalist.add(t1);
				}
			} else if (u.getWorklevel().equals("manager")) {
				List<ItemUser> itmuserlist = itemService.queryItemByMangerid(u
						.getId());// 根据用户id查出所有的人员项目中间表，然后根据中间表获得所有的项目id。
				// List<ItemUser>
				// itmuserlist=itemService.queryItemByMangerid(u.getId());//根据用户id查出所有的人员项目中间表，然后根据中间表获得所有的项目id。
				// listitem=new ArrayList<Item>();
				for (int i = 0; i < itmuserlist.size(); i++) {
					// Total t=new Total();
					ItemUser iu = itmuserlist.get(i);
					listitemid.add(itemService.findItemById(iu.getItemid()));
				}
				// 以上是当这个人为经理的时候，查询出这个人所管的项目id，然后根据项目id去查找所有的total表中的数据。
				// list=totalService.queryalllistbyYear(0, year,0);//查询所有的数据
			}
			for (int j = 0; j < listitemid.size(); j++) {
				list = totalService.queryalllistbyYear(0, date, listitemid.get(
						j).getId());
				for (int i = 0; i < list.size(); i++) {
					Object[] t = (Object[]) list.get(i);

					listitem.add(t[1].toString());
					Total t1 = new Total();
					t1.setItemname(t[1].toString());
					if (t[2] != null) {
						hetongzonge += Float.parseFloat(t[2].toString());
						t1.setHtvalue(t[2].toString());
					} else {
						t1.setHtvalue("");
					}
					if (t[3] != null) {
						totalpeoplevalue += Float.parseFloat(t[3].toString());
						t1.setPeoplevalue(t[3].toString());
					} else {
						t1.setPeoplevalue("");
					}
					if (t[4] != null) {
						totalplayvalue += Float.parseFloat(t[4].toString());
						t1.setPalyvalue(t[4].toString());
					} else {
						t1.setPalyvalue("");
					}
					if (t[5] != null) {
						totalmv = Float.parseFloat(t[5].toString());
						t1.setMv(t[5].toString());
					} else {
						t1.setMv("");
					}
					if (t[6] != null) {
						totalextel += Float.parseFloat(t[6].toString());
						t1.setExtl(t[6].toString());
					} else {
						t1.setExtl("");
					}
					if (t[7] != null) {
						totalchengben += Float.parseFloat(t[7].toString());
						t1.setChengben(t[7].toString());
					} else {
						t1.setChengben("");
					}
					if (t[8] != null) {
						totalRevenue += Float.parseFloat(t[8].toString());
						t1.setRevenue(t[8].toString());
					} else {
						t1.setRevenue("");
					}
					if (t[10] != null) {
						totalsj += Float.parseFloat(t[10].toString());
						t1.setSj(t[10].toString());
					} else {
						t1.setSj("");
					}
					totalist.add(t1);
				}
			}
		}
		if ((null != itemid) && !itemid.equals("")
				&& (null == date || date.equals(""))) {// 项目不为空，日期为空
			xiangmu = "月份";
			if (u.getType().equals("system")) {
				list = totalService.queryalllistbyYear(0, year, Integer
						.valueOf(itemid));

			} else {
				list = totalService.queryalllistbyYear(0, year, Integer
						.parseInt(itemid));
			}
			listitem.add("项目预算");// 添加项目预算。
			Total tl = new Total();
			tl.setItemid(Integer.parseInt(itemid));
			tl = totalService.findtotal1(tl);
			totalist.add(tl);// 添加预算项
			for (int i = 0; i < list.size(); i++) {
				Object[] t = (Object[]) list.get(i);

				listitem.add(t[9].toString().split("-")[1] + "月");
				Total t1 = new Total();
				t1.setItemname(t[1].toString());
				if (t[2] != null) {
					hetongzonge += Float.parseFloat(t[2].toString());
					t1.setHtvalue(t[2].toString());
				} else {
					t1.setHtvalue("");
				}
				if (t[3] != null) {
					totalpeoplevalue += Float.parseFloat(t[3].toString());
					t1.setPeoplevalue(t[3].toString());
				} else {
					t1.setPeoplevalue("");
				}
				if (t[4] != null) {
					totalplayvalue += Float.parseFloat(t[4].toString());
					t1.setPalyvalue(t[4].toString());
				} else {
					t1.setPalyvalue("");
				}
				if (t[5] != null) {
					totalmv = Float.parseFloat(t[5].toString());
					t1.setMv(t[5].toString());
				} else {
					t1.setMv("");
				}
				if (t[6] != null) {
					totalextel += Float.parseFloat(t[6].toString());
					t1.setExtl(t[6].toString());
				} else {
					t1.setExtl("");
				}
				if (t[7] != null) {
					totalchengben += Float.parseFloat(t[7].toString());
					t1.setChengben(t[7].toString());
				} else {
					t1.setChengben("");
				}
				if (t[8] != null) {
					totalRevenue += Float.parseFloat(t[8].toString());
					t1.setRevenue(t[8].toString());
				} else {
					t1.setRevenue("");
				}
				if (t[10] != null) {
					totalsj += Float.parseFloat(t[10].toString());
					t1.setSj(t[10].toString());
				} else {
					t1.setSj("");
				}
				t1.setMv(tl.getMv());
				totalist.add(t1);
			}

		}
		if ((null != itemid) && !itemid.equals("")
				&& (null != date && !date.equals(""))) {// 项目不为空，日期为空
			xiangmu = "项目";
			if (u.getType().equals("system")) {
				list = totalService.queryalllistbyYear(0, date, Integer
						.parseInt(itemid));
			} else {
				list = totalService.queryalllistbyYear(0, date, Integer
						.parseInt(itemid));
			}
			listitem.add("项目预算");// 添加项目预算。
			Total tl = new Total();
			tl.setItemid(Integer.parseInt(itemid));
			tl = totalService.findtotal1(tl);
			totalist.add(tl);// 添加预算项

			for (int i = 0; i < list.size(); i++) {
				Object[] t = (Object[]) list.get(i);

				listitem.add(t[1].toString());
				Total t1 = new Total();
				t1.setItemname(t[1].toString());
				if (t[2] != null) {
					hetongzonge += Float.parseFloat(t[2].toString());
					t1.setHtvalue(t[2].toString());
				} else {
					t1.setHtvalue("");
				}
				if (t[3] != null) {
					totalpeoplevalue += Float.parseFloat(t[3].toString());
					t1.setPeoplevalue(t[3].toString());
				} else {
					t1.setPeoplevalue("");
				}
				if (t[4] != null) {
					totalplayvalue += Float.parseFloat(t[4].toString());
					t1.setPalyvalue(t[4].toString());
				} else {
					t1.setPalyvalue("");
				}
				if (t[5] != null) {
					totalmv = Float.parseFloat(t[5].toString());
					t1.setMv(t[5].toString());
				} else {
					t1.setMv("");
				}
				if (t[6] != null) {
					totalextel += Float.parseFloat(t[6].toString());
					t1.setExtl(t[6].toString());
				} else {
					t1.setExtl("");
				}
				if (t[7] != null) {
					totalchengben += Float.parseFloat(t[7].toString());
					t1.setChengben(t[7].toString());
				} else {
					t1.setChengben("");
				}
				if (t[8] != null) {
					totalRevenue += Float.parseFloat(t[8].toString());
					t1.setRevenue(t[8].toString());
				} else {
					t1.setRevenue("");
				}
				if (t[10] != null) {
					totalsj += Float.parseFloat(t[10].toString());
					t1.setSj(t[10].toString());
				} else {
					t1.setSj("");
				}
				t1.setMv(tl.getMv());
				totalist.add(t1);
			}

		}

		// 总计所有的数据。
		Total total = new Total();
		total.setChengben(String.valueOf(totalchengben));
		total.setSj(String.valueOf(totalsj));
		total.setExtl(String.valueOf(totalextel));
		total.setHtvalue(String.valueOf(hetongzonge));
		total.setRevenue(String.valueOf(totalRevenue));
		total.setPalyvalue(String.valueOf(totalplayvalue));
		total.setPeoplevalue(String.valueOf(totalpeoplevalue));
		total.setMv(String.valueOf(totalmv));
		// 总计所有的完成，然后添加到列表中。
		totalist.add(total);
		listitem.add("总计");
		JxlExceptor.getInstence().getExcerTotal("totaltoo", listitem, userlist,
				totalist, response, xiangmu);

	}

	public void excelworkhours() {
		float hetongzonge = 0.f;// 用于存放合同的总额
		float totalextel = 0.f;// 用户extl的费用总额
		float totalsj = 0.f;// 用于计算税金的总额。
		float totalplayvalue = 0.f;// 用于计算所有的花费的总额
		float totalpeoplevalue = 0.f;// 用于计算所有的人工成本。
		float totalchengben = 0.f;// 用于所有的人工成本。
		float totalRevenue = 0.f;// 用于存放所有的收益。
		float totalmv = 0.f;
		List<Total> totalist = new ArrayList<Total>();// 所有的对象
		List<Item> listitemid = new ArrayList<Item>();// 用于存放这个人是项目经理的所管理的所有的项目id
		String date4 = hoursservice.getnowDateManager();
		String year1 = date4.split("-")[0];
		String mondy = date4.split("-")[1];
		String date3 = "";
		date3 = year1 + "-" + mondy;
		List list = new ArrayList();
		String xiangmu = "";
		User u = (User) this.getServletRequest().getSession().getAttribute(
				"login");
		HttpServletResponse response = ServletActionContext.getResponse();
		ActionContext.getContext().getActionInvocation().getProxy()
				.setExecuteResult(false);
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		List listitem = new ArrayList();// 项目名称
		List userlist = new ArrayList();
		userlist.add("项目名称");
		userlist.add("合同总额");
		userlist.add("人工成本");
		userlist.add("花费金额");
		userlist.add("extl费用");
		userlist.add("M值");
		userlist.add("Contract Cost");
		userlist.add("REVENUE");
		userlist.add("税金");
		if ((null == itemid) || itemid.equals("")
				&& (null == date || date.equals(""))) {// 如果都为空，那么获取当月的，所有的项目，前提是这个人所拥有的。

			/*
			 * if(Integer.parseInt(mondy)+1<=9){ date+=year+"-0"+mondy; }else{
			 * date+=year+"-"+mondy; }
			 */
			if (u.getType().equals("system")) {
				list = totalService.queryalllist1(0, date3);
				for (int i = 0; i < list.size(); i++) {
					Object[] t = (Object[]) list.get(i);

					listitem.add(t[1].toString());
					Total t1 = new Total();
					t1.setItemname(t[1].toString());
					hetongzonge += Float.parseFloat(t[2].toString());// 获得所有的合同总额。还没有写完。
					if (t[2] != null) {
						t1.setHtvalue(t[2].toString());
					} else {
						t1.setHtvalue("");
					}
					if (t[3] != null) {
						totalpeoplevalue += Float.parseFloat(t[3].toString());// 设置总的人工成本
						t1.setPeoplevalue(t[3].toString());
					} else {
						t1.setPeoplevalue("");
					}
					if (t[4] != null) {
						totalplayvalue += Float.parseFloat(t[4].toString());// 所有的花费费用
						t1.setPalyvalue(t[4].toString());
					} else {
						t1.setPalyvalue("");
					}
					if (t[5] != null) {
						totalmv = Float.parseFloat(t[5].toString());
						t1.setMv(t[5].toString());
					} else {
						t1.setMv("");
					}
					if (t[6] != null) {
						totalextel += Float.parseFloat(t[6].toString());// 所有的分保费
						t1.setExtl(t[6].toString());
					} else {
						t1.setExtl("");
					}
					if (t[7] != null) {
						totalchengben += Float.parseFloat(t[7].toString());// 所有的成本
						t1.setChengben(t[7].toString());
					} else {
						t1.setChengben("");
					}
					if (t[8] != null) {
						totalRevenue += Float.parseFloat(t[8].toString());// 所有的收益
						t1.setRevenue(t[8].toString());
					} else {
						t1.setRevenue("");
					}
					if (t[9] != null) {
						totalsj += Float.parseFloat(t[9].toString());// 所有的税金
						t1.setSj(t[9].toString());
					} else {
						t1.setSj("");
					}
					totalist.add(t1);
				}
			} else if (u.getWorklevel().equals("manager")) {
				List<ItemUser> itmuserlist = itemService.queryItemByMangerid(u
						.getId());// 根据用户id查出所有的人员项目中间表，然后根据中间表获得所有的项目id。
				// List<ItemUser>
				// itmuserlist=itemService.queryItemByMangerid(u.getId());//根据用户id查出所有的人员项目中间表，然后根据中间表获得所有的项目id。
				// listitem=new ArrayList<Item>();
				for (int i = 0; i < itmuserlist.size(); i++) {
					// Total t=new Total();
					ItemUser iu = itmuserlist.get(i);
					listitemid.add(itemService.findItemById(iu.getItemid()));
				}
				// 以上是当这个人为经理的时候，查询出这个人所管的项目id，然后根据项目id去查找所有的total表中的数据。
				// list=totalService.queryalllistbyYear(0, year,0);//查询所有的数据
			}
			for (int j = 0; j < listitemid.size(); j++) {
				list = totalService.queryalllist1(listitemid.get(j).getId(),
						date3);
				for (int i = 0; i < list.size(); i++) {
					Object[] t = (Object[]) list.get(i);

					listitem.add(t[1].toString());
					Total t1 = new Total();
					t1.setItemname(t[1].toString());
					hetongzonge += Float.parseFloat(t[2].toString());// 获得所有的合同总额。还没有写完。
					if (t[2] != null) {
						t1.setHtvalue(t[2].toString());
					} else {
						t1.setHtvalue("");
					}
					if (t[3] != null) {
						totalpeoplevalue += Float.parseFloat(t[3].toString());// 设置总的人工成本
						t1.setPeoplevalue(t[3].toString());
					} else {
						t1.setPeoplevalue("");
					}
					if (t[4] != null) {
						totalplayvalue += Float.parseFloat(t[4].toString());// 所有的花费费用
						t1.setPalyvalue(t[4].toString());
					} else {
						t1.setPalyvalue("");
					}
					if (t[5] != null) {
						totalmv = Float.parseFloat(t[5].toString());
						t1.setMv(t[5].toString());
					} else {
						t1.setMv("");
					}
					if (t[6] != null) {
						totalextel += Float.parseFloat(t[6].toString());// 所有的分保费
						t1.setExtl(t[6].toString());
					} else {
						t1.setExtl("");
					}
					if (t[7] != null) {
						totalchengben += Float.parseFloat(t[7].toString());// 所有的成本
						t1.setChengben(t[7].toString());
					} else {
						t1.setChengben("");
					}
					if (t[8] != null) {
						totalRevenue += Float.parseFloat(t[8].toString());// 所有的收益
						t1.setRevenue(t[8].toString());
					} else {
						t1.setRevenue("");
					}
					if (t[9] != null) {
						totalsj += Float.parseFloat(t[9].toString());// 所有的税金
						t1.setSj(t[9].toString());
					} else {
						t1.setSj("");
					}
					totalist.add(t1);
				}
			}

			xiangmu = "项目";
		}
		if ((null != itemid) && !itemid.equals("")
				&& (null == date || date.equals(""))) {// 项目不为空，但是日期为空
			if (u.getType().equals("system")) {
				list = totalService.queryallList(Integer.parseInt(itemid),
						date3, 0);
			} else {
				list = totalService.queryallList(Integer.parseInt(itemid),
						date3, 0);
			}
			listitem.add("项目预算");// 添加项目预算。
			Total tl = new Total();
			tl.setItemid(Integer.parseInt(itemid));
			tl = totalService.findtotal1(tl);
			totalist.add(tl);// 添加预算项
			for (int i = 0; i < list.size(); i++) {
				Total t = (Total) list.get(i);
				t.setMv(tl.getMv());
				if (t.getItemid() == Integer.parseInt((itemid))) {
					listitem.add(t.getCreatedate().split("-")[1] + "月");
					// 最后一个统计
					hetongzonge += Float.parseFloat(t.getHtvalue());
					totalextel += Float.parseFloat(t.getExtl());
					totalsj += Float.parseFloat(t.getSj());
					totalplayvalue += Float.parseFloat(t.getPalyvalue());
					totalpeoplevalue += Float.parseFloat(t.getPeoplevalue());
					totalchengben += Float.parseFloat(t.getChengben());
					totalRevenue += Float.parseFloat(t.getRevenue());
					totalmv = Float.parseFloat(t.getMv());
					// 统计完成
					totalist.add(t);
				}

			}

			xiangmu = "月份";
		}
		if ((null == itemid) || itemid.equals("")
				&& (null != date && !date.equals(""))) {// 项目为空，日期不为空。
			xiangmu = "项目";
			String date1 = hoursservice.getnowDateManager();
			String year = date1.split("-")[0];
			// String mondy=date1.split("-")[1];
			String date2 = "";
			if (Integer.parseInt(date) <= 9) {
				date2 += year + "-0" + date;
			} else {
				date2 += year + "-" + date;
			}
			if (u.getType().equals("system")) {
				list = totalService.queryalllist1(0, date2);
				for (int i = 0; i < list.size(); i++) {
					Object[] t = (Object[]) list.get(i);

					listitem.add(t[1].toString());
					Total t1 = new Total();
					t1.setItemname(t[1].toString());
					hetongzonge += Float.parseFloat(t[2].toString());// 获得所有的合同总额。还没有写完。
					if (t[2] != null) {
						t1.setHtvalue(t[2].toString());
					} else {
						t1.setHtvalue("");
					}
					if (t[3] != null) {
						totalpeoplevalue += Float.parseFloat(t[3].toString());// 设置总的人工成本
						t1.setPeoplevalue(t[3].toString());
					} else {
						t1.setPeoplevalue("");
					}
					if (t[4] != null) {
						totalplayvalue += Float.parseFloat(t[4].toString());// 所有的花费费用
						t1.setPalyvalue(t[4].toString());
					} else {
						t1.setPalyvalue("");
					}
					if (t[5] != null) {
						totalmv = Float.parseFloat(t[5].toString());
						t1.setMv(t[5].toString());
					} else {
						t1.setMv("");
					}
					if (t[6] != null) {
						totalextel += Float.parseFloat(t[6].toString());// 所有的分保费
						t1.setExtl(t[6].toString());
					} else {
						t1.setExtl("");
					}
					if (t[7] != null) {
						totalchengben += Float.parseFloat(t[7].toString());// 所有的成本
						t1.setChengben(t[7].toString());
					} else {
						t1.setChengben("");
					}
					if (t[8] != null) {
						totalRevenue += Float.parseFloat(t[8].toString());// 所有的收益
						t1.setRevenue(t[8].toString());
					} else {
						t1.setRevenue("");
					}
					if (t[9] != null) {
						totalsj += Float.parseFloat(t[9].toString());// 所有的税金
						t1.setSj(t[9].toString());
					} else {
						t1.setSj("");
					}
					totalist.add(t1);
				}
			} else if (u.getWorklevel().equals("manager")) {
				List<ItemUser> itmuserlist = itemService.queryItemByMangerid(u
						.getId());// 根据用户id查出所有的人员项目中间表，然后根据中间表获得所有的项目id。
				// List<ItemUser>
				// itmuserlist=itemService.queryItemByMangerid(u.getId());//根据用户id查出所有的人员项目中间表，然后根据中间表获得所有的项目id。
				// listitem=new ArrayList<Item>();
				for (int i = 0; i < itmuserlist.size(); i++) {
					// Total t=new Total();
					ItemUser iu = itmuserlist.get(i);
					listitemid.add(itemService.findItemById(iu.getItemid()));
				}
				// 以上是当这个人为经理的时候，查询出这个人所管的项目id，然后根据项目id去查找所有的total表中的数据。
				// list=totalService.queryalllistbyYear(0, year,0);//查询所有的数据
			}
			for (int j = 0; j < listitemid.size(); j++) {
				list = totalService.queryalllist1(listitemid.get(j).getId(),
						date2);
				for (int i = 0; i < list.size(); i++) {
					Object[] t = (Object[]) list.get(i);

					listitem.add(t[1].toString());
					Total t1 = new Total();
					t1.setItemname(t[1].toString());
					hetongzonge += Float.parseFloat(t[2].toString());// 获得所有的合同总额。还没有写完。
					if (t[2] != null) {
						t1.setHtvalue(t[2].toString());
					} else {
						t1.setHtvalue("");
					}
					if (t[3] != null) {
						totalpeoplevalue += Float.parseFloat(t[3].toString());// 设置总的人工成本
						t1.setPeoplevalue(t[3].toString());
					} else {
						t1.setPeoplevalue("");
					}
					if (t[4] != null) {
						totalplayvalue += Float.parseFloat(t[4].toString());// 所有的花费费用
						t1.setPalyvalue(t[4].toString());
					} else {
						t1.setPalyvalue("");
					}
					if (t[5] != null) {
						totalmv = Float.parseFloat(t[5].toString());
						t1.setMv(t[5].toString());
					} else {
						t1.setMv("");
					}
					if (t[6] != null) {
						totalextel += Float.parseFloat(t[6].toString());// 所有的分保费
						t1.setExtl(t[6].toString());
					} else {
						t1.setExtl("");
					}
					if (t[7] != null) {
						totalchengben += Float.parseFloat(t[7].toString());// 所有的成本
						t1.setChengben(t[7].toString());
					} else {
						t1.setChengben("");
					}
					if (t[8] != null) {
						totalRevenue += Float.parseFloat(t[8].toString());// 所有的收益
						t1.setRevenue(t[8].toString());
					} else {
						t1.setRevenue("");
					}
					if (t[9] != null) {
						totalsj += Float.parseFloat(t[9].toString());// 所有的税金
						t1.setSj(t[9].toString());
					} else {
						t1.setSj("");
					}
					totalist.add(t1);
				}
			}
		}
		if ((null != itemid) && !itemid.equals("")
				&& (null != date && !date.equals(""))) {// 项目不为空，日期不为空
			xiangmu = "月份";
			String date1 = hoursservice.getnowDateManager();
			String year = date1.split("-")[0];
			// String mondy=date1.split("-")[1];
			String date2 = "";
			if (Integer.parseInt(date) <= 9) {
				date2 += year + "-0" + date;
			} else {
				date2 += year + "-" + date;
			}
			list = totalService
					.queryallList(Integer.parseInt(itemid), date2, 0);
			listitem.add("项目预算");// 添加项目预算。
			Total tl = new Total();
			tl.setItemid(Integer.parseInt(itemid));
			tl = totalService.findtotal1(tl);
			totalist.add(tl);// 添加预算项
			for (int i = 0; i < list.size(); i++) {
				Total t = (Total) list.get(i);
				t.setMv(tl.getMv());
				if (t.getItemid() == Integer.parseInt((itemid))) {
					listitem.add(t.getCreatedate().split("-")[1] + "月");
					// 最后一个统计
					hetongzonge += Float.parseFloat(t.getHtvalue());
					totalextel += Float.parseFloat(t.getExtl());
					totalsj += Float.parseFloat(t.getSj());
					totalplayvalue += Float.parseFloat(t.getPalyvalue());
					totalpeoplevalue += Float.parseFloat(t.getPeoplevalue());
					totalchengben += Float.parseFloat(t.getChengben());
					totalRevenue += Float.parseFloat(t.getRevenue());
					totalmv = Float.parseFloat(t.getMv());
					// 统计完成
					totalist.add(t);
				}

			}
		}
		// 总计所有的数据。
		Total total = new Total();
		total.setChengben(String.valueOf(totalchengben));
		total.setSj(String.valueOf(totalsj));
		total.setExtl(String.valueOf(totalextel));
		total.setHtvalue(String.valueOf(hetongzonge));
		total.setRevenue(String.valueOf(totalRevenue));
		total.setPalyvalue(String.valueOf(totalplayvalue));
		total.setPeoplevalue(String.valueOf(totalpeoplevalue));
		total.setMv(String.valueOf(totalmv));
		// 总计所有的完成，然后添加到列表中。
		totalist.add(total);
		listitem.add("总计");
		// List list=new ArrayList();

		// listitem.add(itemService.findItemById(Integer.parseInt(itemid)).getItemname());
		JxlExceptor.getInstence().getExcerTotal("total", listitem, userlist,
				totalist, response, xiangmu);
	}

	public TotalManager getTotalService() {
		return totalService;
	}

	public void setTotalService(TotalManager totalService) {
		this.totalService = totalService;
	}

	public Total getTotal() {
		return total;
	}

	public void setTotal(Total total) {
		this.total = total;
	}

	public ItemManager getItemService() {
		return itemService;
	}

	public void setItemService(ItemManager itemService) {
		this.itemService = itemService;
	}

	public HoursManager getHoursservice() {
		return hoursservice;
	}

	public void setHoursservice(HoursManager hoursservice) {
		this.hoursservice = hoursservice;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
