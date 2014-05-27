package org.coco.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Random;

import org.coco.util.MD5Utils;
import sun.awt.windows.WWindowPeer;

public class TestClass {

	private static final String A = "Hello World!";

	public static void main(String[] args) throws NoSuchAlgorithmException,
			UnsupportedEncodingException, NoSuchFieldException,
			SecurityException, IllegalArgumentException,
			IllegalAccessException, ClassNotFoundException {
		// System.out.println(TestClass.Const.RAND_NUMBER);
		// System.out.println(TestClass.Const.RAND_NUMBER);
		// System.out.println(TestClass.Const.RAND_NUMBER);
		// System.out.println(TestClass.Const.RAND_NUMBER);
		// System.out.println(doStuff());
		// int $name = 24;
		// int _name = 23;
		// int name12 = 12;
		// int 12name = 12;
		// System.out.println(doFoo().getName());

		// MessageDigest messageDigest = MessageDigest.getInstance("SHA");
		// messageDigest.update("DbVisualizer".getBytes());
		// System.out.println(new String(messageDigest.digest()));
		// System.out.println(new String(messageDigest.digest(), "ISO-8859-1"));

//		Field field = Class.forName("org.coco.test.Util").getDeclaredField(
//				"branchInfoMap");
//		field.setAccessible(true);
//		System.out.println(field.toGenericString());
//		System.out.println(field.get("branchInfoMap") instanceof Map);
		System.out.println(ww());
	}

	private static int doStuff() {
		int a = 1;
		try {
			return a;
		} catch (Exception e) {
		} finally {
			a = -1;
			System.out.println("finally部分");
			return a;
		}
		// System.out.println("doStuff方法结束");
		// return a;
	}

	private static Person doFoo() {
		Person person = new TestClass.Person();
		person.setName("张三");
		try {
			return person;
		} catch (Exception e) {
		} finally {
			person.setName("李四");
		}
		person.setName("王五");
		return person;
	}

	static class Person {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	class Base {
		public Base() throws IOException {
			throw new IOException();
		}
	}

	class Sub extends Base {

		public Sub() throws IOException {
			super();
			try {

			} catch (Exception e) {
				// TODO: handle exception
			}
			// TODO Auto-generated constructor stub
		}

	}

	static class Const {
		public static final int RAND_NUMBER = new Random().nextInt();
	}
	
	private static int ww(){
		return (short) 111110;
	}

}
