package com.csms.pojo;

import com.csms.common.Page;

public class Card extends Page {
	private String cd_id;
	private String cd_type;
	private String cd_balance;
	private String cd_send_datetime;

	public String getCd_id() {
		return cd_id;
	}

	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}

	public String getCd_type() {
		return cd_type;
	}

	public void setCd_type(String cd_type) {
		this.cd_type = cd_type;
	}

	public String getCd_balance() {
		return cd_balance;
	}

	public void setCd_balance(String cd_balance) {
		this.cd_balance = cd_balance;
	}

	public String getCd_send_datetime() {
		return cd_send_datetime;
	}

	public void setCd_send_datetime(String cd_send_datetime) {
		this.cd_send_datetime = cd_send_datetime;
	}

}
