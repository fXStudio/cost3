package com.ruling.cost.itemreport.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.common.been.ParameterMap;
import com.common.been.ResultHashMap;
import com.ruling.cost.item.domain.Item;
import com.ruling.cost.itemreport.domain.ItemReport;
import com.ruling.cost.itemreport.domain.ItemReport2;
import com.ruling.cost.itemreport.domain.ItemReport3;
import com.ruling.cost.itemreport.service.ItemReportManager;
import com.ruling.cost.util.dao.ObjectDao;
import com.ruling.cost.utils.DateTool;

public class ItemReportImpl implements ItemReportManager {
	public final static String hql = "from ItemReport2 where itemId=? order by riqi ";
	private ObjectDao objectDao;

	public ResultHashMap showReport(ParameterMap map) throws Exception {
		// TODO Auto-generated method stub
		ResultHashMap rhm = new ResultHashMap();
		boolean has = false;
		ItemReport ir = null;
		int itemId = map.getInt("itemId");
		List irList = (List) objectDao.query("from ItemReport where itemId=?",
				new Object[] { itemId }, 0, 0);
		// 没有记录
		if (irList == null || irList.isEmpty()) {
			List itemList = (List) objectDao.query("from Item where id=?",
					new Object[] { itemId }, 0, 0);
			if (itemList != null && !itemList.isEmpty()) {
				Item item = (Item) itemList.get(0);
				ir = new ItemReport();
				ir.setItemId(itemId);
				ir.setItemcode(item.getItemcode());
				ir.setItemname(item.getItemname());
			}
			has = false;
		} else {// 有记录
			has = true;
			ir = (ItemReport) irList.get(0);

			List itemReport2List = objectDao.query(hql,
					new Object[] { itemId }, 0, 0);
			double rengongChengben_hj = 0;
			double feiyongChengben_hj = 0;
			double shuijin_hj = 0;
			double fenbaofei_hj = 0;
			double revenue_hj = 0;
			double revenue_tiaozheng_hj = 0;
			double feiyongBaifenbi_hj = 0;
			double revenueBaifenbi_hj = 0;
			if (itemReport2List != null && !itemReport2List.isEmpty()) {
				for (Object obj : itemReport2List) {
					ItemReport2 ir2 = (ItemReport2) obj;
					rengongChengben_hj += ir2.getRengongChengben();
					feiyongChengben_hj += ir2.getFeiyongChengben();
					shuijin_hj += ir2.getShuijin();
					fenbaofei_hj += ir2.getFenbaofei();
					revenue_hj += ir2.getRevenue();
					revenue_tiaozheng_hj += ir2.getRevenueTiaozheng();
					feiyongBaifenbi_hj += ir2.getFeiyongBaifenbi();
					revenueBaifenbi_hj += ir2.getRevenueBaifenbi();
				}
				ItemReport2 hj = new ItemReport2();
				hj.setMingcheng("合计");
				hj.setRengongChengben(rengongChengben_hj);
				hj.setFeiyongChengben(feiyongChengben_hj);
				hj.setShuijin(shuijin_hj);
				hj.setFenbaofei(fenbaofei_hj);
				// double
				// m_hj=(ir.getContractMoney()+ir.getVoMoney()-hj.getFeiyongChengben()-hj.getShuijin()-hj.getFenbaofei())/hj.getRengongChengben();
				// hj.setM(m_hj);
				double contractCost_hj = hj.getRengongChengben()
						+ hj.getFeiyongChengben() + hj.getShuijin()
						+ hj.getFenbaofei();
				hj.setContractCost(contractCost_hj);
				hj.setRevenue(revenue_hj);
				hj.setRevenueTiaozheng(revenue_tiaozheng_hj);
				hj.setFeiyongBaifenbi(feiyongBaifenbi_hj);
				hj.setRevenueBaifenbi(revenueBaifenbi_hj);

				itemReport2List.add(hj);
			}
			rhm.put("itemReport2List", itemReport2List);

			int showModifyIndex = -1;
			List itemReport3List = objectDao
					.query(
							"from ItemReport3 where itemId=? and riqi is not null order by sort ",
							new Object[] { itemId }, 0, 0);

			rhm.put("itemReport3List", itemReport3List);
			if (itemReport3List != null && !itemReport3List.isEmpty()) {
				for (int i = itemReport3List.size() - 1; i >= 0; i--) {
					ItemReport3 ir3 = (ItemReport3) itemReport3List.get(i);
					if (ir3.getType() == 1) {
						showModifyIndex = i;
						break;
					}
				}
			}
			rhm.put("showModifyIndex", showModifyIndex);// 最后一条type是1的记录，才显示修改
		}
		rhm.put("ir", ir);
		rhm.put("has", has);
		return rhm;
	}

