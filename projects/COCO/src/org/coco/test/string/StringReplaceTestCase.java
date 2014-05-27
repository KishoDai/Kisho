package org.coco.test.string;

import junit.framework.TestCase;

public class StringReplaceTestCase extends TestCase {

	public void testReplace() {
		assertTrue("好是好".replace("好", "").equals("是"));
	}

	public void testReplaceAll() {
		System.out.println("好是好".replaceAll("[\u4e00-\u9fa5]$", "ab"));
		assertTrue("好是好".replaceAll("^$", "").equals("是"));
	}
}
