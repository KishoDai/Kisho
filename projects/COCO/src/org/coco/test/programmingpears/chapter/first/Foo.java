package org.coco.test.programmingpears.chapter.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Foo {

	public static void main(String[] args) {
		BufferedReader br = ReadUtils
				.getBufferedReader("G:\\GitHub\\Kisho\\projects\\COCO\\src\\org\\coco\\test\\programmingpears\\chapter\\first\\source.txt");

		PrintWriter pw = WriteUtils
				.getPrintWriter("G:\\GitHub\\Kisho\\projects\\COCO\\src\\org\\coco\\test\\programmingpears\\chapter\\first\\destination.txt");

		String s = null;
		try {
			while ((s = br.readLine()) != null) {
				pw.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.close();
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Finished.");
	}

}
