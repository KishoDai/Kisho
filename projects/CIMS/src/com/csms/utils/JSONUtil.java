/**
 * @(#)JSONUtil.java   2010-9-27 下午09:35:26
 * Copyright 2010 Michael. All rights reserved.
 * 
 */
package com.csms.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import java.text.SimpleDateFormat;

import net.sf.json.processors.JsonValueProcessor;

/**
 * <p>
 * 处理json的工具类，负责json数据转换成java对象和java对象转换成json
 * </p>
 * 
 * @author Michael
 * @date 2010-9-27 下午09:35:26
 * @version 1.0
 */
public class JSONUtil {

	/**
	 * <p>
	 * 从一个JSON 对象字符串格式中得到一个java对象
	 * </p>
	 * 
	 * @author Michael
	 * @since 1.0 2010-9-27 下午09:35:26
	 * @param jsonStr
	 * @param cls
	 * @return T pojo对象
	 */

	@SuppressWarnings("unchecked")
	public static <T> T getPojo4Json(String jsonStr, Class<T> cls) {
		return (T) JSONObject.toBean(JSONObject.fromObject(jsonStr), cls);
	}

	/**
	 * <p>
	 * 从json数组中解析出java字符串数组
	 * </p>
	 * 
	 * @author Michael
	 * @date 2010-9-27 下午09:48:43
	 * @version 1.0
	 * @param jsonStr
	 * @return String[]
	 */
	public static String[] getStringArray4Json(String jsonStr) {

		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		String[] stringArray = new String[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			stringArray[i] = jsonArray.getString(i);

		}
		return stringArray;
	}

	/**
	 * <p>
	 * 从json数组中得到相应Object数组
	 * </p>
	 * 
	 * @author Michael
	 * @date 2010-9-27 下午09:45:32
	 * @version 1.0
	 * @param jsonStr
	 * @return Object[]
	 */
	public static Object[] getObjectArray4Json(String jsonStr) {
		return JSONArray.fromObject(jsonStr).toArray();

	}

	/**
	 * <p>
	 * 从json对象集合表达式中得到一个List
	 * </p>
	 * 
	 * @author Michael
	 * @date 2010-9-27 下午09:46:35
	 * @version 1.0
	 * @param jsonStr
	 * @param cls
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public static List getList4Json(String jsonStr, Class cls) {
		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		JSONObject jsonObject;
		Object pojoValue;
		List list = new ArrayList();
		for (int i = 0; i < jsonArray.size(); i++) {
			jsonObject = jsonArray.getJSONObject(i);
			pojoValue = JSONObject.toBean(jsonObject, cls);
			list.add(pojoValue);
		}
		return list;

	}

	/**
	 * <p>
	 * 从json对象集合表达式中得到一个List
	 * </p>
	 * 
	 * @author Michael
	 * @date 2010-9-27 下午09:46:35
	 * @version 1.0
	 * @param jsonStr
	 * @param cls
	 * @return Set
	 */
	@SuppressWarnings("unchecked")
	public static Set getSet4Json(String jsonStr, Class cls) {
		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		JSONObject jsonObject;
		Object pojoValue;
		Set set = new HashSet();
		for (int i = 0; i < jsonArray.size(); i++) {
			jsonObject = jsonArray.getJSONObject(i);
			pojoValue = JSONObject.toBean(jsonObject, cls);
			set.add(pojoValue);
		}
		return set;
	}

	/**
	 * <p>
	 * 从json表达式中获取一个map，改map支持嵌套功能
	 * </p>
	 * 
	 * @author Michael
	 * @date 2010-9-27 下午09:42:12
	 * @version 1.0
	 * @param jsonStr
	 * @return Map
	 */
	@SuppressWarnings("unchecked")
	public static Map getMap4Json(String jsonStr) {
		JSONObject jo = JSONObject.fromObject(jsonStr);
		Iterator it = jo.keys();
		String key;
		Object value;
		Map valueMap = new HashMap();
		while (it.hasNext()) {
			key = (String) it.next();
			value = jo.get(key);
			valueMap.put(key, value);
		}

		return valueMap;
	}

	/**
	 * <p>
	 * 从json数组中解析出java Integer型对象数组
	 * </p>
	 * 
	 * @author Michael
	 * @date 2010-9-27 下午09:50:29
	 * @version 1.0
	 * @param jsonStr
	 * @return Integer[]
	 */
	public static Integer[] getIntegerArray4Json(String jsonStr) {

		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		Integer[] integerArray = new Integer[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			integerArray[i] = jsonArray.getInt(i);

		}
		return integerArray;
	}

