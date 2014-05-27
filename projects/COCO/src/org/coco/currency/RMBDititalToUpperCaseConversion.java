package org.coco.currency;

public class RMBDititalToUpperCaseConversion {

	private static final String[] units = { "仟亿", "佰亿", "十亿", "亿", "仟万", "佰万",
			"十万", "万", "千", "佰", "十" };

	private static final String[] digitals = { "零", "壹", "贰", "叁", "肆", "伍",
			"陆", "柒", "捌", "玖", "拾" };

	public static String toUpperCase(double rmb) {
		String[] rmbParts = String.valueOf(rmb).split("[.]");
		StringBuilder sb = new StringBuilder();
		if (rmbParts.length == 1) {

		}
		return "";
	}

	public static void main(String[] args) {

		System.out.println(String.valueOf(11));
		System.out.println(String.valueOf(11.00));
		String[] sA = String.valueOf(0.11).split("[.]");
		System.out.println(sA[0]);
		System.out.println(sA[1]); 
	}

}
