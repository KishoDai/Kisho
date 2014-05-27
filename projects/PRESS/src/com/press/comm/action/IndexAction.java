package com.press.comm.action;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.press.admin.pojo.Admin;
import com.press.comm.JSON;
import com.press.sqlmap.SqlMap;
import com.press.util.Constants;

public class IndexAction extends CommAction {
	private static Logger logger = LoggerFactory.getLogger(IndexAction.class);
    @Autowired
	SqlMap sqlMap;

	public String Main() {
		logger.debug("***************转向网站内部首页***************");
		return Constants.TO_MAIN;
	}

	public String header() {
		logger.debug("***************转向网站内部头页***************");
		return Constants.TO_HEADER;
	}

	public String middle() {
		logger.debug("***************转向网站内部中页***************");
		return Constants.TO_MIDDLE;
	}

	public String footer() {
		logger.debug("***************转向网站内部尾页***************");
		return Constants.TO_FOOTER;
	}

	public String left() {
		logger.debug("***************转向网站内部左页***************");
		return Constants.TO_LEFT;
	}

	public String mainindex() {
		logger.debug("***************转向网站内部中间主页***************");
		return Constants.TO_MAININDEX;
	}

	public void exit() {
		logger.debug("***************退出***************");
		HttpSession session = ServletActionContext.getRequest().getSession();
		String adminid = (String) session.getAttribute(Constants.ADMINID);
		String logintime = (String) session.getAttribute(Constants.LOGINTIME);
		String loginip = (String) session.getAttribute(Constants.LOGINIP);
		Admin admin = new Admin();
		admin.setAdminid(adminid);
		admin.setLastlogintime(logintime);
		admin.setLastloginip(loginip);
		sqlMap.update("admin.updateAdminLogininfo", admin);
		session.invalidate();
		session.setMaxInactiveInterval(0);
		logger.debug("退出成功!");
		sendJSON(new JSON("1", ""));
	}
}
