package com.csms.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csms.sqlmap.SqlMap;

@Component("testTimer")
public class TestTimer {

	private static final Logger log = LoggerFactory.getLogger(TestTimer.class);

	@Autowired
	SqlMap sqlMap;

	public void test() {
		sqlMap.selectList("TestTimer.queryTest");
		log.debug("****************总内存：" + Runtime.getRuntime().totalMemory()
				/ (1024 * 1024) + "MB");
		log.debug("****************可用内存：" + Runtime.getRuntime().freeMemory()
				/ (1024 * 1024) + "MB");
		log.debug("****************最大内存：" + Runtime.getRuntime().maxMemory()
				/ (1024 * 1024) + "MB");
	}
}
