package org.coco.util;

import junit.framework.TestCase;

public class StringUtilsTestCase extends TestCase {

	public void testIsEmpty() {
		assertTrue(StringUtils.isEmpty(null));

		assertTrue(StringUtils.isEmpty(""));

		assertFalse(StringUtils.isEmpty(" "));

		assertFalse(StringUtils.isEmpty(" ABC"));

		assertFalse(StringUtils.isEmpty("ABC "));

		assertFalse(StringUtils.isEmpty(" ABC "));

		assertFalse(StringUtils.isEmpty("ABC"));
	}

	public void testIsNotEmpty() {
		assertFalse(StringUtils.isNotEmpty(null));

		assertFalse(StringUtils.isNotEmpty(""));

		assertTrue(StringUtils.isNotEmpty(" "));

		assertTrue(StringUtils.isNotEmpty(" ABC"));

		assertTrue(StringUtils.isNotEmpty("ABC "));

		assertTrue(StringUtils.isNotEmpty(" ABC "));

		assertTrue(StringUtils.isNotEmpty("ABC"));
	}

	public void testIsBlank() {
		assertTrue(StringUtils.isBlank(null));

		assertTrue(StringUtils.isBlank(""));

		assertTrue(StringUtils.isBlank(" "));

		assertFalse(StringUtils.isBlank(" ABC"));

		assertFalse(StringUtils.isBlank("ABC "));

		assertFalse(StringUtils.isBlank(" ABC "));

		assertFalse(StringUtils.isBlank("ABC"));
	}

	public void testIsNotBlank() {
		assertFalse(StringUtils.isNotBlank(null));

		assertFalse(StringUtils.isNotBlank(""));

		assertFalse(StringUtils.isNotBlank(" "));

		assertTrue(StringUtils.isNotBlank(" ABC"));

		assertTrue(StringUtils.isNotBlank("ABC "));

		assertTrue(StringUtils.isNotBlank(" ABC "));

		assertTrue(StringUtils.isNotBlank("ABC"));
	}

}
