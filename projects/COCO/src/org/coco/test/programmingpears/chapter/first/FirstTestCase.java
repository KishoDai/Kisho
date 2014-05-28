package org.coco.test.programmingpears.chapter.first;

import java.io.BufferedReader;
import java.io.IOException;

import junit.framework.TestCase;

public class FirstTestCase extends TestCase {
	public void test() {
		String sourceFileName = "G:\\GitHub\\Kisho\\projects\\COCO\\src\\org\\coco\\test\\programmingpears\\chapter\\first\\source.txt";
		String destinationFileName = "G:\\GitHub\\Kisho\\projects\\COCO\\src\\org\\coco\\test\\programmingpears\\chapter\\first\\destination.txt";
		
		int count = 8000000;
		int range = 10000000;
		
		long startTime = System.currentTimeMillis();
		byte[] bytes = GenerateNotRepeatableRandomNumbers.generateRandomNumbers(count, range);
		System.out.println("generate not repeatable numbers with array!");
		System.out.println("use time : " + (System.currentTimeMillis() - startTime) + "ms");
		
		startTime = System.currentTimeMillis();
		bytes = GenerateNotRepeatableRandomNumbers.generateRandomNumbers2(count, range);
		System.out.println("generate not repeatable numbers with set!");
		System.out.println("use time : " + (System.currentTimeMillis() - startTime) + "ms");
		
		startTime = System.currentTimeMillis();
		WriteDataToFile.writeByteToFile(bytes, sourceFileName, true);
		System.out.println("write not repeatable numbers to file!");
		System.out.println("use time : " + (System.currentTimeMillis() - startTime) + "ms");
		
		startTime = System.currentTimeMillis();
		byte[] bytes2 = new byte[range];
		BufferedReader br = ReadUtils.getBufferedReader(sourceFileName);
		String s = null;
		try {
			while((s = br.readLine()) != null){
				bytes2[Integer.valueOf(s)] += 1;
				if(bytes2[Integer.valueOf(s)] > 1){
					throw new RuntimeException("存在重复的项!");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(true);
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
				assertTrue(true);
			}
		}
		System.out.println("read not repeatable numbers from file!");
		System.out.println("use time : " + (System.currentTimeMillis() - startTime) + "ms");
		
		startTime = System.currentTimeMillis();
		WriteDataToFile.writeByteToFile(bytes2, destinationFileName, false);
		System.out.println("write not repeatable numbers to file with order!");
		System.out.println("use time : " + (System.currentTimeMillis() - startTime) + "ms");
		
		assertTrue(true);
	}
	
}
