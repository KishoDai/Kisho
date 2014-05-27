package org.coco.util;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class JsonUtilsTestCase extends TestCase {
	public void testSerialize() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("A000", "中华人民共和国 ");
		String serializeString = JsonUtils.serialize(map);
		System.out.println("serizlizeString=" + serializeString);
		assertTrue(serializeString.indexOf("A000") > 0);
	}

	public void testDeserialize() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("A000", "中华人民共和国 ");
		String serializeString = JsonUtils.serialize(map);
		Map<String, Object> map2 = (Map<String, Object>) JsonUtils
				.deserialize(serializeString);
		System.out.println("map2=" + map2);
		assertTrue(map2 instanceof Map<?, ?>);
	}

	public void testDeserialize2() {
		Test test = new Test();
		test.setName("代纪章");
		test.setSex("男");
		String testString = JsonUtils.serialize(test);
		System.out.println("testString=" + testString);
		assertTrue(testString.indexOf("代纪章") > 0);
        
		System.out.println(JsonUtils.deserialize(testString) instanceof Map);
		
		Test test2 =  (Test) JsonUtils.deserialize(testString, Test.class);
		System.out.println(test2.getName());
		System.out.println(test2.getSex());
		assertTrue(test2 instanceof Test);
	}

}

class Test {
	private String name;
	private String sex;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
