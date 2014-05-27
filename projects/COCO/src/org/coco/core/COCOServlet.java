package org.coco.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebServlet({ "*.ajax", "*.json", "*.action","*.do" })
public class COCOServlet extends HttpServlet {
	
	private static final long serialVersionUID = 3524666269664818184L;

	private static final Logger log = LoggerFactory.getLogger(COCOServlet.class);
	
	private ApplicationContext applicationContext;
	
	public void init(){
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext();
		classPathXmlApplicationContext.setConfigLocation("Total.xml");
		classPathXmlApplicationContext.refresh();
		applicationContext = classPathXmlApplicationContext;
		getServletContext().setAttribute("COCO-http", applicationContext);
		log.debug("成功初始化spring容器!");
	}
	
	public COCOServlet() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		StringBuffer requestURL = request.getRequestURL();
		String beanId = requestURL.substring(requestURL.lastIndexOf("/") + 1, requestURL.lastIndexOf("."));
		if(!applicationContext.containsBean(beanId)){
			request.setAttribute("errorInfo", beanId + "未定义");
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
			return;
		}
		Object obj = applicationContext.getBean(beanId);
		if(!(obj instanceof HttpServlet)){
			request.setAttribute("errorInfo", beanId + "不是HttpServlet");
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
			return;
		}
		
		((HttpServlet)obj).service(request, response);
		
	}

}
