package com.csms.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.csms.pojo.UserRoleRel;
import com.csms.sqlmap.SqlMap;

public class TestBean {
	@Resource
	SqlMap sqlMap;

	@Transactional
	public void test() {
		Map<String, Object> insertMap = new HashMap<String, Object>();
		insertMap.put("", "");
		UserRoleRel userRoleRel = new UserRoleRel();
		userRoleRel.setRe_id("1111111");
		userRoleRel.setUr_id("2222222");
		sqlMap.insert("UserAction.addUserRoleRel", userRoleRel);
		userRoleRel.setUr_id("22222222");
		sqlMap.insert("UserAction.addUserRoleRel", userRoleRel);
	}
}
