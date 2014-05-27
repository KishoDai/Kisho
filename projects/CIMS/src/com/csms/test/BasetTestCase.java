package com.csms.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class BasetTestCase extends TestCase {

	public ApplicationContext getApplicationContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.setConfigLocation("Total.xml");
        context.refresh();
        return context;
	}

}
