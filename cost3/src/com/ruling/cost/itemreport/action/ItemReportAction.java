package com.ruling.cost.itemreport.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.common.been.ParameterMap;
import com.common.been.ResultHashMap;
import com.opensymphony.xwork2.ActionSupport;
import com.ruling.cost.itemreport.domain.ItemReport;
import com.ruling.cost.itemreport.domain.ItemReport2;
import com.ruling.cost.itemreport.domain.ItemReport3;
import com.ruling.cost.itemreport.service.ItemReportManager;
import com.ruling.cost.utils.Javascript;

public class ItemReportAction extends ActionSupport {
	private static final Logger log = Logger.getLogger(ItemReportAction.class);
	private ItemReportManager itemReportManager;
	private int itemId;
	private ItemReport itemReport = new ItemReport();
	private ItemReport2 itemReport2 = new ItemReport2();
	private ItemReport3 itemReport3 = new ItemReport3();
	private List itemReport2List;
	private List itemReport3List;

	// 由于struts2.1.6，从页面传入action中double值的bug，在页面中定义itemReport2.fenbaofei会报错,所以此处定义字符串变量。
	private String itemReportId;
	private String id;
	private String mingcheng;
	private String riqi;
	private String rengongChengben;
	private String feiyongChengben;
	private String shuijin;
	private String fenbaofei;
	private String m;
	private String contractCost;
	private String revenue;
	private String revenueTiaozheng;
	private String feiyongBaifenbi;
	private String revenueBaifenbi;
	private String xiangmuJinduBaifenbi;
	private String reportId;

	private String billed;
	private String cashIn;
	private String eub;
	private String ar;

	// 用于item_report2，添加页面、修改页面计算m值时，用到的累计值
	private String feiyongChengbenSum;
	private String shuijinSum;
	private String fenbaofeiSum;
	private String rengongChengbenSum;

	private int sort;
	private int type;
	private String id2;

