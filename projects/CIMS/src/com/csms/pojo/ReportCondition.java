package com.csms.pojo;

import com.csms.common.Page;

public class ReportCondition extends Page {
	private String dateType;
	private String startDate;
	private String endDate;
	private String sortType;
	private String excelType;

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getExcelType() {
		return excelType;
	}

	public void setExcelType(String excelType) {
		this.excelType = excelType;
	}

}
