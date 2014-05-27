package com.press.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;
import org.objectweb.asm.Type;

/**
 * @author HERO
 * 
 */
public class Test1 {
	static String ss; // 要进行截取操作的字符串
	static int n; // 截取的字符串的字节数

	public static void main(String[] args) {
		System.out.println("中=" + "中".getBytes().length);
		System.out.println(substring("123中国好人民", 4));
		System.out.println(substring("123中国好人民", 5));
		System.out.println(substring("123中国好人民", 6));
		System.out.println(substring("123中国好人民", 7));
		System.out.println(substring("123中国好人民", 8));
		System.out.println(substring("123中国好人民", 9));
		System.out.println("=========================");
		System.out.println(substring("中", 0));
		System.out.println(substring("中", 1));
		System.out.println(substring("中", 2));
		System.out.println(substring("中", 3));
		System.out.println(substring("中", 1000));
//		Driver
//		org.springframework.asm.Type
	}

	public static String substring(String str, int toCount) {
		if (StringUtils.isEmpty(str) || toCount <= 0) {
			return "";
		}
		int l = str.getBytes().length;
		if (l <= toCount || l == 1) {
			return str;
		}

		int totalCount = 0;
		int currCount = 0;
		char[] tempChars = str.toCharArray();
		List<Character> charList = new ArrayList<Character>();
		for (int i = 0; i < tempChars.length; i++) {
			currCount = String.valueOf(tempChars[i]).getBytes().length;
			totalCount += currCount;
			if (totalCount == toCount) {
				charList.add(tempChars[i]);
				break;
			} else if (totalCount > toCount) {
				if (i == 0) {
					return "";
				} else {
					break;
				}
			}
			charList.add(tempChars[i]);
		}
		char[] rsChars = new char[charList.size()];
		for (int i = 0; i < charList.size(); i++) {
			rsChars[i] = charList.get(i);
		}
		return String.valueOf(rsChars);
	}
}
