/**
 * @(#) UUIDUtils.java 2010-9-12 下午01:47:39
 * Copyright 2010 Michael. All rights reserved.
 */
package com.press.util;

import java.util.UUID;

/**
 * UUID工具类
 * 
 * @author Michael
 * 
 */
public class UUIDUtil {
	/**
	 * 获得一个UUID字符串
	 * 
	 * @author Michael
	 * @since 1.0 2010-9-12 下午01:47:39
	 * @return String
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	public static String getUUIDStr() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
}
