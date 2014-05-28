package org.coco.test.programmingpears.chapter.first;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Solution1 {

	public static void main(String[] args) {
//		System.out.println(1L << 1);
		long start = System.currentTimeMillis();
		BufferedReader br = ReadDataFromFile
				.getReaderStream("G:\\GitHub\\Kisho\\projects\\COCO\\src\\org\\coco\\test\\programmingpears\\chapter\\first\\source.txt");
		long[] lArr = new long[10000000 / 64 + 1];
		String s = null;
		try {
			while ((s = br.readLine()) != null) {
				int i = Integer.valueOf(s);
				int j = i % 64;
				long m = 1l << (j == 0 ? 63 : j - 1);
//				System.out.println("i " + i);
//				System.out.println((j == 0 ? i / 64 - 1 : i / 64) + " " + i);
//				System.out.println("" + lArr[j == 0 ? i / 64 - 1 : i / 64] + " " + (j == 0 ? 62 : j - 1) + " "+ m);
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
			File file = new File("G:\\GitHub\\Kisho\\projects\\COCO\\src\\org\\coco\\test\\programmingpears\\chapter\\first\\destination.txt");
			if (!(!file.exists() ? file.createNewFile() : true)) {
				throw new RuntimeException("文件不存在，创建文件不成功!");
			}
			pw = new PrintWriter(file);
			for(int k = 0; k < lArr.length; k++){
				for(int x = 0; x < 64; x++){
					if((lArr[k] & (1l << x)) != 0){
						pw.println(k * 64 + (x + 1));
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
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
