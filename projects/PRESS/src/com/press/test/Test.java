package com.press.test;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.press.product.action.ProductAction;
import com.press.product.pojo.Product;
import com.press.product.pojo.Product2;
import com.press.util.DateUtil;
import com.press.util.JSONUtil;

/**
 * 功能:编写一个截取字符串的程序，输入为一个字符串和需要字节数，输出为按字节截取的字符串。
 * 但是要保证汉字不被截半个，如输入“我ABC”4，应该截为“我AB”，输入“我ABC汉DEF”，6，
 * 应该输出为“我ABC”而不是“我ABC+‘汉’的半个字”。 注意:本功能只适用于一个汉字为两个字节的编码
 * 
 * @author HERO
 * 
 */
public class Test {

	private static Set<Integer> set = new HashSet<Integer>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Pattern pattern = Pattern.compile("[\u4E00-\u9FA5]");
//		Matcher matcher = null;
//        
//		Scanner scanner = new Scanner(System.in);
//
//		System.out.println("~>请输入字符串如下格式为:" + "abc中国,4");
//		while (true) {
//			set.clear();
//			System.out.print("~>");
//			String s = scanner.nextLine();
//			if("".equals(s)){
//				continue;
//			}
//			int i = s.lastIndexOf(",");
//			if (i == -1 || i == s.length() - 1) {
//				System.out.println("~>输入格式不合法");
//				continue;
//			}
//			try {
//				Integer num = Integer.valueOf(s.substring(i + 1));
//			    matcher = pattern.matcher(s);
//			    int count = 0;
//			    while(matcher.find()){
//			    	set.add(Integer.valueOf(matcher.end()+count));
//			    	count ++;
//			    }
//			    System.out.println(set);
//				byte[] byteArr = s.getBytes();
//				
//				if (num > byteArr.length) {
//					num = byteArr.length;
//				}
//				//字符串有汉字
//				if(count != 0) {
//                   if(!set.contains(Integer.valueOf(num-1))){
//                	   if(set.contains(Integer.valueOf(num))){
//                		  num = num - 1; 
//                	   }
//                   }					
//				}
//				byte[] resultByteArr = new byte[num];
//                System.arraycopy(byteArr, 0, resultByteArr,0, num);
//                System.out.println("截取的字符串为:"+new String(resultByteArr));
//			} catch (Exception e) {
//				System.out.println("~>输入格式不合法");
//				continue;
//			}
//		}
//		try {
//			System.out.println(DateUtil.formateDate("20110818000000", "yyyy-MM-dd"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		List<Product> list = new ArrayList<Product>();
//		Product product = new Product();
//		product.setCatid("123");
//		list.add(product);
//		list.add(product);
//		StringBuffer jsonSb = new StringBuffer();
//		jsonSb.append("{\"total\":");
//		jsonSb.append("10");
//		jsonSb.append(",\"rows\":");
//		jsonSb.append(JSONUtil.getJsonStr4List(list));
//		System.out.println(jsonSb.toString());
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
		context.setConfigLocation("Total.xml");
		context.refresh();
		ProductAction pa = (ProductAction) context.getBean("productAction");
		Product product = new Product();
		product.setPronam("1");
		product.setOrder("desc");
		product.setSort("proid");
		product.setStartnum(0);
		product.setLimitcount(10);
		
//		List<Product> pList = pa.getSqlMap().selectList("product.queryPageProductList", product);
//		System.out.println(pList);
	}

}
