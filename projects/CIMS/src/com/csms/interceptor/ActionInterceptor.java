package com.csms.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.csms.common.JSON;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.ognl.OgnlValueStack;

@Component("actionInterceptor")
public class ActionInterceptor implements Interceptor{

	private static Logger log = LoggerFactory.getLogger(ActionInterceptor.class);
	
	private static final long serialVersionUID = 9089528580081905358L;

	@Resource
	Map<String, String> infoConfig;
	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation invocation) {
		long start = System.currentTimeMillis();
		log.info("Start processing " + invocation.getAction());
		log.info("execute method: " + invocation.getInvocationContext().getName());
		String result = null;
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		try {
			request.setAttribute("infoConfig", infoConfig);
			result = invocation.invoke();
			OgnlValueStack ognl = (OgnlValueStack) request.getAttribute("struts.valueStack");
			Map<String, Object> map = ognl.getContext();
			log.info("request : " +  map.get("request"));
		} catch (Throwable e) {
			log.error("" , e);
			writeJson(new JSON("1", infoConfig.get("systemError")));
		} finally {
			
			log.info("Stop processing " + invocation.getAction());
			log.info("Use time " + (System.currentTimeMillis() - start) + "ms for processing " + invocation.getAction());
		}
		return result;
	}
	
	protected void writeJson(String json) {
		ServletActionContext.getResponse().setContentType(
				"application/json;charset=UTF-8");
		write(json);
	}

	protected void writeJson(JSON json) {
		writeJson(json.toString());
	}

	protected void write(String str) {
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(str);
			log.debug("write string : " + str);
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

}
