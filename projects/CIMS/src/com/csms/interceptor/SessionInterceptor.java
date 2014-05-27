package com.csms.interceptor;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.csms.action.LoginAction;
import com.csms.pojo.User;
import com.csms.utils.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@Component("sessionInterceptor")
public class SessionInterceptor implements Interceptor {

	private static final long serialVersionUID = 8209557056994801073L;

	@Resource
	Map<String, String> sysInfo;

	public void init() {
	}

	public void destroy() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = (HttpServletRequest) invocation
				.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setMaxInactiveInterval(
				Integer.valueOf(sysInfo.get("maxInactiveInterval")));
		if (invocation.getAction() instanceof LoginAction) {
			return invocation.invoke();
		} else {
			User user = (User) ActionContext.getContext().getSession()
					.get(Constants.LOGIN_USER);
			if (user == null) {
				return "login";
			}
		}
		return invocation.invoke();
	}

}
