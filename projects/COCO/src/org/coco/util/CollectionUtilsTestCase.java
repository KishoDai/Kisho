package org.coco.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


import junit.framework.TestCase;

public class CollectionUtilsTestCase extends TestCase {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testIsEmpty() {
		Collection collection = null;
		assertTrue(CollectionUtils.isEmpty(collection));
		
		collection = new ArrayList();
		assertTrue(CollectionUtils.isEmpty(collection));
		
		collection.add(new Object());
		assertFalse(CollectionUtils.isEmpty(collection));
		
		collection = new HashSet();
		assertTrue(CollectionUtils.isEmpty(collection));
		
		collection.add(new Object());
		assertFalse(CollectionUtils.isEmpty(collection));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testIsNotEmpty() {
		Collection collection = null;
		assertFalse(CollectionUtils.isNotEmpty(collection));
		
		collection = new ArrayList();
		assertFalse(CollectionUtils.isNotEmpty(collection));
		
		collection.add(new Object());
		assertTrue(CollectionUtils.isNotEmpty(collection));
		
		collection = new HashSet();
		assertFalse(CollectionUtils.isNotEmpty(collection));
		
		collection.add(new Object());
		assertTrue(CollectionUtils.isNotEmpty(collection));
	}

}
