package org.coco.test.enums;

public enum TestEnum {

	MAP("A","asdfsdf");

	private String key;
	private String value;

	private TestEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public static TestEnum getTestEnum(String name){
		return TestEnum.valueOf(name);
	}

	public static void main(String[] args) {
		System.out.println(TestEnum.MAP instanceof TestEnum);
		System.out.println(TestEnum.MAP.key);
		System.out.println(TestEnum.MAP.value);
		System.out.println(TestEnum.MAP.name() instanceof String);
		System.out.println(TestEnum.MAP.ordinal());
		System.out.println(TestEnum.MAP.toString());
		System.out.println(TestEnum.MAP.valueOf("MAP") instanceof TestEnum);
	}
}
