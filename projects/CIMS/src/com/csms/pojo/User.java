package com.csms.pojo;

import com.csms.common.Page;

public class User extends Page {
	private String ur_id;
	private String ur_name;
	private String ur_password;
	private String ur_level;
	private String ur_status;
	private String sp_id;
	private String sp_name;

	public String getUr_id() {
		return ur_id;
	}

	public void setUr_id(String ur_id) {
		this.ur_id = ur_id;
	}

	public String getUr_name() {
		return ur_name;
	}

	public void setUr_name(String ur_name) {
		this.ur_name = ur_name;
	}

	public String getUr_password() {
		return ur_password;
	}

	public void setUr_password(String ur_password) {
		this.ur_password = ur_password;
	}

	public String getUr_level() {
		return ur_level;
	}

	public void setUr_level(String ur_level) {
		this.ur_level = ur_level;
	}

	public String getUr_status() {
		return ur_status;
	}

	public void setUr_status(String ur_status) {
		this.ur_status = ur_status;
	}

	public String getSp_id() {
		return sp_id;
	}

	public void setSp_id(String sp_id) {
		this.sp_id = sp_id;
	}

	public String getSp_name() {
		return sp_name;
	}

	public void setSp_name(String sp_name) {
		this.sp_name = sp_name;
	}
	
	

}
