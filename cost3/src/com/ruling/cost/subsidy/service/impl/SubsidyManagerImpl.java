package com.ruling.cost.subsidy.service.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.ruling.cost.subsidy.domain.Subsidy;
import com.ruling.cost.subsidy.domain.SubsidyHistory;
import com.ruling.cost.subsidy.service.SubsidyManager;
import com.ruling.cost.util.dao.ObjectDao;
import com.ruling.cost.utils.PageModel;

/**
 * 工资管理
 * 
 * @author FXStudio.com.cn(Ajaxfan)
 */
public class SubsidyManagerImpl implements SubsidyManager {

	private ObjectDao objectDao;

	private Subsidy subsidy;

	public ObjectDao getObjectDao() {
		return objectDao;
	}

	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}

	public Subsidy getSubsidy() {
		return subsidy;
	}

	public void setSubsidy(Subsidy subsidy) {
		this.subsidy = subsidy;
	}

	/**
	 * 添加新的工资记录
	 * 
	 * @param subsi
	 */
	public void addSubsidy(Subsidy subsi) {
		// 保存工资条对象，一个员工只有一个工资条对象
		this.objectDao.save(subsi);

		saveHistory(subsi);
	}

	/**
	 * 删除工资记录
	 * 
	 * @param subsidyIds
	 */
	public void delSubsidy(List subsidyIds) {
		for (Object o : subsidyIds) {
			Integer subsid = Integer.parseInt(((String) o).trim());

			// 查询出所有的工资信息
			List sublist = (List) this.objectDao.getByParm("from SubsidyHistory s where s.subsidyId=?",
					new Object[] { subsid });

			// 遍历所有的工资信息
			for (Object obj : sublist) {
				this.objectDao.delete(obj);
			}
			// 删除工资对象
			this.objectDao.delete(this.objectDao.getById(Subsidy.class, subsid));
		}
	}

	/**
	 * 查询所有的工资条
	 * 
	 * @param subsidy
	 * @return
	 */
	public PageModel findAllSubsidy(Subsidy subsidy) {
		PageModel pm = new PageModel();

		String hql = "from Subsidy";

		if (subsidy == null) {
			pm = objectDao.searchPaginated(hql, null);

			return pm;
		}

		if (!subsidy.getUsername().trim().equals("")) {
			Object[] obj = new Object[1];

			hql = " from Subsidy s where s.user.username like ?";

			obj[0] = "%" + subsidy.getUsername() + "%";

			pm = objectDao.searchPaginated(hql, obj);

			return pm;
		} else {
			pm = objectDao.searchPaginated(hql, null);

			return pm;
		}
	}

	/**
	 * 修改工资条信息
	 * 
	 * @param subsi
	 */
	public void updateSubsidy(Subsidy subsi) {
		saveHistory(subsi);
	}

	/**
	 * 查询指定的工资条信息
	 * 
	 * @param subsidyid
	 * @return
	 */
	public Subsidy findSubsidyById(int subsidyid) {
		return (Subsidy) this.objectDao.getById(Subsidy.class, subsidyid);
	}

	/**
	 * 查询所有的变更记录
	 */
	public SubsidyHistory findSubsidyHistoryById(int id) {
		List sublist = (List) this.objectDao.getByParm("from SubsidyHistory s where s.subsidyId=? order by s.id desc",
				new Object[] { id });

		if (sublist.size() > 0) {
			return (SubsidyHistory) sublist.get(0);
		}
		return new SubsidyHistory();
	}

	/**
	 * 导出个人的工资条报表
	 * 
	 * @param out
	 */
	public void exportPersonSalary(Integer id, OutputStream out) {
		StringBuilder sb = new StringBuilder();
		sb.append("Select effectivedate, usercode, username, created, postname, basepay,");
		sb.append(" subsidy1, subsidy2, subsidy3, hourspaly");
		sb.append(" from PersonSalaryReport where id ='");
		sb.append(id);
		sb.append("'");

		exportExcel(out, sb.toString());
	}

	/**
	 * 导出某一年的所有的变更记录
	 * 
	 * @param out
	 */
	public void excelAllChdange(Subsidy s, OutputStream out) {
		StringBuilder sb = new StringBuilder();
		sb.append("Select effectivedate, usercode, username, created, postname, basepay,");
		sb.append(" subsidy1, subsidy2, subsidy3, hourspaly");
		sb.append(" from alllchangerecord");
		sb.append(" where effectivedate = '");
		sb.append(s.getExportyear());
		sb.append("'");

		exportExcel(out, sb.toString());
	}

	/**
	 * 导出全公司的工资报表
	 * 
	 * @param out
	 */
	public void excelCompanySalary(Subsidy s, OutputStream out) {
		StringBuilder sb = new StringBuilder();
		sb.append("Select effectivedate, usercode, username, created, postname, basepay,");
		sb.append(" subsidy1, subsidy2, subsidy3, hourspaly");
		sb.append(" from compaysalary");
		sb.append(" where effectivedate = '");
		sb.append(s.getExportyear());
		sb.append("'");

		exportExcel(out, sb.toString());
	}

	/**
	 * 导出报表
	 * 
	 * @param out
	 * @param sql
	 */
	private void exportExcel(OutputStream out, String sql) {
		try {
			// 加载报表模板
			InputStream in = SubsidyManagerImpl.class.getResourceAsStream("/exportTemp.xls");
			// 获得工作表
			Workbook work = new HSSFWorkbook(in);
			// 得到excel的第0张表
			Sheet sheet = work.getSheetAt(0);

			// 行列对象
			Row row = null;
			Cell cell = null;

			// 查询工资信息
			List salarys = objectDao.querySQL(sql, null, 0, 0);

			int rowIndex = 1;// 计数器
			int columnIndex = 0;
			// 填充Excel单元格
			for (Object obj : salarys) {
				row = sheet.createRow(rowIndex);// 得到n行

				for (Object val : (Object[]) obj) {
					cell = row.createCell(columnIndex++);// 得到第n个单元格

					cell.setCellValue((String) val);
				}
				rowIndex++;
				columnIndex = 0;
			}
			work.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存工资变更历史记录
	 * 
	 * @param subsi
	 */
	private void saveHistory(Subsidy subsi) {
		// 设置公司对象
		subsi.getSubsidyhistory().setSubsidyId(subsi.getId());

		// 获得日历对象
		Calendar calendar = Calendar.getInstance();
		// 获得本月的第一天日期
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

		// 设置新工资标准的生效日期
		subsi.getSubsidyhistory().setEffectivedate(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));

		// 保存工资记录信息，每个工资条对应多天工资记录
		this.objectDao.save(subsi.getSubsidyhistory());
	}
}
