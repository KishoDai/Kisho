package com.csms.pojo;

import com.csms.common.Page;

public class Shop extends Page {

	private String sp_id;
	private String sp_status;
	private String sp_name;
	private String sp_shopman;
	private String sp_shopman_phone;
	private String sp_shopman_email;
	private String ur_id;
	private String sp_update_time;
	private String sp_remark;

	public String getSp_id() {
		return sp_id;
	}

	public void setSp_id(String sp_id) {
		this.sp_id = sp_id;
	}

	public String getSp_status() {
		return sp_status;
	}

	public void setSp_status(String sp_status) {
		this.sp_status = sp_status;
	}

	public String getSp_name() {
		return sp_name;
	}

	public void setSp_name(String sp_name) {
		this.sp_name = sp_name;
	}

	public String getSp_shopman() {
		return sp_shopman;
	}

	public void setSp_shopman(String sp_shopman) {
		this.sp_shopman = sp_shopman;
	}

	public String getSp_shopman_phone() {
		return sp_shopman_phone;
	}

	public void setSp_shopman_phone(String sp_shopman_phone) {
		this.sp_shopman_phone = sp_shopman_phone;
	}

	public String getSp_shopman_email() {
		return sp_shopman_email;
	}

	public void setSp_shopman_email(String sp_shopman_email) {
		this.sp_shopman_email = sp_shopman_email;
	}

	public String getUr_id() {
		return ur_id;
	}

	public void setUr_id(String ur_id) {
		this.ur_id = ur_id;
	}

	public String getSp_update_time() {
		return sp_update_time;
	}

	public void setSp_update_time(String sp_update_time) {
		this.sp_update_time = sp_update_time;
	}

	public String getSp_remark() {
		return sp_remark;
	}

	public void setSp_remark(String sp_remark) {
		this.sp_remark = sp_remark;
	}

}
