package com.press.officialinfo.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.press.comm.JSON;
import com.press.comm.action.CommAction;
import com.press.officialinfo.pojo.OfficialInfo;
import com.press.sqlmap.SqlMap;
import com.press.util.Constants;

public class OfficialInfoAction extends CommAction {
	@Autowired
	private SqlMap sqlMap;
	private OfficialInfo officialInfo;

	public String to_index() {
		officialInfo = (OfficialInfo) sqlMap.selectOne("officialinfo.queryOfficialInfo");
		return Constants.TO_INDEX;
	}


	public void do_update() {
		sqlMap.update("officialinfo.updateOfficialInfo",officialInfo);
		sendJSON(new JSON("1", ""));
	}

	public OfficialInfo getOfficialInfo() {
		return officialInfo;
	}

	public void setOfficialInfo(OfficialInfo officialInfo) {
		this.officialInfo = officialInfo;
	}
	
}
