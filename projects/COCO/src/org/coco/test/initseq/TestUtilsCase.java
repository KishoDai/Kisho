package org.coco.test.initseq;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class TestUtilsCase extends TestCase {

	public void test() {
        HashMap map1 = new HashMap();
        map1.put("1", "1213");
        HashMap map2 = (HashMap) map1.clone();
        System.out.println(map1);
        System.out.println(map2);
        System.out.println(map1 == map2);
		try {
			Constructor[] constructors = Class.forName(
					"org.coco.test.initseq.TestUtils")
					.getDeclaredConstructors();
			constructors[0].setAccessible(true);
			constructors[0].newInstance(null);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