	// private List dataingItemReport3List(List list,double
	// contract_cost_yusuan_hj){
	// List reList=new ArrayList();
	// double rengongChengben_hj=0;
	// double feiyongChengben_hj=0;
	// double shuijin_hj=0;
	// double fenbaofei_hj=0;
	// double contract_cost_hj=0;
	// double revenue_hj=0;
	// double revenue_tiaozheng_hj=0;
	// if(list!=null && !list.isEmpty()){
	// for(Object obj:list){
	// ItemReport3 ir3=(ItemReport3)obj;
	// reList.add(ir3);
	//				
	// rengongChengben_hj+=ir3.getRengongChengben();
	// feiyongChengben_hj+=ir3.getFeiyongChengben();
	// shuijin_hj+=ir3.getShuijin();
	// fenbaofei_hj+=ir3.getFenbaofei();
	// revenue_hj+=ir3.getRevenue();
	// revenue_tiaozheng_hj+=ir3.getRevenueTiaozheng();
	// contract_cost_hj+=ir3.getContractCost();
	// ItemReport3 ir3Sum=new ItemReport3();
	// // ir3Sum.setRiqi("合计");
	// ir3Sum.setRengongChengben(rengongChengben_hj);
	// ir3Sum.setFeiyongChengben(feiyongChengben_hj);
	// ir3Sum.setShuijin(shuijin_hj);
	// ir3Sum.setFenbaofei(fenbaofei_hj);
	// ir3Sum.setContractCost(contract_cost_hj);
	// ir3Sum.setRevenue(revenue_hj);
	// ir3Sum.setRevenueTiaozheng(revenue_tiaozheng_hj);
	// double feiyongBaifenbi=contract_cost_hj/contract_cost_yusuan_hj*100;
	// BigDecimal b=new BigDecimal(feiyongBaifenbi);
	// feiyongBaifenbi= b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	//
	// ir3Sum.setFeiyongBaifenbi(feiyongBaifenbi);
	// }
	// }
	// return reList;
	// }
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}

	public ResultHashMap add(ParameterMap map) throws Exception {
		// TODO Auto-generated method stub
		ResultHashMap rhm = new ResultHashMap();
		ItemReport ir = (ItemReport) map.get("itemReport");
		objectDao.save(ir);
		rhm.setId(ir.getId());
		return rhm;
	}

	public ResultHashMap addItemReport2(ParameterMap map) throws Exception {
		// TODO Auto-generated method stub
		ResultHashMap rhm = new ResultHashMap();
		ItemReport2 ir2 = (ItemReport2) map.get("itemReport2");
		// 判断日期是否为最大
		List list = objectDao.querySQL(
				"select riqi from item_report2 where report_id='"
						+ ir2.getReportId() + "' and '" + ir2.getRiqi()
						+ "'<=riqi  ", null, 0, 1);
		if (list == null || list.isEmpty() || list.get(0) == null) {
			objectDao.save(ir2);
			rhm.setId(ir2.getId());
			rhm.setResult(true);

		} else {
			rhm.setResult(false);
			rhm.setMessage("输入的日期必须比其他记录中的日期都要大！");
		}
		return rhm;
	}

	public ResultHashMap addItemReport3(ParameterMap map) throws Exception {
		ResultHashMap rhm = new ResultHashMap();
		ItemReport3 ir3 = (ItemReport3) map.get("itemReport3");
		// 判断日期是否为最大
		List list = objectDao.querySQL(
				"select riqi from item_report3 where report_id='"
						+ ir3.getReportId() + "' and '" + ir3.getRiqi()
						+ "'<=riqi  and type='1'", null, 0, 1);
		if (list == null || list.isEmpty() || list.get(0) == null) {
			rhm.setResult(true);

		} else {
			rhm.setResult(false);
			rhm.setMessage("输入的日期必须比其他记录中的日期都要大！");
			return rhm;
		}

		int maxSort = queryMaxSortFromItemReport3();
		ir3.setSort(maxSort + 1);
		ir3.setType(1);
		objectDao.save(ir3);
		rhm.setId(ir3.getId());

		// ====================================添加合计的记录=======================================
		addSumIr3(ir3, maxSort + 2);
		return rhm;
	}

	private void addSumIr3(ItemReport3 ir3, int sort) {
		// 查询item_report3合计，由于sumItemReport3是用sql语句查询，与hibernate保存不在一个session中，所以要添加上本次的值
		ResultHashMap sumRhm = sumItemReport3(ir3.getItemId());
		double feiyongChengbenSum = sumRhm.getDouble("feiyongChengbenSum")
				+ ir3.getFeiyongChengben();
		feiyongChengbenSum = new BigDecimal(feiyongChengbenSum).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		double shuijinSum = sumRhm.getDouble("shuijinSum") + ir3.getShuijin();
		shuijinSum = new BigDecimal(shuijinSum).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		double fenbaofeiSum = sumRhm.getDouble("fenbaofeiSum")
				+ ir3.getFenbaofei();
		fenbaofeiSum = new BigDecimal(fenbaofeiSum).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		double rengongChengbenSum = sumRhm.getDouble("rengongChengbenSum")
				+ ir3.getRengongChengben();
		rengongChengbenSum = new BigDecimal(rengongChengbenSum).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		double contractCostSum = sumRhm.getDouble("contractCostSum")
				+ ir3.getContractCost();
		contractCostSum = new BigDecimal(contractCostSum).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		double revenueSum = sumRhm.getDouble("revenueSum") + ir3.getRevenue();
		revenueSum = new BigDecimal(revenueSum).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		double revenueTiaozhengSum = sumRhm.getDouble("revenueTiaozhengSum")
				+ ir3.getRevenueTiaozheng();
		revenueTiaozhengSum = new BigDecimal(revenueTiaozhengSum).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		double billedSum = sumRhm.getDouble("billedSum") + ir3.getBilled();
		billedSum = new BigDecimal(billedSum).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		double cashInSum = sumRhm.getDouble("cashInSum") + ir3.getCashIn();
		cashInSum = new BigDecimal(cashInSum).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		// 预算部分
		ResultHashMap sumItemReport2 = sumItemReport2(ir3.getItemId());
		double contractCostYusuanSum = sumItemReport2
				.getDouble("contractCostSum");
		contractCostYusuanSum = new BigDecimal(contractCostYusuanSum).setScale(
				2, BigDecimal.ROUND_HALF_UP).doubleValue();

		// item_report部分
		ItemReport ir = (ItemReport) objectDao.query(
				"from ItemReport where itemId=?",
				new Object[] { ir3.getItemId() }, 0, 0).get(0);

		// rhm.put("feiyongChengbenSum", feiyongChengbenSum);
		// rhm.put("shuijinSum", shuijinSum);
		// rhm.put("fenbaofeiSum", fenbaofeiSum);
		// rhm.put("rengongChengbenSum", rengongChengbenSum);
		// rhm.put("contractCostSum", contractCostSum);

		ItemReport3 ir3Sum = new ItemReport3();
		// ir3Sum.setMingcheng("");
		ir3Sum.setRiqi("合计");
		ir3Sum.setSort(sort);
		ir3Sum.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		ir3Sum.setRengongChengben(rengongChengbenSum);
		ir3Sum.setFeiyongChengben(feiyongChengbenSum);
		ir3Sum.setShuijin(shuijinSum);
		ir3Sum.setFenbaofei(fenbaofeiSum);
		ir3Sum.setContractCost(contractCostSum);
		ir3Sum.setRevenue(revenueSum);
		ir3Sum.setRevenueTiaozheng(revenueTiaozhengSum);
		double feiyongBaifenbiSum = contractCostSum / contractCostYusuanSum
				* 100;
		feiyongBaifenbiSum = new BigDecimal(feiyongBaifenbiSum).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		ir3Sum.setFeiyongBaifenbi(feiyongBaifenbiSum);
		double revenueBaifenbiSum = (revenueSum + revenueTiaozhengSum)
				/ (ir.getContractMoney() + ir.getVoMoney()) * 100;
		revenueBaifenbiSum = new BigDecimal(revenueBaifenbiSum).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		ir3Sum.setRevenueBaifenbi(revenueBaifenbiSum);
		ir3Sum.setBilled(billedSum);
		ir3Sum.setCashIn(cashInSum);
		ir3Sum.setAr(billedSum - cashInSum);
		ir3Sum.setType(2);
		ir3Sum.setItemId(ir3.getItemId());
		ir3Sum.setReportId(ir3.getReportId());
		ir3Sum.setId2(ir3.getId());
		objectDao.save(ir3Sum);

		// =======================================添加超过80%的记录=====================================================
		// List ir2List=objectDao.querySQL("select
		// rengong_chengben,feiyong_chengben,shuijin,fenbaofei from item_report2
		// order by riqi desc ", null, 0, 1);
		ResultHashMap sumItemReport2RHM = sumItemReport2(ir3.getItemId(),
				" and riqi<='" + ir3.getRiqi() + "'");
		double rengong_chengbenYusuan = sumItemReport2RHM
				.getDouble("rengongChengbenSum");
		double feiyong_chengbenYusuan = sumItemReport2RHM
				.getDouble("feiyongChengbenSum");
		double shuijinYusuan = sumItemReport2RHM.getDouble("shuijinSum");
		double fenbaofeiYusuan = sumItemReport2RHM.getDouble("fenbaofeiSum");
		boolean rengongChengbenChao = false;
		boolean feiyongChengbenChao = false;
		boolean shuijinChao = false;
		boolean fenbaofeiChao = false;
		if (rengongChengbenSum > (0.8 * rengong_chengbenYusuan)) {
			rengongChengbenChao = true;
		}
		if (feiyongChengbenSum > (0.8 * feiyong_chengbenYusuan)) {
			feiyongChengbenChao = true;
		}
		if (shuijinSum > (0.8 * shuijinYusuan)) {
			shuijinChao = true;
		}
		if (fenbaofeiSum > (0.8 * fenbaofeiYusuan)) {
			fenbaofeiChao = true;
		}
		if (rengongChengbenChao || feiyongChengbenChao || shuijinChao
				|| fenbaofeiChao) {
			// 取上一条非合计的记录
			List lastIr3List = objectDao.query(
					"from ItemReport3 where type=1 and id<>'" + ir3.getId()
							+ "' order by sort desc", null, 0, 1);
			double lastRengongChengben = 0d;
			double lastFeiyongChengben = 0d;
			double lastShuijin = 0d;
			double lastFenbaofei = 0d;
			double lastContractCost = 0d;
			double lastRevenue = 0d;
			double lastRevenueBaifenbi = 0d;
			double lastRevenueTiaozheng = 0d;
			if (lastIr3List != null && !lastIr3List.isEmpty()) {
				ItemReport3 lastIr3 = (ItemReport3) lastIr3List.get(0);
				lastRengongChengben = lastIr3.getRengongChengben();
				lastFeiyongChengben = lastIr3.getFeiyongChengben();
				lastShuijin = lastIr3.getShuijin();
				lastFenbaofei = lastIr3.getFenbaofei();
				lastContractCost = lastIr3.getContractCost();
				lastRevenue = lastIr3.getRevenue();
				lastRevenueBaifenbi = lastIr3.getRevenueBaifenbi();
				lastRevenueTiaozheng = lastIr3.getRevenueTiaozheng();
			}
			ItemReport3 jingbaoIr3 = new ItemReport3();
			jingbaoIr3.setRengongChengben(new BigDecimal(lastRengongChengben
					+ ir3.getRengongChengben()).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue());
			jingbaoIr3.setFeiyongChengben(new BigDecimal(lastFeiyongChengben
					+ ir3.getFeiyongChengben()).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue());
			jingbaoIr3
					.setShuijin(new BigDecimal(lastShuijin + ir3.getShuijin())
							.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue());
			jingbaoIr3.setFenbaofei(new BigDecimal(lastFenbaofei
					+ ir3.getFenbaofei()).setScale(2, BigDecimal.ROUND_HALF_UP)
					.doubleValue());
			jingbaoIr3.setContractCost(new BigDecimal(lastContractCost
					+ ir3.getContractCost()).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue());
			jingbaoIr3
					.setRevenue(new BigDecimal(lastRevenue + ir3.getRevenue())
							.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue());
			jingbaoIr3.setRevenueTiaozheng(new BigDecimal(lastRevenueTiaozheng
					+ ir3.getRevenueTiaozheng()).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue());
			jingbaoIr3.setRevenueBaifenbi(new BigDecimal(lastRevenueBaifenbi
					+ ir3.getRevenueBaifenbi()).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue());
			jingbaoIr3.setType(3);
			jingbaoIr3.setSort(sort + 1);
			jingbaoIr3.setId2(ir3.getId());
			jingbaoIr3.setItemId(ir3.getItemId());
			jingbaoIr3.setReportId(ir3.getReportId());
			objectDao.save(jingbaoIr3);
		}

	}

	private int queryMaxSortFromItemReport3() {
		List list = objectDao.querySQL("select max(sort) from item_report3",
				null, 0, 0);
		int maxSort = 0;
		if (list == null || list.isEmpty() || list.get(0) == null) {
			maxSort = 1;
		} else {
			maxSort = Integer.parseInt(list.get(0).toString());
		}
		return maxSort;
	}

	public ResultHashMap deleteItemReport(ParameterMap map) throws Exception {
		String id = (String) map.get("id");
		ResultHashMap rhm = new ResultHashMap();
		// 判断item_report2和Item_report3表是否还有记录
		boolean hasItemReport = false;
		List list = objectDao.querySQL(
				"select count(id) from item_report2 where report_id=?",
				new Object[] { id }, 0, 0);
		if (list != null && !list.isEmpty()) {
			Object o = list.get(0);
			if (o != null && !"".equals(o)) {
				int i = Integer.parseInt(o.toString());
				if (i > 0)
					hasItemReport = true;
			}
		}
		if (hasItemReport) {
			rhm.setResult(false);
			rhm.setMessage("请将预算删除，然后再删除本记录！");
			return rhm;
		}

		ItemReport ir = new ItemReport();
		ir.setId(id);
		objectDao.delete(ir);
		rhm.setResult(true);
		return rhm;
	}

	public ResultHashMap deleteItemReport2(ParameterMap map) throws Exception {
		String id = (String) map.get("id");
		ItemReport2 ir2 = new ItemReport2();
		ir2.setId(id);
		objectDao.delete(ir2);
		return null;
	}

	public ResultHashMap deleteItemReport3(ParameterMap map) throws Exception {
		String id = (String) map.get("id");
		ItemReport3 ir3 = new ItemReport3();
		ir3.setId(id);
		objectDao.delete(ir3);

		int i = objectDao.updateSQL("delete from item_report3 where id2=?", id);
		return null;
	}

	public ResultHashMap showItemReport2(ParameterMap map) throws Exception {
		String id = (String) map.get("id");
		ResultHashMap rhm = new ResultHashMap();
		List list = objectDao.query("from ItemReport2 where id=?",
				new Object[] { id }, 0, 0);
		int itemId = -1;
		if (list != null && !list.isEmpty()) {
			Object obj = list.get(0);
			ItemReport2 ir2 = (ItemReport2) obj;
			itemId = ir2.getItemId();
			rhm.put("itemReport2", obj);

			ResultHashMap rhmSum = sumItemReport2(itemId);

			rhm.put("feiyongChengbenSum", rhmSum
					.getDouble("feiyongChengbenSum")
					- ir2.getFeiyongChengben());
			rhm.put("shuijinSum", rhmSum.getDouble("shuijinSum")
					- ir2.getShuijin());
			rhm.put("fenbaofeiSum", rhmSum.getDouble("fenbaofeiSum")
					- ir2.getFenbaofei());
			rhm.put("rengongChengbenSum", rhmSum
					.getDouble("rengongChengbenSum")
					- ir2.getRengongChengben());
		}

		return rhm;
	}

	public ResultHashMap showItemReport3(ParameterMap map) throws Exception {
		String id = (String) map.get("id");
		ResultHashMap rhm = new ResultHashMap();
		List list = objectDao.query("from ItemReport3 where id=?",
				new Object[] { id }, 0, 0);
		if (list != null && !list.isEmpty()) {
			Object obj = list.get(0);
			rhm.put("itemReport3", obj);

			ItemReport3 ir3 = (ItemReport3) obj;
			// 查询最近预算的m值和contractCost
			// ResultHashMap rhm2=queryLastestM(ir3.getItemId());
			// double m=queryLastestM(ir3.getItemId());
			// double
			// contractCost=sumItemReport2(ir3.getItemId()).getDouble("contractCostSum");
			ResultHashMap sumItemReport2RHM = sumItemReport2(ir3.getItemId(),
					" and riqi<='" + ir3.getRiqi() + "'");
			double contractCost = sumItemReport2RHM
					.getDouble("contractCostSum");

			ResultHashMap sr3Rhm = sumItemReport3(ir3.getItemId());
			double contractCostSum = sr3Rhm.getDouble("contractCostSum");
			double revenueSum = sr3Rhm.getDouble("revenueSum");
			double revenueTiaozhengSum = sr3Rhm
					.getDouble("revenueTiaozhengSum");
			double billedSum = sr3Rhm.getDouble("billedSum");
			double cashInSum = sr3Rhm.getDouble("cashInSum");

			rhm.put("contractCost", contractCost);
			// 合计值需要减掉当前修改的项目的值
			rhm.put("contractCostSum", contractCostSum - ir3.getContractCost());
			rhm.put("revenueSum", revenueSum - ir3.getRevenue());
			rhm.put("revenueTiaozhengSum", revenueTiaozhengSum
					- ir3.getRevenueTiaozheng());
			rhm.put("billedSum", billedSum - ir3.getBilled());
			rhm.put("cashInSum", cashInSum - ir3.getCashIn());
		}

		return rhm;
	}

	public ResultHashMap updateItemReport(ParameterMap map) throws Exception {
		// TODO Auto-generated method stub
		ItemReport ir = (ItemReport) map.get("itemReport");
		objectDao.update(ir);
		return null;
	}

	public ResultHashMap updateItemReport2(ParameterMap map) throws Exception {
		ItemReport2 ir2 = (ItemReport2) map.get("itemReport2");
		objectDao.update(ir2);
		return null;
	}

	public ResultHashMap updateItemReport3(ParameterMap map) throws Exception {
		ItemReport3 ir3 = (ItemReport3) map.get("itemReport3");
		// objectDao.update(ir3);
		objectDao.delete(ir3);

		int i = objectDao.updateHQL(
				"delete from ItemReport3 where id2=? and type<>1", ir3.getId());

		// ====================================添加合计的记录=======================================
		// addSumIr3(ir3,ir3.getSort()+1);

		ResultHashMap rhm = new ResultHashMap();
		objectDao.save(ir3);
		rhm.setId(ir3.getId());

		// ====================================添加合计的记录=======================================
		addSumIr3(ir3, ir3.getSort() + 1);

		return null;
	}

	public ResultHashMap forwardAddItemReport3Page(ParameterMap map)
			throws Exception {
		ResultHashMap rhm = new ResultHashMap();
		String riqi = DateTool.getDate(new Date(), "yyyy-MM");
		// String riqi="2013-04";

		int itemId = map.getInt("itemId");
		// int itemId=18;
		String itemReportId = map.getString("itemReportId");

		// ==========================计算人工成本=============================
		rhm.put("rengongChengben", getRengongChengben(itemId, riqi));

		// 查询最近预算的m值和contractCost
		// ResultHashMap rhm2=queryLastestM(itemId);
		double m = 0;// queryLastestM(itemId);
		double contractCost = 0;// sumItemReport2(itemId).getDouble("contractCostSum");

		ResultHashMap sr3Rhm = sumItemReport3(itemId);
		double contractCostSum = sr3Rhm.getDouble("contractCostSum");
		double revenueSum = sr3Rhm.getDouble("revenueSum");
		double revenueTiaozhengSum = sr3Rhm.getDouble("revenueTiaozhengSum");
		double billedSum = sr3Rhm.getDouble("billedSum");
		double cashInSum = sr3Rhm.getDouble("cashInSum");

		rhm.put("m", m);
		rhm.put("contractCost", contractCost);
		rhm.put("contractCostSum", contractCostSum);
		rhm.put("revenueSum", revenueSum);
		rhm.put("revenueTiaozhengSum", revenueTiaozhengSum);
		rhm.put("billedSum", billedSum);
		rhm.put("cashInSum", cashInSum);

		return rhm;
	}

	private double getRengongChengben(int itemId, String riqi) {
		List hourList = queryHourByItemid(itemId, riqi);// 计算该项目在本月中，所有参与人员的工时
		List hourspalyList = null;
		double rengongChengben = 0;
		if (hourList != null && !hourList.isEmpty()) {// 如果该项目在本月有参与人员的工时
			hourspalyList = queryHourspalyByItemid(itemId);// 查询该项目中，参与人员的工时
			for (Object obj : hourList) {
				Object[] array = (Object[]) obj;
				int userid = (Integer) array[0];
				double hours = (Double) array[1];

				for (Object hourspalyTemp : hourspalyList) {
					Object[] hourspalyArray = (Object[]) hourspalyTemp;
					int userid2 = (Integer) hourspalyArray[0];
					double hoursPaly = (Double) hourspalyArray[1];
					if (userid == userid2) {
						rengongChengben = hours * hoursPaly + rengongChengben;
					}
				}
			}
			BigDecimal b = new BigDecimal(rengongChengben);
			rengongChengben = b.setScale(2, BigDecimal.ROUND_HALF_UP)
					.doubleValue();
		} else {
			rengongChengben = 0;
		}
		return rengongChengben;
	}

	/**
	 * 查询小于但最接近riqi的记录的m值
	 * 
	 * @param itemId
	 * @param riqi
	 * @return
	 */
	private double queryLastestM(int itemId, String riqi) {
		List resultList = objectDao.querySQL(
				"select m,contract_cost from item_report2 where item_id='"
						+ itemId + "' and riqi<='" + riqi
						+ "' order by riqi desc", null, 0, 1);
		double m = 0;
		// double contractCost=0;
		if (resultList != null && !resultList.isEmpty()) {
			Object[] array = (Object[]) resultList.get(0);
			m = (Double) array[0];
			// contractCost=(Double)array[1];
		}
		// ResultHashMap reMap=new ResultHashMap();
		// reMap.put("m", m);
		// reMap.put("contractCost", contractCost);
		return m;
	}

	/**
	 * 根据项目id查询该项目人员的时薪
	 * 
	 * @param itemid
	 * @return
	 */
	public List queryHourspalyByItemid(int itemid) {
		String sql = "select b.user,b.hourspaly from itemuser a,subsidy b where a.userid=b.user and a.itemid ='"
				+ itemid + "' order by user";
		List list = objectDao.querySQL(sql, null, 0, 0);
		List reList = new ArrayList();
		for (Object obj : list) {
			Object[] array = (Object[]) obj;
			int userid = (Integer) array[0];
			String hourspaly = (String) array[1];
			if (hourspaly != null && !"".equals(hourspaly)) {
				String[] strArray = hourspaly.split("\\|");
				if (strArray != null && strArray.length > 0) {
					String str = strArray[strArray.length - 1];
					String[] hourspalyArray = str.split(",");
					if (hourspalyArray != null && hourspalyArray.length > 0) {
						String paly = hourspalyArray[1];
						double palDouble = 0;
						try {
							palDouble = Double.parseDouble(paly);
						} catch (Exception e) {

						}
						reList.add(new Object[] { userid, palDouble });
					}
				}
			}
		}
		return reList;
	}

	/**
	 * 根据项目id、日期（日期格式为：yyyy-MM），查询该项目在该年月中，参与人员的所有工时
	 * 
	 * @param itemid
	 * @param riqi
	 * @return
	 */
	public List queryHourByItemid(int itemid, String riqi) {
		String sql = "select userid,sum(hour) from hour where hour<>'0' and itmeid ='"
				+ itemid
				+ "' and todaydate like '"
				+ riqi
				+ "%' group by userid";
		List list = objectDao.querySQL(sql, null, 0, 0);
		return list;
	}

	public ResultHashMap forwardAddItemReport2Page(ParameterMap map)
			throws Exception {
		int itemId = map.getInt("itemId");
		String itemReportId = map.getString("itemReportId");

		return sumItemReport2(itemId);
	}

	private ResultHashMap sumItemReport2(int itemId) {
		return sumItemReport2(itemId, null);
	}

	private ResultHashMap sumItemReport2(int itemId, String andWhereSql) {
		ResultHashMap rhm = new ResultHashMap();
		double feiyongChengbenSum = 0d;
		double shuijinSum = 0d;
		double fenbaofeiSum = 0d;
		double rengongChengbenSum = 0d;
		double contractCostSum = 0d;

		if (andWhereSql == null)
			andWhereSql = "";
		List list = objectDao
				.querySQL(
						"select sum(feiyong_chengben),sum(shuijin),sum(fenbaofei),sum(rengong_chengben),sum(contract_cost)  from item_report2 where item_id='"
								+ itemId + "' " + andWhereSql, null, 0, 0);
		if (list != null && !list.isEmpty()) {
			Object[] array = (Object[]) list.get(0);
			if (array[0] != null) {
				feiyongChengbenSum = Double.parseDouble(array[0].toString());
			}
			if (array[1] != null) {
				shuijinSum = Double.parseDouble(array[1].toString());
			}
			if (array[2] != null) {
				fenbaofeiSum = Double.parseDouble(array[2].toString());
			}
			if (array[3] != null) {
				rengongChengbenSum = Double.parseDouble(array[3].toString());
			}
			if (array[4] != null) {
				contractCostSum = Double.parseDouble(array[4].toString());
			}

		}

		rhm.put("feiyongChengbenSum", feiyongChengbenSum);
		rhm.put("shuijinSum", shuijinSum);
		rhm.put("fenbaofeiSum", fenbaofeiSum);
		rhm.put("rengongChengbenSum", rengongChengbenSum);
		rhm.put("contractCostSum", contractCostSum);

		return rhm;
	}

	private ResultHashMap sumItemReport3(int itemId) {
		ResultHashMap rhm = new ResultHashMap();
		double feiyongChengbenSum = 0d;
		double shuijinSum = 0d;
		double fenbaofeiSum = 0d;
		double rengongChengbenSum = 0d;
		double contractCostSum = 0d;
		double revenueSum = 0d;
		double revenueTiaozhengSum = 0d;
		double billedSum = 0d;
		double cashInSum = 0d;

		List list = objectDao
				.querySQL(
						"select sum(feiyong_chengben),sum(shuijin),sum(fenbaofei),sum(rengong_chengben),sum(contract_cost),sum(revenue),sum(revenue_tiaozheng),sum(billed),sum(cash_in)   from item_report3 where item_id='"
								+ itemId + "' and type=1", null, 0, 0);
		if (list != null && !list.isEmpty()) {
			Object[] array = (Object[]) list.get(0);
			if (array[0] != null) {
				feiyongChengbenSum = Double.parseDouble(array[0].toString());
			}
			if (array[1] != null) {
				shuijinSum = Double.parseDouble(array[1].toString());
			}
			if (array[2] != null) {
				fenbaofeiSum = Double.parseDouble(array[2].toString());
			}
			if (array[3] != null) {
				rengongChengbenSum = Double.parseDouble(array[3].toString());
			}
			if (array[4] != null) {
				contractCostSum = Double.parseDouble(array[4].toString());
			}
			if (array[5] != null) {
				revenueSum = Double.parseDouble(array[5].toString());
			}
			if (array[6] != null) {
				revenueTiaozhengSum = Double.parseDouble(array[6].toString());
			}
			if (array[7] != null) {
				billedSum = Double.parseDouble(array[7].toString());
			}
			if (array[8] != null) {
				cashInSum = Double.parseDouble(array[8].toString());
			}

		}

		rhm.put("feiyongChengbenSum", feiyongChengbenSum);
		rhm.put("shuijinSum", shuijinSum);
		rhm.put("fenbaofeiSum", fenbaofeiSum);
		rhm.put("rengongChengbenSum", rengongChengbenSum);
		rhm.put("contractCostSum", contractCostSum);
		rhm.put("revenueSum", revenueSum);
		rhm.put("revenueTiaozhengSum", revenueTiaozhengSum);
		rhm.put("billedSum", billedSum);
		rhm.put("cashInSum", cashInSum);

		return rhm;
	}

	public ResultHashMap getYusuanByRiqi(ParameterMap map) throws Exception {
		// TODO Auto-generated method stub
		int itemId = map.getInt("itemId");
		String riqi = map.getString("riqi");
		ResultHashMap rhm = new ResultHashMap();
		double m = queryLastestM(itemId, riqi);
		ResultHashMap sumItemReport2RHM = sumItemReport2(itemId, " and riqi<='"
				+ riqi + "'");
		double contractCostSum = sumItemReport2RHM.getDouble("contractCostSum");
		rhm.setResult(true);
		rhm.put("m", m);
		rhm.put("contractCostSum", contractCostSum);
		return rhm;
	}
}
