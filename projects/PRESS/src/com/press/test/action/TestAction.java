package com.press.test.action;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionSupport;
import com.press.sqlmap.SqlMap;

public class TestAction extends ActionSupport {
	private static Logger logger = LoggerFactory.getLogger(TestAction.class);
	@Autowired
	private SqlMap sqlMap;

	private String name;
	private String id;
	private String sex;

	@Transactional(rollbackFor = { Exception.class })
	public String test() throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", id);
		paramMap.put("NAME", name);
		paramMap.put("SEX", sex);
//		try {
//			logger.debug(getText("jzdai"));
//			session.insert("test.insertStudent", paramMap);
//			session.commit();
//		} catch (Exception e) {
//			session.rollback();
//			e.printStackTrace();
//		} finally {
//			session.close();
//		}
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
