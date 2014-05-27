package org.coco.test.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class TestCaseBase extends TestCase {
	private static ApplicationContext applicationContext;
	
	protected ApplicationContext getApplicationContext() {
		if(applicationContext == null){
			ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext();
			classPathXmlApplicationContext.setConfigLocation("Total.xml");
			classPathXmlApplicationContext.refresh();
			this.applicationContext = classPathXmlApplicationContext;
		}
		return applicationContext;
	}
}
