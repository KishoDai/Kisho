package com.csms.action;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csms.common.CommonAction;
import com.csms.pojo.ReportCondition;
import com.csms.sqlmap.SqlMap;
import com.csms.utils.DateUtil;
import com.csms.utils.UUIDUtils;

@Component("reportAction")
public class ReportAction extends CommonAction<ReportCondition> {

	private static final long serialVersionUID = -7499835551068402117L;
	@Autowired
	private SqlMap sqlMap;

	private ReportCondition rc;

	public String toReport1MainPage() {
		return "toReport1MainPage";
	}

	public void queryReport1PageList() {
		rc = rc == null ? new ReportCondition() : rc;
		String beforePattern = "0".equals(rc.getDateType()) ? "yyyy-MM-dd"
				: "yyyy-MM";
		try {
			rc.setStartDate(DateUtil.parseDateStr(rc.getStartDate(),
					beforePattern, "yyyy-MM-dd") + " 00:00:00");
			rc.setEndDate(DateUtil.parseDateStr(rc.getEndDate(), beforePattern,
					"yyyy-MM-dd") + " 23:59:59");
		} catch (ParseException e) {
			log.error(getSheetName(), e);
			return;
		}
		doPageList("ReportAction.queryReport1Count",
				"ReportAction.queryReport1PageList", rc);
	}