	public String showReport() {
		try {
			ParameterMap pm = new ParameterMap();
			pm.put("itemId", itemId);
			ResultHashMap rhm = itemReportManager.showReport(pm);
			boolean has = rhm.getBoolean("has");
			itemReport = (ItemReport) rhm.get("ir");
			if (!has) {
				return "add";
			} else {
				itemReport2List = (List) rhm.get("itemReport2List");
				itemReport3List = (List) rhm.get("itemReport3List");
				int showModifyIndex = (Integer) rhm.getInt("showModifyIndex");
				ServletActionContext.getRequest().setAttribute(
						"showModifyIndex", showModifyIndex);
				return "update";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return NONE;
	}

	private String itemcode;
	private String itemname;
	private String contractMoney;
	private String startDate;
	private String voMoney;
	private String voDate;
	private String engineerConfig;

	/**
	 * 插入itemReport记录
	 * 
	 * @return
	 */
	public String addItemReport() {
		double contractMoneyDou = 0;
		try {
			contractMoneyDou = Double.parseDouble(contractMoney);
		} catch (Exception e) {

		}
		double voMoneyDou = 0;
		try {
			voMoneyDou = Double.parseDouble(voMoney);
		} catch (Exception e) {

		}

		itemReport = new ItemReport();
		itemReport.setContractMoney(contractMoneyDou);
		itemReport.setEngineerConfig(engineerConfig);
		itemReport.setItemcode(itemcode);
		itemReport.setItemId(itemId);
		itemReport.setItemname(itemname);
		itemReport.setStartDate(startDate);
		itemReport.setVoDate(voDate);
		itemReport.setVoMoney(voMoneyDou);

		ParameterMap pm = new ParameterMap();
		pm.put("itemReport", itemReport);
		try {
			ResultHashMap rhm = itemReportManager.add(pm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ServletActionContext.getResponse().sendRedirect(
					"itemReport_showReport.action?itemId="
							+ itemReport.getItemId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 删除item_report记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteItemReport() throws Exception {
		ParameterMap pm = new ParameterMap();
		pm.put("id", id);
		ResultHashMap rhm = itemReportManager.deleteItemReport(pm);
		if (!rhm.getResult()) {
			Javascript js = new Javascript(ServletActionContext.getResponse());
			js.alert(rhm.getMessage());
		} else {
			ServletActionContext.getResponse().sendRedirect("browe.action");

		}
		return NONE;
	}

	/**
	 * 更新item_report记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateItemReport() throws Exception {
		double contractMoneyDou = 0;
		try {
			contractMoneyDou = Double.parseDouble(contractMoney);
		} catch (Exception e) {

		}
		double voMoneyDou = 0;
		try {
			voMoneyDou = Double.parseDouble(voMoney);
		} catch (Exception e) {

		}

		itemReport = new ItemReport();
		itemReport.setId(id);
		itemReport.setContractMoney(contractMoneyDou);
		itemReport.setEngineerConfig(engineerConfig);
		itemReport.setItemcode(itemcode);
		itemReport.setItemId(itemId);
		itemReport.setItemname(itemname);
		itemReport.setStartDate(startDate);
		itemReport.setVoDate(voDate);
		itemReport.setVoMoney(voMoneyDou);

		ParameterMap pm = new ParameterMap();
		pm.put("itemReport", itemReport);
		try {
			ResultHashMap rhm = itemReportManager.updateItemReport(pm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ServletActionContext.getResponse().sendRedirect(
					"itemReport_showReport.action?itemId="
							+ itemReport.getItemId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 插入itemReport2记录
	 * 
	 * @return
	 */
	public String addItemReport2() {
		itemReport2 = new ItemReport2();
		double contractCostDou = 0;
		try {
			contractCostDou = Double.parseDouble(contractCost);
		} catch (Exception e) {

		}
		double feiyongBaifenbiDou = 0;
		try {
			feiyongBaifenbiDou = Double.parseDouble(feiyongBaifenbi);
		} catch (Exception e) {

		}
		double feiyongChengbenDou = 0;
		try {
			feiyongChengbenDou = Double.parseDouble(feiyongChengben);
		} catch (Exception e) {

		}
		double fenbaofeiDou = 0;
		try {
			fenbaofeiDou = Double.parseDouble(fenbaofei);
		} catch (Exception e) {

		}
		double mDou = 0;
		try {
			mDou = Double.parseDouble(m);
		} catch (Exception e) {

		}
		double rengongChengbenDou = 0;
		try {
			rengongChengbenDou = Double.parseDouble(rengongChengben);
		} catch (Exception e) {

		}
		double revenueDou = 0;
		try {
			revenueDou = Double.parseDouble(revenue);
		} catch (Exception e) {

		}
		double revenueBaifenbiDou = 0;
		try {
			revenueBaifenbiDou = Double.parseDouble(revenueBaifenbi);
		} catch (Exception e) {

		}
		double revenueTiaozhengDou = 0;
		try {
			revenueTiaozhengDou = Double.parseDouble(revenueTiaozheng);
		} catch (Exception e) {

		}
		double shuijinDou = 0;
		try {
			shuijinDou = Double.parseDouble(shuijin);
		} catch (Exception e) {

		}
		double xiangmuJinduBaifenbiDou = 0;
		try {
			xiangmuJinduBaifenbiDou = Double.parseDouble(xiangmuJinduBaifenbi);
		} catch (Exception e) {

		}
		itemReport2.setContractCost(contractCostDou);
		itemReport2.setFeiyongBaifenbi(feiyongBaifenbiDou);
		itemReport2.setFeiyongChengben(feiyongChengbenDou);
		itemReport2.setFenbaofei(fenbaofeiDou);
		itemReport2.setItemId(itemId);
		itemReport2.setM(mDou);
		itemReport2.setMingcheng(mingcheng);
		itemReport2.setRengongChengben(rengongChengbenDou);
		itemReport2.setReportId(reportId);
		itemReport2.setRevenue(revenueDou);
		itemReport2.setRevenueBaifenbi(revenueBaifenbiDou);
		itemReport2.setRevenueTiaozheng(revenueTiaozhengDou);
		itemReport2.setRiqi(riqi);
		itemReport2.setShuijin(shuijinDou);
		itemReport2.setXiangmuJinduBaifenbi(xiangmuJinduBaifenbiDou);

		ParameterMap pm = new ParameterMap();
		pm.put("itemReport2", itemReport2);
		try {
			ResultHashMap rhm = itemReportManager.addItemReport2(pm);
			if (!rhm.getResult()) {
				Javascript js = new Javascript(ServletActionContext
						.getResponse());
				js.alert(rhm.getMessage());
				return NONE;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ServletActionContext.getResponse().sendRedirect(
					"itemReport_showReport.action?itemId="
							+ itemReport2.getItemId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 显示item_report2记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showItemReport2() throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			ParameterMap pm = new ParameterMap();
			pm.put("id", id);
			ResultHashMap rhm = itemReportManager.showItemReport2(pm);
			itemReport2 = (ItemReport2) rhm.get("itemReport2");
			feiyongChengbenSum = rhm.getDouble("feiyongChengbenSum") + "";
			shuijinSum = rhm.getDouble("shuijinSum") + "";
			fenbaofeiSum = rhm.getDouble("fenbaofeiSum") + "";
			rengongChengbenSum = rhm.getDouble("rengongChengbenSum") + "";

			request.getRequestDispatcher("/item/itemreport2_update.jsp")
					.forward(request, response);
		} catch (Exception e) {
			log.error("", e);
		}
		return NONE;
	}

	/**
	 * 删除item_report2记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteItemReport2() throws Exception {
		ParameterMap pm = new ParameterMap();
		pm.put("id", id);
		itemReportManager.deleteItemReport2(pm);
		try {
			ServletActionContext.getResponse().sendRedirect(
					"itemReport_showReport.action?itemId=" + itemId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 更新item_report记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateItemReport2() throws Exception {

		double contractCostDou = 0;
		try {
			contractCostDou = Double.parseDouble(contractCost);
		} catch (Exception e) {

		}
		double feiyongBaifenbiDou = 0;
		try {
			feiyongBaifenbiDou = Double.parseDouble(feiyongBaifenbi);
		} catch (Exception e) {

		}
		double feiyongChengbenDou = 0;
		try {
			feiyongChengbenDou = Double.parseDouble(feiyongChengben);
		} catch (Exception e) {

		}
		double fenbaofeiDou = 0;
		try {
			fenbaofeiDou = Double.parseDouble(fenbaofei);
		} catch (Exception e) {

		}
		double mDou = 0;
		try {
			mDou = Double.parseDouble(m);
		} catch (Exception e) {

		}
		double rengongChengbenDou = 0;
		try {
			rengongChengbenDou = Double.parseDouble(rengongChengben);
		} catch (Exception e) {

		}
		double revenueDou = 0;
		try {
			revenueDou = Double.parseDouble(revenue);
		} catch (Exception e) {

		}
		double revenueBaifenbiDou = 0;
		try {
			revenueBaifenbiDou = Double.parseDouble(revenueBaifenbi);
		} catch (Exception e) {

		}
		double revenueTiaozhengDou = 0;
		try {
			revenueTiaozhengDou = Double.parseDouble(revenueTiaozheng);
		} catch (Exception e) {

		}
		double shuijinDou = 0;
		try {
			shuijinDou = Double.parseDouble(shuijin);
		} catch (Exception e) {

		}
		double xiangmuJinduBaifenbiDou = 0;
		try {
			xiangmuJinduBaifenbiDou = Double.parseDouble(xiangmuJinduBaifenbi);
		} catch (Exception e) {

		}
		itemReport2 = new ItemReport2();
		itemReport2.setId(id);
		itemReport2.setContractCost(contractCostDou);
		itemReport2.setFeiyongBaifenbi(feiyongBaifenbiDou);
		itemReport2.setFeiyongChengben(feiyongChengbenDou);
		itemReport2.setFenbaofei(fenbaofeiDou);
		itemReport2.setItemId(itemId);
		itemReport2.setM(mDou);
		itemReport2.setMingcheng(mingcheng);
		itemReport2.setRengongChengben(rengongChengbenDou);
		itemReport2.setReportId(reportId);
		itemReport2.setRevenue(revenueDou);
		itemReport2.setRevenueBaifenbi(revenueBaifenbiDou);
		itemReport2.setRevenueTiaozheng(revenueTiaozhengDou);
		itemReport2.setRiqi(riqi);
		itemReport2.setShuijin(shuijinDou);
		itemReport2.setXiangmuJinduBaifenbi(xiangmuJinduBaifenbiDou);

		ParameterMap pm = new ParameterMap();
		pm.put("itemReport2", itemReport2);
		try {
			ResultHashMap rhm = itemReportManager.updateItemReport2(pm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ServletActionContext.getResponse().sendRedirect(
					"itemReport_showReport.action?itemId="
							+ itemReport2.getItemId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 跳转到添加item_report3页面
	 * 
	 * @return
	 */
	public String forwardAddItemReport2Page() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ParameterMap pm = new ParameterMap();
		pm.put("itemId", itemId);
		pm.put("itemReportId", itemReportId);
		try {
			ResultHashMap rhm = itemReportManager.forwardAddItemReport2Page(pm);
			feiyongChengbenSum = rhm.getDouble("feiyongChengbenSum") + "";
			shuijinSum = rhm.getDouble("shuijinSum") + "";
			fenbaofeiSum = rhm.getDouble("fenbaofeiSum") + "";
			rengongChengbenSum = rhm.getDouble("rengongChengbenSum") + "";
			request.getRequestDispatcher("/item/itemreport2_add.jsp").forward(
					request, response);
		} catch (Exception e) {
			log.error("", e);
		}
		return null;
	}

	/**
	 * 跳转到添加item_report3页面
	 * 
	 * @return
	 */
	public String forwardAddItemReport3Page() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ParameterMap pm = new ParameterMap();
		pm.put("itemId", itemId);
		pm.put("itemReportId", itemReportId);
		try {
			ResultHashMap rhm = itemReportManager.forwardAddItemReport3Page(pm);
			rengongChengben = rhm.getDouble("rengongChengben") + "";
			m = rhm.getDouble("m") + "";
			contractCost = rhm.getDouble("contractCost") + "";

			double contractCostSum = rhm.getDouble("contractCostSum");
			double revenueSum = rhm.getDouble("revenueSum");
			double revenueTiaozhengSum = rhm.getDouble("revenueTiaozhengSum");
			double billedSum = rhm.getDouble("billedSum");
			double cashInSum = rhm.getDouble("cashInSum");

			request.setAttribute("contractCostSum", contractCostSum);
			request.setAttribute("revenueSum", revenueSum);
			request.setAttribute("revenueTiaozhengSum", revenueTiaozhengSum);
			request.setAttribute("billedSum", billedSum);
			request.setAttribute("cashInSum", cashInSum);
			request.getRequestDispatcher("/item/itemreport3_add.jsp").forward(
					request, response);
		} catch (Exception e) {
			log.error("", e);
		}
		return null;
	}

	/**
	 * 插入itemReport2记录
	 * 
	 * @return
	 */
	public String addItemReport3() {
		itemReport3 = new ItemReport3();
		double contractCostDou = 0;
		try {
			contractCostDou = Double.parseDouble(contractCost);
		} catch (Exception e) {

		}
		double feiyongBaifenbiDou = 0;
		try {
			feiyongBaifenbiDou = Double.parseDouble(feiyongBaifenbi);
		} catch (Exception e) {

		}
		double feiyongChengbenDou = 0;
		try {
			feiyongChengbenDou = Double.parseDouble(feiyongChengben);
		} catch (Exception e) {

		}
		double fenbaofeiDou = 0;
		try {
			fenbaofeiDou = Double.parseDouble(fenbaofei);
		} catch (Exception e) {

		}
		double mDou = 0;
		try {
			mDou = Double.parseDouble(m);
		} catch (Exception e) {

		}
		double rengongChengbenDou = 0;
		try {
			rengongChengbenDou = Double.parseDouble(rengongChengben);
		} catch (Exception e) {

		}
		double revenueDou = 0;
		try {
			revenueDou = Double.parseDouble(revenue);
		} catch (Exception e) {

		}
		double revenueBaifenbiDou = 0;
		try {
			revenueBaifenbiDou = Double.parseDouble(revenueBaifenbi);
		} catch (Exception e) {

		}
		double revenueTiaozhengDou = 0;
		try {
			revenueTiaozhengDou = Double.parseDouble(revenueTiaozheng);
		} catch (Exception e) {

		}
		double shuijinDou = 0;
		try {
			shuijinDou = Double.parseDouble(shuijin);
		} catch (Exception e) {

		}
		double xiangmuJinduBaifenbiDou = 0;
		try {
			xiangmuJinduBaifenbiDou = Double.parseDouble(xiangmuJinduBaifenbi);
		} catch (Exception e) {

		}
		double billedDou = 0;
		try {
			billedDou = Double.parseDouble(billed);
		} catch (Exception e) {

		}
		double cashInDou = 0;
		try {
			cashInDou = Double.parseDouble(cashIn);
		} catch (Exception e) {

		}
		double eubDou = 0;
		try {
			eubDou = Double.parseDouble(eub);
		} catch (Exception e) {

		}
		double arDou = 0;
		try {
			arDou = Double.parseDouble(ar);
		} catch (Exception e) {

		}

		itemReport3.setContractCost(contractCostDou);
		itemReport3.setFeiyongBaifenbi(feiyongBaifenbiDou);
		itemReport3.setFeiyongChengben(feiyongChengbenDou);
		itemReport3.setFenbaofei(fenbaofeiDou);
		itemReport3.setItemId(itemId);
		itemReport3.setM(mDou);
		itemReport3.setMingcheng(mingcheng);
		itemReport3.setRengongChengben(rengongChengbenDou);
		itemReport3.setReportId(reportId);
		itemReport3.setRevenue(revenueDou);
		itemReport3.setRevenueBaifenbi(revenueBaifenbiDou);
		itemReport3.setRevenueTiaozheng(revenueTiaozhengDou);
		itemReport3.setRiqi(riqi);
		itemReport3.setShuijin(shuijinDou);
		itemReport3.setXiangmuJinduBaifenbi(xiangmuJinduBaifenbiDou);
		itemReport3.setBilled(billedDou);
		itemReport3.setCashIn(cashInDou);
		itemReport3.setEub(eubDou);
		itemReport3.setAr(arDou);

		ParameterMap pm = new ParameterMap();
		pm.put("itemReport3", itemReport3);
		try {
			ResultHashMap rhm = itemReportManager.addItemReport3(pm);
			if (!rhm.getResult()) {
				Javascript js = new Javascript(ServletActionContext
						.getResponse());
				js.alert(rhm.getMessage());
				return NONE;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ServletActionContext.getResponse().sendRedirect(
					"itemReport_showReport.action?itemId="
							+ itemReport3.getItemId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 删除item_report2记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteItemReport3() throws Exception {
		ParameterMap pm = new ParameterMap();
		pm.put("id", id);
		try {
			itemReportManager.deleteItemReport3(pm);
			ServletActionContext.getResponse().sendRedirect(
					"itemReport_showReport.action?itemId=" + itemId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return NONE;
	}

	/**
	 * 显示item_report2记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showItemReport3() throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			ParameterMap pm = new ParameterMap();
			pm.put("id", id);
			ResultHashMap rhm = itemReportManager.showItemReport3(pm);
			itemReport3 = (ItemReport3) rhm.get("itemReport3");
			contractCost = rhm.getDouble("contractCost") + "";

			double contractCostSum = rhm.getDouble("contractCostSum");
			double revenueSum = rhm.getDouble("revenueSum");
			double revenueTiaozhengSum = rhm.getDouble("revenueTiaozhengSum");
			double billedSum = rhm.getDouble("billedSum");
			double cashInSum = rhm.getDouble("cashInSum");

			request.setAttribute("contractCostSum", contractCostSum);
			request.setAttribute("revenueSum", revenueSum);
			request.setAttribute("revenueTiaozhengSum", revenueTiaozhengSum);
			request.setAttribute("billedSum", billedSum);
			request.setAttribute("cashInSum", cashInSum);

			request.getRequestDispatcher("/item/itemreport3_update.jsp")
					.forward(request, response);
		} catch (Exception e) {
			log.error("", e);
		}
		return NONE;
	}

	/**
	 * 更新itemReport2记录
	 * 
	 * @return
	 */
	public String updateItemReport3() {
		itemReport3 = new ItemReport3();
		double contractCostDou = 0;
		try {
			contractCostDou = Double.parseDouble(contractCost);
		} catch (Exception e) {

		}
		double feiyongBaifenbiDou = 0;
		try {
			feiyongBaifenbiDou = Double.parseDouble(feiyongBaifenbi);
		} catch (Exception e) {

		}
		double feiyongChengbenDou = 0;
		try {
			feiyongChengbenDou = Double.parseDouble(feiyongChengben);
		} catch (Exception e) {

		}
		double fenbaofeiDou = 0;
		try {
			fenbaofeiDou = Double.parseDouble(fenbaofei);
		} catch (Exception e) {

		}
		double mDou = 0;
		try {
			mDou = Double.parseDouble(m);
		} catch (Exception e) {

		}
		double rengongChengbenDou = 0;
		try {
			rengongChengbenDou = Double.parseDouble(rengongChengben);
		} catch (Exception e) {

		}
		double revenueDou = 0;
		try {
			revenueDou = Double.parseDouble(revenue);
		} catch (Exception e) {

		}
		double revenueBaifenbiDou = 0;
		try {
			revenueBaifenbiDou = Double.parseDouble(revenueBaifenbi);
		} catch (Exception e) {

		}
		double revenueTiaozhengDou = 0;
		try {
			revenueTiaozhengDou = Double.parseDouble(revenueTiaozheng);
		} catch (Exception e) {

		}
		double shuijinDou = 0;
		try {
			shuijinDou = Double.parseDouble(shuijin);
		} catch (Exception e) {

		}
		double xiangmuJinduBaifenbiDou = 0;
		try {
			xiangmuJinduBaifenbiDou = Double.parseDouble(xiangmuJinduBaifenbi);
		} catch (Exception e) {

		}
		double billedDou = 0;
		try {
			billedDou = Double.parseDouble(billed);
		} catch (Exception e) {

		}
		double cashInDou = 0;
		try {
			cashInDou = Double.parseDouble(cashIn);
		} catch (Exception e) {

		}
		double eubDou = 0;
		try {
			eubDou = Double.parseDouble(eub);
		} catch (Exception e) {

		}
		double arDou = 0;
		try {
			arDou = Double.parseDouble(ar);
		} catch (Exception e) {

		}
		itemReport3.setId(id);
		itemReport3.setContractCost(contractCostDou);
		itemReport3.setFeiyongBaifenbi(feiyongBaifenbiDou);
		itemReport3.setFeiyongChengben(feiyongChengbenDou);
		itemReport3.setFenbaofei(fenbaofeiDou);
		itemReport3.setItemId(itemId);
		itemReport3.setM(mDou);
		itemReport3.setMingcheng(mingcheng);
		itemReport3.setRengongChengben(rengongChengbenDou);
		itemReport3.setReportId(reportId);
		itemReport3.setRevenue(revenueDou);
		itemReport3.setRevenueBaifenbi(revenueBaifenbiDou);
		itemReport3.setRevenueTiaozheng(revenueTiaozhengDou);
		itemReport3.setRiqi(riqi);
		itemReport3.setShuijin(shuijinDou);
		itemReport3.setXiangmuJinduBaifenbi(xiangmuJinduBaifenbiDou);
		itemReport3.setBilled(billedDou);
		itemReport3.setCashIn(cashInDou);
		itemReport3.setEub(eubDou);
		itemReport3.setAr(arDou);
		itemReport3.setSort(sort);
		itemReport3.setType(type);

		ParameterMap pm = new ParameterMap();
		pm.put("itemReport3", itemReport3);
		try {
			ResultHashMap rhm = itemReportManager.updateItemReport3(pm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ServletActionContext.getResponse().sendRedirect(
					"itemReport_showReport.action?itemId="
							+ itemReport3.getItemId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}

	public String getYusuanByRiqi() {
		ParameterMap pm = new ParameterMap();
		pm.put("itemId", itemId);
		pm.put("riqi", riqi);
		try {
			ResultHashMap rhm = itemReportManager.getYusuanByRiqi(pm);

			double m = rhm.getDouble("m");
			double contractCostSum = rhm.getDouble("contractCostSum");
			String json = "{\"m\":" + m + ",\"contractCostSum\":"
					+ contractCostSum + "}";
			Javascript js = new Javascript(ServletActionContext.getResponse());
			js.outPlainText(json);
		} catch (Exception e) {
			log.error("", e);
		}
		return NONE;
	}

	public void setItemReportManager(ItemReportManager itemReportManager) {
		this.itemReportManager = itemReportManager;
	}

	public List getItemReport2List() {
		return itemReport2List;
	}

	public ItemReport2 getItemReport2() {
		return itemReport2;
	}

	public ItemReport getItemReport() {
		return itemReport;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getContractMoney() {
		return contractMoney;
	}

	public void setContractMoney(String contractMoney) {
		this.contractMoney = contractMoney;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getVoMoney() {
		return voMoney;
	}

	public void setVoMoney(String voMoney) {
		this.voMoney = voMoney;
	}

	public String getVoDate() {
		return voDate;
	}

	public void setVoDate(String voDate) {
		this.voDate = voDate;
	}

	public String getEngineerConfig() {
		return engineerConfig;
	}

	public void setEngineerConfig(String engineerConfig) {
		this.engineerConfig = engineerConfig;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMingcheng() {
		return mingcheng;
	}

	public void setMingcheng(String mingcheng) {
		this.mingcheng = mingcheng;
	}

	public String getRiqi() {
		return riqi;
	}

	public void setRiqi(String riqi) {
		this.riqi = riqi;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getRengongChengben() {
		return rengongChengben;
	}

	public void setRengongChengben(String rengongChengben) {
		this.rengongChengben = rengongChengben;
	}

	public String getFeiyongChengben() {
		return feiyongChengben;
	}

	public void setFeiyongChengben(String feiyongChengben) {
		this.feiyongChengben = feiyongChengben;
	}

	public String getShuijin() {
		return shuijin;
	}

	public void setShuijin(String shuijin) {
		this.shuijin = shuijin;
	}

	public String getFenbaofei() {
		return fenbaofei;
	}

	public void setFenbaofei(String fenbaofei) {
		this.fenbaofei = fenbaofei;
	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getContractCost() {
		return contractCost;
	}

	public void setContractCost(String contractCost) {
		this.contractCost = contractCost;
	}

	public String getRevenue() {
		return revenue;
	}

	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}

	public String getRevenueTiaozheng() {
		return revenueTiaozheng;
	}

	public void setRevenueTiaozheng(String revenueTiaozheng) {
		this.revenueTiaozheng = revenueTiaozheng;
	}

	public String getFeiyongBaifenbi() {
		return feiyongBaifenbi;
	}

	public void setFeiyongBaifenbi(String feiyongBaifenbi) {
		this.feiyongBaifenbi = feiyongBaifenbi;
	}

	public String getRevenueBaifenbi() {
		return revenueBaifenbi;
	}

	public void setRevenueBaifenbi(String revenueBaifenbi) {
		this.revenueBaifenbi = revenueBaifenbi;
	}

	public String getXiangmuJinduBaifenbi() {
		return xiangmuJinduBaifenbi;
	}

	public void setXiangmuJinduBaifenbi(String xiangmuJinduBaifenbi) {
		this.xiangmuJinduBaifenbi = xiangmuJinduBaifenbi;
	}

	public String getBilled() {
		return billed;
	}

	public void setBilled(String billed) {
		this.billed = billed;
	}

	public String getAr() {
		return ar;
	}

	public void setAr(String ar) {
		this.ar = ar;
	}

	public String getEub() {
		return eub;
	}

	public void setEub(String eub) {
		this.eub = eub;
	}

	public String getCashIn() {
		return cashIn;
	}

	public void setCashIn(String cashIn) {
		this.cashIn = cashIn;
	}

	public List getItemReport3List() {
		return itemReport3List;
	}

	public ItemReport3 getItemReport3() {
		return itemReport3;
	}

	public String getRengongChengbenSum() {
		return rengongChengbenSum;
	}

	public void setRengongChengbenSum(String rengongChengbenSum) {
		this.rengongChengbenSum = rengongChengbenSum;
	}

	public String getFenbaofeiSum() {
		return fenbaofeiSum;
	}

	public void setFenbaofeiSum(String fenbaofeiSum) {
		this.fenbaofeiSum = fenbaofeiSum;
	}

	public String getShuijinSum() {
		return shuijinSum;
	}

	public void setShuijinSum(String shuijinSum) {
		this.shuijinSum = shuijinSum;
	}

	public String getFeiyongChengbenSum() {
		return feiyongChengbenSum;
	}

	public void setFeiyongChengbenSum(String feiyongChengbenSum) {
		this.feiyongChengbenSum = feiyongChengbenSum;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}
}
