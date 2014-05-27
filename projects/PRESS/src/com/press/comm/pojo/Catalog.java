package com.press.comm.pojo;

public class Catalog extends Page {
	private String catid;
	private String catnam;
	private String cattyp;
	private String sortnum;

	public String getCatid() {
		return catid;
	}

	public void setCatid(String catid) {
		this.catid = catid;
	}

	public String getCatnam() {
		return catnam;
	}

	public void setCatnam(String catnam) {
		this.catnam = catnam;
	}

	public String getCattyp() {
		return cattyp;
	}

	public void setCattyp(String cattyp) {
		this.cattyp = cattyp;
	}

	public String getSortnum() {
		return sortnum;
	}

	public void setSortnum(String sortnum) {
		this.sortnum = sortnum;
	}

}