	public void exportReport1ToExcel() {
		ServletActionContext.getResponse().setContentType(
				"application/vnd.ms-excel;charset=UTF-8");
		OutputStream os = null;
		try {
			os = ServletActionContext.getResponse().getOutputStream();

			if ("0".equals(rc.getExcelType())) { // 2007及以上版本
				ServletActionContext.getResponse().addHeader(
						"content-disposition",
						"attachment;  filename=" + UUIDUtils.getUUID()
								+ ".xlsx");
				XSSFWorkbook book = new XSSFWorkbook();
				XSSFSheet sheet = book.createSheet(getSheetName());

				int rownum = 0;
				XSSFRow row = sheet.createRow(rownum++);
				int colnum = 0;
				row.createCell(colnum++).setCellValue("时间");
				row.createCell(colnum++).setCellValue("店铺编号");
				row.createCell(colnum++).setCellValue("店铺名称");
				if (!"0".equals(rc.getSortType())) {
					row.createCell(colnum++).setCellValue("菜品分类编号");
					row.createCell(colnum++).setCellValue("菜品分类编号");
				}
				if ("2".equals(rc.getSortType())) {
					row.createCell(colnum++).setCellValue("菜品编号");
					row.createCell(colnum++).setCellValue("菜品名称");
				}
				row.createCell(colnum).setCellValue("营业额(元)");

				rc = rc == null ? new ReportCondition() : rc;
				String beforePattern = "0".equals(rc.getDateType()) ? "yyyy-MM-dd"
						: "yyyy-MM";
				try {
					rc.setStartDate(DateUtil.parseDateStr(rc.getStartDate(),
							beforePattern, "yyyy-MM-dd") + " 00:00:00");
					rc.setEndDate(DateUtil.parseDateStr(rc.getEndDate(),
							beforePattern, "yyyy-MM-dd") + " 23:59:59");
				} catch (ParseException e) {
					log.error(getSheetName(), e);
					return;
				}
				@SuppressWarnings("unchecked")
				List<Map<String, String>> reportList = sqlMap.selectList(
						"ReportAction.queryReport1ToExcel", rc);
				for (Map<String, String> reportMap : reportList) {
					colnum = 0;
					row = sheet.createRow(rownum++);
					row.createCell(colnum++).setCellValue(
							reportMap.get("or_datetime"));
					row.createCell(colnum++).setCellValue(
							reportMap.get("sp_id"));
					row.createCell(colnum++).setCellValue(
							reportMap.get("sp_name"));
					if (!"0".equals(rc.getSortType())) {
						row.createCell(colnum++).setCellValue(
								reportMap.get("dh_parentid"));
						row.createCell(colnum++).setCellValue(
								reportMap.get("dh_parentname"));
					}
					if ("2".equals(rc.getSortType())) {
						row.createCell(colnum++).setCellValue(
								reportMap.get("dh_id"));
						row.createCell(colnum++).setCellValue(
								reportMap.get("dh_name"));
					}
					row.createCell(colnum).setCellValue(
							String.valueOf(reportMap.get("totalMoney")));

				}

				try {
					book.write(os);
				} catch (IOException e) {
					log.error("", e);
				}

			} else { // 2003版
				ServletActionContext.getResponse()
						.addHeader(
								"content-disposition",
								"attachment;  filename=" + UUIDUtils.getUUID()
										+ ".xls");

				HSSFWorkbook book = new HSSFWorkbook();
				HSSFSheet sheet = book.createSheet(getSheetName());

				int rownum = 0;
				HSSFRow row = sheet.createRow(rownum++);
				int colnum = 0;
				row.createCell(colnum++).setCellValue("时间");
				row.createCell(colnum++).setCellValue("店铺编号");
				row.createCell(colnum++).setCellValue("店铺名称");
				if (!"0".equals(rc.getSortType())) {
					row.createCell(colnum++).setCellValue("菜品分类编号");
					row.createCell(colnum++).setCellValue("菜品分类编号");
				}
				if ("2".equals(rc.getSortType())) {
					row.createCell(colnum++).setCellValue("菜品编号");
					row.createCell(colnum++).setCellValue("菜品名称");
				}
				row.createCell(colnum).setCellValue("营业额(元)");

				rc = rc == null ? new ReportCondition() : rc;
				String beforePattern = "0".equals(rc.getDateType()) ? "yyyy-MM-dd"
						: "yyyy-MM";
				try {
					rc.setStartDate(DateUtil.parseDateStr(rc.getStartDate(),
							beforePattern, "yyyy-MM-dd") + " 00:00:00");
					rc.setEndDate(DateUtil.parseDateStr(rc.getEndDate(),
							beforePattern, "yyyy-MM-dd") + " 23:59:59");
				} catch (ParseException e) {
					log.error(getSheetName(), e);
					return;
				}
				@SuppressWarnings("unchecked")
				List<Map<String, String>> reportList = sqlMap.selectList(
						"ReportAction.queryReport1ToExcel", rc);
				for (Map<String, String> reportMap : reportList) {
					colnum = 0;
					row = sheet.createRow(rownum++);
					row.createCell(colnum++).setCellValue(
							reportMap.get("or_datetime"));
					row.createCell(colnum++).setCellValue(
							reportMap.get("sp_id"));
					row.createCell(colnum++).setCellValue(
							reportMap.get("sp_name"));
					if (!"0".equals(rc.getSortType())) {
						row.createCell(colnum++).setCellValue(
								reportMap.get("dh_parentid"));
						row.createCell(colnum++).setCellValue(
								reportMap.get("dh_parentname"));
					}
					if ("2".equals(rc.getSortType())) {
						row.createCell(colnum++).setCellValue(
								reportMap.get("dh_id"));
						row.createCell(colnum++).setCellValue(
								reportMap.get("dh_name"));
					}
					row.createCell(colnum).setCellValue(
							String.valueOf(reportMap.get("totalMoney")));

				}

				try {
					book.write(os);
				} catch (IOException e) {
					log.error("", e);
				}
			}
		} catch (IOException e) {
			log.error("" + e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					log.error(getSheetName(), e);
				}
			}
		}

	}

	private String getSheetName() {
		String sheetName = "报表_";
		if ("0".equals(rc.getDateType())) {
			sheetName += "按天";
		} else if ("1".equals(rc.getDateType())) {
			sheetName += "按月";
		}
		sheetName += "(" + rc.getStartDate() + "~" + rc.getEndDate() + ")";
		if ("0".equals(rc.getSortType())) {
			sheetName += "_按店铺";
		} else if ("1".equals(rc.getSortType())) {
			sheetName += "_按菜品分类";
		} else if ("2".equals(rc.getSortType())) {
			sheetName += "_按菜品";
		}
		return sheetName;
	}

	public ReportCondition getRc() {
		return rc;
	}

	public void setRc(ReportCondition rc) {
		this.rc = rc;
	}

}
