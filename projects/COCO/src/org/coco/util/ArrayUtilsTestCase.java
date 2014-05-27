package org.coco.util;

import junit.framework.TestCase;

public class ArrayUtilsTestCase extends TestCase {

	public void testIsEmpty() {
		byte[] byteArr = null;
		assertTrue(ArrayUtils.isEmpty(byteArr));

		byteArr = new byte[] {};
		assertTrue(ArrayUtils.isEmpty(byteArr));

		byteArr = new byte[3];
		assertFalse(ArrayUtils.isEmpty(byteArr));

		byteArr = new byte[] { 1, 2, 3 };
		assertFalse(ArrayUtils.isEmpty(byteArr));

		short[] shortArr = null;
		assertTrue(ArrayUtils.isEmpty(shortArr));

		shortArr = new short[] {};
		assertTrue(ArrayUtils.isEmpty(shortArr));

		shortArr = new short[3];
		assertFalse(ArrayUtils.isEmpty(shortArr));

		shortArr = new short[] { 1, 2, 3 };
		assertFalse(ArrayUtils.isEmpty(shortArr));

		int[] intArr = null;
		assertTrue(ArrayUtils.isEmpty(intArr));

		intArr = new int[] {};
		assertTrue(ArrayUtils.isEmpty(intArr));

		intArr = new int[3];
		assertFalse(ArrayUtils.isEmpty(intArr));

		intArr = new int[] { 1, 2, 3 };
		assertFalse(ArrayUtils.isEmpty(intArr));

		long[] longArr = null;
		assertTrue(ArrayUtils.isEmpty(longArr));

		longArr = new long[] {};
		assertTrue(ArrayUtils.isEmpty(longArr));

		longArr = new long[3];
		assertFalse(ArrayUtils.isEmpty(longArr));

		longArr = new long[] { 1, 2, 3 };
		assertFalse(ArrayUtils.isEmpty(longArr));

		float[] floatArr = null;
		assertTrue(ArrayUtils.isEmpty(floatArr));

		floatArr = new float[] {};
		assertTrue(ArrayUtils.isEmpty(floatArr));

		floatArr = new float[3];
		assertFalse(ArrayUtils.isEmpty(floatArr));

		floatArr = new float[] { 1.0f, 2, 3 };
		assertFalse(ArrayUtils.isEmpty(floatArr));

		double[] doubleArr = null;
		assertTrue(ArrayUtils.isEmpty(doubleArr));

		doubleArr = new double[] {};
		assertTrue(ArrayUtils.isEmpty(doubleArr));

		doubleArr = new double[3];
		assertFalse(ArrayUtils.isEmpty(doubleArr));

		doubleArr = new double[] { 1.0, 2, 3 };
		assertFalse(ArrayUtils.isEmpty(doubleArr));

		String[] stringArr = null;
		assertTrue(ArrayUtils.isEmpty(stringArr));

		stringArr = new String[] {};
		assertTrue(ArrayUtils.isEmpty(stringArr));

		stringArr = new String[3];
		assertFalse(ArrayUtils.isEmpty(stringArr));

		stringArr = new String[] { "1", "2", "3" };
		assertFalse(ArrayUtils.isEmpty(stringArr));

		Integer[] integerArr = null;
		assertTrue(ArrayUtils.isEmpty(integerArr));

		integerArr = new Integer[] {};
		assertTrue(ArrayUtils.isEmpty(integerArr));

		integerArr = new Integer[3];
		assertFalse(ArrayUtils.isEmpty(integerArr));

		integerArr = new Integer[] { 1, 2, 3 };
		assertFalse(ArrayUtils.isEmpty(integerArr));

		stringArr = new String[] { "1", "2", "3" };
		assertFalse(ArrayUtils.isEmpty(stringArr));

		Object[] objectArr = null;
		assertTrue(ArrayUtils.isEmpty(objectArr));

		objectArr = new Object[] {};
		assertTrue(ArrayUtils.isEmpty(objectArr));

		objectArr = new Object[3];
		assertFalse(ArrayUtils.isEmpty(objectArr));

		objectArr = new Object[] { 1, 2, 3 };
		assertFalse(ArrayUtils.isEmpty(objectArr));
	}

