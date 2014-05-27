package com.csms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
	private static SimpleDateFormat sdf = new SimpleDateFormat();

	private DateTimeUtils() {

	}

	public static String getCurrentDateTime(String pattern) {
		sdf.applyPattern(pattern);
		return sdf.format(new Date());
	}

	public static String getDefaultDateTime() {
		sdf.applyPattern("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}

}
