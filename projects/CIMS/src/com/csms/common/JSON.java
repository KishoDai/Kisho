package com.csms.common;

public class JSON {
	private String flag;
	private String message;

	public JSON(String flag, String message) {
		this.flag = flag;
		this.message = message;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return "{\"flag\":\"" + flag + "\",\"message\":\"" + message + "\"}";
	}

}
