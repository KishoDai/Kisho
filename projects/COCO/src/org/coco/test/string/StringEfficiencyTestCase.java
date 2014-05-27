package org.coco.test.string;

import junit.framework.TestCase;

public class StringEfficiencyTestCase extends TestCase {
	public void testEfficiency() {
		int count = 50000;
		long startTime = System.currentTimeMillis();
		String s = "";
		for (int i = 0; i < count; i++) {
			s += i;
		}
		System.out.println("+ use time : "
				+ (System.currentTimeMillis() - startTime) + "ms");

		startTime = System.currentTimeMillis();
		s = "";
		for (int i = 0; i < count; i++) {
			s.concat(i + "");
		}
		System.out.println("concat use time : "
				+ (System.currentTimeMillis() - startTime) + "ms");

		startTime = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append(i);
		}
		System.out.println("StringBuilder use time : "
				+ (System.currentTimeMillis() - startTime) + "ms");
	}
}