	/**
	 * <p>
	 * 从json数组中解析出javaLong型对象数组
	 * </p>
	 * 
	 * @author Michael
	 * @date 2010-9-27 下午09:49:47
	 * @version 1.0
	 * @param jsonStr
	 * @return Long[]
	 */
	public static Long[] getLongArray4Json(String jsonStr) {

		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		Long[] longArray = new Long[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			longArray[i] = jsonArray.getLong(i);

		}
		return longArray;
	}

	/**
	 * <p>
	 * 从json数组中解析出java Double 型对象数组
	 * </p>
	 * 
	 * @author Michael
	 * @date 2010-9-27 下午09:52:17
	 * @version 1.0
	 * @param jsonStr
	 * @return Double[]
	 */
	public static Double[] getDoubleArray4Json(String jsonStr) {

		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		Double[] doubleArray = new Double[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			doubleArray[i] = jsonArray.getDouble(i);

		}
		return doubleArray;
	}

	/**
	 * <p>
	 * 从json数组中解析出java Date 型对象数组
	 * </p>
	 * 
	 * @author Michael
	 * @date 2010-9-27 下午09:51:10
	 * @version 1.0
	 * @param jsonStr
	 * @param pattern
	 * @return Date[]
	 * @throws ParseException
	 */
	public static Date[] getDateArray4Json(String jsonStr, String pattern)
			throws ParseException {

		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		Date[] dateArray = new Date[jsonArray.size()];
		String dateString;
		Date date;

		for (int i = 0; i < jsonArray.size(); i++) {
			dateString = jsonArray.getString(i);
			date = DateUtil.parseDate(dateString, pattern);
			dateArray[i] = date;

		}
		return dateArray;
	}

	/**
	 * <p>
	 * 
	 * 将java对象转换成json字符串
	 * </p>
	 * 
	 * @author Michael
	 * @date 2010-9-27 下午09:54:20
	 * @version 1.0
	 * @param obj
	 * @return String
	 */
	public static String getJsonStr4Pojo(Object obj) {
		return JSONObject.fromObject(obj).toString();
	}

	/**
	 * <p>
	 * 将java对象转换成json字符串,并设定日期格式
	 * </p>
	 * 
	 * @author Michael
	 * @date 2010-9-27 下午09:53:25
	 * @version 1.0
	 * @param obj
	 * @param dateFormat
	 * @return String
	 */
	public static String getJsonStr4Pojo(Object obj, String dateFormat) {
		return JSONObject.fromObject(obj, configJson(dateFormat)).toString();
	}

	/**
	 * <p>
	 * 将list转换成json字符串
	 * </p>
	 * 
	 * @author Michael
	 * @date 2010-9-27 下午09:53:25
	 * @version 1.0
	 * @param list
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public static String getJsonStr4List(List list) {
		return JSONArray.fromObject(list).toString();
	}

	/**
	 * *
	 * <p>
	 * 将set转换成json字符串
	 * </p>
	 * 
	 * @author Michael
	 * @date 2010-9-27 下午10:10:09
	 * @version 1.0
	 * @param set
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public static String getJsonStr4Set(Set set) {
		return JSONArray.fromObject(set).toString();
	}

	/**
	 * *
	 * <p>
	 * 将set转换成json字符串
	 * </p>
	 * 
	 * @author Michael
	 * @date 2010-9-27 下午10:10:09
	 * @version 1.0
	 * @param map
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public static String getJsonStr4Map(Map map) {
		return JSONArray.fromObject(map).toString();
	}

	/**
	 * <p>
	 * JSON 时间解析工具
	 * </p>
	 * 
	 * @author Michael
	 * @date 2010-9-27 下午09:56:52
	 * @version 1.0
	 * @param datePattern
	 * @return
	 */
	private static JsonConfig configJson(String datePattern) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "" });
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor(datePattern));
		return jsonConfig;
	}

}

class JsonDateValueProcessor implements JsonValueProcessor {

	private String format = "yyyy-MM-dd HH:mm:ss";

	public JsonDateValueProcessor() {

	}

	public JsonDateValueProcessor(String format) {
		this.format = format;
	}

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		String[] obj = {};
		if (value instanceof Date[]) {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			Date[] dates = (Date[]) value;
			obj = new String[dates.length];
			for (int i = 0; i < dates.length; i++) {
				obj[i] = sf.format(dates[i]);
			}
		}
		return obj;
	}

	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		if (value instanceof Date) {
			String str = new SimpleDateFormat(format).format((Date) value);
			return str;
		}
		return value == null ? null : value.toString();
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
