package com.press.comm.pojo;

public class Page {
	private String order;//升降序
	private Integer startnum;//起始记录
	private Integer limitcount;//共记录
	private String sort;//排序字段
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	public Integer getStartnum() {
		return startnum;
	}
	public void setStartnum(Integer startnum) {
		this.startnum = startnum;
	}
	public Integer getLimitcount() {
		return limitcount;
	}
	public void setLimitcount(Integer limitcount) {
		this.limitcount = limitcount;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	
}
