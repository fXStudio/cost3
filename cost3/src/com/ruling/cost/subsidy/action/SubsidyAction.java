package com.ruling.cost.subsidy.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.ruling.cost.subsidy.domain.Subsidy;
import com.ruling.cost.subsidy.service.SubsidyManager;
import com.ruling.cost.utils.BaseAction;
import com.ruling.cost.utils.PageModel;

public class SubsidyAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(SubsidyAction.class);

	private SubsidyManager subsidyService;

	private Subsidy subsidy;

	public SubsidyManager getSubsidyService() {
		return subsidyService;
	}

	public void setSubsidyService(SubsidyManager subsidyService) {
		this.subsidyService = subsidyService;
	}

	public Subsidy getSubsidy() {
		return subsidy;
	}

	public void setSubsidy(Subsidy subsidy) {
		this.subsidy = subsidy;
	}

	public String addInput() {
		return "subsidy_add_input";
	}

	public String add() {
		this.subsidyService.addSubsidy(subsidy);

		return "subsidy_add_success";
	}

	public String delete() {
		String[] deleteid = subsidy.getDeleteid().split(",");

		this.subsidyService.delSubsidy(java.util.Arrays.asList(deleteid));

		return "subsidy_del_success";
	}

	public String updateInput() {
		try {
			Subsidy newsub = this.subsidyService.findSubsidyById(subsidy.getId());
			newsub.setSubsidyhistory(this.subsidyService.findSubsidyHistoryById(newsub.getId()));
			this.getServletRequest().setAttribute("subsidy", newsub);
		} catch (Exception e) {
			logger.error("", e);
		}
		return "subsidy_update_input";
	}

	public String update() throws Exception {
		this.subsidyService.updateSubsidy(subsidy);

		return "subsidy_update_success";
	}

	public String browe() {
		try {
			PageModel pm = this.subsidyService.findAllSubsidy(subsidy);

			for (Object obj : pm.getDatas()) {
				Subsidy ss = (Subsidy) obj;
				ss.setSubsidyhistory(this.subsidyService.findSubsidyHistoryById(ss.getId()));
			}
			this.getServletRequest().setAttribute("pm", pm);
			this.getServletRequest().setAttribute("subsidy", subsidy);
		} catch (Exception e) {
			logger.error("", e);
		}

		return "browe";
	}

	InputStream excelStream;

	/**
	 * @return 导出个人工资报表
	 */
	public String expPersonExcel() {
		// 缓存IO
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// 生成保镖的二进制IO流
		this.subsidyService.exportPersonSalary(subsidy.getId(), out);
		// 创建到客户端的二进制数据库
		excelStream = new ByteArrayInputStream(out.toByteArray());

		// 输出报表
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "excelPerson";
	}

	/**
	 * @return 导出个人工资报表
	 */
	public String excelAllChdange() {
		// 缓存IO
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// 生成保镖的二进制IO流
		this.subsidyService.excelAllChdange(subsidy, out);
		// 创建到客户端的二进制数据库
		excelStream = new ByteArrayInputStream(out.toByteArray());

		// 输出报表
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "excelAllChdange";
	}

	/**
	 * @return 导出公司工资报表
	 */
	public String excelCompanySalary() {
		// 缓存IO
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// 生成保镖的二进制IO流
		this.subsidyService.excelCompanySalary(subsidy, out);
		// 创建到客户端的二进制数据库
		excelStream = new ByteArrayInputStream(out.toByteArray());

		// 输出报表
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "excelCompanySalary";
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}
}
