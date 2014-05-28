package org.coco.test.programmingpears.chapter.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Solution1 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		BufferedReader br = ReadUtils
				.getBufferedReader("G:\\GitHub\\Kisho\\projects\\COCO\\src\\org\\coco\\test\\programmingpears\\chapter\\first\\source.txt");
		long[] lArr = new long[10000000 / 64 + 1];
		String s = null;
		try {
			while ((s = br.readLine()) != null) {
				int i = Integer.valueOf(s);
				int j = i % 64;
				long m = 1l << (j == 0 ? 63 : j - 1);
				if ((lArr[j == 0 ? i / 64 - 1 : i / 64] & m) != 0) {
					throw new RuntimeException("存在重复的项!");
				}
				setData(lArr, i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("use :" + (System.currentTimeMillis() - start)/1000);
		PrintWriter pw = null;
		try {
			pw = WriteUtils.getPrintWriter("G:\\GitHub\\Kisho\\projects\\COCO\\src\\org\\coco\\test\\programmingpears\\chapter\\first\\destination.txt");
			for(int k = 0; k < lArr.length; k++){
				for(int x = 0; x < 64; x++){
					if((lArr[k] & (1l << x)) != 0){
						pw.println(k * 64 + (x + 1));
					}
				}
			}
		} finally {
			pw.close();
		}
		System.out.println("use 2 : " + (System.currentTimeMillis() - start)/1000);
		
	}

	private static void setData(long[] lArr, int i) {
		int j = i % 64;
		lArr[j == 0 ? i / 64 - 1 : i / 64] += (1l << (j == 0 ? 63 : j - 1));
	}

}
