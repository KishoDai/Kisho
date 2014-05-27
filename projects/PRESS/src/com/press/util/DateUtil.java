/**
 * @(#)DateUtil.java   2010-9-12 下午12:54:13
 * Copyright 2010 Michael. All rights reserved.
 * 
 */
package com.press.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类 ,用于将特定格式的时间字符串解析时Date对象,以及将Date对象解析成特定格式的时间字符串
 * 
 * @author Michael
 * 
 */
public class DateUtil {
	private static final long L = (long) 24 * 60 * 60 * 1000;
	/** sdf :时间格式化对象 */
	private static SimpleDateFormat sdf;
	static {
		sdf = new SimpleDateFormat();
	}

	/**
	 * 按照格式:<code>yyyyMMddHHmmss</code>返回当前时间的字符串形式
	 * 
	 * @author Michael
	 * @since 1.0 2010-9-18 下午12:03:34
	 * @param String
	 * @return String
	 */
	public static String getCurrDate() {
		sdf.applyPattern("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}

	/**
	 * 按照格式<code>String pattern</code>返回当前时间的字符串形式
	 * 
	 * @author Michael
	 * @since 1.0 2010-9-18 下午12:03:34
	 * @param String
	 *            pattern 时间模式
	 * @return String
	 */
	public static String getCurrDate(String pattern) {
		sdf.applyPattern(pattern);
		return sdf.format(new Date());
	}

	/**
	 * 将Date对象date解析成pattern模式的时间字符串
	 * 
	 * @author Michael
	 * @param Date
	 *            date 时间对象
	 * @param String
	 *            pattern 时间模式
	 * @since 1.1 2010-9-12 下午12:54:13
	 * @return String
	 * @throws
	 */
	public static String formateDate(Date date, String pattern) {
		sdf.applyPattern(pattern);
		return sdf.format(date);
	}

	/**
	 * 将dateStr时间字符串对象解析成pattern模式的时间字符串
	 * 
	 * @author Michael
	 * @param String
	 *            dateStr 时间字符串对象
	 * @param String
	 *            pattern 时间模式
	 * @since 1.1 2010-9-12 下午12:54:20
	 * @return String
	 * @throws
	 */
	public static String formateDate(String dateStr, String pattern)
			throws ParseException {
		Date date = parseDate(dateStr);
		sdf.applyPattern(pattern);
		return sdf.format(date);
	}

	/**
	 * 将一个时间字符串对象<code>dateStr</code>解析成<code>Date</code>对象,
	 * <p>
	 * 该时间字符串对象的格式必须为下列中的一个：
	 * <ol>
	 * <li>yyyyMMddHHmmss</li>
	 * <li>yyyyMMdd</li>
	 * <li>HHmmss</li>
	 * <li>yyyy/MM/dd HH:mm:ss</li>
	 * <li>yyyy-MM-dd HH:mm:ss</li>
	 * <li>yyyy/MM/dd</li>
	 * <li>yyyy-MM-dd</li>
	 * <li>HH:mm:ss</li>
	 * </p>
	 * 
	 * @author Michael
	 * @param String
	 *            dateStr 时间字符串对象
	 * @since 1.1 2010-9-12 下午12:13:15
	 * @return Date
	 * @throws ParseException
	 */

	public static Date parseDate(String dateStr) throws ParseException {
		/** result 为返回结果时间对象 */
		Date result = null;
		/** 存储解析dateStr字符串的时间模式 */
		String pattern = "";
		if (dateStr != null && !"".equals(dateStr)) {
			if (dateStr.length() == 19 && dateStr.indexOf("/") > 0) {
				pattern = "yyyy/MM/dd HH:mm:ss";
			} else if (dateStr.length() == 19 && dateStr.indexOf("-") > 0) {
				pattern = "yyyy-MM-dd HH:mm:ss";
			} else if (dateStr.length() == 14) {
				pattern = "yyyyMMddHHmmss";
			} else if (dateStr.length() == 10 && dateStr.indexOf("/") > 0) {
				pattern = "yyyy/MM/dd";
			} else if (dateStr.length() == 10 && dateStr.indexOf("-") > 0) {
				pattern = "yyyy-MM-dd";
			} else if (dateStr.length() == 8 && dateStr.indexOf(":") < 0) {
				pattern = "yyyyMMdd";
			} else if (dateStr.length() == 8 && dateStr.indexOf(":") > 0) {
				pattern = "HH:mm:ss";
			} else if (dateStr.length() == 6) {
				pattern = "HHmmss";
			}

			sdf.applyPattern(pattern);
			result = sdf.parse(dateStr);
		}

		return result;
	}

	/**
	 * 将一个时间字符串对象<code>dateStr</code>解析成<code>Date</code>对象,
	 * 
	 * @author Michael
	 * @param String
	 *            dateStr 时间字符串对象
	 * @since 1.1 2010-9-12 下午12:13:15
	 * @return Date
	 * @throws ParseException
	 */

	public static Date parseDate(String dateStr, String pattern)
			throws ParseException {
		/** result 为返回结果时间对象 */
		Date result = null;
		sdf.applyPattern(pattern);
		result = sdf.parse(dateStr);
		return result;
	}

	/**
	 * 计算两个日期对象之间的天数
	 * 
	 * @author Michael
	 * @since 1.1 2010-9-12 下午01:37:28
	 * @return double
	 * @param Date
	 *            date1
	 * @param Date
	 *            date2
	 * @throws
	 */
	public static double betweenDays(Date date1, Date date2) {
		long l1 = date1.getTime();
		long l2 = date2.getTime();
		if (date1.before(date2)) {
			l1 = date2.getTime();
			l2 = date1.getTime();
		}
		BigDecimal bd1 = new BigDecimal(l1 - l2);
		BigDecimal bd2 = new BigDecimal(L);
		return bd1.divide(bd2, MathContext.DECIMAL32).doubleValue();
	}

	/**
	 * 计算两个时间字符串对象之间的天数
	 * 
	 * @author Michael
	 * @since 1.1 2010-9-12 下午01:38:56
	 * @param String
	 *            dateStr1
	 * @param String
	 *            dateStr2
	 * @return double
	 * @throws
	 */
	public static double betweenDays(String dateStr1, String dateStr2)
			throws ParseException {
		return betweenDays(parseDate(dateStr1), parseDate(dateStr2));
	}

	/**
	 * 计算时间字符串对象和时间对象之间的天数
	 * 
	 * @author Michael
	 * @since 1.1 2010-9-12 下午01:39:43
	 * @param String
	 *            dateStr1
	 * @param Date
	 *            date2
	 * @return double
	 * @throws ParseException
	 */
	public static double betweenDays(String dateStr1, Date date2)
			throws ParseException {
		return betweenDays(parseDate(dateStr1), date2);
	}

	/**
	 * 计算时间对象和时间字符串对象之间的天数
	 * 
	 * @author Michael
	 * @since 1.1 2010-9-12 下午01:45:20
	 * @param Date
	 *            date1
	 * @param String
	 *            dateStr2
	 * @return double
	 * @throws
	 */
	public static double betweenDays(Date date1, String dateStr2)
			throws ParseException {
		return betweenDays(date1, parseDate(dateStr2));
	}

}