	public void testIsNotEmpty() {
		byte[] byteArr = null;
		assertFalse(ArrayUtils.isNotEmpty(byteArr));

		byteArr = new byte[] {};
		assertFalse(ArrayUtils.isNotEmpty(byteArr));

		byteArr = new byte[3];
		assertTrue(ArrayUtils.isNotEmpty(byteArr));

		byteArr = new byte[] { 1, 2, 3 };
		assertTrue(ArrayUtils.isNotEmpty(byteArr));

		short[] shortArr = null;
		assertFalse(ArrayUtils.isNotEmpty(shortArr));

		shortArr = new short[] {};
		assertFalse(ArrayUtils.isNotEmpty(shortArr));

		shortArr = new short[3];
		assertTrue(ArrayUtils.isNotEmpty(shortArr));

		shortArr = new short[] { 1, 2, 3 };
		assertTrue(ArrayUtils.isNotEmpty(shortArr));

		int[] intArr = null;
		assertFalse(ArrayUtils.isNotEmpty(intArr));

		intArr = new int[] {};
		assertFalse(ArrayUtils.isNotEmpty(intArr));

		intArr = new int[3];
		assertTrue(ArrayUtils.isNotEmpty(intArr));

		intArr = new int[] { 1, 2, 3 };
		assertTrue(ArrayUtils.isNotEmpty(intArr));

		long[] longArr = null;
		assertFalse(ArrayUtils.isNotEmpty(longArr));

		longArr = new long[] {};
		assertFalse(ArrayUtils.isNotEmpty(longArr));

		longArr = new long[3];
		assertTrue(ArrayUtils.isNotEmpty(longArr));

		longArr = new long[] { 1, 2, 3 };
		assertTrue(ArrayUtils.isNotEmpty(longArr));

		float[] floatArr = null;
		assertFalse(ArrayUtils.isNotEmpty(floatArr));

		floatArr = new float[] {};
		assertFalse(ArrayUtils.isNotEmpty(floatArr));

		floatArr = new float[3];
		assertTrue(ArrayUtils.isNotEmpty(floatArr));

		floatArr = new float[] { 1.0f, 2, 3 };
		assertTrue(ArrayUtils.isNotEmpty(floatArr));

		double[] doubleArr = null;
		assertFalse(ArrayUtils.isNotEmpty(doubleArr));

		doubleArr = new double[] {};
		assertFalse(ArrayUtils.isNotEmpty(doubleArr));

		doubleArr = new double[3];
		assertTrue(ArrayUtils.isNotEmpty(doubleArr));

		doubleArr = new double[] { 1.0, 2, 3 };
		assertTrue(ArrayUtils.isNotEmpty(doubleArr));

		String[] stringArr = null;
		assertFalse(ArrayUtils.isNotEmpty(stringArr));

		stringArr = new String[] {};
		assertFalse(ArrayUtils.isNotEmpty(stringArr));

		stringArr = new String[3];
		assertTrue(ArrayUtils.isNotEmpty(stringArr));

		stringArr = new String[] { "1", "2", "3" };
		assertTrue(ArrayUtils.isNotEmpty(stringArr));

		Integer[] integerArr = null;
		assertFalse(ArrayUtils.isNotEmpty(integerArr));

		integerArr = new Integer[] {};
		assertFalse(ArrayUtils.isNotEmpty(integerArr));

		integerArr = new Integer[3];
		assertTrue(ArrayUtils.isNotEmpty(integerArr));

		integerArr = new Integer[] { 1, 2, 3 };
		assertTrue(ArrayUtils.isNotEmpty(integerArr));

		stringArr = new String[] { "1", "2", "3" };
		assertTrue(ArrayUtils.isNotEmpty(stringArr));

		Object[] objectArr = null;
		assertFalse(ArrayUtils.isNotEmpty(objectArr));

		objectArr = new Object[] {};
		assertFalse(ArrayUtils.isNotEmpty(objectArr));

		objectArr = new Object[3];
		assertTrue(ArrayUtils.isNotEmpty(objectArr));

		objectArr = new Object[] { 1, 2, 3 };
		assertTrue(ArrayUtils.isNotEmpty(objectArr));
	}

}
