package org.coco.util;

public final class StringUtils {
	private StringUtils() {

	}
	
	public static boolean isEmpty(String str){
		return str == null || "".equals(str);
	}
	
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
	public static boolean isBlank(String str){
		return isEmpty(str) || "".equals(str.trim());
	}
	
	public static boolean isNotBlank(String str){
		return !isBlank(str);
	}

}
