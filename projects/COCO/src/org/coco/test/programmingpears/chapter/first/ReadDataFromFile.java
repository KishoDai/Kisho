package org.coco.test.programmingpears.chapter.first;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public final class ReadDataFromFile {

	private ReadDataFromFile() {
	}

	public static BufferedReader getReaderStream(String fileName) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("文件不存在!");
		}
		return br;
	}

}
