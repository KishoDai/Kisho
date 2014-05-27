package org.coco.test.serializable;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = -8459861651975879117L;

	public static final String AGE = "50";

	private final String name;

	public Person() {
		name = "大德天子";
		System.out.println("产生一个实例,名字为:" + name);

	}

	@Override
	public String toString() {
		return name;
	}

}
