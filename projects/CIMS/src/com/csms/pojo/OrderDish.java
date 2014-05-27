package com.csms.pojo;

import com.csms.common.Page;

public class OrderDish extends Page {
	private String ID;
	private String dh_id;
	private String od_amount;
	private String od_balance;
	private String or_id;
	private String od_datetime;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getDh_id() {
		return dh_id;
	}

	public void setDh_id(String dh_id) {
		this.dh_id = dh_id;
	}

	public String getOd_amount() {
		return od_amount;
	}

	public void setOd_amount(String od_amount) {
		this.od_amount = od_amount;
	}

	public String getOd_balance() {
		return od_balance;
	}

	public void setOd_balance(String od_balance) {
		this.od_balance = od_balance;
	}

	public String getOr_id() {
		return or_id;
	}

	public void setOr_id(String or_id) {
		this.or_id = or_id;
	}

	public String getOd_datetime() {
		return od_datetime;
	}

	public void setOd_datetime(String od_datetime) {
		this.od_datetime = od_datetime;
	}

}
