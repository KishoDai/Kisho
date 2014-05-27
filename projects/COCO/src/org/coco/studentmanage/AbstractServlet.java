package org.coco.studentmanage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractServlet extends HttpServlet {

	private static final long serialVersionUID = -2389724570234776704L;
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long start = System.currentTimeMillis();
		log.info("Start processing servlet " + getClass().getSimpleName());
		try {
		  super.service(request, response);
		} catch (Exception e) {
			log.error("" + e.getMessage(), e);
			request.setAttribute("errorInfo", e.getMessage());
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
			return;
		} finally{
			log.info("Stop processing servlet " + getClass().getSimpleName());
			log.info("Use " + (System.currentTimeMillis() - start) + "ms for " + getClass().getSimpleName());
		}
	}

}
