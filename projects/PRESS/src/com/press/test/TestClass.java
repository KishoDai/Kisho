package com.press.test;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestClass {
	
	
	public static void main(String[] args){
		int x = 5;
		TestClass p = new TestClass();
		p.doStuff(x);
		System.out.print(" main x = " + x);
		}
		void doStuff(int x){
		System.out.println(" doStuff x = " + x++);
		 }
}
