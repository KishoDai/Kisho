package com.csms.test;

public class AnnotationTestCase extends BasetTestCase {
	public void testA() {
		TestBean testBean = (TestBean) getApplicationContext().getBean(
				"TestBean");
		testBean.test();
	}
}
