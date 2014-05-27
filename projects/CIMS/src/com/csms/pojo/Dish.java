package com.csms.pojo;

import com.csms.common.Page;

public class Dish extends Page{

	private String dh_id;
	private String dh_name;
	private String dh_parentid;
	private String dh_price;
	private String dh_discount;
	private String dh_describe;
	private String dh_img_path;
	private String dh_sn;
	private String sp_id;
	private String dh_isleaf;

	public String getDh_id() {
		return dh_id;
	}

	public void setDh_id(String dh_id) {
		this.dh_id = dh_id;
	}

	public String getDh_name() {
		return dh_name;
	}

	public void setDh_name(String dh_name) {
		this.dh_name = dh_name;
	}

	public String getDh_parentid() {
		return dh_parentid;
	}

	public void setDh_parentid(String dh_parentid) {
		this.dh_parentid = dh_parentid;
	}

	public String getDh_price() {
		return dh_price;
	}

	public void setDh_price(String dh_price) {
		this.dh_price = dh_price;
	}

	public String getDh_discount() {
		return dh_discount;
	}

	public void setDh_discount(String dh_discount) {
		this.dh_discount = dh_discount;
	}

	public String getDh_describe() {
		return dh_describe;
	}

	public void setDh_describe(String dh_describe) {
		this.dh_describe = dh_describe;
	}

	public String getDh_img_path() {
		return dh_img_path;
	}

	public void setDh_img_path(String dh_img_path) {
		this.dh_img_path = dh_img_path;
	}

	public String getDh_sn() {
		return dh_sn;
	}

	public void setDh_sn(String dh_sn) {
		this.dh_sn = dh_sn;
	}

	public String getSp_id() {
		return sp_id;
	}

	public void setSp_id(String sp_id) {
		this.sp_id = sp_id;
	}

	public String getDh_isleaf() {
		return dh_isleaf;
	}

	public void setDh_isleaf(String dh_isleaf) {
		this.dh_isleaf = dh_isleaf;
	}

}
