package com.press.comm;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.press.admin.pojo.Admin;
import com.press.sqlmap.SqlMap;
import com.press.util.Constants;




public class SessionListner implements HttpSessionListener {
   private static Logger logger = LoggerFactory.getLogger(SessionListner.class);
	public void sessionCreated(HttpSessionEvent arg0) {
		logger.debug("******************session被创建******************");
		String maxInactiveInterval = arg0.getSession().getServletContext().getInitParameter(Constants.MAXINACTIVEINTERVAL);
	    if(StringUtils.isNotBlank(maxInactiveInterval)){
		    arg0.getSession().setMaxInactiveInterval(Integer.valueOf(maxInactiveInterval));
		}
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		logger.debug("******************session被销毁******************");
		HttpSession session = arg0.getSession();
		String adminid = (String) session.getAttribute(Constants.ADMINID);
		String logintime = (String) session.getAttribute(Constants.LOGINTIME);
		String loginip = (String) session.getAttribute(Constants.LOGINIP);
		if (StringUtils.isNotBlank(adminid)) {
			Admin admin = new Admin();
			admin.setAdminid(adminid);
			admin.setLastlogintime(logintime);
			admin.setLastloginip(loginip);
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
		    SqlMap sqlMap = (SqlMap) ctx.getBean("sqlMap");
		    sqlMap.update("admin.updateAdminLogininfo", admin);
		}
	}

}
