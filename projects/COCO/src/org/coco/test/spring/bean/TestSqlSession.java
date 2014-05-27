package org.coco.test.spring.bean;

import java.util.List;
import java.util.Map;

import org.coco.test.spring.TestCaseBase;
import org.mybatis.spring.SqlSessionTemplate;

public class TestSqlSession extends TestCaseBase {
	public void test() {
      SqlSessionTemplate sqlSessionTemplate = (SqlSessionTemplate) getApplicationContext().getBean("sqlSession");
      List<Map<String, Object>> studentList = (List<Map<String, Object>>) sqlSessionTemplate.selectList("student.queryStudentList");
	}
}
