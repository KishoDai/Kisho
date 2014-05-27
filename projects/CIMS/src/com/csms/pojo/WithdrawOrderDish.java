package com.csms.pojo;

public class WithdrawOrderDish {
	private String ID;
	private String dh_id;
	private String wod_amount;
	private String wod_balance;
	private String wod_status;
	private String or_id;
	private String wod_datetime;

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

	public String getWod_amount() {
		return wod_amount;
	}

	public void setWod_amount(String wod_amount) {
		this.wod_amount = wod_amount;
	}

	public String getWod_balance() {
		return wod_balance;
	}

	public void setWod_balance(String wod_balance) {
		this.wod_balance = wod_balance;
	}

	public String getWod_status() {
		return wod_status;
	}

	public void setWod_status(String wod_status) {
		this.wod_status = wod_status;
	}

	public String getOr_id() {
		return or_id;
	}

	public void setOr_id(String or_id) {
		this.or_id = or_id;
	}

	public String getWod_datetime() {
		return wod_datetime;
	}

	public void setWod_datetime(String wod_datetime) {
		this.wod_datetime = wod_datetime;
	}

}
