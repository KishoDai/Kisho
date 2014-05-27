package org.coco.test.initseq;

import junit.framework.TestCase;

public class TestInitiationSequence extends TestCase {
	public static void main(String[] args) {
//		Father father = new Son();
//		father.printAge();
		Father father = new Father("a");

	}
}

class Father {

	private static final int fatherAge = initFatherAge();

	private String colleage = getFatherColleage();

	static {
		System.out.println("父亲静态初始化器!");
	}



	protected Father() {
		System.out.println("父亲collegae :" + colleage);
		System.out.println("父亲无参构造器");
	}
	protected Father(String name) {
	}
	
	{
		System.out.println("此处为父类构造代码块==========");
	}

	private String getFatherColleage() {
		System.out.println("初始化父亲的大学");
		return "无";
	}

	private static int initFatherAge() {
		System.out.println("初始化父亲的年龄!");
		return 50;
	}

	protected void printAge() {
		System.out.println("父亲的年龄为:" + fatherAge);

	}
}

class Son extends Father {
	private static final int sonAge = initSonAge();

	static {
		System.out.println("儿子静态初始化器!");
	}

	private String colleage = getSonColleage();

	protected Son() {
		System.out.println("儿子colleage :" + colleage);
		System.out.println("儿子构造器");
	}

	private String getSonColleage() {
		System.out.println("初始化儿子的大学");
		return "安徽理工大学";
	}

	private static int initSonAge() {
		System.out.println("初始化儿子的年龄!");
		return 25;
	}

	protected void printAge() {
		System.out.println("儿子的年龄为:" + sonAge);

	}
}
