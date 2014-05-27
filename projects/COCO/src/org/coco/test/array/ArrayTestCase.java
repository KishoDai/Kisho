package org.coco.test.array;

import java.util.Arrays;

import junit.framework.TestCase;

public class ArrayTestCase extends TestCase {
	public void test() {
		int[] iArr = new int[] { 1, 2, 3, 4, 5 };
		int[] iArr2 = Arrays.copyOf(iArr, 2);
		System.out.println(iArr2.length);
	}
}
