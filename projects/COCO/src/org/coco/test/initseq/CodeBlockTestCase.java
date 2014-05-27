package org.coco.test.initseq;

import junit.framework.TestCase;

public class CodeBlockTestCase extends TestCase {

	public static void main(String[] args) {
		CodeBlockTestCase codeBlockTestCase = new CodeBlockTestCase();
		Father father = codeBlockTestCase.new Son();
//		System.out.println(CodeBlockTestCase.Sta.print());
	}

	static class Sta {
		{
			System.out.println("sta");
		}
		
		public static void print(){
			
		}
	}

	class Father {
		{
			System.out.println("父类构造代码块!");
		}
	}

	class Son extends Father {
		{

			System.out.println("子类构造代码块！");
		}
	}
}
