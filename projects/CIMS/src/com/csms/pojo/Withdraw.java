package com.csms.pojo;

import com.csms.common.Page;

public class Withdraw extends Page {

	private String wp_id;
	private String cd_id;
	private String or_id;
	private String wp_created_ur_id;
	private String sp_id;
	private String wp_status;
	private String wp_approve_ur_id;
	private String wp_startdatetime;
	private String wp_donedatetime;

	public String getWp_id() {
		return wp_id;
	}

	public void setWp_id(String wp_id) {
		this.wp_id = wp_id;
	}

	public String getCd_id() {
		return cd_id;
	}

	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}

	public String getOr_id() {
		return or_id;
	}

	public void setOr_id(String or_id) {
		this.or_id = or_id;
	}

	public String getWp_created_ur_id() {
		return wp_created_ur_id;
	}

	public void setWp_created_ur_id(String wp_created_ur_id) {
		this.wp_created_ur_id = wp_created_ur_id;
	}

	public String getSp_id() {
		return sp_id;
	}

	public void setSp_id(String sp_id) {
		this.sp_id = sp_id;
	}

	public String getWp_status() {
		return wp_status;
	}

	public void setWp_status(String wp_status) {
		this.wp_status = wp_status;
	}

	public String getWp_approve_ur_id() {
		return wp_approve_ur_id;
	}

	public void setWp_approve_ur_id(String wp_approve_ur_id) {
		this.wp_approve_ur_id = wp_approve_ur_id;
	}

	public String getWp_startdatetime() {
		return wp_startdatetime;
	}

	public void setWp_startdatetime(String wp_startdatetime) {
		this.wp_startdatetime = wp_startdatetime;
	}

	public String getWp_donedatetime() {
		return wp_donedatetime;
	}

	public void setWp_donedatetime(String wp_donedatetime) {
		this.wp_donedatetime = wp_donedatetime;
	}

}
