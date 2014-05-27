package com.press.comm.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.press.comm.JSON;
import com.press.comm.action.CommAction;
import com.press.comm.pojo.Catalog;
import com.press.comm.pojo.Param;
import com.press.sqlmap.SqlMap;
import com.press.util.Constants;
import com.press.util.UUIDUtil;
import com.press.util.Utils;

public class CommonParamAction extends CommAction<Catalog> {
	private static Logger logger = LoggerFactory.getLogger(CommonParamAction.class);
	@Autowired
	SqlMap sqlMap;
	private String paramnam;
	private String paramvalue;

	public String to_index() {
		Param param = (Param) sqlMap.selectOne("comm.queryParam", paramnam);
		logger.debug("paramnam=" + param.getParamnam());
		logger.debug("paramvalue=" + param.getParamvalue());
		paramnam = param.getParamnam();
		paramvalue = param.getParamvalue();
		return Constants.TO_INDEX;
	}

	public void do_update() {
		Param param = new Param();
		param.setParamnam(paramnam);
		param.setParamvalue(paramvalue);
		sqlMap.update("comm.updateParam", param);
		sendJSON(new JSON("1", "修改成功!"));
	}

	public String getParamnam() {
		return paramnam;
	}

	public void setParamnam(String paramnam) {
		this.paramnam = paramnam;
	}

	public String getParamvalue() {
		return paramvalue;
	}

	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}

}
