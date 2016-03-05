package com.ruling.cost.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ruling.cost.total.domain.Total;

import freemarker.log.Logger;

import jxl.Cell;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.JxlWriteException;
import jxl.write.biff.RowsExceededException;
import jxl.write.Number;

public class JxlExceptor {
	// private static Logger log = Logger.getLogger(JxlExceptor.class);
	OutputStream out;
	// BufferedOutputStream bos;
	private static JxlExceptor jxl = null;
	private WritableWorkbook write = null;
	WritableSheet sheet = null;
	Label label = null;
	Number num = null;
	DateTime date = null;

	WritableCellFormat headerFormat;
	WritableCellFormat headtoFormat;
	WritableCellFormat titleFormat;
	WritableCellFormat otherFormat;
	WritableCellFormat detFormat;
	WritableCellFormat numFormat;
	WritableCellFormat dateFormat;
	WritableCellFormat headertitleFormat;// 明细报表第二标题样式。
	WritableCellFormat floatFormat;

	private JxlExceptor() {
	};

	public static JxlExceptor getInstence() {
		if (jxl == null)
			jxl = new JxlExceptor();
		return jxl;
	}

	public void exportExcelMianMethod(HttpServletResponse response, String title)
			throws IOException {
		out = response.getOutputStream();
		write = Workbook.createWorkbook(out);
		response.setContentType("aplication/vnd.ms-excel");
		// response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.addHeader("Content-Disposition", "inline; filename="
				+ new String(title.getBytes("GB2312"), "ISO8859_1") + ".xls"); // 有中文必须转码
	}

	public void exportFormatDate() throws WriteException {

		WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 12,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		// 表头样式
		headerFormat = new WritableCellFormat(headerFont);
		// 文字水平居中对齐
		headerFormat.setAlignment(Alignment.CENTRE);

		// 表头信息
		WritableFont allToFont = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		headtoFormat = new WritableCellFormat(allToFont);
		headtoFormat.setAlignment(Alignment.LEFT);

		// 标题样式
		titleFormat = new WritableCellFormat(allToFont);
		titleFormat.setAlignment(Alignment.CENTRE);
		titleFormat.setBackground(Colour.getInternalColour(22));
		titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

		// 显示数据样式
		detFormat = new WritableCellFormat(allToFont);
		// 文字水平靠右对齐
		detFormat.setAlignment(Alignment.RIGHT);
		detFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		// 设置明细第二标题
		headertitleFormat = new WritableCellFormat(allToFont);
		headertitleFormat.setAlignment(Alignment.CENTRE);
		NumberFormat fivedps1 = new NumberFormat("#.00");
		floatFormat = new WritableCellFormat(fivedps1);
		jxl.write.NumberFormat cnf = new jxl.write.NumberFormat(
				"###,###,###,###,###.00");
		jxl.write.WritableCellFormat cwcfN = new jxl.write.WritableCellFormat(
				cnf);
		cwcfN.setBorder(Border.ALL, BorderLineStyle.THIN);

	}

	public static void main(String[] args) {
		List list = new ArrayList();
		List userlist = new ArrayList();
		List listitem = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		userlist.add("张三");
		userlist.add("李四");
		userlist.add("wangwu");
		listitem.add("x项目");
		listitem.add("y项目");
		listitem.add("z项目");
	}

	public void getExcerByuser1(String filename, List listitem, List userlist,
			List list, HttpServletResponse response) {
		try {

			exportExcelMianMethod(response, filename);
			exportFormatDate();
			sheet = write.createSheet("工时报表", 0);
			sheet.mergeCells(0, 0, 0, 1);
			sheet.setRowView(0, 500);
			label = new Label(0, 0, "项目", headerFormat);
			sheet.addCell(label);
			sheet.mergeCells(1, 0, userlist.size(), 0);
			label = new Label(1, 0, "用户列表", headerFormat);
			sheet.addCell(label);
			int healen = userlist.size();// 添加user列表
			for (int i = 0; i < healen; i++) {
				Map map = (Map) userlist.get(i);
				label = new Label(i + 1, 1, map.get("key").toString(),
						titleFormat);
				sheet.addCell(label);

			}
			label = new Label(healen + 1, 1, "统计", titleFormat);
			sheet.addCell(label);
			int margelent = listitem.size();// 获得项目id的个数
			for (int j = 0; j < margelent; j++) {// 设置项目个数的标题
				Map map = (Map) listitem.get(j);
				label = new Label(0, j + 2, map.get("key").toString(),
						titleFormat);
				sheet.addCell(label);
			}
			// 添加單元格內容
			int size = 0;
			for (int i = 0; i < list.size() - 1; i++) {// 先循环这个所有的list，这个list中包括了一个最右边统计的一个list；
				List itemlist = (List) list.get(i);// 获得一个人所有的list，包括总计
				size = itemlist.size();// 获得所有这个人所有项目的大小还要加上那个总计。
				for (int j = 0; j < itemlist.size(); j++) {
					label = new Label(i + 1, 2 + j, itemlist.get(j).toString(),
							detFormat);
					sheet.addCell(label);
				}
				List zongjilist = (List) list.get(list.size() - 1);
				for (int z = 0; z < margelent; z++) {
					label = new Label(healen + 1, z + 2, zongjilist.get(z)
							.toString(), detFormat);
					sheet.addCell(label);
				}
			}
			label = new Label(0, size + 1, "总计", titleFormat);
			sheet.addCell(label);

			write.write();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				write.close();
				// out.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}

	}

	public void getExcerByuser2(String filename, List listitem, List userlist,
			List list, HttpServletResponse response) {
		try {

			exportExcelMianMethod(response, filename);
			exportFormatDate();
			sheet = write.createSheet("工时报表", 0);
			sheet.mergeCells(0, 0, 0, 1);
			sheet.setRowView(0, 500);
			label = new Label(0, 0, "项目", headerFormat);
			sheet.addCell(label);
			sheet.mergeCells(1, 0, userlist.size(), 0);
			label = new Label(1, 0, "用户列表", headerFormat);
			sheet.addCell(label);
			int healen = userlist.size();// 添加user列表
			for (int i = 0; i < healen; i++) {
				Map map = (Map) userlist.get(i);
				label = new Label(i + 1, 1, map.get("key").toString(),
						titleFormat);
				sheet.addCell(label);

			}
			label = new Label(healen + 1, 1, "统计", titleFormat);
			sheet.addCell(label);
			int margelent = listitem.size();// 获得项目id的个数
			for (int j = 0; j < margelent; j++) {// 设置项目个数的标题
				Map map = (Map) listitem.get(j);
				label = new Label(0, j + 2, map.get("key").toString(),
						titleFormat);
				sheet.addCell(label);
			}
			// 添加單元格內容
			int size = 0;
			for (int i = 0; i < list.size() - 1; i++) {// 先循环这个所有的list，这个list中包括了一个最右边统计的一个list；
				List itemlist = (List) list.get(i);// 获得一个人所有的list，包括总计
				size = itemlist.size();// 获得所有这个人所有项目的大小还要加上那个总计。
				for (int j = 0; j < itemlist.size(); j++) {
					num = new jxl.write.Number(i + 1, 2 + j, Double
							.parseDouble(itemlist.get(j).toString()),
							floatFormat);
					sheet.addCell(num);
				}
				List zongjilist = (List) list.get(list.size() - 1);
				for (int z = 0; z < margelent; z++) {
					num = new Number(healen + 1, z + 2, Double
							.parseDouble(zongjilist.get(z).toString()),
							floatFormat);
					sheet.addCell(num);
				}
			}
			label = new Label(0, size + 1, "总计", titleFormat);
			sheet.addCell(label);

			write.write();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				write.close();
				// out.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}

	}

	public void getExcerTotal(String filename, List listitem, List userlist,
			List list, HttpServletResponse response, String xiangmu) {
		try {

			exportExcelMianMethod(response, filename);
			exportFormatDate();
			sheet = write.createSheet("统计报表", 0);
			sheet.mergeCells(0, 0, 0, 1);
			sheet.setRowView(0, 500);
			label = new Label(0, 0, xiangmu, headerFormat);
			sheet.addCell(label);
			sheet.mergeCells(1, 0, userlist.size(), 0);
			label = new Label(1, 0, "详细列表", headerFormat);
			sheet.addCell(label);
			int healen = userlist.size();// 添加user列表
			for (int i = 0; i < healen; i++) {
				// Map map=(Map) userlist.get(i);
				label = new Label(i + 1, 1, userlist.get(i).toString(),
						titleFormat);
				sheet.addCell(label);

			}
			/*
			 * label = new Label(healen+1,1,"统计", titleFormat);
			 * sheet.addCell(label);
			 */
			int margelent = listitem.size();// 获得项目id的个数
			for (int j = 0; j < margelent; j++) {// 设置项目个数的标题
				// Map map=(Map) listitem.get(j);
				label = new Label(0, j + 2, listitem.get(j).toString(),
						titleFormat);
				sheet.addCell(label);
			}
			// 添加單元格內容
			int size = 0;
			for (int j = 0; j < userlist.size() - 1; j++) {
				for (int i = 0; i < list.size(); i++) {// 先循环这个所有的list，这个list里面包含一个对象指哪一列
					Total t = (Total) list.get(i);
					// List itemlist=(List) list.get(i);//获得一个人所有的list，包括总计
					// size=itemlist.size();//获得所有这个人所有项目的大小还要加上那个总计。
					// 获取所有的字段名称//获得行数
					if (t != null) {
						if (t.getItemname() != null
								&& !t.getItemname().equals("")) {
							label = new Label(1, i + 2, t.getItemname()
									.toString(), detFormat);// 2代表行
							sheet.addCell(label);
						} else {
							label = new Label(1, i + 2, "", detFormat);// 2代表行
							sheet.addCell(label);
						}
						if (t.getHtvalue() != null
								&& !t.getHtvalue().equals("")) {
							label = new Label(2, i + 2, t.getHtvalue()
									.toString(), floatFormat);
							sheet.addCell(label);
						} else {
							label = new Label(2, i + 2, "", floatFormat);
							sheet.addCell(label);
						}
						if (t.getPeoplevalue() != null
								&& !t.getPeoplevalue().equals("")) {
							label = new Label(3, i + 2, t.getPeoplevalue()
									.toString(), floatFormat);
							sheet.addCell(label);
						} else {
							label = new Label(3, i + 2, "", floatFormat);
							sheet.addCell(label);
						}
						if (t.getPalyvalue() != null
								&& !t.getPalyvalue().equals("")) {
							label = new Label(4, i + 2, t.getPalyvalue()
									.toString(), floatFormat);
							sheet.addCell(label);
						} else {
							label = new Label(4, i + 2, "", floatFormat);
							sheet.addCell(label);
						}
						if (t.getExtl() != null && !t.getExtl().equals("")) {
							label = new Label(5, i + 2, t.getExtl().toString(),
									floatFormat);
							sheet.addCell(label);
						} else {
							label = new Label(5, i + 2, "", floatFormat);
							sheet.addCell(label);
						}
						if (t.getMv() != null && !t.getMv().equals("")) {
							label = new Label(6, i + 2, t.getMv().toString(),
									floatFormat);
							sheet.addCell(label);
						} else {
							label = new Label(6, i + 2, "", floatFormat);
							sheet.addCell(label);
						}
						if (t.getChengben() != null
								&& !t.getChengben().equals("")) {
							label = new Label(7, i + 2, t.getChengben()
									.toString(), floatFormat);
							sheet.addCell(label);
						} else {
							label = new Label(7, i + 2, "", floatFormat);
							sheet.addCell(label);
						}
						if (t.getRevenue() != null
								&& !t.getRevenue().equals("")) {
							label = new Label(8, i + 2, t.getRevenue()
									.toString(), floatFormat);
							sheet.addCell(label);
						} else {
							label = new Label(8, i + 2, "", floatFormat);
							sheet.addCell(label);
						}
						if (t.getSj() != null && !t.getSj().equals("")) {
							label = new Label(9, i + 2, t.getSj().toString(),
									floatFormat);
							sheet.addCell(label);
						} else {
							label = new Label(9, i + 2, "", floatFormat);
							sheet.addCell(label);
						}
					}
				}
				/*
				 * Total zongjilist=(Total) list.get(list.size()-1); for(int
				 * z=0;z<margelent;z++){ label = new
				 * Label(healen+1,z+2,zongjilist.get(z).toString(),detFormat);
				 * sheet.addCell(label); }
				 */
			}
			/*
			 * label = new Label(0,size+1,"总计",titleFormat);
			 * sheet.addCell(label);
			 */

			write.write();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				write.close();
				// out.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}

	}
}
