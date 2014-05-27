package org.coco.studentmanage;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentManagerServlet extends AbstractServlet {

	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("@@@@@@@@@ doGet");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("@@@@@@@@@ doPost");
		List<Map<String, String>> studentList = (List<Map<String, String>>) sqlSession
				.selectList("student.queryStudentList");
		req.setAttribute("studentList", studentList);
		req.getRequestDispatcher("studentMain.jsp").forward(req, resp);
	}

}
