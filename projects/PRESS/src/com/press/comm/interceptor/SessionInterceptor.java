package com.press.comm.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.press.login.action.LoginAction;
import com.press.util.Constants;

public class SessionInterceptor implements Interceptor {
	private static Logger logger = LoggerFactory
			.getLogger(SessionInterceptor.class);

	public void init() {
	}

	public void destroy() {
		logger.debug("****************session拦截器销毁******************");
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		logger.debug("****************session过滤开始******************");
		String username = (String) ActionContext.getContext().getSession()
				.get(Constants.ADMINID);
		Object action = invocation.getAction();
		logger.debug("username=" + username);
		logger.debug("action=" + action);
		if (action instanceof LoginAction) {
			logger.debug("****************session过滤结束******************");
			return invocation.invoke();
		} else {
			if (username == null) {
				logger.debug("****************session过滤结束******************");
				return Constants.TO_LOGIN;
			}
		}
		logger.debug("****************session过滤结束******************");
		return invocation.invoke();
	}

}
