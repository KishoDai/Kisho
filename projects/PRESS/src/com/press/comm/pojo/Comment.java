package com.press.comm.pojo;

public class Comment extends Page {
	private String comid;
	private String userid;
	private String nicknam;
	private String relid;
	private String reltyp;
	private String comcontent;
	private String comtime;

	public String getComid() {
		return comid;
	}

	public void setComid(String comid) {
		this.comid = comid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getNicknam() {
		return nicknam;
	}

	public void setNicknam(String nicknam) {
		this.nicknam = nicknam;
	}

	public String getRelid() {
		return relid;
	}

	public void setRelid(String relid) {
		this.relid = relid;
	}

	public String getComcontent() {
		return comcontent;
	}

	public void setComcontent(String comcontent) {
		this.comcontent = comcontent;
	}

	public String getComtime() {
		return comtime;
	}

	public void setComtime(String comtime) {
		this.comtime = comtime;
	}

	public String getReltyp() {
		return reltyp;
	}

	public void setReltyp(String reltyp) {
		this.reltyp = reltyp;
	}

}
